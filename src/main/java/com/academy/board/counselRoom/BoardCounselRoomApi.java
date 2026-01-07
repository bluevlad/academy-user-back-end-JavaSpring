package com.academy.board.counselRoom;

import com.academy.board.counselRoom.service.BoardCounselRoomService;
import com.academy.board.counselRoom.service.BoardCounselRoomVO;
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
 * BoardCounselRoomApi.java
 * 상담실 게시판 REST API Controller
 */
@Tag(name = "BoardCounselRoom", description = "상담실 게시판 API")
@RestController
@RequestMapping("/api/board/counselRoom")
public class BoardCounselRoomApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardCounselRoomService boardCounselRoomService;

    @Autowired
    public BoardCounselRoomApi(BoardCounselRoomService boardCounselRoomService) {
        this.boardCounselRoomService = boardCounselRoomService;
    }

    /**
     * 상담실 게시판 목록 조회
     */
    @Operation(summary = "상담실 게시판 목록 조회", description = "상담실 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("BoardCounselRoomVO") BoardCounselRoomVO boardCounselRoomVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardMngSeq(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(boardCounselRoomVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(boardCounselRoomVO.getSearchKind(), ""));

        int currentPage = boardCounselRoomVO.getCurrentPage() > 0 ? boardCounselRoomVO.getCurrentPage() : 1;
        int recordCountPerPage = boardCounselRoomVO.getPageRow() > 0 ? boardCounselRoomVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardCounselRoomService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardCounselRoomService.boardList(params);
        HashMap<String, Object> boardInfo = boardCounselRoomService.getBoardInfo(params);

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
    @Operation(summary = "게시물 상세 조회", description = "상담 게시물 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("BoardCounselRoomVO") BoardCounselRoomVO boardCounselRoomVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardMngSeq(), ""));
        params.put("PARENT_BOARD_SEQ", CommonUtil.isNull(boardCounselRoomVO.getParentBoardSeq(), ""));
        params.put("ISREPLY", CommonUtil.isNull(boardCounselRoomVO.getIsReply(), ""));

        // 조회수 증가
        boardCounselRoomService.updateBoardHits(params);

        HashMap<String, Object> boardInfo = boardCounselRoomService.getBoardInfo(params);
        HashMap<String, Object> detailView = boardCounselRoomService.boardView(params);
        List<HashMap<String, Object>> attachFiles = boardCounselRoomService.boardAttachFile(params);
        List<HashMap<String, Object>> attachImgs = boardCounselRoomService.boardAttachFileImg(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardInfo", boardInfo);
        jsonObject.put("detailView", detailView);
        jsonObject.put("attachFiles", attachFiles);
        jsonObject.put("attachImgs", attachImgs);

        // 답변이 있는 경우
        if ("Y".equals(boardCounselRoomVO.getIsReply())) {
            HashMap<String, Object> replyView = boardCounselRoomService.boardReplyView(params);
            List<HashMap<String, Object>> replyAttachFiles = boardCounselRoomService.boardReplyAttachFile(params);
            List<HashMap<String, Object>> replyAttachImgs = boardCounselRoomService.boardReplyAttachFileImg(params);

            jsonObject.put("replyView", replyView);
            jsonObject.put("replyAttachFiles", replyAttachFiles);
            jsonObject.put("replyAttachImgs", replyAttachImgs);
        }

        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 등록
     */
    @Operation(summary = "게시물 등록", description = "상담 게시물을 등록합니다.")
    @PostMapping("/insert")
    public JSONObject insert(
            @ModelAttribute("BoardCounselRoomVO") BoardCounselRoomVO boardCounselRoomVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCounselRoomVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardMngSeq(), ""));
        params.put("SUBJECT", CommonUtil.isNull(boardCounselRoomVO.getSubject(), ""));
        params.put("CONTENT", CommonUtil.isNull(boardCounselRoomVO.getContent(), ""));
        params.put("OPEN_YN", CommonUtil.isNull(boardCounselRoomVO.getOpenYn(), "Y"));
        params.put("CATEGORY_CODE", CommonUtil.isNull(boardCounselRoomVO.getCategoryCode(), "001"));
        params.put("NOTICE_TOP_YN", "N");

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            String boardSeq = boardCounselRoomService.insertProcess(params);
            jsonObject.put("boardSeq", boardSeq);
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
    @Operation(summary = "게시물 수정", description = "상담 게시물을 수정합니다.")
    @PostMapping("/update")
    public JSONObject update(
            @ModelAttribute("BoardCounselRoomVO") BoardCounselRoomVO boardCounselRoomVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCounselRoomVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("UPD_ID", userId);
        params.put("BOARD_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardMngSeq(), ""));
        params.put("SUBJECT", CommonUtil.isNull(boardCounselRoomVO.getSubject(), ""));
        params.put("CONTENT", CommonUtil.isNull(boardCounselRoomVO.getContent(), ""));
        params.put("OPEN_YN", CommonUtil.isNull(boardCounselRoomVO.getOpenYn(), "Y"));
        params.put("CATEGORY_CODE", CommonUtil.isNull(boardCounselRoomVO.getCategoryCode(), "001"));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardCounselRoomService.updateProcess(params);
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
    @Operation(summary = "게시물 삭제", description = "상담 게시물을 삭제합니다.")
    @PostMapping("/delete")
    public JSONObject delete(
            @ModelAttribute("BoardCounselRoomVO") BoardCounselRoomVO boardCounselRoomVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCounselRoomVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("UPD_ID", userId);
        params.put("BOARD_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardMngSeq(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardCounselRoomService.deleteProcess(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "게시물이 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "게시물 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 답변 등록
     */
    @Operation(summary = "답변 등록", description = "상담 게시물에 답변을 등록합니다.")
    @PostMapping("/insertReply")
    public JSONObject insertReply(
            @ModelAttribute("BoardCounselRoomVO") BoardCounselRoomVO boardCounselRoomVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCounselRoomVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);
        params.put("BOARD_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardSeq(), ""));
        params.put("PARENT_BOARD_SEQ", CommonUtil.isNull(boardCounselRoomVO.getParentBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardMngSeq(), ""));
        params.put("SUBJECT", CommonUtil.isNull(boardCounselRoomVO.getSubject(), ""));
        params.put("CONTENT", CommonUtil.isNull(boardCounselRoomVO.getContent(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardCounselRoomService.insertReply(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "답변이 등록되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "답변 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 답변 수정
     */
    @Operation(summary = "답변 수정", description = "상담 게시물의 답변을 수정합니다.")
    @PostMapping("/updateReply")
    public JSONObject updateReply(
            @ModelAttribute("BoardCounselRoomVO") BoardCounselRoomVO boardCounselRoomVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCounselRoomVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("UPD_ID", userId);
        params.put("BOARD_SEQ", CommonUtil.isNull(boardCounselRoomVO.getBoardSeq(), ""));
        params.put("SUBJECT", CommonUtil.isNull(boardCounselRoomVO.getSubject(), ""));
        params.put("CONTENT", CommonUtil.isNull(boardCounselRoomVO.getContent(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardCounselRoomService.updateReply(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "답변이 수정되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "답변 수정에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }
}
