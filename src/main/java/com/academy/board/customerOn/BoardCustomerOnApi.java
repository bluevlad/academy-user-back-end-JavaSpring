package com.academy.board.customerOn;

import com.academy.board.customerOn.service.BoardCustomerOnService;
import com.academy.board.customerOn.service.BoardCustomerOnVO;
import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * BoardCustomerOnApi.java
 * 고객센터 온라인 게시판 REST API Controller
 */
@Tag(name = "BoardCustomerOn", description = "고객센터 온라인 게시판 API")
@RestController
@RequestMapping("/api/board/customerOn")
public class BoardCustomerOnApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardCustomerOnService boardCustomerOnService;

    @Autowired
    public BoardCustomerOnApi(BoardCustomerOnService boardCustomerOnService) {
        this.boardCustomerOnService = boardCustomerOnService;
    }

    /**
     * 쿠폰 유효성 검사
     */
    @Operation(summary = "쿠폰 유효성 검사", description = "쿠폰 코드의 유효성을 검사합니다.")
    @PostMapping("/checkCoupon")
    public JSONObject checkCoupon(
            @ModelAttribute("BoardCustomerOnVO") BoardCustomerOnVO boardCustomerOnVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        String couponCode = CommonUtil.isNull(boardCustomerOnVO.getCouponCode(), "").toUpperCase();
        params.put("COUPONCODE", couponCode);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> result = boardCustomerOnService.isUseCouponCheck(params);
        if (result != null && result.size() > 0) {
            HashMap<String, Object> resultMap = result.get(0);
            String couponType = String.valueOf(resultMap.get("coupon_type"));

            if ("EE".equals(couponType)) {
                params.put("CCode", String.valueOf(resultMap.get("coupon_num_new")));

                List<HashMap<String, Object>> couponCount = boardCustomerOnService.getCouponCount(params);
                String couponEndDate = boardCustomerOnService.getCouponEndDate(params);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
                String nowDate = formatter.format(new Date());
                String endDate = couponEndDate.replaceAll("-", "").substring(0, 8);

                if (Integer.parseInt(nowDate) > Integer.parseInt(endDate)) {
                    jsonObject.put("result", "EXPIRED");
                    jsonObject.put("message", "쿠폰 유효기간이 만료되었습니다.");
                } else if (couponCount != null && couponCount.size() > 0) {
                    jsonObject.put("result", "ALREADY_USED");
                    jsonObject.put("message", "이미 사용된 쿠폰입니다.");
                } else {
                    jsonObject.put("result", "OK");
                    jsonObject.put("idx", resultMap.get("idx"));
                    jsonObject.put("couponType", couponType);
                    jsonObject.put("cCode", resultMap.get("coupon_num_new"));
                }
            } else {
                jsonObject.put("result", "OK");
                jsonObject.put("idx", resultMap.get("idx"));
                jsonObject.put("couponType", couponType);
            }
        } else {
            jsonObject.put("result", "NOT_FOUND");
            jsonObject.put("message", "유효하지 않은 쿠폰입니다.");
        }

        jsonObject.put("retMsg", "OK");
        return new JSONObject(jsonObject);
    }

    /**
     * 쿠폰 등록
     */
    @Operation(summary = "쿠폰 등록", description = "쿠폰을 등록합니다.")
    @PostMapping("/insertCoupon")
    public JSONObject insertCoupon(
            @ModelAttribute("BoardCustomerOnVO") BoardCustomerOnVO boardCustomerOnVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("CCode", CommonUtil.isNull(boardCustomerOnVO.getcCode(), ""));
        params.put("IDX", CommonUtil.isNull(boardCustomerOnVO.getIdx(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> couponMap = boardCustomerOnService.getCouponMap(params);
            if (couponMap != null) {
                couponMap.put("USERID", params.get("REG_ID"));
                couponMap.put("CCode", params.get("CCode"));
                couponMap.put("USER_ID", params.get("USER_ID"));

                boardCustomerOnService.insertMyCoupon(couponMap);

                // EE 타입이 아닌 경우 MSSQL 쿠폰 업데이트
                if (!"EE".equals(couponMap.get("PUB_COUPON_GUBUN"))) {
                    couponMap.put("IDX", params.get("IDX"));
                    boardCustomerOnService.updateMssqlCoupon(couponMap);
                }

                jsonObject.put("retMsg", "OK");
                jsonObject.put("message", "쿠폰이 등록되었습니다.");
            } else {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "쿠폰 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "쿠폰 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 제휴 쿠폰 등록
     */
    @Operation(summary = "제휴 쿠폰 등록", description = "제휴 쿠폰을 등록합니다.")
    @PostMapping("/insertCoopCoupon")
    public JSONObject insertCoopCoupon(
            @ModelAttribute("BoardCustomerOnVO") BoardCustomerOnVO boardCustomerOnVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        String couponCode = CommonUtil.isNull(boardCustomerOnVO.getCouponCode(), "").toUpperCase();
        params.put("COUPONCODE", couponCode);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> result = boardCustomerOnService.coopIsUseCouponCheck(params);
        if (result != null && result.size() > 0) {
            HashMap<String, Object> resultMap = result.get(0);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
            String nowDate = formatter.format(new Date());
            String endDate = String.valueOf(resultMap.get("ED_DT")).replaceAll("-", "").substring(0, 8);

            if (Integer.parseInt(nowDate) > Integer.parseInt(endDate)) {
                jsonObject.put("result", "EXPIRED");
                jsonObject.put("message", "쿠폰 유효기간이 만료되었습니다.");
            } else if (resultMap.get("ORDERNO") != null && !"null".equals(String.valueOf(resultMap.get("ORDERNO")))) {
                jsonObject.put("result", "ALREADY_USED");
                jsonObject.put("message", "이미 사용된 쿠폰입니다.");
            } else {
                params.put("LECCODE", String.valueOf(resultMap.get("LECCODE")));
                params.put("COOP_CD", String.valueOf(resultMap.get("COOP_CD")));

                boardCustomerOnService.coopInsertOrder(params);
                boardCustomerOnService.coopCouponMstUpdate(params);

                jsonObject.put("result", "OK");
                jsonObject.put("message", "쿠폰이 등록되었습니다.");
            }
        } else {
            jsonObject.put("result", "NOT_FOUND");
            jsonObject.put("message", "유효하지 않은 쿠폰입니다.");
        }

        jsonObject.put("retMsg", "OK");
        return new JSONObject(jsonObject);
    }

    /**
     * 이벤트 댓글 목록 조회
     */
    @Operation(summary = "이벤트 댓글 목록 조회", description = "이벤트 댓글 목록을 페이징하여 조회합니다.")
    @GetMapping("/getEventReplyList")
    public JSONObject getEventReplyList(
            @ModelAttribute("BoardCustomerOnVO") BoardCustomerOnVO boardCustomerOnVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchEventNo", CommonUtil.isNull(boardCustomerOnVO.getSearchEventNo(), ""));

        int currentPage = boardCustomerOnVO.getCurrentPage() > 0 ? boardCustomerOnVO.getCurrentPage() : 1;
        int recordCountPerPage = boardCustomerOnVO.getPageRow() > 0 ? boardCustomerOnVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardCustomerOnService.getEventReplyCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardCustomerOnService.getEventReplyList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
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
            params.put("USER_NM", "");
            params.put("REG_ID", "");
            params.put("UPD_ID", "");
            params.put("ISLOGIN", "N");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
                params.put("USER_NM", loginInfo.get("USER_NM"));
                params.put("USER_ROLE", loginInfo.get("USER_ROLE"));
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

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "O"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "MAIN"));
    }
}
