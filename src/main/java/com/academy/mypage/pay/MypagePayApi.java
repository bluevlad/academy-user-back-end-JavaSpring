package com.academy.mypage.pay;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mypage.pay.service.MypagePayService;
import com.academy.mypage.pay.service.MypagePayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Tag(name = "Pay", description = "결제 API")
@RestController("mypagePayApi")
@RequestMapping("/api/mypage/pay")
public class MypagePayApi extends CORSFilter {

    private final MypagePayService payService;

    @Autowired
    public MypagePayApi(MypagePayService payService) {
        this.payService = payService;
    }

    @Operation(summary = "전체 주문 목록 조회", description = "전체 주문 내역을 조회합니다.")
    @GetMapping("/getOrderAllList")
    public JSONObject getOrderAllList(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int currentPage = payVO.getCurrentPage();
            int pageRow = payVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            HashMap<String, String> params = new HashMap<>();
            params.put("userId", payVO.getUserId());
            params.put("searchStartDate", payVO.getSearchStartDate());
            params.put("searchEndDate", payVO.getSearchEndDate());
            params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
            params.put("recordCountPerPage", String.valueOf(pageRow));

            List<HashMap<String, Object>> list = payService.payOrderAlllist(params);
            int totalCnt = payService.orderAlllistCount(params);

            paginationInfo.setTotalRecordCount(totalCnt);

            jsonObject.put("list", list);
            jsonObject.put("totalCnt", totalCnt);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 주문 목록 조회", description = "온라인 강의/교재 주문 내역을 조회합니다.")
    @GetMapping("/getOrderOnList")
    public JSONObject getOrderOnList(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", payVO.getUserId());
            params.put("searchStartDate", payVO.getSearchStartDate());
            params.put("searchEndDate", payVO.getSearchEndDate());

            List<HashMap<String, Object>> list = payService.payOrderOnlist(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 주문 목록 조회", description = "오프라인 강의 주문 내역을 조회합니다.")
    @GetMapping("/getOrderOffList")
    public JSONObject getOrderOffList(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", payVO.getUserId());
            params.put("searchStartDate", payVO.getSearchStartDate());
            params.put("searchEndDate", payVO.getSearchEndDate());

            List<HashMap<String, Object>> list = payService.payOrderOfflist(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "T 주문 목록 조회", description = "T 상품 주문 내역을 조회합니다.")
    @GetMapping("/getTOrderList")
    public JSONObject getTOrderList(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", payVO.getUserId());
            params.put("searchStartDate", payVO.getSearchStartDate());
            params.put("searchEndDate", payVO.getSearchEndDate());

            List<HashMap<String, Object>> list = payService.payTOrderList(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 강의 주문 상세 조회", description = "온라인 강의 주문 상세 내역을 조회합니다.")
    @GetMapping("/getOrderDetailLectureOn")
    public JSONObject getOrderDetailLectureOn(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            List<HashMap<String, Object>> list = payService.orderDetailLectureOnlist(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 교재 주문 상세 조회", description = "온라인 교재 주문 상세 내역을 조회합니다.")
    @GetMapping("/getOrderDetailBookOn")
    public JSONObject getOrderDetailBookOn(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            List<HashMap<String, Object>> list = payService.orderDetailBookOnlist(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 강의 주문 상세 조회", description = "오프라인 강의 주문 상세 내역을 조회합니다.")
    @GetMapping("/getOrderDetailLectureOff")
    public JSONObject getOrderDetailLectureOff(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            List<HashMap<String, Object>> list = payService.orderDetailLectureOfflist(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "박스 주문 상세 조회", description = "박스 상품 주문 상세 내역을 조회합니다.")
    @GetMapping("/getOrderDetailBox")
    public JSONObject getOrderDetailBox(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            List<HashMap<String, Object>> list = payService.orderDetailBoxlist(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "룸 주문 상세 조회", description = "룸 상품 주문 상세 내역을 조회합니다.")
    @GetMapping("/getOrderDetailRoom")
    public JSONObject getOrderDetailRoom(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            List<HashMap<String, Object>> list = payService.orderDetailRoomlist(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "T 주문 상세 조회", description = "T 상품 주문 상세 내역을 조회합니다.")
    @GetMapping("/getTOrderDetail")
    public JSONObject getTOrderDetail(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            List<HashMap<String, Object>> list = payService.tOrderDetailList(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 주문 정보 조회", description = "온라인 주문 정보를 조회합니다.")
    @GetMapping("/getOrdersOn")
    public JSONObject getOrdersOn(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            HashMap<String, Object> orderInfo = payService.getOrdersOn(params);

            jsonObject.put("data", orderInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 승인 정보 조회", description = "온라인 결제 승인 정보를 조회합니다.")
    @GetMapping("/getApprovalsOn")
    public JSONObject getApprovalsOn(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            HashMap<String, Object> approvalInfo = payService.getApprovalsOn(params);

            jsonObject.put("data", approvalInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 주문 정보 조회", description = "오프라인 주문 정보를 조회합니다.")
    @GetMapping("/getOrdersOff")
    public JSONObject getOrdersOff(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            HashMap<String, Object> orderInfo = payService.getOrdersOff(params);

            jsonObject.put("data", orderInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 승인 정보 조회", description = "오프라인 결제 승인 정보를 조회합니다.")
    @GetMapping("/getApprovalsOff")
    public JSONObject getApprovalsOff(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            HashMap<String, Object> approvalInfo = payService.getApprovalsOff(params);

            jsonObject.put("data", approvalInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "T 주문 정보 조회", description = "T 상품 주문 정보를 조회합니다.")
    @GetMapping("/getTOrders")
    public JSONObject getTOrders(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            HashMap<String, Object> orderInfo = payService.getTOrders(params);

            jsonObject.put("data", orderInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "T 승인 정보 조회", description = "T 상품 결제 승인 정보를 조회합니다.")
    @GetMapping("/getTApprovals")
    public JSONObject getTApprovals(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            HashMap<String, Object> approvalInfo = payService.getTApprovals(params);

            jsonObject.put("data", approvalInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "배송 정보 조회", description = "주문의 배송 정보를 조회합니다.")
    @GetMapping("/getDelivers")
    public JSONObject getDelivers(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            HashMap<String, Object> deliverInfo = payService.getDelivers(params);

            jsonObject.put("data", deliverInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "교재 배송 목록 조회", description = "온라인 교재 배송 목록을 조회합니다.")
    @GetMapping("/getBookDeliveryOnList")
    public JSONObject getBookDeliveryOnList(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", payVO.getUserId());

            List<HashMap<String, Object>> list = payService.bookDeliveryOnlist(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "배송 정보 수정", description = "배송 정보를 수정합니다.")
    @PutMapping("/updateDeliverInfo")
    public JSONObject updateDeliverInfo(
            @RequestBody MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());
            params.put("deliveryName", payVO.getDeliveryName());
            params.put("deliveryPhone", payVO.getDeliveryPhone());
            params.put("deliveryAddress", payVO.getDeliveryAddress());
            params.put("deliveryMemo", payVO.getDeliveryMemo());

            payService.updateDeliverInfo(params);

            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "사용자 취소 가능 여부 확인", description = "사용자가 주문을 취소할 수 있는지 확인합니다.")
    @GetMapping("/getUserCancelYN")
    public JSONObject getUserCancelYN(
            @ModelAttribute("PayVO") MypagePayVO payVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("orderNo", payVO.getOrderNo());

            String cancelYN = payService.getUserCancelYN(params);

            jsonObject.put("data", cancelYN);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
