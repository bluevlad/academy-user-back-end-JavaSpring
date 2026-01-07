package com.academy.event.on;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.event.on.service.EventOnService;
import com.academy.event.on.service.EventOnVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * EventOnApi.java
 * 온라인 동영상 이벤트 REST API Controller
 */
@Tag(name = "EventOn", description = "온라인 동영상 이벤트 API")
@RestController
@RequestMapping("/api/event/on")
public class EventOnApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final EventOnService eventOnService;

    @Autowired
    public EventOnApi(EventOnService eventOnService) {
        this.eventOnService = eventOnService;
    }

    /**
     * 온라인 이벤트 목록 조회
     */
    @Operation(summary = "온라인 이벤트 목록 조회", description = "온라인 동영상 이벤트 목록을 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("EventOnVO") EventOnVO eventOnVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("SEARCHKIND", CommonUtil.isNull(eventOnVO.getSearchKind(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(eventOnVO.getSearchText(), ""));
        params.put("SEARCHINGLIST", CommonUtil.isNull(eventOnVO.getSearchingList(), ""));

        int currentPage = eventOnVO.getCurrentPage() > 0 ? eventOnVO.getCurrentPage() : 1;
        int recordCountPerPage = eventOnVO.getPageRow() > 0 ? eventOnVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        int totalCount = eventOnService.onEventListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("firstIndex", paginationInfo.getFirstRecordIndex());
        params.put("recordCountPerPage", recordCountPerPage);

        List<HashMap<String, Object>> eventList = eventOnService.onEventList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("eventList", eventList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 이벤트 상세 조회
     */
    @Operation(summary = "온라인 이벤트 상세 조회", description = "온라인 동영상 이벤트 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("EventOnVO") EventOnVO eventOnVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        String eventNo = CommonUtil.isNull(eventOnVO.getEventNo(), "");
        params.put("EVENT_NO", eventNo);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 조회수 증가 (INCTYPE이 'Y'인 경우에만)
        String incType = CommonUtil.isNull(eventOnVO.getIncType(), "Y");
        if ("Y".equals(incType)) {
            eventOnService.updateOnEventHits(params);
        }

        HashMap<String, Object> eventDetail = eventOnService.onEventDetail(params);
        List<HashMap<String, Object>> fileList = eventOnService.onEventAttachFileList(params);
        List<HashMap<String, Object>> lectureList = eventOnService.onEventLectureList(params);
        List<HashMap<String, Object>> commentList = eventOnService.onEventCommentList(params);

        if (eventDetail != null) {
            jsonObject.put("eventDetail", eventDetail);
            jsonObject.put("fileList", fileList);
            jsonObject.put("lectureList", lectureList);
            jsonObject.put("commentList", commentList);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "이벤트 정보를 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 이벤트 댓글 등록
     */
    @Operation(summary = "온라인 이벤트 댓글 등록", description = "온라인 동영상 이벤트에 댓글을 등록합니다.")
    @PostMapping("/insertComment")
    public JSONObject insertComment(
            @ModelAttribute("EventOnVO") EventOnVO eventOnVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("EVENT_NO", CommonUtil.isNull(eventOnVO.getEventNo(), ""));
        params.put("COMMENT_TEXT", CommonUtil.isNull(eventOnVO.getCommentText(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = eventOnVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        try {
            eventOnService.insertOnEventComment(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "댓글이 등록되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "댓글 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 이벤트 댓글 삭제
     */
    @Operation(summary = "온라인 이벤트 댓글 삭제", description = "온라인 동영상 이벤트 댓글을 삭제합니다.")
    @PostMapping("/deleteComment")
    public JSONObject deleteComment(
            @ModelAttribute("EventOnVO") EventOnVO eventOnVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("SEQ", CommonUtil.isNull(eventOnVO.getSeq(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = eventOnVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        try {
            eventOnService.deleteOnEventComment(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "댓글이 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "댓글 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 이벤트 출석 체크
     */
    @Operation(summary = "온라인 이벤트 출석 체크", description = "온라인 동영상 이벤트 출석을 체크합니다.")
    @PostMapping("/attendance")
    public JSONObject attendance(
            @ModelAttribute("EventOnVO") EventOnVO eventOnVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("EVENT_NO", CommonUtil.isNull(eventOnVO.getEventNo(), ""));
        params.put("ATTENDANCE_DATE", CommonUtil.isNull(eventOnVO.getAttendanceDate(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = eventOnVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        try {
            // 중복 출석 체크
            int dupCheck = eventOnService.checkOnEventAttendance(params);
            if (dupCheck > 0) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "이미 출석 체크를 하셨습니다.");
                return new JSONObject(jsonObject);
            }

            eventOnService.insertOnEventAttendance(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "출석 체크가 완료되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "출석 체크에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 이벤트 출석 목록 조회
     */
    @Operation(summary = "온라인 이벤트 출석 목록 조회", description = "온라인 동영상 이벤트 출석 목록을 조회합니다.")
    @GetMapping("/getAttendanceList")
    public JSONObject getAttendanceList(
            @ModelAttribute("EventOnVO") EventOnVO eventOnVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("EVENT_NO", CommonUtil.isNull(eventOnVO.getEventNo(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = eventOnVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        List<HashMap<String, Object>> attendanceList = eventOnService.onEventAttendanceList(params);

        jsonObject.put("attendanceList", attendanceList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

}
