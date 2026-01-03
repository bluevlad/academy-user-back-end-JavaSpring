package com.academy.mypage.coupon;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mypage.coupon.service.CouponService;
import com.academy.mypage.coupon.service.CouponVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Tag(name = "Coupon", description = "쿠폰/적립금 API")
@RestController
@RequestMapping("/api/mypage/coupon")
public class CouponApi extends CORSFilter {

    private final CouponService couponService;

    @Autowired
    public CouponApi(CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * 내 쿠폰 목록 조회
     */
    @Operation(summary = "내 쿠폰 목록 조회", description = "사용 가능한 쿠폰 목록을 조회합니다.")
    @GetMapping("/getMyCoupon")
    public JSONObject getMyCoupon(@ModelAttribute("CouponVO") CouponVO couponVO,
                                  HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("USER_ID", couponVO.getUserId());
            params.put("ADD_FLAG", couponVO.getAddFlag());
            params.put("IS_AVAILABLE", couponVO.getIsAvailable());
            params.put("REGTYPECD", couponVO.getRegTypecd());

            List<HashMap<String, Object>> list = couponService.getMyCoupon(params);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", list.size());
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 쿠폰 사용
     */
    @Operation(summary = "쿠폰 사용", description = "쿠폰을 사용합니다.")
    @PostMapping("/useCoupon")
    public JSONObject useCoupon(@RequestBody HashMap<String, Object> requestBody,
                                HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            requestBody.put("UPD_ID", requestBody.get("userId"));

            int result = couponService.useCoupon(requestBody);

            if (result > 0) {
                jsonObject.put("retMsg", "OK");
                jsonObject.put("message", "쿠폰이 사용되었습니다.");
            } else {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "쿠폰 사용에 실패했습니다.");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 적립금 현황 조회
     */
    @Operation(summary = "적립금 현황 조회", description = "적립금 현황을 조회합니다.")
    @GetMapping("/getMileageStatus")
    public JSONObject getMileageStatus(@ModelAttribute("CouponVO") CouponVO couponVO,
                                       HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("USER_ID", couponVO.getUserId());

            List<HashMap<String, Object>> mileageStatus = couponService.getMileageStatus(params);
            int nowPoint = couponService.getNowPoint(params);

            jsonObject.put("mileageStatus", mileageStatus);
            jsonObject.put("nowPoint", nowPoint);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 적립금 내역 조회
     */
    @Operation(summary = "적립금 내역 조회", description = "적립금 적립/사용 내역을 조회합니다.")
    @GetMapping("/getMileageList")
    public JSONObject getMileageList(@ModelAttribute("CouponVO") CouponVO couponVO,
                                     HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int currentPage = couponVO.getCurrentPage();
            int pageRow = couponVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            HashMap<String, Object> params = new HashMap<>();
            params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
            params.put("recordCountPerPage", String.valueOf(pageRow));
            params.put("USER_ID", couponVO.getUserId());

            List<HashMap<String, Object>> list = couponService.getMileageList(params);
            int totalCount = couponService.getMileageListCount(params);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 적립금 사용
     */
    @Operation(summary = "적립금 사용", description = "적립금을 사용합니다.")
    @PostMapping("/useMileage")
    public JSONObject useMileage(@RequestBody HashMap<String, Object> requestBody,
                                 HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            requestBody.put("REG_ID", requestBody.get("userId"));
            requestBody.put("UPD_ID", requestBody.get("userId"));

            int result = couponService.useMileage(requestBody);

            if (result > 0) {
                jsonObject.put("retMsg", "OK");
                jsonObject.put("message", "적립금이 사용되었습니다.");
            } else {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "적립금 사용에 실패했습니다.");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
