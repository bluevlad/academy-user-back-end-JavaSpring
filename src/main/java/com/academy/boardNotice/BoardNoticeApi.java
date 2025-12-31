package com.academy.boardNotice;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.boardNotice.service.BoardNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * BoardNoticeApi.java
 * 공지사항 관련 REST API Controller
 */
@Tag(name = "BoardNotice", description = "공지사항 관리 API")
@RestController
@RequestMapping("/api/boardNotice")
public class BoardNoticeApi extends CORSFilter {

    private final BoardNoticeService boardNoticeService;

    @Autowired
    public BoardNoticeApi(BoardNoticeService boardNoticeService) {
        this.boardNoticeService = boardNoticeService;
    }

    /**
     * 공지사항 목록 조회
     */
    @Operation(summary = "공지사항 목록 조회", description = "공지사항 목록을 조회합니다.")
    @GetMapping("/getNoticeList")
    public JSONObject getNoticeList(
            @Parameter(description = "게시판 관리 SEQ") @RequestParam(required = false, defaultValue = "NOTICE_000") String boardMngSeq,
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String topMenu,
            @Parameter(description = "탑메뉴타입") @RequestParam(required = false) String topMenuType,
            @Parameter(description = "검색 유형") @RequestParam(required = false) String searchKind,
            @Parameter(description = "검색어") @RequestParam(required = false) String searchText,
            @Parameter(description = "디바이스 타입") @RequestParam(required = false, defaultValue = "PC") String diviceType,
            @Parameter(description = "페이지 번호") @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @Parameter(description = "페이지 크기") @RequestParam(required = false, defaultValue = "10") int pageUnit,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", boardMngSeq);
        params.put("topMenu", CommonUtil.isNull(topMenu, ""));
        params.put("topMenuType", CommonUtil.isNull(topMenuType, ""));
        params.put("SEARCHKIND", CommonUtil.isNull(searchKind, ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(searchText, ""));
        params.put("DIVICE_TYPE", diviceType);

        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageUnit);
        paginationInfo.setPageSize(10);

        int totalCount = boardNoticeService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> noticeList = boardNoticeService.boardList(params);

        jsonObject.put("noticeList", noticeList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("pageIndex", pageIndex);
        jsonObject.put("pageUnit", pageUnit);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 공지사항 상세 조회
     */
    @Operation(summary = "공지사항 상세 조회", description = "공지사항 상세 정보를 조회합니다.")
    @GetMapping("/getNoticeDetail")
    public JSONObject getNoticeDetail(
            @Parameter(description = "게시판 관리 SEQ") @RequestParam(required = false, defaultValue = "NOTICE_000") String boardMngSeq,
            @Parameter(description = "게시글 SEQ", required = true) @RequestParam String boardSeq,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_MNG_SEQ", boardMngSeq);
        params.put("BOARD_SEQ", boardSeq);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 조회수 증가
        boardNoticeService.updateBoardHits(params);

        HashMap<String, Object> noticeDetail = boardNoticeService.boardView(params);

        if (noticeDetail != null) {
            jsonObject.put("noticeDetail", noticeDetail);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "공지사항을 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 메인 공지사항 목록 조회
     */
    @Operation(summary = "메인 공지사항 목록 조회", description = "메인 페이지용 공지사항 목록을 조회합니다.")
    @GetMapping("/getMainNoticeList")
    public JSONObject getMainNoticeList(
            @Parameter(description = "게시판 관리 SEQ") @RequestParam(required = false, defaultValue = "NOTICE_000") String boardMngSeq,
            @Parameter(description = "카테고리 코드") @RequestParam(required = false, defaultValue = "MAIN") String topMenu,
            @Parameter(description = "탑메뉴타입") @RequestParam(required = false, defaultValue = "O") String topMenuType,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_MNG_SEQ", boardMngSeq);
        params.put("topMenu", topMenu);
        params.put("topMenuType", topMenuType);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> mainNoticeList = boardNoticeService.getMainBoardList(params);

        jsonObject.put("mainNoticeList", mainNoticeList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * FAQ 목록 조회
     */
    @Operation(summary = "FAQ 목록 조회", description = "FAQ 목록을 조회합니다.")
    @GetMapping("/getFaqList")
    public JSONObject getFaqList(
            @Parameter(description = "게시판 관리 SEQ") @RequestParam(required = false, defaultValue = "FAQ_000") String boardMngSeq,
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String topMenu,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("BOARD_MNG_SEQ", boardMngSeq);
        params.put("topMenu", CommonUtil.isNull(topMenu, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> faqList = boardNoticeService.boardFAQList(params);

        jsonObject.put("faqList", faqList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

}
