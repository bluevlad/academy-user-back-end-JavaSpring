package com.academy.board.cmmnty;

import com.academy.board.cmmnty.service.BoardCmmntyService;
import com.academy.board.cmmnty.service.BoardCmmntyVO;
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
 * BoardCmmntyApi.java
 * 커뮤니티 게시판 REST API Controller
 */
@Tag(name = "BoardCmmnty", description = "커뮤니티 게시판 API")
@RestController
@RequestMapping("/api/board/cmmnty")
public class BoardCmmntyApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardCmmntyService boardCmmntyService;

    @Autowired
    public BoardCmmntyApi(BoardCmmntyService boardCmmntyService) {
        this.boardCmmntyService = boardCmmntyService;
    }

    /**
     * 커뮤니티 게시판 목록 조회
     */
    @Operation(summary = "커뮤니티 게시판 목록 조회", description = "커뮤니티 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardMngSeq(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(boardCmmntyVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(boardCmmntyVO.getSearchKind(), ""));
        params.put("SCH_COMMNTY_GUBN", CommonUtil.isNull(boardCmmntyVO.getSchCmmntyGubn(), ""));
        params.put("MOCKCODE", CommonUtil.isNull(boardCmmntyVO.getMockCode(), ""));

        int currentPage = boardCmmntyVO.getCurrentPage() > 0 ? boardCmmntyVO.getCurrentPage() : 1;
        int recordCountPerPage = boardCmmntyVO.getPageRow() > 0 ? boardCmmntyVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardCmmntyService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardCmmntyService.boardList(params);
        HashMap<String, Object> boardInfo = boardCmmntyService.getBoardInfo(params);

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
     * BEST 게시물 목록 조회
     */
    @Operation(summary = "BEST 게시물 목록 조회", description = "BEST 게시물 목록을 조회합니다.")
    @GetMapping("/getBestList")
    public JSONObject getBestList(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardMngSeq(), ""));
        params.put("topMenu", "MAIN");
        params.put("startNo", "0");
        params.put("endNo", "3");

        List<HashMap<String, Object>> bestList = boardCmmntyService.boardBestList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", bestList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 상세 조회
     */
    @Operation(summary = "게시물 상세 조회", description = "게시물 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardMngSeq(), ""));
        params.put("PARENT_BOARD_SEQ", CommonUtil.isNull(boardCmmntyVO.getParentBoardSeq(), ""));
        params.put("BOARDTYPE", CommonUtil.isNull(boardCmmntyVO.getBoardType(), ""));

        // 조회수 증가
        boardCmmntyService.updateBoardHits(params);

        HashMap<String, Object> detailView = boardCmmntyService.boardView(params);
        List<HashMap<String, Object>> attachFiles = boardCmmntyService.boardAttachFile(params);
        List<HashMap<String, Object>> attachImgs = boardCmmntyService.boardAttachFileImg(params);

        // 댓글이 있는 게시판 타입인 경우 (22, 23)
        String boardType = boardCmmntyVO.getBoardType();
        if ("22".equals(boardType) || "23".equals(boardType)) {
            List<HashMap<String, Object>> comments = boardCmmntyService.getCommentList(params);
            int commentCount = boardCmmntyService.getCommentCount(params);

            HashMap<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("detailView", detailView);
            jsonObject.put("attachFiles", attachFiles);
            jsonObject.put("attachImgs", attachImgs);
            jsonObject.put("comments", comments);
            jsonObject.put("commentCount", commentCount);
            jsonObject.put("retMsg", "OK");

            return new JSONObject(jsonObject);
        }

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("detailView", detailView);
        jsonObject.put("attachFiles", attachFiles);
        jsonObject.put("attachImgs", attachImgs);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 등록
     */
    @Operation(summary = "게시물 등록", description = "커뮤니티 게시물을 등록합니다.")
    @PostMapping("/insert")
    public JSONObject insert(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCmmntyVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("REG_ID", userId);
        params.put("UPD_ID", userId);
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardMngSeq(), ""));
        params.put("SUBJECT", CommonUtil.isNull(boardCmmntyVO.getSubject(), ""));
        params.put("CONTENT", CommonUtil.isNull(boardCmmntyVO.getContent(), ""));
        params.put("OPEN_YN", CommonUtil.isNull(boardCmmntyVO.getOpenYn(), "Y"));
        params.put("COMMNTY_GUBN", CommonUtil.isNull(boardCmmntyVO.getCmmntyGubn(), ""));
        params.put("MOCKCODE", CommonUtil.isNull(boardCmmntyVO.getMockCode(), ""));
        params.put("NOTICE_TOP_YN", "N");

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            String boardSeq = boardCmmntyService.insertProcess(params);
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
    @Operation(summary = "게시물 수정", description = "커뮤니티 게시물을 수정합니다.")
    @PostMapping("/update")
    public JSONObject update(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCmmntyVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("UPD_ID", userId);
        params.put("BOARD_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardMngSeq(), ""));
        params.put("SUBJECT", CommonUtil.isNull(boardCmmntyVO.getSubject(), ""));
        params.put("CONTENT", CommonUtil.isNull(boardCmmntyVO.getContent(), ""));
        params.put("OPEN_YN", CommonUtil.isNull(boardCmmntyVO.getOpenYn(), "Y"));
        params.put("COMMNTY_GUBN", CommonUtil.isNull(boardCmmntyVO.getCmmntyGubn(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardCmmntyService.updateProcess(params);
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
    @Operation(summary = "게시물 삭제", description = "커뮤니티 게시물을 삭제합니다.")
    @PostMapping("/delete")
    public JSONObject delete(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCmmntyVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("UPD_ID", userId);
        params.put("BOARD_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardMngSeq(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardCmmntyService.deleteProcess(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "게시물이 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "게시물 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 추천 등록
     */
    @Operation(summary = "추천 등록", description = "게시물을 추천합니다.")
    @PostMapping("/recommend")
    public JSONObject recommend(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCmmntyVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("REG_ID", userId);
        params.put("BOARD_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardSeq(), ""));
        params.put("ISTYPE", "R");
        params.put("VOTING", "Y");

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            String votingYCnt = boardCmmntyService.insertRecommnd(params);
            jsonObject.put("votingYCnt", votingYCnt);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "추천에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 댓글 목록 조회
     */
    @Operation(summary = "댓글 목록 조회", description = "게시물의 댓글 목록을 조회합니다.")
    @GetMapping("/getComments")
    public JSONObject getComments(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardSeq(), ""));

        List<HashMap<String, Object>> comments = boardCmmntyService.getCommentList(params);
        int commentCount = boardCmmntyService.getCommentCount(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("comments", comments);
        jsonObject.put("commentCount", commentCount);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 댓글 등록
     */
    @Operation(summary = "댓글 등록", description = "게시물에 댓글을 등록합니다.")
    @PostMapping("/insertComment")
    public JSONObject insertComment(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO,
            @RequestParam(value = "commentContent", required = false) String commentContent) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCmmntyVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("REG_ID", userId);
        params.put("BOARD_SEQ", CommonUtil.isNull(boardCmmntyVO.getBoardSeq(), ""));
        params.put("CONTENT", CommonUtil.isNull(commentContent, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardCmmntyService.insertComment(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "댓글이 등록되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "댓글 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 댓글 삭제
     */
    @Operation(summary = "댓글 삭제", description = "게시물의 댓글을 삭제합니다.")
    @PostMapping("/deleteComment")
    public JSONObject deleteComment(
            @ModelAttribute("BoardCmmntyVO") BoardCmmntyVO boardCmmntyVO,
            @RequestParam(value = "commentSeq", required = false) String commentSeq) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        String userId = CommonUtil.isNull(boardCmmntyVO.getUserId(), "");
        params.put("USER_ID", userId);
        params.put("UPD_ID", userId);
        params.put("COMMENT_SEQ", CommonUtil.isNull(commentSeq, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();
        try {
            boardCmmntyService.deleteComment(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "댓글이 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "댓글 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }
}
