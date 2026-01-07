package com.academy.interview;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.interview.service.InterviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * InterviewApi.java
 * 상담/문의 게시판 관련 REST API Controller
 */
@Tag(name = "Interview", description = "상담/문의 게시판 관리 API")
@RestController
@RequestMapping("/api/interview")
public class InterviewApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final InterviewService interviewService;

    @Autowired
    public InterviewApi(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    /**
     * 게시판 리스트 조회
     */
    @Operation(summary = "게시판 리스트 조회", description = "상담/문의 게시판 목록을 조회합니다.")
    @GetMapping("/getBoardList")
    public JSONObject getBoardList(
            @Parameter(description = "게시판 관리 SEQ", required = true) @RequestParam String boardMngSeq,
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String topMenu,
            @Parameter(description = "검색 종류") @RequestParam(required = false) String searchKind,
            @Parameter(description = "검색어") @RequestParam(required = false) String searchText,
            @Parameter(description = "현재 페이지") @RequestParam(defaultValue = "1") int currentPage,
            @Parameter(description = "페이지당 건수") @RequestParam(required = false) Integer pageRow) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", boardMngSeq);
        params.put("topMenu", CommonUtil.isNull(topMenu, "001"));
        params.put("SEARCHKIND", CommonUtil.isNull(searchKind, ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(searchText, ""));

        int recordCountPerPage = pageRow != null ? pageRow : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        int totalCount = interviewService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        List<HashMap<String, Object>> resultList = interviewService.boardList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시글 상세 조회
     */
    @Operation(summary = "게시글 상세 조회", description = "상담/문의 게시글 상세 정보를 조회합니다.")
    @GetMapping("/getBoardDetail")
    public JSONObject getBoardDetail(
            @Parameter(description = "게시판 관리 SEQ", required = true) @RequestParam String boardMngSeq,
            @Parameter(description = "게시글 SEQ", required = true) @RequestParam String boardSeq) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", boardMngSeq);
        params.put("BOARD_SEQ", boardSeq);

        HashMap<String, Object> boardDetail = interviewService.boardDetail(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardDetail", boardDetail);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시글 등록
     */
    @Operation(summary = "게시글 등록", description = "상담/문의 게시글을 등록합니다.")
    @PostMapping("/insertBoard")
    public JSONObject insertBoard(
            @RequestBody HashMap<String, String> params) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            params.put("REG_ID", userId);
            params.put("CREATENAME", CommonUtil.isNull(params.get("CREATENAME"), ""));
            params.put("OPEN_YN", CommonUtil.isNull(params.get("OPEN_YN"), "N"));
            params.put("NOTICE_TOP_YN", "N");

            interviewService.insertProcess(params);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "게시글이 등록되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "게시글 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 게시글 수정
     */
    @Operation(summary = "게시글 수정", description = "상담/문의 게시글을 수정합니다.")
    @PostMapping("/updateBoard")
    public JSONObject updateBoard(
            @RequestBody HashMap<String, String> params) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            params.put("REG_ID", userId);
            params.put("NOTICE_TOP_YN", CommonUtil.isNull(params.get("NOTICE_TOP_YN"), "N"));
            params.put("OPEN_YN", CommonUtil.isNull(params.get("OPEN_YN"), "N"));

            interviewService.updateProcess(params);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "게시글이 수정되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "게시글 수정에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 게시글 삭제
     */
    @Operation(summary = "게시글 삭제", description = "상담/문의 게시글을 삭제합니다.")
    @PostMapping("/deleteBoard")
    public JSONObject deleteBoard(
            @RequestBody HashMap<String, String> params) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            interviewService.deleteProcess(params);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "게시글이 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "게시글 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

}
