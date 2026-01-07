package com.academy.lecture;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.lecture.service.LectureService;
import com.academy.lecture.service.LectureVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("SEARCHGUBN", CommonUtil.isNull(lectureVO.getSearchType(), "T"));
        params.put("USER_ID", CommonUtil.isNull(lectureVO.getUserId(), ""));

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
    public JSONObject getLearningFormList(
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(lectureVO.getCategoryCd(), ""));
        params.put("LEARNING_CD", CommonUtil.isNull(lectureVO.getLearningCd(), ""));
        params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(lectureVO.getLecTypeChoice(), ""));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(lectureVO.getSubjectSjtCd(), ""));
        params.put("searchUserId", CommonUtil.isNull(lectureVO.getUserId(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(lectureVO.getSearchText(), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(lectureVO.getSearchType(), ""));
        params.put("FREE_TAB", CommonUtil.isNull(lectureVO.getFreeTab(), ""));
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(lectureVO.getSearchSeriesCode(), ""));
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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(lectureVO.getCategoryCd(), ""));
        params.put("LEARNING_CD", CommonUtil.isNull(lectureVO.getLearningCd(), ""));
        params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(lectureVO.getLecTypeChoice(), ""));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(lectureVO.getSubjectSjtCd(), ""));
        params.put("SUBJECT_PRICE", CommonUtil.isNull(lectureVO.getSubjectPrice(), ""));
        params.put("SEARCHYEARMONTH", CommonUtil.isNull(lectureVO.getSearchYearMonth(), ""));

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("LECCODE", lectureVO.getLeccode());

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("LECCODE", lectureVO.getLeccode());

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(lectureVO.getCategoryCd(), ""));
        params.put("LEARNING_CD", CommonUtil.isNull(lectureVO.getLearningCd(), ""));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(lectureVO.getSubjectSjtCd(), ""));
        params.put("FREE_TAB", CommonUtil.isNull(lectureVO.getFreeTab(), ""));
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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("searchCategoryCode", lectureVO.getCategoryCd());

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(lectureVO.getCategoryCd(), ""));
        params.put("SERIES_CD", CommonUtil.isNull(lectureVO.getSearchSeriesCode(), ""));

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(lectureVO.getCategoryCd(), ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(lectureVO.getSubjectSjtCd(), ""));
        params.put("SERIES_CD", CommonUtil.isNull(lectureVO.getSearchSeriesCode(), ""));

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(lectureVO.getCategoryCd(), ""));

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("CATEGORY_CD", CommonUtil.isNull(lectureVO.getCategoryCd(), ""));

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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("USER_ID", CommonUtil.isNull(lectureVO.getUserId(), ""));
        params.put("LECCODE", CommonUtil.isNull(lectureVO.getLeccode(), ""));
        params.put("RSC_ID", CommonUtil.isNull(lectureVO.getRscId(), ""));
        params.put("RSC_TYPE", CommonUtil.isNull(lectureVO.getRscType(), ""));
        params.put("BOOK_COUNT", CommonUtil.isNull(lectureVO.getBookCount(), "1"));
        params.put("KIND_TYPE", CommonUtil.isNull(lectureVO.getKindType(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = CommonUtil.isNull(lectureVO.getUserId(), "");
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
            @ModelAttribute("LectureVO") LectureVO lectureVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("USER_ID", CommonUtil.isNull(lectureVO.getUserId(), ""));
        params.put("LECCODE", CommonUtil.isNull(lectureVO.getLeccode(), ""));
        params.put("RSC_ID", CommonUtil.isNull(lectureVO.getRscId(), ""));
        params.put("RSC_TYPE", CommonUtil.isNull(lectureVO.getRscType(), ""));
        params.put("BOOK_COUNT", CommonUtil.isNull(lectureVO.getBookCount(), "1"));
        params.put("KIND_TYPE", CommonUtil.isNull(lectureVO.getKindType(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = CommonUtil.isNull(lectureVO.getUserId(), "");
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

}
