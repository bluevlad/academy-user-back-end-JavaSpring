package com.academy.board.notice;

import com.academy.board.notice.service.BoardNoticeService;
import com.academy.board.notice.service.BoardNoticeVO;
import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * BoardNoticeApi.java
 * 공지사항 게시판 REST API Controller
 */
@Tag(name = "BoardNotice", description = "공지사항 게시판 API")
@RestController
@RequestMapping("/api/board/notice")
public class BoardNoticeApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardNoticeService boardNoticeService;

    @Autowired
    public BoardNoticeApi(BoardNoticeService boardNoticeService) {
        this.boardNoticeService = boardNoticeService;
    }

    /**
     * 공지사항 게시판 목록 조회
     */
    @Operation(summary = "공지사항 게시판 목록 조회", description = "공지사항 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("BoardNoticeVO") BoardNoticeVO boardNoticeVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardNoticeVO.getBoardMngSeq(), ""));
        params.put("BOARDTYPE", CommonUtil.isNull(boardNoticeVO.getBoardType(), ""));
        params.put("NOTICETYPE", CommonUtil.isNull(boardNoticeVO.getNoticeType(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(boardNoticeVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(boardNoticeVO.getSearchKind(), ""));
        params.put("DIVICE_TYPE", CommonUtil.isNull(boardNoticeVO.getDiviceType(), "PC"));

        int currentPage = boardNoticeVO.getCurrentPage() > 0 ? boardNoticeVO.getCurrentPage() : 1;
        int recordCountPerPage = boardNoticeVO.getPageRow() > 0 ? boardNoticeVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardNoticeService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardNoticeService.boardList(params);
        HashMap<String, Object> boardInfo = boardNoticeService.getBoardInfo(params);

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
    @Operation(summary = "게시물 상세 조회", description = "공지사항 게시물 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("BoardNoticeVO") BoardNoticeVO boardNoticeVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_SEQ", CommonUtil.isNull(boardNoticeVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardNoticeVO.getBoardMngSeq(), ""));
        params.put("PARENT_BOARD_SEQ", CommonUtil.isNull(boardNoticeVO.getParentBoardSeq(), ""));
        params.put("INCTYPE", CommonUtil.isNull(boardNoticeVO.getIncType(), "Y"));

        // 조회수 증가 (INCTYPE이 Y인 경우만)
        if ("Y".equals(params.get("INCTYPE"))) {
            boardNoticeService.updateBoardHits(params);
        }

        HashMap<String, Object> detailView = boardNoticeService.boardView(params);
        List<HashMap<String, Object>> attachFiles = boardNoticeService.boardAttachFile(params);
        List<HashMap<String, Object>> attachImgs = boardNoticeService.boardAttachFileImg(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("detailView", detailView);
        jsonObject.put("attachFiles", attachFiles);
        jsonObject.put("attachImgs", attachImgs);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 메인 공지사항 목록 조회
     */
    @Operation(summary = "메인 공지사항 목록 조회", description = "메인 페이지용 공지사항 목록을 조회합니다.")
    @GetMapping("/getMainList")
    public JSONObject getMainList(
            @ModelAttribute("BoardNoticeVO") BoardNoticeVO boardNoticeVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardNoticeVO.getBoardMngSeq(), ""));

        List<HashMap<String, Object>> resultList = boardNoticeService.getMainBoardList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }
}
