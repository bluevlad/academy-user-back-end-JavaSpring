package com.academy.board.faq;

import com.academy.board.faq.service.BoardFaqService;
import com.academy.board.faq.service.BoardFaqVO;
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
 * BoardFaqApi.java
 * FAQ 게시판 REST API Controller
 */
@Tag(name = "BoardFaq", description = "FAQ 게시판 API")
@RestController
@RequestMapping("/api/board/faq")
public class BoardFaqApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardFaqService boardFaqService;

    @Autowired
    public BoardFaqApi(BoardFaqService boardFaqService) {
        this.boardFaqService = boardFaqService;
    }

    /**
     * FAQ 게시판 목록 조회
     */
    @Operation(summary = "FAQ 게시판 목록 조회", description = "FAQ 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("BoardFaqVO") BoardFaqVO boardFaqVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardFaqVO.getBoardMngSeq(), ""));
        params.put("BOARDTYPE", CommonUtil.isNull(boardFaqVO.getBoardType(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(boardFaqVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(boardFaqVO.getSearchKind(), ""));
        params.put("CATEGORY_CODE", CommonUtil.isNull(boardFaqVO.getCategoryCode(), ""));

        int currentPage = boardFaqVO.getCurrentPage() > 0 ? boardFaqVO.getCurrentPage() : 1;
        int recordCountPerPage = boardFaqVO.getPageRow() > 0 ? boardFaqVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardFaqService.boardFaqListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardFaqService.boardFaqList(params);
        HashMap<String, Object> boardInfo = boardFaqService.getBoardInfo(params);
        List<HashMap<String, Object>> categoryList = boardFaqService.getFaqCategoryList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardInfo", boardInfo);
        jsonObject.put("list", resultList);
        jsonObject.put("categoryList", categoryList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * FAQ 상세 조회
     */
    @Operation(summary = "FAQ 상세 조회", description = "FAQ 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("BoardFaqVO") BoardFaqVO boardFaqVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_SEQ", CommonUtil.isNull(boardFaqVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardFaqVO.getBoardMngSeq(), ""));

        HashMap<String, Object> detailView = boardFaqService.boardFaqView(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("detailView", detailView);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * FAQ 카테고리 목록 조회
     */
    @Operation(summary = "FAQ 카테고리 목록 조회", description = "FAQ 카테고리 목록을 조회합니다.")
    @GetMapping("/getCategoryList")
    public JSONObject getCategoryList(
            @ModelAttribute("BoardFaqVO") BoardFaqVO boardFaqVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardFaqVO.getBoardMngSeq(), ""));

        List<HashMap<String, Object>> categoryList = boardFaqService.getFaqCategoryList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("categoryList", categoryList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }
}
