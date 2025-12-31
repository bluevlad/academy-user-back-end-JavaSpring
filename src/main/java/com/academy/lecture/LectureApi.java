package com.academy.lecture;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.lecture.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * LectureApi.java
 * 강의 관련 REST API Controller
 */
@Tag(name = "Lecture", description = "강의 관리 API")
@RestController
@RequestMapping("/api/lecture")
public class LectureApi extends CORSFilter {

    private final LectureService lectureService;

    @Autowired
    public LectureApi(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    /**
     * 직종 목록 조회
     */
    @Operation(summary = "직종 목록 조회", description = "직종 목록을 조회합니다.")
    @GetMapping("/getKindList")
    public JSONObject getKindList(
            @Parameter(description = "검색구분 (T:전체, M:회원)") @RequestParam(required = false, defaultValue = "T") String searchGubn,
            @Parameter(description = "사용자ID") @RequestParam(required = false) String userId,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("SEARCHGUBN", searchGubn);
        params.put("USER_ID", CommonUtil.isNull(userId, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> kindList = lectureService.getKindList(params);

        jsonObject.put("kindList", kindList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 학습형태 목록 조회
     */
    @Operation(summary = "학습형태 목록 조회", description = "학습형태 목록을 조회합니다.")
    @GetMapping("/getLearningFormList")
    public JSONObject getLearningFormList(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> learningFormList = lectureService.getLearningFormList(params);

        jsonObject.put("learningFormList", learningFormList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 강의 목록 조회
     */
    @Operation(summary = "온라인 강의 목록 조회", description = "온라인(동영상) 강의 목록을 조회합니다.")
    @GetMapping("/getLectureOnList")
    public JSONObject getLectureOnList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String categoryCd,
            @Parameter(description = "학습형태 코드") @RequestParam(required = false) String learningCd,
            @Parameter(description = "강의유형 선택") @RequestParam(required = false) String lecTypeChoice,
            @Parameter(description = "과목코드") @RequestParam(required = false) String subjectSjtCd,
            @Parameter(description = "강사ID") @RequestParam(required = false) String searchUserId,
            @Parameter(description = "검색어") @RequestParam(required = false) String searchText,
            @Parameter(description = "검색유형") @RequestParam(required = false) String searchType,
            @Parameter(description = "무료탭") @RequestParam(required = false) String freeTab,
            @Parameter(description = "시리즈코드") @RequestParam(required = false) String searchSeriesCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(categoryCd, ""));
        params.put("LEARNING_CD", CommonUtil.isNull(learningCd, ""));
        params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(lecTypeChoice, ""));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(subjectSjtCd, ""));
        params.put("searchUserId", CommonUtil.isNull(searchUserId, ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(searchText, ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(searchType, ""));
        params.put("FREE_TAB", CommonUtil.isNull(freeTab, ""));
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(searchSeriesCode, ""));
        params.put("NO_TAB_007", "Y");

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> lectureList = lectureService.lectureOnList(params);
        List<HashMap<String, Object>> bookList = lectureService.lectureOnBookList(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 강의 목록 조회
     */
    @Operation(summary = "오프라인 강의 목록 조회", description = "오프라인(학원실강) 강의 목록을 조회합니다.")
    @GetMapping("/getLectureOffList")
    public JSONObject getLectureOffList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String categoryCd,
            @Parameter(description = "학습형태 코드") @RequestParam(required = false) String learningCd,
            @Parameter(description = "강의유형 선택") @RequestParam(required = false) String lecTypeChoice,
            @Parameter(description = "과목코드") @RequestParam(required = false) String subjectSjtCd,
            @Parameter(description = "가격") @RequestParam(required = false) String subjectPrice,
            @Parameter(description = "검색년월") @RequestParam(required = false) String searchYearMonth,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(categoryCd, ""));
        params.put("LEARNING_CD", CommonUtil.isNull(learningCd, ""));
        params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(lecTypeChoice, ""));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(subjectSjtCd, ""));
        params.put("SUBJECT_PRICE", CommonUtil.isNull(subjectPrice, ""));
        params.put("SEARCHYEARMONTH", CommonUtil.isNull(searchYearMonth, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> lectureList = lectureService.lectureOffList(params);
        List<HashMap<String, Object>> bookList = lectureService.lectureOffBookList(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 강의 상세 조회
     */
    @Operation(summary = "온라인 강의 상세 조회", description = "온라인(동영상) 강의 상세 정보를 조회합니다.")
    @GetMapping("/getLectureOnDetail")
    public JSONObject getLectureOnDetail(
            @Parameter(description = "강의코드", required = true) @RequestParam String leccode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("LECCODE", leccode);

        HashMap<String, Object> jsonObject = new HashMap<>();

        HashMap<String, Object> lectureDetail = lectureService.lectureOnDetail(params);
        List<HashMap<String, Object>> bookList = lectureService.lectureOnDetailBookList(params);
        List<HashMap<String, Object>> movieList = lectureService.lectureOnDetailMovieList(params);

        if (lectureDetail != null) {
            jsonObject.put("lectureDetail", lectureDetail);
            jsonObject.put("bookList", bookList);
            jsonObject.put("movieList", movieList);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "강의 정보를 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 강의 상세 조회
     */
    @Operation(summary = "오프라인 강의 상세 조회", description = "오프라인(학원실강) 강의 상세 정보를 조회합니다.")
    @GetMapping("/getLectureOffDetail")
    public JSONObject getLectureOffDetail(
            @Parameter(description = "강의코드", required = true) @RequestParam String leccode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("LECCODE", leccode);

        HashMap<String, Object> jsonObject = new HashMap<>();

        HashMap<String, Object> lectureDetail = lectureService.lectureOffDetail(params);
        List<HashMap<String, Object>> bookList = lectureService.lectureOffDetailBookList(params);

        if (lectureDetail != null) {
            jsonObject.put("lectureDetail", lectureDetail);
            jsonObject.put("bookList", bookList);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "강의 정보를 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 무료특강 목록 조회
     */
    @Operation(summary = "무료특강 목록 조회", description = "무료특강 목록을 조회합니다.")
    @GetMapping("/getFreeLectureList")
    public JSONObject getFreeLectureList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String categoryCd,
            @Parameter(description = "학습형태 코드") @RequestParam(required = false) String learningCd,
            @Parameter(description = "과목코드") @RequestParam(required = false) String subjectSjtCd,
            @Parameter(description = "무료탭") @RequestParam(required = false) String freeTab,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(categoryCd, ""));
        params.put("LEARNING_CD", CommonUtil.isNull(learningCd, ""));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(subjectSjtCd, ""));
        params.put("FREE_TAB", CommonUtil.isNull(freeTab, ""));
        params.put("SUBJECT_PRICE", "0");

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> lectureList = lectureService.lectureOnList(params);
        List<HashMap<String, Object>> topLectureList = lectureService.lectureFreeNewTopOnList(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("topLectureList", topLectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 과목 목록 조회 (카테고리별)
     */
    @Operation(summary = "과목 목록 조회", description = "카테고리별 과목 목록을 조회합니다.")
    @GetMapping("/getSubjectListByCategory")
    public JSONObject getSubjectListByCategory(
            @Parameter(description = "카테고리 코드", required = true) @RequestParam String categoryCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("searchCategoryCode", categoryCd);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> subjectList = lectureService.subjectListByCategory(params);

        jsonObject.put("subjectList", subjectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 시리즈별 과목 목록 조회
     */
    @Operation(summary = "시리즈별 과목 목록 조회", description = "시리즈별 과목 목록을 조회합니다.")
    @GetMapping("/getSeriesSubjectList")
    public JSONObject getSeriesSubjectList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String categoryCd,
            @Parameter(description = "시리즈 코드") @RequestParam(required = false) String seriesCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(categoryCd, ""));
        params.put("SERIES_CD", CommonUtil.isNull(seriesCd, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> subjectList = lectureService.selectSeriesSubject(params);

        jsonObject.put("subjectList", subjectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 시리즈별 강사 목록 조회
     */
    @Operation(summary = "시리즈별 강사 목록 조회", description = "시리즈별 강사 목록을 조회합니다.")
    @GetMapping("/getSeriesProfList")
    public JSONObject getSeriesProfList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String categoryCd,
            @Parameter(description = "과목코드") @RequestParam(required = false) String subjectCd,
            @Parameter(description = "시리즈 코드") @RequestParam(required = false) String seriesCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(categoryCd, ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(subjectCd, ""));
        params.put("SERIES_CD", CommonUtil.isNull(seriesCd, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> profList = lectureService.selectSeriesProf(params);

        jsonObject.put("profList", profList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 메인 카테고리 과목 목록 조회
     */
    @Operation(summary = "메인 카테고리 과목 목록 조회", description = "메인 카테고리별 과목 목록을 조회합니다.")
    @GetMapping("/getMainCateSubjectList")
    public JSONObject getMainCateSubjectList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String categoryCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(categoryCd, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> subjectList = lectureService.selectSubMainCateSubjectList(params);

        jsonObject.put("subjectList", subjectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 메인 카테고리 강사 목록 조회
     */
    @Operation(summary = "메인 카테고리 강사 목록 조회", description = "메인 카테고리별 강사 목록을 조회합니다.")
    @GetMapping("/getMainCateTeacherList")
    public JSONObject getMainCateTeacherList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String categoryCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(categoryCd, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> teacherList = lectureService.selectSubMainCateTeacherList(params);

        jsonObject.put("teacherList", teacherList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 강의 장바구니 담기
     */
    @Operation(summary = "온라인 강의 장바구니 담기", description = "온라인 강의를 장바구니에 담습니다.")
    @PostMapping("/addOnLectureToCart")
    public JSONObject addOnLectureToCart(
            @RequestBody HashMap<String, String> params,
            HttpServletRequest request) throws Exception {

        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            // 중복 체크
            HashMap<String, Object> checkResult = lectureService.lectureOnCartCheck(params);
            if (checkResult != null) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "이미 장바구니에 담긴 강의입니다.");
                return new JSONObject(jsonObject);
            }

            lectureService.lectureOnCartInsert(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "장바구니에 담았습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "장바구니 담기에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 강의 장바구니 담기
     */
    @Operation(summary = "오프라인 강의 장바구니 담기", description = "오프라인 강의를 장바구니에 담습니다.")
    @PostMapping("/addOffLectureToCart")
    public JSONObject addOffLectureToCart(
            @RequestBody HashMap<String, String> params,
            HttpServletRequest request) throws Exception {

        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            // 중복 체크
            HashMap<String, Object> checkResult = lectureService.lectureOffCartCheck(params);
            if (checkResult != null) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "이미 장바구니에 담긴 강의입니다.");
                return new JSONObject(jsonObject);
            }

            lectureService.lectureOffCartInsert(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "장바구니에 담았습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "장바구니 담기에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 파라미터 설정
     */
    @SuppressWarnings("unchecked")
    private void setParam(HashMap<String, String> params, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            params.put("USER_ID", "");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
            } else {
                params.put("USER_ID", "");
            }
        }
    }

}
