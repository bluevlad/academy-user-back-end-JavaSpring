package com.academy.pay;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.pay.service.PayService;
import com.academy.pay.service.PayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * PayApi.java
 * 결제 관련 REST API Controller
 */
@Tag(name = "Pay", description = "결제 관리 API")
@RestController
@RequestMapping("/api/pay")
public class PayApi extends CORSFilter {

    private final PayService payService;

    @Autowired
    public PayApi(PayService payService) {
        this.payService = payService;
    }

    /**
     * 전체 주문 결제 정보 조회 (온라인 + 오프라인 + 모의고사)
     */
    @Operation(summary = "전체 주문 결제 목록 조회", description = "온라인, 오프라인, 모의고사 전체 주문 결제 목록을 조회합니다.")
    @GetMapping("/getOrderAllList")
    public JSONObject getOrderAllList(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("searchSDate", CommonUtil.isNull(payVO.getSearchSDate(), ""));
        params.put("searchEDate", CommonUtil.isNull(payVO.getSearchEDate(), ""));

        List<HashMap<String, Object>> orderList = payService.payOrderAlllist(params);
        int totalCount = payService.orderAlllistCount(params);

        jsonObject.put("orderList", orderList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 주문 결제 정보 조회
     */
    @Operation(summary = "온라인 주문 결제 목록 조회", description = "온라인 주문 결제 목록을 조회합니다.")
    @GetMapping("/getOrderOnList")
    public JSONObject getOrderOnList(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("searchSDate", CommonUtil.isNull(payVO.getSearchSDate(), ""));
        params.put("searchEDate", CommonUtil.isNull(payVO.getSearchEDate(), ""));
        params.put("OFType", CommonUtil.isNull(payVO.getOfType(), ""));

        List<HashMap<String, Object>> orderList = payService.payOrderOnlist(params);

        jsonObject.put("orderList", orderList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 주문 결제 정보 조회
     */
    @Operation(summary = "오프라인 주문 결제 목록 조회", description = "오프라인(학원실강) 주문 결제 목록을 조회합니다.")
    @GetMapping("/getOrderOffList")
    public JSONObject getOrderOffList(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("searchSDate", CommonUtil.isNull(payVO.getSearchSDate(), ""));
        params.put("searchEDate", CommonUtil.isNull(payVO.getSearchEDate(), ""));

        List<HashMap<String, Object>> orderList = payService.payOrderOfflist(params);

        jsonObject.put("orderList", orderList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 주문 결제 정보 조회
     */
    @Operation(summary = "모의고사 주문 결제 목록 조회", description = "모의고사 주문 결제 목록을 조회합니다.")
    @GetMapping("/getTOrderList")
    public JSONObject getTOrderList(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("searchSDate", CommonUtil.isNull(payVO.getSearchSDate(), ""));
        params.put("searchEDate", CommonUtil.isNull(payVO.getSearchEDate(), ""));

        List<HashMap<String, Object>> orderList = payService.payTOrderList(params);

        jsonObject.put("orderList", orderList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 주문 상세 조회
     */
    @Operation(summary = "온라인 주문 상세 조회", description = "온라인 주문 상세 정보를 조회합니다.")
    @GetMapping("/getOrderOnDetail")
    public JSONObject getOrderOnDetail(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("ORDERNO", payVO.getOrderNo());

        List<HashMap<String, Object>> lectureList = payService.orderDetailLectureOnlist(params);
        List<HashMap<String, Object>> bookList = payService.orderDetailBookOnlist(params);
        HashMap<String, Object> orderInfo = payService.getOrdersOn(params);
        HashMap<String, Object> approvalInfo = payService.getApprovalsOn(params);
        HashMap<String, Object> deliverInfo = payService.getDelivers(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("orderInfo", orderInfo);
        jsonObject.put("approvalInfo", approvalInfo);
        jsonObject.put("deliverInfo", deliverInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 주문 상세 조회
     */
    @Operation(summary = "오프라인 주문 상세 조회", description = "오프라인(학원실강) 주문 상세 정보를 조회합니다.")
    @GetMapping("/getOrderOffDetail")
    public JSONObject getOrderOffDetail(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("ORDERNO", payVO.getOrderNo());

        List<HashMap<String, Object>> lectureList = payService.orderDetailLectureOfflist(params);
        List<HashMap<String, Object>> boxList = payService.orderDetailBoxlist(params);
        List<HashMap<String, Object>> roomList = payService.orderDetailRoomlist(params);
        HashMap<String, Object> orderInfo = payService.getOrdersOff(params);
        HashMap<String, Object> approvalInfo = payService.getApprovalsOff(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("boxList", boxList);
        jsonObject.put("roomList", roomList);
        jsonObject.put("orderInfo", orderInfo);
        jsonObject.put("approvalInfo", approvalInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 주문 상세 조회
     */
    @Operation(summary = "모의고사 주문 상세 조회", description = "모의고사 주문 상세 정보를 조회합니다.")
    @GetMapping("/getTOrderDetail")
    public JSONObject getTOrderDetail(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("ORDERNO", payVO.getOrderNo());

        List<HashMap<String, Object>> detailList = payService.tOrderDetailList(params);
        HashMap<String, Object> orderInfo = payService.getTOrders(params);
        HashMap<String, Object> approvalInfo = payService.getTApprovals(params);

        jsonObject.put("detailList", detailList);
        jsonObject.put("orderInfo", orderInfo);
        jsonObject.put("approvalInfo", approvalInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 도서 배송 목록 조회
     */
    @Operation(summary = "도서 배송 목록 조회", description = "도서 배송 목록을 조회합니다.")
    @GetMapping("/getBookDeliveryList")
    public JSONObject getBookDeliveryList(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        List<HashMap<String, Object>> deliveryList = payService.bookDeliveryOnlist(params);

        jsonObject.put("deliveryList", deliveryList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 배송 정보 수정
     */
    @Operation(summary = "배송 정보 수정", description = "배송 정보를 수정합니다.")
    @PostMapping("/updateDeliverInfo")
    public JSONObject updateDeliverInfo(
            @RequestBody HashMap<String, String> params) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("userId");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        try {
            payService.updateDeliverInfo(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "배송 정보가 수정되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "배송 정보 수정에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 사용자 취소 가능 여부 조회
     */
    @Operation(summary = "주문 취소 가능 여부 조회", description = "해당 주문의 취소 가능 여부를 조회합니다.")
    @GetMapping("/getUserCancelYN")
    public JSONObject getUserCancelYN(
            @ModelAttribute("PayVO") PayVO payVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = payVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);
        params.put("ORDERNO", payVO.getOrderNo());

        String cancelYN = payService.getUserCancelYN(params);

        jsonObject.put("cancelYN", cancelYN);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

}
