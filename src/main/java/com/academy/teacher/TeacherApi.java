package com.academy.teacher;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.teacher.service.TeacherService;
import com.academy.teacher.service.TeacherVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * TeacherApi.java
 * 교수 관련 REST API Controller
 */
@Tag(name = "Teacher", description = "교수 관리 API")
@RestController
@RequestMapping("/api/teacher")
public class TeacherApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final TeacherService teacherService;

    @Autowired
    public TeacherApi(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 교수 리스트 조회
     */
    @Operation(summary = "교수 리스트 조회", description = "카테고리별 교수 목록을 조회합니다.")
    @GetMapping("/getTeacherList")
    public JSONObject getTeacherList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            @Parameter(description = "과목 코드") @RequestParam(required = false) String searchSubjectCode,
            @Parameter(description = "온/오프라인 구분 (O/F)") @RequestParam(defaultValue = "O") String topMenuType,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchCategoryCode", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));
        params.put("searchSubjectCode", CommonUtil.isNull(searchSubjectCode, ""));
        params.put("topMenuType", topMenuType);

        List<HashMap<String, Object>> resultList = teacherService.teacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 좌측 교수 리스트 조회
     */
    @Operation(summary = "좌측 교수 리스트 조회", description = "좌측 메뉴용 교수 목록을 조회합니다.")
    @GetMapping("/getLeftTeacherList")
    public JSONObject getLeftTeacherList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            @Parameter(description = "온/오프라인 구분 (O/F)") @RequestParam(defaultValue = "O") String topMenuType,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchCategoryCode", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));
        params.put("topMenuType", topMenuType);

        List<HashMap<String, Object>> resultList = teacherService.leftTeacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("leftTeacherList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 카테고리별 과목 리스트 조회
     */
    @Operation(summary = "카테고리별 과목 리스트 조회", description = "카테고리에 해당하는 과목 목록을 조회합니다.")
    @GetMapping("/getSubjectList")
    public JSONObject getSubjectList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            @Parameter(description = "온/오프라인 구분 (O/F)") @RequestParam(defaultValue = "O") String topMenuType,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchCategoryCode", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));
        params.put("topMenuType", topMenuType);

        List<HashMap<String, Object>> resultList = teacherService.subjectListByCategory(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 상세 정보 조회
     */
    @Operation(summary = "교수 상세 정보 조회", description = "교수의 상세 정보를 조회합니다.")
    @GetMapping("/getTeacherDetail")
    public JSONObject getTeacherDetail(
            @Parameter(description = "교수 ID", required = true) @RequestParam String searchUserId,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchUserId", searchUserId);

        HashMap<String, Object> teacherInfo = teacherService.teacherDetail(params);
        List<HashMap<String, Object>> bookLogList = teacherService.teacherBookLog(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherInfo", teacherInfo);
        jsonObject.put("bookLogList", bookLogList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 오프라인 강의 목록 조회
     */
    @Operation(summary = "교수 오프라인 강의 목록 조회", description = "교수의 오프라인 강의 목록을 조회합니다.")
    @GetMapping("/getTeacherLectureList")
    public JSONObject getTeacherLectureList(
            @Parameter(description = "교수 ID", required = true) @RequestParam String searchUserId,
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchUserId", searchUserId);
        params.put("searchCategoryCode", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));

        List<HashMap<String, Object>> lectureList = teacherService.teacherLectureList(params);
        List<HashMap<String, Object>> bookList = teacherService.teacherLectureBookList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("lectureList", lectureList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 온라인 강의 목록 조회
     */
    @Operation(summary = "교수 온라인 강의 목록 조회", description = "교수의 온라인 강의 목록을 조회합니다.")
    @GetMapping("/getTeacherMovieLectureList")
    public JSONObject getTeacherMovieLectureList(
            @Parameter(description = "교수 ID", required = true) @RequestParam String searchUserId,
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            @Parameter(description = "학습 형태 코드") @RequestParam(required = false) String searchLearningCd,
            @Parameter(description = "강의 유형") @RequestParam(required = false) String lecTypeChoice,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchUserId", searchUserId);
        params.put("searchCategoryCode", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));
        params.put("searchLearningCd", CommonUtil.isNull(searchLearningCd, ""));
        params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(lecTypeChoice, "D"));

        List<HashMap<String, Object>> lectureList = teacherService.teacherMovieLectureList(params);
        List<HashMap<String, Object>> bookList = teacherService.teacherMovieLectureBookList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("lectureList", lectureList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 온라인 강의 베스트 목록 조회
     */
    @Operation(summary = "교수 온라인 강의 베스트 목록 조회", description = "교수의 베스트 온라인 강의 목록을 조회합니다.")
    @GetMapping("/getTeacherMovieLectureBestList")
    public JSONObject getTeacherMovieLectureBestList(
            @Parameter(description = "교수 ID", required = true) @RequestParam String searchUserId,
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchUserId", searchUserId);
        params.put("searchCategoryCode", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));
        params.put("LEC_TYPE_CHOICE", "D");

        List<HashMap<String, Object>> resultList = teacherService.teacherMovieLectureBestList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("bestLectureList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 게시판 목록 조회
     */
    @Operation(summary = "교수 게시판 목록 조회", description = "교수의 게시판 목록을 조회합니다.")
    @GetMapping("/getTeacherBoardList")
    public JSONObject getTeacherBoardList(
            @Parameter(description = "교수 ID", required = true) @RequestParam String searchUserId,
            @Parameter(description = "게시판 관리 SEQ") @RequestParam(required = false) String boardMngSeq,
            @Parameter(description = "현재 페이지") @RequestParam(defaultValue = "1") int currentPage,
            @Parameter(description = "페이지당 건수") @RequestParam(required = false) Integer pageRow,
            @Parameter(description = "검색어") @RequestParam(required = false) String searchText,
            @Parameter(description = "검색 종류") @RequestParam(required = false) String searchKind,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchUserId", searchUserId);
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardMngSeq, "BOARD_002"));
        params.put("SEARCHTEXT", CommonUtil.isNull(searchText, ""));
        params.put("SEARCHKIND", CommonUtil.isNull(searchKind, ""));

        int recordCountPerPage = pageRow != null ? pageRow : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        int totalCount = teacherService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        List<HashMap<String, Object>> resultList = teacherService.boardList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 장바구니 담기
     */
    @Operation(summary = "강의 장바구니 담기", description = "강의를 장바구니에 담습니다.")
    @PostMapping("/addToCart")
    public JSONObject addToCart(
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
            String kindType = CommonUtil.isNull(params.get("KIND_TYPE"), "L");

            // 장바구니 중복 체크
            List<HashMap<String, Object>> cartCheck;
            if ("L".equals(kindType) || "D".equals(kindType)) {
                cartCheck = teacherService.movieLectureCartCheck(params);
            } else {
                cartCheck = teacherService.lectureCartCheck(params);
            }

            if (cartCheck != null && !cartCheck.isEmpty()) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "이미 장바구니에 담긴 강의입니다.");
                return new JSONObject(jsonObject);
            }

            // 장바구니 등록
            if ("L".equals(kindType) || "D".equals(kindType)) {
                teacherService.movieLectureCartInsert(params);
            } else {
                teacherService.lectureCartInsert(params);
            }

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

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "O"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "001"));
    }

}
