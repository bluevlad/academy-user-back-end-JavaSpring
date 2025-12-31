package com.academy.book;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.book.service.BookService;
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
 * BookApi.java
 * 도서 관련 REST API Controller
 */
@Tag(name = "Book", description = "도서 관리 API")
@RestController
@RequestMapping("/api/book")
public class BookApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BookService bookService;

    @Autowired
    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 도서 리스트 조회
     */
    @Operation(summary = "도서 리스트 조회", description = "도서 목록을 페이징하여 조회합니다.")
    @GetMapping("/getBookList")
    public JSONObject getBookList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String topMenu,
            @Parameter(description = "학습형태 코드") @RequestParam(required = false) String searchForm,
            @Parameter(description = "검색 카테고리") @RequestParam(required = false) String searchKind,
            @Parameter(description = "과목코드#강사ID") @RequestParam(required = false) String searchStjCode,
            @Parameter(description = "직렬 코드") @RequestParam(required = false) String searchSeriesCode,
            @Parameter(description = "과목 코드") @RequestParam(required = false) String searchSubjectCode,
            @Parameter(description = "강사 코드") @RequestParam(required = false) String searchPrfCode,
            @Parameter(description = "검색 유형 (1:도서명, 2:저자명, 3:출판사)") @RequestParam(required = false) String searchType,
            @Parameter(description = "검색어") @RequestParam(required = false) String searchText,
            @Parameter(description = "메인 여부") @RequestParam(required = false) String isMain,
            @Parameter(description = "강사 ID") @RequestParam(required = false) String searchUserId,
            @Parameter(description = "현재 페이지") @RequestParam(defaultValue = "1") int currentPage,
            @Parameter(description = "페이지당 건수") @RequestParam(required = false) Integer pageRow,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(topMenu, params.get("topMenu")));
        params.put("SEARCHFORM", CommonUtil.isNull(searchForm, ""));
        params.put("SEARCHKIND", CommonUtil.isNull(searchKind, ""));
        params.put("SEARCHSTJCODE", CommonUtil.isNull(searchStjCode, ""));
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(searchSeriesCode, ""));
        params.put("SEARCHSUBJECTCODE", CommonUtil.isNull(searchSubjectCode, ""));
        params.put("SEARCHPRFCODE", CommonUtil.isNull(searchPrfCode, ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(searchType, ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(searchText, ""));
        params.put("isMain", CommonUtil.isNull(isMain, "N"));
        params.put("searchUserId", CommonUtil.isNull(searchUserId, ""));

        int recordCountPerPage = pageRow != null ? pageRow : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        int totalCount = bookService.bookListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        List<HashMap<String, Object>> resultList = bookService.bookList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("bookList", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 상세 조회
     */
    @Operation(summary = "도서 상세 조회", description = "도서 상세 정보를 조회합니다.")
    @GetMapping("/getBookDetail")
    public JSONObject getBookDetail(
            @Parameter(description = "리소스 ID", required = true) @RequestParam String rscId,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", rscId);

        HashMap<String, Object> bookInfo = bookService.bookView(params);
        List<HashMap<String, Object>> lectureList = bookService.bookLectureList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("bookInfo", bookInfo);
        jsonObject.put("lectureList", lectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 관련 정보 조회 (SEQ 기준)
     */
    @Operation(summary = "도서 관련 정보 조회", description = "동일 SEQ의 도서 목록을 조회합니다.")
    @GetMapping("/getBookViewList")
    public JSONObject getBookViewList(
            @Parameter(description = "SEQ", required = true) @RequestParam String seq,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("SEQ", seq);

        List<HashMap<String, Object>> resultList = bookService.bookViewList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("bookList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 좌측 학습형태 리스트 조회
     */
    @Operation(summary = "학습형태 리스트 조회", description = "좌측 메뉴용 학습형태 목록을 조회합니다.")
    @GetMapping("/getLeftFormList")
    public JSONObject getLeftFormList(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        List<HashMap<String, Object>> resultList = bookService.leftFormList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("formList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 좌측 교수 리스트 조회
     */
    @Operation(summary = "좌측 교수 리스트 조회", description = "좌측 메뉴용 교수 목록을 조회합니다.")
    @GetMapping("/getLeftTeacherList")
    public JSONObject getLeftTeacherList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String topMenu,
            @Parameter(description = "온/오프라인 구분 (O/F)") @RequestParam(defaultValue = "O") String topMenuType,
            @Parameter(description = "직렬 코드") @RequestParam(required = false) String searchSeriesCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(topMenu, params.get("topMenu")));
        params.put("topMenuType", topMenuType);
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(searchSeriesCode, ""));

        List<HashMap<String, Object>> resultList = bookService.leftTeacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 리스트 조회
     */
    @Operation(summary = "교수 리스트 조회", description = "도서 관련 교수 목록을 조회합니다.")
    @GetMapping("/getTeacherList")
    public JSONObject getTeacherList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String topMenu,
            @Parameter(description = "온/오프라인 구분 (O/F)") @RequestParam(defaultValue = "O") String topMenuType,
            @Parameter(description = "직렬 코드") @RequestParam(required = false) String searchSeriesCode,
            @Parameter(description = "과목 코드") @RequestParam(required = false) String searchSubjectCode,
            @Parameter(description = "도서 플래그") @RequestParam(required = false) String bookFlag,
            @Parameter(description = "과목코드#강사ID") @RequestParam(required = false) String subjectSjtCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(topMenu, params.get("topMenu")));
        params.put("topMenuType", topMenuType);
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(searchSeriesCode, ""));
        params.put("SEARCHSUBJECTCODE", CommonUtil.isNull(searchSubjectCode, ""));
        params.put("BOOKFLAG", CommonUtil.isNull(bookFlag, "N"));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(subjectSjtCd, ""));

        List<HashMap<String, Object>> resultList = bookService.selectTeacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 학습형태 리스트 조회
     */
    @Operation(summary = "학습형태 리스트 조회", description = "학습형태 목록을 조회합니다.")
    @GetMapping("/getLearningFormList")
    public JSONObject getLearningFormList(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        List<HashMap<String, Object>> resultList = bookService.getLearningFormList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("learningFormList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 과목-교수 리스트 조회
     */
    @Operation(summary = "과목-교수 리스트 조회", description = "과목별 교수 목록을 조회합니다.")
    @GetMapping("/getCaSubjectTeacherList")
    public JSONObject getCaSubjectTeacherList(
            @Parameter(description = "리소스 ID") @RequestParam(required = false) String rscId,
            @Parameter(description = "강좌 코드") @RequestParam(required = false) String lecCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", CommonUtil.isNull(rscId, ""));
        params.put("LECCODE", CommonUtil.isNull(lecCode, ""));

        List<HashMap<String, Object>> resultList = bookService.getCaSubjectTeacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectTeacherList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 연관 강의 리스트 조회
     */
    @Operation(summary = "도서 연관 강의 리스트 조회", description = "도서와 연관된 강의 목록을 조회합니다.")
    @GetMapping("/getBookLectureList")
    public JSONObject getBookLectureList(
            @Parameter(description = "리소스 ID") @RequestParam(required = false) String rscId,
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String topMenu,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", CommonUtil.isNull(rscId, ""));
        params.put("topMenu", CommonUtil.isNull(topMenu, params.get("topMenu")));

        List<HashMap<String, Object>> resultList = bookService.bookLectureList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("lectureList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 장바구니 담기
     */
    @Operation(summary = "도서 장바구니 담기", description = "도서를 장바구니에 담습니다.")
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
            params.put("KIND_TYPE", CommonUtil.isNull(params.get("KIND_TYPE"), "B"));
            params.put("RSC_TYPE", CommonUtil.isNull(params.get("RSC_TYPE"), "BOOK"));
            params.put("BOOK_COUNT", CommonUtil.isNull(params.get("BOOK_COUNT"), "1"));

            // 장바구니 중복 체크
            List<HashMap<String, Object>> cartCheck = bookService.bookCartCheck(params);

            if (cartCheck != null && !cartCheck.isEmpty()) {
                // 이미 담긴 경우 수량 업데이트
                int currentCount = Integer.parseInt(String.valueOf(cartCheck.get(0).get("BOOK_COUNT")));
                int addCount = Integer.parseInt(params.get("BOOK_COUNT"));
                params.put("BOOK_COUNT", String.valueOf(currentCount + addCount));
                bookService.bookCartUpdate(params);
                jsonObject.put("message", "장바구니 수량이 업데이트되었습니다.");
            } else {
                // 신규 등록
                bookService.bookCartInsert(params);
                jsonObject.put("message", "장바구니에 담았습니다.");
            }

            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "장바구니 담기에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 과목 정보 조회
     */
    @Operation(summary = "과목 정보 조회", description = "과목 상세 정보를 조회합니다.")
    @GetMapping("/getSubjectInfo")
    public JSONObject getSubjectInfo(
            @Parameter(description = "과목 코드", required = true) @RequestParam String searchSubjectCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("SEARCHSUBJECTCODE", searchSubjectCode);

        HashMap<String, Object> subjectInfo = bookService.getSubjectInfo(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectInfo", subjectInfo);
        jsonObject.put("retMsg", "OK");

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
