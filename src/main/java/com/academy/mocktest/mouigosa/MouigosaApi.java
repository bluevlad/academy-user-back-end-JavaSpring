package com.academy.mocktest.mouigosa;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.mouigosa.service.MouigosaService;
import com.academy.mocktest.mouigosa.service.MouigosaVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Tag(name = "Mouigosa", description = "모의고사 API")
@RestController
@RequestMapping("/api/mocktest/mouigosa")
public class MouigosaApi extends CORSFilter {

    private final MouigosaService mouigosaService;

    @Autowired
    public MouigosaApi(MouigosaService mouigosaService) {
        this.mouigosaService = mouigosaService;
    }

    /**
     * 모의고사 등록 리스트 조회
     */
    @Operation(summary = "모의고사 등록 리스트 조회", description = "모의고사 등록 리스트를 조회합니다.")
    @GetMapping("/getRegistrationList")
    public JSONObject getRegistrationList(@ModelAttribute("MouigosaVO") MouigosaVO mouigosaVO,
                                          HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            // 페이징 설정
            int currentPage = mouigosaVO.getCurrentPage();
            int pageRow = mouigosaVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            HashMap<String, Object> params = new HashMap<>();
            params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
            params.put("recordCountPerPage", String.valueOf(pageRow));
            params.put("MOCKCODE", mouigosaVO.getMockCode());
            params.put("EXAMYEAR", mouigosaVO.getExamYear());
            params.put("EXAMROUND", mouigosaVO.getExamRound());
            params.put("USEFLAG", "Y");
            params.put("userId", mouigosaVO.getUserId());

            List<HashMap<String, Object>> list = mouigosaService.getMouigosaRegistrationList(params);
            int totalCount = mouigosaService.getRegistrationCount(params);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 정보 조회
     */
    @Operation(summary = "모의고사 정보 조회", description = "모의고사 상세 정보를 조회합니다.")
    @GetMapping("/getDetail")
    public JSONObject getDetail(@ModelAttribute("MouigosaVO") MouigosaVO mouigosaVO,
                                HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("MOCKCODE", mouigosaVO.getMockCode());
            params.put("IDENTYID", mouigosaVO.getIdentyId());
            params.put("ITEMID", mouigosaVO.getItemId());
            params.put("userId", mouigosaVO.getUserId());

            List<HashMap<String, Object>> detailList = mouigosaService.getUpdateRegistrationDetail(params);

            if (detailList != null && !detailList.isEmpty()) {
                jsonObject.put("data", detailList.get(0));
                jsonObject.put("retMsg", "OK");
            } else {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "데이터를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 시험 시작
     */
    @Operation(summary = "모의고사 시험 시작", description = "모의고사 시험을 시작합니다.")
    @PostMapping("/startExam")
    public JSONObject startExam(@ModelAttribute("MouigosaVO") MouigosaVO mouigosaVO,
                                HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("IDENTYID", mouigosaVO.getIdentyId());
            params.put("MOCKCODE", mouigosaVO.getMockCode());
            params.put("ITEMID", mouigosaVO.getItemId());
            params.put("USER_ID", mouigosaVO.getUserId());

            // 모의고사 정보 조회
            List<HashMap<String, Object>> list = mouigosaService.getUpdateRegistrationDetail(params);
            HashMap<String, Object> examInfo = list.get(0);

            String tcounter = mouigosaVO.getTcounter();
            if (tcounter == null || tcounter.equals("")) {
                // 답안 초기화
                HashMap<String, Object> params2 = new HashMap<>();
                params2.put("QUESTIONNUMBER", "1");
                params2.put("IDENTYID", mouigosaVO.getIdentyId());
                params2.put("MOCKCODE", mouigosaVO.getMockCode());
                params2.put("ITEMID", mouigosaVO.getItemId());
                params2.put("USER_ID", mouigosaVO.getUserId());

                int getCnt = mouigosaService.getCnt(params2);
                if (getCnt == 0) {
                    mouigosaService.insertAnswerMouigosa(params2);
                }
            }

            // 응시상태 업데이트
            params.put("EXAMSTATUS", "2");
            mouigosaService.mouigosaStatusUpdate12(params);

            // 과목 리스트 조회
            List<HashMap<String, Object>> subjectList = mouigosaService.getSubjectList(params);

            // 문제 리스트 조회
            List<HashMap<String, Object>> questionList = mouigosaService.getQuestionList(params);

            jsonObject.put("examInfo", examInfo);
            jsonObject.put("subjectList", subjectList);
            jsonObject.put("questionList", questionList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 답안 저장
     */
    @Operation(summary = "모의고사 답안 저장", description = "모의고사 답안을 저장합니다.")
    @PostMapping("/saveAnswers")
    public JSONObject saveAnswers(@RequestBody HashMap<String, Object> requestBody,
                                  HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            String identyId = (String) requestBody.get("identyId");
            String mockCode = (String) requestBody.get("mockCode");
            String itemId = (String) requestBody.get("itemId");
            String userId = (String) requestBody.get("userId");
            String questionNum = (String) requestBody.get("questionNum");
            String sts = (String) requestBody.get("sts");
            List<HashMap<String, Object>> answers = (List<HashMap<String, Object>>) requestBody.get("answers");

            HashMap<String, Object> params = new HashMap<>();
            params.put("IDENTYID", identyId);
            params.put("MOCKCODE", mockCode);
            params.put("ITEMID", itemId);
            params.put("USER_ID", userId);
            params.put("QUESTIONNUM", questionNum);
            params.put("sts", sts);

            // 정답 조회
            HashMap<String, Object> correctAnswers = mouigosaService.selectQuestionAnswers(params);

            // 답안 처리
            ArrayList<HashMap<String, Object>> answerList = new ArrayList<>();
            for (HashMap<String, Object> answer : answers) {
                int questionNumber = (Integer) answer.get("questionNumber");
                String answerNumber = (String) answer.get("answerNumber");
                String correctAnswer = String.valueOf(correctAnswers.get(String.valueOf(questionNumber)));

                HashMap<String, Object> ans = new HashMap<>();
                ans.put("QUESTIONNUMBER", questionNumber);
                ans.put("ANSWERNUMBER", answerNumber);

                if (answerNumber != null && !answerNumber.equals("")) {
                    if (correctAnswer.contains(answerNumber)) {
                        ans.put("CORRECTYN", "Y");
                    } else {
                        ans.put("CORRECTYN", "N");
                    }
                } else {
                    ans.put("CORRECTYN", "N");
                }

                answerList.add(ans);
            }

            if (!answerList.isEmpty()) {
                params.put("ANSWS", answerList);
                mouigosaService.updateAnswerMouigosa(params);
            }

            // 응시 상태 확인
            int getCnt2 = mouigosaService.getCnt2(params);
            int getCnt3 = mouigosaService.getCnt3(params);

            String examStatus;
            if (getCnt2 == 0) {
                if (getCnt2 == getCnt3) {
                    examStatus = "1";
                } else {
                    examStatus = "2";
                }
            } else if (getCnt2 == Integer.parseInt(questionNum)) {
                examStatus = "3";
            } else {
                examStatus = "2";
            }

            if ("3".equals(sts)) {
                examStatus = "3";
                params.put("EXAMENDTIME", "Y");
            }

            params.put("EXAMSTATUS", examStatus);
            mouigosaService.mouigosaStatusUpdate3(params);

            jsonObject.put("examStatus", examStatus);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 시험 종료
     */
    @Operation(summary = "모의고사 시험 종료", description = "모의고사 시험을 종료합니다.")
    @PostMapping("/endExam")
    public JSONObject endExam(@ModelAttribute("MouigosaVO") MouigosaVO mouigosaVO,
                             HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("IDENTYID", mouigosaVO.getIdentyId());
            params.put("MOCKCODE", mouigosaVO.getMockCode());
            params.put("USER_ID", mouigosaVO.getUserId());
            params.put("EXAMSPARETIME", mouigosaVO.getTcounter());
            params.put("EXAMENDTIME", "Y");
            params.put("EXAMSTATUS", "3");

            // 모의고사 정보 조회
            List<HashMap<String, Object>> list = mouigosaService.getUpdateRegistrationDetail(params);
            HashMap<String, Object> examInfo = list.get(0);

            // 온라인 응시 여부 확인
            if (examInfo.get("USEFLAG") != null && "3".equals(String.valueOf(examInfo.get("USEFLAG")))) {
                params.put("ONLINE_YN", "Y");
            }
            if (examInfo.get("ISEXAMTYPEON") != null && "1".equals(String.valueOf(examInfo.get("ISEXAMTYPEON")))) {
                params.put("ONLINE_YN", "Y");
            }

            mouigosaService.mouigosaStatusUpdate4(params);

            // 과목 리스트 조회
            List<HashMap<String, Object>> subjectList = mouigosaService.getSubjectList(params);

            jsonObject.put("examInfo", examInfo);
            jsonObject.put("subjectList", subjectList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
