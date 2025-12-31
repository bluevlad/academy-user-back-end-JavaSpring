package com.academy.board.library;

import com.academy.board.library.service.BoardLibraryService;
import com.academy.board.library.service.BoardLibraryVO;
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
 * BoardLibraryApi.java
 * 자료실 게시판 REST API Controller
 */
@Tag(name = "BoardLibrary", description = "자료실 게시판 API")
@RestController
@RequestMapping("/api/board/library")
public class BoardLibraryApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardLibraryService boardLibraryService;

    @Autowired
    public BoardLibraryApi(BoardLibraryService boardLibraryService) {
        this.boardLibraryService = boardLibraryService;
    }

    /**
     * 자료실 게시판 목록 조회
     */
    @Operation(summary = "자료실 게시판 목록 조회", description = "자료실 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("BoardLibraryVO") BoardLibraryVO boardLibraryVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardLibraryVO.getBoardMngSeq(), ""));
        params.put("BOARDTYPE", CommonUtil.isNull(boardLibraryVO.getBoardType(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(boardLibraryVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(boardLibraryVO.getSearchKind(), ""));

        int currentPage = boardLibraryVO.getCurrentPage() > 0 ? boardLibraryVO.getCurrentPage() : 1;
        int recordCountPerPage = boardLibraryVO.getPageRow() > 0 ? boardLibraryVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardLibraryService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardLibraryService.boardList(params);
        HashMap<String, Object> boardInfo = boardLibraryService.getBoardInfo(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardInfo", boardInfo);
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 상세 조회
     */
    @Operation(summary = "게시물 상세 조회", description = "자료실 게시물 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("BoardLibraryVO") BoardLibraryVO boardLibraryVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_SEQ", CommonUtil.isNull(boardLibraryVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardLibraryVO.getBoardMngSeq(), ""));
        params.put("PARENT_BOARD_SEQ", CommonUtil.isNull(boardLibraryVO.getParentBoardSeq(), ""));
        params.put("INCTYPE", CommonUtil.isNull(boardLibraryVO.getIncType(), "Y"));

        // 조회수 증가 (INCTYPE이 Y인 경우만)
        if ("Y".equals(params.get("INCTYPE"))) {
            boardLibraryService.updateBoardHits(params);
        }

        HashMap<String, Object> detailView = boardLibraryService.boardView(params);
        List<HashMap<String, Object>> attachFiles = boardLibraryService.boardAttachFile(params);
        List<HashMap<String, Object>> attachImgs = boardLibraryService.boardAttachFileImg(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("detailView", detailView);
        jsonObject.put("attachFiles", attachFiles);
        jsonObject.put("attachImgs", attachImgs);
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
