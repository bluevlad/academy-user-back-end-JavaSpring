package com.academy.book;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.book.service.BookService;
import com.academy.book.service.BookVO;
import io.swagger.v3.oas.annotations.Operation;
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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(bookVO.getTopMenu(), params.get("topMenu")));
        params.put("SEARCHFORM", CommonUtil.isNull(bookVO.getSearchForm(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(bookVO.getSearchKind(), ""));
        params.put("SEARCHSTJCODE", CommonUtil.isNull(bookVO.getSearchStjCode(), ""));
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(bookVO.getSearchSeriesCode(), ""));
        params.put("SEARCHSUBJECTCODE", CommonUtil.isNull(bookVO.getSearchSubjectCode(), ""));
        params.put("SEARCHPRFCODE", CommonUtil.isNull(bookVO.getSearchPrfCode(), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(bookVO.getSearchType(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(bookVO.getSearchText(), ""));
        params.put("isMain", CommonUtil.isNull(bookVO.getIsMain(), "N"));
        params.put("searchUserId", CommonUtil.isNull(bookVO.getSearchUserId(), ""));

        int currentPage = bookVO.getCurrentPage() > 0 ? bookVO.getCurrentPage() : 1;
        int recordCountPerPage = bookVO.getPageRow() > 0 ? bookVO.getPageRow() : pageUnit;

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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", bookVO.getRscId());

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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("SEQ", bookVO.getSeq());

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
    public JSONObject getLeftFormList(
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(bookVO.getTopMenu(), params.get("topMenu")));
        params.put("topMenuType", CommonUtil.isNull(bookVO.getTopMenuType(), "O"));
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(bookVO.getSearchSeriesCode(), ""));

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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(bookVO.getTopMenu(), params.get("topMenu")));
        params.put("topMenuType", CommonUtil.isNull(bookVO.getTopMenuType(), "O"));
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(bookVO.getSearchSeriesCode(), ""));
        params.put("SEARCHSUBJECTCODE", CommonUtil.isNull(bookVO.getSearchSubjectCode(), ""));
        params.put("BOOKFLAG", CommonUtil.isNull(bookVO.getBookFlag(), "N"));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(bookVO.getSearchStjCode(), ""));

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
    public JSONObject getLearningFormList(
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", CommonUtil.isNull(bookVO.getRscId(), ""));
        params.put("LECCODE", CommonUtil.isNull(bookVO.getLecCode(), ""));

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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", CommonUtil.isNull(bookVO.getRscId(), ""));
        params.put("topMenu", CommonUtil.isNull(bookVO.getTopMenu(), params.get("topMenu")));

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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", CommonUtil.isNull(bookVO.getRscId(), ""));
        params.put("KIND_TYPE", CommonUtil.isNull(bookVO.getKindType(), "B"));
        params.put("RSC_TYPE", CommonUtil.isNull(bookVO.getRscType(), "BOOK"));
        params.put("BOOK_COUNT", CommonUtil.isNull(bookVO.getBookCount(), "1"));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
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
            @ModelAttribute("BookVO") BookVO bookVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("SEARCHSUBJECTCODE", bookVO.getSearchSubjectCode());

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
