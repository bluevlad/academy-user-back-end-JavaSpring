package com.academy.search;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.search.service.SearchService;
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
 * SearchApi.java
 * 검색 관련 REST API Controller
 */
@Tag(name = "Search", description = "통합 검색 API")
@RestController
@RequestMapping("/api/search")
public class SearchApi extends CORSFilter {

    private final SearchService searchService;

    @Autowired
    public SearchApi(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 통합 검색
     */
    @Operation(summary = "통합 검색", description = "강사, 강의, 교재, 게시판, 이벤트 등 통합 검색을 수행합니다.")
    @GetMapping("/searchAll")
    public JSONObject searchAll(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            @Parameter(description = "탑메뉴") @RequestParam(required = false) String topMenu,
            @Parameter(description = "탑메뉴타입 (O:온라인, F:오프라인)") @RequestParam(required = false) String topMenuType,
            @Parameter(description = "게시판관리SEQ") @RequestParam(required = false) String boardMngSeq,
            @Parameter(description = "페이지 번호") @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @Parameter(description = "페이지 크기") @RequestParam(required = false, defaultValue = "10") int pageUnit,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);
        params.put("topMenu", CommonUtil.isNull(topMenu, ""));
        params.put("topMenuType", CommonUtil.isNull(topMenuType, ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardMngSeq, ""));

        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageUnit);
        paginationInfo.setPageSize(10);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 검색 키워드 저장
        try {
            searchService.insertSearchKeyword(params);
        } catch (Exception e) {
            // 키워드 저장 실패해도 검색은 진행
        }

        // 강사 검색
        List<HashMap<String, Object>> teacherList = searchService.searchTeacherInfo(params);

        // 동영상 강의 검색
        List<HashMap<String, Object>> movieList = searchService.searchMovie(params);

        // 동영상 강의 교재 검색
        List<HashMap<String, Object>> movieBookList = searchService.searchMovieBookList(params);

        // 교재 검색
        List<HashMap<String, Object>> bookList = searchService.searchBook(params);

        // 이벤트 검색
        List<HashMap<String, Object>> eventList = searchService.searchEvent(params);

        jsonObject.put("teacherList", teacherList);
        jsonObject.put("movieList", movieList);
        jsonObject.put("movieBookList", movieBookList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("eventList", eventList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강사 검색
     */
    @Operation(summary = "강사 검색", description = "강사를 검색합니다.")
    @GetMapping("/searchTeacher")
    public JSONObject searchTeacher(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            @Parameter(description = "탑메뉴타입 (O:온라인, F:오프라인)") @RequestParam(required = false) String topMenuType,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);
        params.put("topMenuType", CommonUtil.isNull(topMenuType, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> teacherList = searchService.searchTeacherInfo(params);

        jsonObject.put("teacherList", teacherList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 동영상 강의 검색
     */
    @Operation(summary = "동영상 강의 검색", description = "동영상 강의를 검색합니다.")
    @GetMapping("/searchMovie")
    public JSONObject searchMovie(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> movieList = searchService.searchMovie(params);
        List<HashMap<String, Object>> movieBookList = searchService.searchMovieBookList(params);

        jsonObject.put("movieList", movieList);
        jsonObject.put("movieBookList", movieBookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 패스 검색
     */
    @Operation(summary = "패스 검색", description = "패스를 검색합니다.")
    @GetMapping("/searchPass")
    public JSONObject searchPass(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> passList = searchService.searchPass(params);

        jsonObject.put("passList", passList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 강의 검색
     */
    @Operation(summary = "오프라인 강의 검색", description = "오프라인 강의를 검색합니다.")
    @GetMapping("/searchLectureOff")
    public JSONObject searchLectureOff(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> lectureOffList = searchService.searchLectureOffList(params);
        List<HashMap<String, Object>> lectureOffBookList = searchService.searchLectureOffBookList(params);

        jsonObject.put("lectureOffList", lectureOffList);
        jsonObject.put("lectureOffBookList", lectureOffBookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교재 검색
     */
    @Operation(summary = "교재 검색", description = "교재를 검색합니다.")
    @GetMapping("/searchBook")
    public JSONObject searchBook(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            @Parameter(description = "탑메뉴") @RequestParam(required = false) String topMenu,
            @Parameter(description = "페이지 번호") @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @Parameter(description = "페이지 크기") @RequestParam(required = false, defaultValue = "10") int pageUnit,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);
        params.put("topMenu", CommonUtil.isNull(topMenu, ""));

        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageUnit);
        paginationInfo.setPageSize(10);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> bookList = searchService.searchBook(params);
        List<HashMap<String, Object>> bookLectureList = searchService.searchBookLectureList(params);

        jsonObject.put("bookList", bookList);
        jsonObject.put("bookLectureList", bookLectureList);
        jsonObject.put("pageIndex", pageIndex);
        jsonObject.put("pageUnit", pageUnit);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시판 검색
     */
    @Operation(summary = "게시판 검색", description = "게시판을 검색합니다.")
    @GetMapping("/searchBoard")
    public JSONObject searchBoard(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            @Parameter(description = "탑메뉴") @RequestParam(required = false) String topMenu,
            @Parameter(description = "게시판관리SEQ", required = true) @RequestParam String boardMngSeq,
            @Parameter(description = "페이지 번호") @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @Parameter(description = "페이지 크기") @RequestParam(required = false, defaultValue = "10") int pageUnit,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);
        params.put("topMenu", CommonUtil.isNull(topMenu, ""));
        params.put("BOARD_MNG_SEQ", boardMngSeq);

        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageUnit);
        paginationInfo.setPageSize(10);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> boardList = searchService.searchBoard(params);

        jsonObject.put("boardList", boardList);
        jsonObject.put("pageIndex", pageIndex);
        jsonObject.put("pageUnit", pageUnit);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 후기 검색
     */
    @Operation(summary = "강의 후기 검색", description = "강의 후기를 검색합니다.")
    @GetMapping("/searchLectureReply")
    public JSONObject searchLectureReply(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            @Parameter(description = "탑메뉴") @RequestParam(required = false) String topMenu,
            @Parameter(description = "페이지 번호") @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @Parameter(description = "페이지 크기") @RequestParam(required = false, defaultValue = "10") int pageUnit,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);
        params.put("topMenu", CommonUtil.isNull(topMenu, ""));

        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageUnit);
        paginationInfo.setPageSize(10);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> lectureReplyList = searchService.searchLectureReply(params);

        jsonObject.put("lectureReplyList", lectureReplyList);
        jsonObject.put("pageIndex", pageIndex);
        jsonObject.put("pageUnit", pageUnit);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 검색
     */
    @Operation(summary = "이벤트 검색", description = "이벤트를 검색합니다.")
    @GetMapping("/searchEvent")
    public JSONObject searchEvent(
            @Parameter(description = "검색어", required = true) @RequestParam String searchText,
            @Parameter(description = "페이지 번호") @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @Parameter(description = "페이지 크기") @RequestParam(required = false, defaultValue = "10") int pageUnit,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("unitedSarchText", searchText);

        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageUnit);
        paginationInfo.setPageSize(10);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> eventList = searchService.searchEvent(params);

        jsonObject.put("eventList", eventList);
        jsonObject.put("pageIndex", pageIndex);
        jsonObject.put("pageUnit", pageUnit);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

}
