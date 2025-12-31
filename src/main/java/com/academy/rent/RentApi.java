package com.academy.rent;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.rent.service.RentService;
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
 * RentApi.java
 * 사물함/독서실 대여 관련 REST API Controller
 */
@Tag(name = "Rent", description = "사물함/독서실 대여 관리 API")
@RestController
@RequestMapping("/api/rent")
public class RentApi extends CORSFilter {

    private final RentService rentService;

    @Autowired
    public RentApi(RentService rentService) {
        this.rentService = rentService;
    }

    /**
     * 사물함 리스트 조회
     */
    @Operation(summary = "사물함 리스트 조회", description = "사물함 목록을 조회합니다.")
    @GetMapping("/getBoxList")
    public JSONObject getBoxList(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        List<HashMap<String, Object>> boxList = rentService.boxList(params);
        List<HashMap<String, Object>> userBoxInfo = rentService.boxNumChk(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boxList", boxList);
        jsonObject.put("userBoxInfo", userBoxInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 사물함 상세 번호 리스트 조회
     */
    @Operation(summary = "사물함 상세 번호 리스트 조회", description = "특정 사물함의 상세 번호 목록을 조회합니다.")
    @GetMapping("/getBoxNumList")
    public JSONObject getBoxNumList(
            @Parameter(description = "사물함 코드", required = true) @RequestParam String boxCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("BOX_CD", boxCd);

        List<HashMap<String, Object>> boxNumList = rentService.boxNumList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boxNumList", boxNumList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 독서실 리스트 조회
     */
    @Operation(summary = "독서실 리스트 조회", description = "독서실 목록을 조회합니다.")
    @GetMapping("/getRoomList")
    public JSONObject getRoomList(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        List<HashMap<String, Object>> roomList = rentService.roomList(params);
        List<HashMap<String, Object>> userRoomInfo = rentService.roomNumChk(params);
        List<HashMap<String, Object>> offLearnedCheck = rentService.offLearnedChkForRoom(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("roomList", roomList);
        jsonObject.put("userRoomInfo", userRoomInfo);
        jsonObject.put("offLearnedCheck", offLearnedCheck);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 독서실 상세 번호 리스트 조회
     */
    @Operation(summary = "독서실 상세 번호 리스트 조회", description = "특정 독서실의 상세 번호 목록을 조회합니다.")
    @GetMapping("/getRoomNumList")
    public JSONObject getRoomNumList(
            @Parameter(description = "독서실 코드", required = true) @RequestParam String roomCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("ROOM_CD", roomCd);

        List<HashMap<String, Object>> roomNumList = rentService.roomNumList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("roomNumList", roomNumList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 사물함/독서실 신청 (장바구니 담기)
     */
    @Operation(summary = "사물함/독서실 신청", description = "사물함 또는 독서실을 장바구니에 담습니다.")
    @PostMapping("/applyRent")
    public JSONObject applyRent(
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

        String cmd = params.get("CMD");
        if (cmd == null || cmd.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "잘못된 접근입니다.");
            return new JSONObject(jsonObject);
        }

        try {
            if ("box".equals(cmd)) {
                params.put("KIND_TYPE", "S");
            } else if ("room".equals(cmd)) {
                params.put("KIND_TYPE", "T");
            }

            rentService.aplRentCartInsert(params);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "장바구니에 담았습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "신청에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 사물함 결제 정보 조회
     */
    @Operation(summary = "사물함 결제 정보 조회", description = "사물함 결제 정보를 조회합니다.")
    @GetMapping("/getBoxPaymentInfo")
    public JSONObject getBoxPaymentInfo(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        String userId = params.get("USER_ID");
        HashMap<String, Object> jsonObject = new HashMap<>();

        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        List<HashMap<String, Object>> paymentInfo = rentService.boxPayViewSelect(params);

        jsonObject.put("paymentInfo", paymentInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 독서실 결제 정보 조회
     */
    @Operation(summary = "독서실 결제 정보 조회", description = "독서실 결제 정보를 조회합니다.")
    @GetMapping("/getRoomPaymentInfo")
    public JSONObject getRoomPaymentInfo(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        String userId = params.get("USER_ID");
        HashMap<String, Object> jsonObject = new HashMap<>();

        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        List<HashMap<String, Object>> paymentInfo = rentService.roomPayViewSelect(params);

        jsonObject.put("paymentInfo", paymentInfo);
        jsonObject.put("retMsg", "OK");

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

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "F"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "001"));
    }

}
