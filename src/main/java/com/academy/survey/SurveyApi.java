package com.academy.survey;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.survey.service.SurveyService;
import com.academy.survey.service.SurveyVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * SurveyApi.java
 * 설문조사 REST API Controller
 */
@Tag(name = "Survey", description = "설문조사 API")
@RestController
@RequestMapping("/api/survey")
public class SurveyApi extends CORSFilter {

    private final SurveyService surveyService;

    @Autowired
    public SurveyApi(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    /**
     * 설문조사 목록 조회
     */
    @Operation(summary = "설문조사 목록 조회", description = "활성화된 설문조사 목록을 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("SurveyVO") SurveyVO surveyVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        List<HashMap<String, Object>> surveyList = surveyService.surveyList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("surveyList", surveyList);
        jsonObject.put("totalCount", surveyList.size());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 설문조사 상세 조회
     */
    @Operation(summary = "설문조사 상세 조회", description = "설문조사 상세 정보와 질문 목록을 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("SurveyVO") SurveyVO surveyVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("SURVEYID", CommonUtil.isNull(surveyVO.getSurveyId(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        HashMap<String, Object> surveyInfo = surveyService.surveyView(params);
        List<HashMap<String, Object>> questionList = surveyService.getSurveyById(params);

        if (surveyInfo != null) {
            jsonObject.put("surveyInfo", surveyInfo);
            jsonObject.put("questionList", questionList);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "설문조사 정보를 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 설문조사 결과 통계 조회
     */
    @Operation(summary = "설문조사 결과 통계 조회", description = "설문조사 결과 통계를 조회합니다.")
    @GetMapping("/getResult")
    public JSONObject getResult(
            @ModelAttribute("SurveyVO") SurveyVO surveyVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("SURVEYID", CommonUtil.isNull(surveyVO.getSurveyId(), ""));

        HashMap<String, Object> surveyInfo = surveyService.surveyView(params);
        List<HashMap<String, Object>> resultList = surveyService.surveyRstList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("surveyInfo", surveyInfo);
        jsonObject.put("resultList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 설문조사 참여 여부 확인
     */
    @Operation(summary = "설문조사 참여 여부 확인", description = "사용자의 설문조사 참여 여부를 확인합니다.")
    @GetMapping("/checkParticipation")
    public JSONObject checkParticipation(
            @ModelAttribute("SurveyVO") SurveyVO surveyVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("SURVEYID", CommonUtil.isNull(surveyVO.getSurveyId(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = surveyVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        int count = surveyService.checkSurveyCnt(params);

        if (count > 0) {
            jsonObject.put("isParticipated", "Y");
            jsonObject.put("message", "이미 참여한 설문조사입니다.");
        } else {
            jsonObject.put("isParticipated", "N");
            jsonObject.put("message", "참여 가능합니다.");
        }
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 설문조사 참여 (결과 제출)
     */
    @Operation(summary = "설문조사 참여", description = "설문조사에 참여하여 응답을 제출합니다.")
    @PostMapping("/submit")
    public JSONObject submit(
            @RequestBody HashMap<String, Object> requestBody) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        String surveyId = CommonUtil.isNull((String) requestBody.get("surveyId"), "");
        params.put("SURVEYID", surveyId);
        params.put("POSITION", CommonUtil.isNull((String) requestBody.get("position"), "GOSI"));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = (String) requestBody.get("userId");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        // 중복 참여 체크
        int count = surveyService.checkSurveyCnt(params);
        if (count > 0) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "이미 참여한 설문조사입니다.");
            return new JSONObject(jsonObject);
        }

        try {
            // 응답 목록 추출
            @SuppressWarnings("unchecked")
            List<HashMap<String, Object>> answers = (List<HashMap<String, Object>>) requestBody.get("answers");

            if (answers == null || answers.isEmpty()) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "응답 데이터가 없습니다.");
                return new JSONObject(jsonObject);
            }

            // 응답 데이터 변환
            List<HashMap<String, Object>> answerList = new ArrayList<>();
            for (HashMap<String, Object> answer : answers) {
                HashMap<String, Object> item = new HashMap<>();
                item.put("QUEID", answer.get("queId"));
                item.put("QSEQ", answer.get("qseq"));
                item.put("USER_ANSW", answer.get("userAnsw"));
                answerList.add(item);
            }

            surveyService.submitSurvey(params, answerList);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "설문조사에 참여해 주셔서 감사합니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "설문조사 제출에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }
}
