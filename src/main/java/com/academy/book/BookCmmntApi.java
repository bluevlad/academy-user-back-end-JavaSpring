package com.academy.book;

import com.academy.book.service.BookCmmntService;
import com.academy.book.service.BookCmmntVO;
import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
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
 * BookCmmntApi.java
 * 도서 후기(댓글) REST API Controller
 */
@Tag(name = "BookCmmnt", description = "도서 후기 API")
@RestController
@RequestMapping("/api/book/comment")
public class BookCmmntApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BookCmmntService bookCmmntService;

    @Autowired
    public BookCmmntApi(BookCmmntService bookCmmntService) {
        this.bookCmmntService = bookCmmntService;
    }

    /**
     * 도서 후기 목록 조회
     */
    @Operation(summary = "도서 후기 목록 조회", description = "도서 후기 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("BookCmmntVO") BookCmmntVO bookCmmntVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("SEARCHSUBJECT", CommonUtil.isNull(bookCmmntVO.getSearchSubject(), ""));
        params.put("SEARCHTEAC", CommonUtil.isNull(bookCmmntVO.getSearchTeac(), ""));
        params.put("searchSubjectCode", CommonUtil.isNull(bookCmmntVO.getSearchSubjectCode(), ""));
        params.put("searchLeccode", CommonUtil.isNull(bookCmmntVO.getSearchLecCode(), ""));

        int currentPage = bookCmmntVO.getCurrentPage() > 0 ? bookCmmntVO.getCurrentPage() : 1;
        int recordCountPerPage = bookCmmntVO.getPageRow() > 0 ? bookCmmntVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = bookCmmntService.getBookCmmtCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = bookCmmntService.selectBookCmmt(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 상세 후기 목록 조회
     */
    @Operation(summary = "도서 상세 후기 목록 조회", description = "특정 도서의 후기 목록을 페이징하여 조회합니다.")
    @GetMapping("/getDetailList")
    public JSONObject getDetailList(
            @ModelAttribute("BookCmmntVO") BookCmmntVO bookCmmntVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", CommonUtil.isNull(bookCmmntVO.getRscId(), ""));

        int currentPage = bookCmmntVO.getCurrentPage() > 0 ? bookCmmntVO.getCurrentPage() : 1;
        int recordCountPerPage = bookCmmntVO.getPageRow() > 0 ? bookCmmntVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = bookCmmntService.detailCmmtListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = bookCmmntService.detailCmmtList(params);

        // 도서 구매 여부 확인
        int isBuyYn = bookCmmntService.isBookCmmt(params);
        String myBookYN = isBuyYn > 0 ? "Y" : "N";

        // 사용자 후기 작성 여부 확인
        int userCmmtCnt = bookCmmntService.getBookCmmtCntByUser(params);
        String myCmmtYN = userCmmtCnt > 0 ? "N" : "Y";

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("isBuyYN", myBookYN);
        jsonObject.put("myCmmtYN", myCmmtYN);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 후기 등록
     */
    @Operation(summary = "도서 후기 등록", description = "도서 후기를 등록합니다.")
    @PostMapping("/insert")
    public JSONObject insert(
            @ModelAttribute("BookCmmntVO") BookCmmntVO bookCmmntVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 로그인 체크
        if (params.get("USER_ID") == null || params.get("USER_ID").isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("RSC_ID", CommonUtil.isNull(bookCmmntVO.getRscId(), ""));
        params.put("TITLE", CommonUtil.isNull(bookCmmntVO.getTitle(), ""));
        params.put("CONTENT", CommonUtil.isNull(bookCmmntVO.getContent(), ""));
        params.put("CHOICE_POINT", CommonUtil.isNull(bookCmmntVO.getChoicePoint(), "5"));

        try {
            // 이미 후기 작성 여부 확인
            int userCmmtCnt = bookCmmntService.getBookCmmtCntByUser(params);
            if (userCmmtCnt > 0) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "이미 후기를 작성하셨습니다.");
                return new JSONObject(jsonObject);
            }

            // 도서 구매 여부 확인
            int isBuyYn = bookCmmntService.isBookCmmt(params);
            if (isBuyYn == 0) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "도서를 구매하신 분만 후기를 작성할 수 있습니다.");
                return new JSONObject(jsonObject);
            }

            bookCmmntService.insertCmmt(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "후기가 등록되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "후기 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 후기 삭제
     */
    @Operation(summary = "도서 후기 삭제", description = "도서 후기를 삭제합니다.")
    @PostMapping("/delete")
    public JSONObject delete(
            @ModelAttribute("BookCmmntVO") BookCmmntVO bookCmmntVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 로그인 체크
        if (params.get("USER_ID") == null || params.get("USER_ID").isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("DELETE_RSCID", CommonUtil.isNull(bookCmmntVO.getDeleteRscId(), ""));
        params.put("DELETE_SEQ", CommonUtil.isNull(bookCmmntVO.getDeleteSeq(), ""));

        try {
            bookCmmntService.deleteCmmt(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "후기가 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "후기 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 구매 여부 확인
     */
    @Operation(summary = "도서 구매 여부 확인", description = "사용자의 도서 구매 여부를 확인합니다.")
    @GetMapping("/checkPurchase")
    public JSONObject checkPurchase(
            @ModelAttribute("BookCmmntVO") BookCmmntVO bookCmmntVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("RSC_ID", CommonUtil.isNull(bookCmmntVO.getRscId(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 로그인 체크
        if (params.get("USER_ID") == null || params.get("USER_ID").isEmpty()) {
            jsonObject.put("isBuyYN", "N");
            jsonObject.put("myCmmtYN", "N");
            jsonObject.put("retMsg", "OK");
            return new JSONObject(jsonObject);
        }

        // 도서 구매 여부 확인
        int isBuyYn = bookCmmntService.isBookCmmt(params);
        String myBookYN = isBuyYn > 0 ? "Y" : "N";

        // 사용자 후기 작성 여부 확인
        int userCmmtCnt = bookCmmntService.getBookCmmtCntByUser(params);
        String myCmmtYN = userCmmtCnt > 0 ? "N" : "Y";

        jsonObject.put("isBuyYN", myBookYN);
        jsonObject.put("myCmmtYN", myCmmtYN);
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
            params.put("USER_NM", "");
            params.put("REG_ID", "");
            params.put("UPD_ID", "");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
                params.put("USER_NM", loginInfo.get("USER_NM"));
                params.put("REG_ID", loginInfo.get("USER_ID"));
                params.put("UPD_ID", loginInfo.get("USER_ID"));
            } else {
                params.put("USER_ID", "");
                params.put("USER_NM", "");
                params.put("REG_ID", "");
                params.put("UPD_ID", "");
            }
        }

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "O"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "001"));
    }
}
