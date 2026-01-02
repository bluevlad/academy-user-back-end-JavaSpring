package com.academy.event.off;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.event.off.service.EventOffService;
import com.academy.event.off.service.EventOffVO;
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
 * EventOffApi.java
 * 오프라인 이벤트(설명회) REST API Controller
 */
@Tag(name = "EventOff", description = "오프라인 이벤트(설명회) API")
@RestController
@RequestMapping("/api/event/off")
public class EventOffApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final EventOffService eventOffService;

    @Autowired
    public EventOffApi(EventOffService eventOffService) {
        this.eventOffService = eventOffService;
    }

    /**
     * 오프라인 이벤트 목록 조회
     */
    @Operation(summary = "오프라인 이벤트 목록 조회", description = "오프라인 이벤트(설명회) 목록을 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("EventOffVO") EventOffVO eventOffVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("SEARCHKIND", CommonUtil.isNull(eventOffVO.getSearchKind(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(eventOffVO.getSearchText(), ""));
        params.put("SEARCHINGLIST", CommonUtil.isNull(eventOffVO.getSearchingList(), ""));

        int currentPage = eventOffVO.getCurrentPage() > 0 ? eventOffVO.getCurrentPage() : 1;
        int recordCountPerPage = eventOffVO.getPageRow() > 0 ? eventOffVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        int totalCount = eventOffService.offEventListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("firstIndex", paginationInfo.getFirstRecordIndex());
        params.put("recordCountPerPage", recordCountPerPage);

        List<HashMap<String, Object>> eventList = eventOffService.offEventList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("eventList", eventList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 이벤트 상세 조회
     */
    @Operation(summary = "오프라인 이벤트 상세 조회", description = "오프라인 이벤트(설명회) 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("EventOffVO") EventOffVO eventOffVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        String eventNo = CommonUtil.isNull(eventOffVO.getEventNo(), "");
        params.put("EVENT_NO", eventNo);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 조회수 증가 (INCTYPE이 'Y'인 경우에만)
        String incType = CommonUtil.isNull(eventOffVO.getIncType(), "Y");
        if ("Y".equals(incType)) {
            eventOffService.updateOffEventHits(params);
        }

        HashMap<String, Object> eventDetail = eventOffService.offEventDetail(params);
        List<HashMap<String, Object>> fileList = eventOffService.offEventAttachFileList(params);
        List<HashMap<String, Object>> option1List = eventOffService.offEventDetailOption1(params);

        // 사용자 참여 정보 조회
        HashMap<String, Object> userResult = null;
        if ("Y".equals(params.get("ISLOGIN"))) {
            userResult = eventOffService.getOffEventResult(params);
        }

        if (eventDetail != null) {
            jsonObject.put("eventDetail", eventDetail);
            jsonObject.put("fileList", fileList);
            jsonObject.put("option1List", option1List);
            jsonObject.put("userResult", userResult);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "이벤트 정보를 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 이벤트 참여 신청
     */
    @Operation(summary = "오프라인 이벤트 참여 신청", description = "오프라인 이벤트(설명회)에 참여 신청합니다.")
    @PostMapping("/join")
    public JSONObject join(
            @ModelAttribute("EventOffVO") EventOffVO eventOffVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("EVENT_NO", CommonUtil.isNull(eventOffVO.getEventNo(), ""));
        params.put("OPTION1_SEQ", CommonUtil.isNull(eventOffVO.getOption1Seq(), ""));
        params.put("USER_NAME", CommonUtil.isNull(eventOffVO.getUserName(), ""));
        params.put("PHONE_NO", CommonUtil.isNull(eventOffVO.getPhoneNo(), ""));
        params.put("EMAIL", CommonUtil.isNull(eventOffVO.getEmail(), ""));
        params.put("SMS_AGREE", CommonUtil.isNull(eventOffVO.getSmsAgree(), "N"));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = (String) params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            // 중복 참여 체크
            int dupCheck = eventOffService.dupOffEventCheck(params);
            if (dupCheck > 0) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "이미 참여 신청한 설명회입니다.");
                return new JSONObject(jsonObject);
            }

            eventOffService.insertOffEventResult(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "설명회 참여 신청이 완료되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "참여 신청에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 이벤트 참여 취소
     */
    @Operation(summary = "오프라인 이벤트 참여 취소", description = "오프라인 이벤트(설명회) 참여를 취소합니다.")
    @PostMapping("/cancel")
    public JSONObject cancel(
            @ModelAttribute("EventOffVO") EventOffVO eventOffVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("EVENT_NO", CommonUtil.isNull(eventOffVO.getEventNo(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = (String) params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            eventOffService.deleteOffEventResult(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "참여 취소가 완료되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "참여 취소에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 파라미터 설정
     */
    @SuppressWarnings("unchecked")
    private void setParam(HashMap<String, Object> params, HttpServletRequest request) {
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
