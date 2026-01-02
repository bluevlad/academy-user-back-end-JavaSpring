package com.academy.event;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.event.service.EventService;
import com.academy.event.service.EventVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final EventService eventService;

    @Autowired
    public EventApi(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * 이벤트 목록 조회
     */
    @Operation(summary = "이벤트 목록 조회", description = "이벤트 목록을 조회합니다. (L:온라인, O:오프라인, F:무료, A:전체)")
    @GetMapping("/getEventList")
    public JSONObject getEventList(
            @ModelAttribute("EventVO") EventVO eventVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        String eventType = CommonUtil.isNull(eventVO.getEventType(), "A");
        params.put("EVENT_TYPE", eventType);
        params.put("SEARCHKIND", CommonUtil.isNull(eventVO.getSearchKind(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(eventVO.getSearchText(), ""));
        params.put("SEARCHINGLIST", CommonUtil.isNull(eventVO.getSearchingList(), ""));

        int currentPage = eventVO.getCurrentPage() > 0 ? eventVO.getCurrentPage() : 1;
        int recordCountPerPage = eventVO.getPageRow() > 0 ? eventVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        int totalCount = eventService.eventListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        List<HashMap<String, Object>> eventList = eventService.eventList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("eventList", eventList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 상세 조회
     */
    @Operation(summary = "이벤트 상세 조회", description = "이벤트 상세 정보를 조회합니다.")
    @GetMapping("/getEventDetail")
    public JSONObject getEventDetail(
            @ModelAttribute("EventVO") EventVO eventVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        String eventNo = CommonUtil.isNull(eventVO.getEventNo(), "");
        params.put("searchEventNo", eventNo);
        params.put("EVENT_NO", eventNo);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 조회수 증가 (INCTYPE이 'Y'가 아닌 경우에만)
        String incType = CommonUtil.isNull(eventVO.getIncType(), "Y");
        if ("Y".equals(incType)) {
            eventService.updateEventHits(params);
        }

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
            @ModelAttribute("EventVO") EventVO eventVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("EVENT_NO", CommonUtil.isNull(eventVO.getEventNo(), ""));

        List<HashMap<String, Object>> lectureList = eventService.eventLectureList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
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
            @ModelAttribute("EventVO") EventVO eventVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("EVENT_NO", CommonUtil.isNull(eventVO.getEventNo(), ""));
        params.put("OPTION1_SEQ", CommonUtil.isNull(eventVO.getOption1Seq(), ""));

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
            @ModelAttribute("EventVO") EventVO eventVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("EVENT_NO", CommonUtil.isNull(eventVO.getEventNo(), ""));
        params.put("COMMENT_TEXT", CommonUtil.isNull(eventVO.getCommentText(), ""));

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
            @ModelAttribute("EventVO") EventVO eventVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("SEQ", CommonUtil.isNull(eventVO.getSeq(), ""));

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
            params.put("USER_NM", "");
            params.put("REG_ID", "");
            params.put("UPD_ID", "");
            params.put("ISLOGIN", "N");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
                params.put("USER_NM", loginInfo.get("USER_NM"));
                params.put("REG_ID", loginInfo.get("USER_ID"));
                params.put("UPD_ID", loginInfo.get("USER_ID"));
                params.put("ISLOGIN", "Y");
            } else {
                params.put("USER_ID", "");
                params.put("USER_NM", "");
                params.put("REG_ID", "");
                params.put("UPD_ID", "");
                params.put("ISLOGIN", "N");
            }
        }
    }

}
