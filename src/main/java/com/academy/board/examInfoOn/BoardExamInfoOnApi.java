package com.academy.board.examInfoOn;

import com.academy.board.examInfoOn.service.BoardExamInfoOnService;
import com.academy.board.examInfoOn.service.BoardExamInfoOnVO;
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
 * BoardExamInfoOnApi.java
 * 시험정보 온라인 게시판 REST API Controller
 */
@Tag(name = "BoardExamInfoOn", description = "시험정보 온라인 게시판 API")
@RestController
@RequestMapping("/api/board/examInfoOn")
public class BoardExamInfoOnApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardExamInfoOnService boardExamInfoOnService;

    @Autowired
    public BoardExamInfoOnApi(BoardExamInfoOnService boardExamInfoOnService) {
        this.boardExamInfoOnService = boardExamInfoOnService;
    }

    /**
     * 시험정보 게시판 목록 조회
     */
    @Operation(summary = "시험정보 게시판 목록 조회", description = "시험정보 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("BoardExamInfoOnVO") BoardExamInfoOnVO boardExamInfoOnVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardExamInfoOnVO.getBoardMngSeq(), ""));
        params.put("BOARDTYPE", CommonUtil.isNull(boardExamInfoOnVO.getBoardType(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(boardExamInfoOnVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(boardExamInfoOnVO.getSearchKind(), ""));
        params.put("SCH_EXAM_TYPE", CommonUtil.isNull(boardExamInfoOnVO.getSchExamType(), ""));
        params.put("SCH_EXAM_AREA", CommonUtil.isNull(boardExamInfoOnVO.getSchExamArea(), ""));

        String boardType = params.get("BOARDTYPE");

        // 게시판 타입이 1, 2가 아닌 경우 빈 목록 반환
        if (!"1".equals(boardType) && !"2".equals(boardType)) {
            HashMap<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "해당 게시판 타입에서는 목록 조회를 지원하지 않습니다.");
            return new JSONObject(jsonObject);
        }

        int currentPage = boardExamInfoOnVO.getCurrentPage() > 0 ? boardExamInfoOnVO.getCurrentPage() : 1;
        int recordCountPerPage = boardExamInfoOnVO.getPageRow() > 0 ? boardExamInfoOnVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardExamInfoOnService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardExamInfoOnService.boardList(params);
        HashMap<String, Object> boardInfo = boardExamInfoOnService.getBoardInfo(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardInfo", boardInfo);
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());

        // NOTICE_008 게시판인 경우 공고 구분 정보 추가
        if ("NOTICE_008".equals(params.get("BOARD_MNG_SEQ"))) {
            HashMap<String, Object> codeParams = new HashMap<>();
            codeParams.put("SYS_CD", "PUB_TYPE");
            jsonObject.put("pubGubun", boardExamInfoOnService.getPubGubun(codeParams));
            codeParams.put("SYS_CD", "PUB_AREA");
            jsonObject.put("pubArea", boardExamInfoOnService.getPubGubun(codeParams));
        }

        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 상세 조회
     */
    @Operation(summary = "게시물 상세 조회", description = "시험정보 게시물 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("BoardExamInfoOnVO") BoardExamInfoOnVO boardExamInfoOnVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_SEQ", CommonUtil.isNull(boardExamInfoOnVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardExamInfoOnVO.getBoardMngSeq(), ""));
        params.put("PARENT_BOARD_SEQ", CommonUtil.isNull(boardExamInfoOnVO.getParentBoardSeq(), ""));

        // 조회수 증가
        boardExamInfoOnService.updateBoardHits(params);

        HashMap<String, Object> detailView = boardExamInfoOnService.boardView(params);
        List<HashMap<String, Object>> attachFiles = boardExamInfoOnService.boardAttachFile(params);
        List<HashMap<String, Object>> attachImgs = boardExamInfoOnService.boardAttachFileImg(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("detailView", detailView);
        jsonObject.put("attachFiles", attachFiles);
        jsonObject.put("attachImgs", attachImgs);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 공고 게시판 목록 조회
     */
    @Operation(summary = "공고 게시판 목록 조회", description = "공고 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getPubList")
    public JSONObject getPubList(
            @ModelAttribute("BoardExamInfoOnVO") BoardExamInfoOnVO boardExamInfoOnVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParamObj(params, request);

        params.put("sType", boardExamInfoOnVO.getsType());

        int currentPage = boardExamInfoOnVO.getCurrentPage() > 0 ? boardExamInfoOnVO.getCurrentPage() : 1;
        int recordCountPerPage = boardExamInfoOnVO.getPageRow() > 0 ? boardExamInfoOnVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardExamInfoOnService.pubNoticeListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardExamInfoOnService.pubNoticeList(params);
        List<HashMap<String, Object>> configCDs = boardExamInfoOnService.getPubGubun(params);
        List<HashMap<String, Object>> pubCateAll = boardExamInfoOnService.getPubCategorySel(params);

        params.put("LVL", "1");
        List<HashMap<String, Object>> pubMain = boardExamInfoOnService.getPubCategorySel(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("configCDs", configCDs);
        jsonObject.put("pubCateAll", pubCateAll);
        jsonObject.put("pubMain", pubMain);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 공고 상세 조회
     */
    @Operation(summary = "공고 상세 조회", description = "공고 상세 정보를 조회합니다.")
    @GetMapping("/getPubView")
    public JSONObject getPubView(
            @ModelAttribute("BoardExamInfoOnVO") BoardExamInfoOnVO boardExamInfoOnVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_SEQ", CommonUtil.isNull(boardExamInfoOnVO.getBoardSeq(), ""));

        // 조회수 증가
        boardExamInfoOnService.updatePubNoticeHits(params);

        HashMap<String, Object> detailView = boardExamInfoOnService.pubNoticeView(params);
        List<HashMap<String, Object>> attachFiles = boardExamInfoOnService.pubNoticeAttachFile(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("detailView", detailView);
        jsonObject.put("attachFiles", attachFiles);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 공고 구분 코드 목록 조회
     */
    @Operation(summary = "공고 구분 코드 목록 조회", description = "공고 구분 코드 목록을 조회합니다.")
    @GetMapping("/getPubGubun")
    public JSONObject getPubGubun(
            @RequestParam(value = "sysCd", required = false, defaultValue = "PUB_TYPE") String sysCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        params.put("SYS_CD", sysCd);

        List<HashMap<String, Object>> pubGubun = boardExamInfoOnService.getPubGubun(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", pubGubun);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 파라미터 설정 (String)
     */
    @SuppressWarnings("unchecked")
    private void setParam(HashMap<String, String> params, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            params.put("USER_ID", "");
            params.put("USER_NM", "");
            params.put("REG_ID", "");
            params.put("UPD_ID", "");
            params.put("ISLOGIN", "N");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
                params.put("USER_NM", loginInfo.get("USER_NM"));
                params.put("USER_ROLE", loginInfo.get("USER_ROLE"));
                params.put("REG_ID", loginInfo.get("USER_ID"));
                params.put("UPD_ID", loginInfo.get("USER_ID"));
                params.put("ISLOGIN", "Y");
            } else {
                params.put("USER_ID", "");
                params.put("USER_NM", "");
                params.put("REG_ID", "");
                params.put("UPD_ID", "");
                params.put("ISLOGIN", "N");
            }
        }

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "O"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "MAIN"));
    }

    /**
     * 파라미터 설정 (Object)
     */
    @SuppressWarnings("unchecked")
    private void setParamObj(HashMap<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            params.put("USER_ID", "");
            params.put("USER_NM", "");
            params.put("REG_ID", "");
            params.put("UPD_ID", "");
            params.put("ISLOGIN", "N");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
                params.put("USER_NM", loginInfo.get("USER_NM"));
                params.put("USER_ROLE", loginInfo.get("USER_ROLE"));
                params.put("REG_ID", loginInfo.get("USER_ID"));
                params.put("UPD_ID", loginInfo.get("USER_ID"));
                params.put("ISLOGIN", "Y");
            } else {
                params.put("USER_ID", "");
                params.put("USER_NM", "");
                params.put("REG_ID", "");
                params.put("UPD_ID", "");
                params.put("ISLOGIN", "N");
            }
        }

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "O"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "MAIN"));
    }
}
