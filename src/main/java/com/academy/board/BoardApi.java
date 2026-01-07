package com.academy.board;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.board.service.BoardService;
import com.academy.board.service.BoardVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * BoardApi.java
 * 게시판 관련 REST API Controller
 */
@Tag(name = "Board", description = "게시판 관리 API")
@RestController
@RequestMapping("/api/board")
public class BoardApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardService boardService;

    @Autowired
    public BoardApi(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시판 리스트 조회
     */
    @Operation(summary = "게시판 리스트 조회", description = "게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getBoardList")
    public JSONObject getBoardList(
            @ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardVO.getBoardMngSeq(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(boardVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(boardVO.getSearchKind(), ""));

        int currentPage = boardVO.getCurrentPage() > 0 ? boardVO.getCurrentPage() : 1;
        int recordCountPerPage = boardVO.getPageRow() > 0 ? boardVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardService.boardList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
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
    @Operation(summary = "게시물 상세 조회", description = "게시물 상세 정보를 조회합니다.")
    @GetMapping("/getBoardView")
    public JSONObject getBoardView(
            @ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_SEQ", boardVO.getBoardSeq());
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardVO.getBoardMngSeq(), ""));

        // 조회수 증가
        boardService.updateBoardHits(params);

        HashMap<String, Object> boardInfo = boardService.boardView(params);
        List<HashMap<String, Object>> fileList = boardService.boardAttachFile(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardInfo", boardInfo);
        jsonObject.put("fileList", fileList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 메인 게시판 리스트 조회
     */
    @Operation(summary = "메인 게시판 리스트 조회", description = "메인 페이지에 표시할 게시판 목록을 조회합니다.")
    @GetMapping("/getMainBoardList")
    public JSONObject getMainBoardList(
            @ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardVO.getBoardMngSeq(), "NOTICE_000"));

        List<HashMap<String, Object>> resultList = boardService.getMainBoardList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 등록
     */
    @Operation(summary = "게시물 등록", description = "새 게시물을 등록합니다.")
    @PostMapping("/insertBoard")
    public JSONObject insertBoard(
            @ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardVO.getBoardMngSeq(), ""));
        params.put("BOARD_TITLE", CommonUtil.isNull(boardVO.getBoardTitle(), ""));
        params.put("BOARD_CONTENT", CommonUtil.isNull(boardVO.getBoardContent(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardService.insertProcess(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "게시물이 등록되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "게시물 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 수정
     */
    @Operation(summary = "게시물 수정", description = "게시물을 수정합니다.")
    @PostMapping("/updateBoard")
    public JSONObject updateBoard(
            @ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_SEQ", CommonUtil.isNull(boardVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardVO.getBoardMngSeq(), ""));
        params.put("BOARD_TITLE", CommonUtil.isNull(boardVO.getBoardTitle(), ""));
        params.put("BOARD_CONTENT", CommonUtil.isNull(boardVO.getBoardContent(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardService.updateProcess(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "게시물이 수정되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "게시물 수정에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 삭제
     */
    @Operation(summary = "게시물 삭제", description = "게시물을 삭제합니다.")
    @PostMapping("/deleteBoard")
    public JSONObject deleteBoard(
            @ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_SEQ", CommonUtil.isNull(boardVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardVO.getBoardMngSeq(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardService.deleteProcess(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "게시물이 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "게시물 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

}
