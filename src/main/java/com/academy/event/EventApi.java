package com.academy.event;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.event.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * EventApi.java
 * 이벤트 관련 REST API Controller
 */
@Tag(name = "Event", description = "이벤트 관리 API")
@RestController
@RequestMapping("/api/event")
public class EventApi extends CORSFilter {

    private final EventService eventService;

    @Autowired
    public EventApi(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * 이벤트 목록 조회
     */
    @Operation(summary = "이벤트 목록 조회", description = "이벤트 목록을 조회합니다.")
    @GetMapping("/getEventList")
    public JSONObject getEventList(
            @Parameter(description = "이벤트 타입 (L:온라인, O:오프라인, F:무료, A:전체)") @RequestParam(required = false, defaultValue = "A") String eventType,
            @Parameter(description = "검색 유형") @RequestParam(required = false) String searchKind,
            @Parameter(description = "검색어") @RequestParam(required = false) String searchText,
            @Parameter(description = "진행중만 조회") @RequestParam(required = false) String searchingList,
            @Parameter(description = "페이지 번호") @RequestParam(required = false, defaultValue = "1") int pageIndex,
            @Parameter(description = "페이지 크기") @RequestParam(required = false, defaultValue = "10") int pageUnit,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("EVENT_TYPE", eventType);
        params.put("SEARCHKIND", CommonUtil.isNull(searchKind, ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(searchText, ""));
        params.put("SEARCHINGLIST", CommonUtil.isNull(searchingList, ""));

        // 페이징 처리
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(pageIndex);
        paginationInfo.setRecordCountPerPage(pageUnit);
        paginationInfo.setPageSize(10);

        int totalCount = eventService.eventListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> eventList = eventService.eventList(params);

        jsonObject.put("eventList", eventList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("pageIndex", pageIndex);
        jsonObject.put("pageUnit", pageUnit);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 상세 조회
     */
    @Operation(summary = "이벤트 상세 조회", description = "이벤트 상세 정보를 조회합니다.")
    @GetMapping("/getEventDetail")
    public JSONObject getEventDetail(
            @Parameter(description = "이벤트 번호", required = true) @RequestParam String eventNo,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("searchEventNo", eventNo);
        params.put("EVENT_NO", eventNo);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 조회수 증가
        eventService.updateEventHits(params);

        HashMap<String, Object> eventDetail = eventService.eventDetail(params);
        List<HashMap<String, Object>> fileList = eventService.eventAttachFileList(params);
        List<HashMap<String, Object>> option1List = eventService.eventDetailOption1(params);
        List<HashMap<String, Object>> option2List = eventService.eventDetailOption2(params);

        if (eventDetail != null) {
            jsonObject.put("eventDetail", eventDetail);
            jsonObject.put("fileList", fileList);
            jsonObject.put("option1List", option1List);
            jsonObject.put("option2List", option2List);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "이벤트 정보를 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 강좌 목록 조회
     */
    @Operation(summary = "이벤트 강좌 목록 조회", description = "이벤트 관련 강좌 목록을 조회합니다.")
    @GetMapping("/getEventLectureList")
    public JSONObject getEventLectureList(
            @Parameter(description = "이벤트 번호", required = true) @RequestParam String eventNo,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("EVENT_NO", eventNo);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> lectureList = eventService.eventLectureList(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 참여
     */
    @Operation(summary = "이벤트 참여", description = "이벤트에 참여합니다.")
    @PostMapping("/joinEvent")
    public JSONObject joinEvent(
            @RequestBody HashMap<String, String> params,
            HttpServletRequest request) throws Exception {

        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            // 중복 체크
            String dupCheck = eventService.dupCheck(params);
            if (dupCheck != null && Integer.parseInt(dupCheck) > 0) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "이미 참여한 이벤트입니다.");
                return new JSONObject(jsonObject);
            }

            eventService.insertEventResult(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "이벤트에 참여되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "이벤트 참여에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 댓글 등록
     */
    @Operation(summary = "이벤트 댓글 등록", description = "이벤트에 댓글을 등록합니다.")
    @PostMapping("/insertEventComment")
    public JSONObject insertEventComment(
            @RequestBody HashMap<String, String> params,
            HttpServletRequest request) throws Exception {

        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            eventService.insertEventComment(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "댓글이 등록되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "댓글 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 댓글 삭제
     */
    @Operation(summary = "이벤트 댓글 삭제", description = "이벤트 댓글을 삭제합니다.")
    @PostMapping("/deleteEventComment")
    public JSONObject deleteEventComment(
            @RequestBody HashMap<String, String> params,
            HttpServletRequest request) throws Exception {

        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            eventService.deleteEventComment(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "댓글이 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "댓글 삭제에 실패했습니다.");
        }

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
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
            } else {
                params.put("USER_ID", "");
            }
        }
    }

}
