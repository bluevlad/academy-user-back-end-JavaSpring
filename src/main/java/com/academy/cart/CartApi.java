package com.academy.cart;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.cart.service.CartService;
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
 * CartApi.java
 * 장바구니 관련 REST API Controller
 */
@Tag(name = "Cart", description = "장바구니 관리 API")
@RestController
@RequestMapping("/api/cart")
public class CartApi extends CORSFilter {

    private final CartService cartService;

    @Autowired
    public CartApi(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * 온라인 장바구니 조회
     */
    @Operation(summary = "온라인 장바구니 조회", description = "온라인 강좌 및 도서 장바구니 목록을 조회합니다.")
    @GetMapping("/getOnlineCartList")
    public JSONObject getOnlineCartList(
            @Parameter(description = "마킹 주문번호") @RequestParam(required = false) String marking,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("MARKING", CommonUtil.isNull(marking, ""));

        List<HashMap<String, Object>> lectureList = cartService.cartLectureOnList(params);
        List<HashMap<String, Object>> bookList = cartService.cartBookOnList(params);
        HashMap<String, Object> memberInfo = cartService.getMemberInfo(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("memberInfo", memberInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 장바구니 조회
     */
    @Operation(summary = "오프라인 장바구니 조회", description = "오프라인 강좌 장바구니 목록을 조회합니다.")
    @GetMapping("/getOfflineCartList")
    public JSONObject getOfflineCartList(
            @Parameter(description = "마킹 주문번호") @RequestParam(required = false) String marking,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("MARKING", CommonUtil.isNull(marking, ""));

        List<HashMap<String, Object>> lectureList = cartService.cartLectureOffList(params);
        HashMap<String, Object> memberInfo = cartService.getMemberInfo(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("memberInfo", memberInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 장바구니 전체 삭제
     */
    @Operation(summary = "온라인 장바구니 전체 삭제", description = "온라인 장바구니를 전체 삭제합니다.")
    @PostMapping("/clearOnlineCart")
    public JSONObject clearOnlineCart(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            cartService.clearOnCartAll(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "장바구니가 비워졌습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "장바구니 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 장바구니 강좌 삭제
     */
    @Operation(summary = "온라인 장바구니 강좌 삭제", description = "온라인 장바구니에서 특정 강좌를 삭제합니다.")
    @PostMapping("/deleteOnlineCartLecture")
    public JSONObject deleteOnlineCartLecture(
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
            if (params.get("SEQ") != null && !params.get("SEQ").isEmpty()) {
                cartService.deleteOnCartSeq(params);
            } else if (params.get("LECCODE") != null && !params.get("LECCODE").isEmpty()) {
                cartService.deleteOnCartLecCode(params);
            }
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "강좌가 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "강좌 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 장바구니 도서 삭제
     */
    @Operation(summary = "온라인 장바구니 도서 삭제", description = "온라인 장바구니에서 특정 도서를 삭제합니다.")
    @PostMapping("/deleteOnlineCartBook")
    public JSONObject deleteOnlineCartBook(
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
            cartService.deleteOnCartBook(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "도서가 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "도서 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 장바구니 전체 삭제
     */
    @Operation(summary = "오프라인 장바구니 전체 삭제", description = "오프라인 장바구니를 전체 삭제합니다.")
    @PostMapping("/clearOfflineCart")
    public JSONObject clearOfflineCart(HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            cartService.clearOffCartAll(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "장바구니가 비워졌습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "장바구니 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 장바구니 강좌 삭제
     */
    @Operation(summary = "오프라인 장바구니 강좌 삭제", description = "오프라인 장바구니에서 특정 강좌를 삭제합니다.")
    @PostMapping("/deleteOfflineCartLecture")
    public JSONObject deleteOfflineCartLecture(
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
            if (params.get("SEQ") != null && !params.get("SEQ").isEmpty()) {
                cartService.deleteOffCartSeq(params);
            } else if (params.get("LECCODE") != null && !params.get("LECCODE").isEmpty()) {
                cartService.deleteOffCartLecCode(params);
            }
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "강좌가 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "강좌 삭제에 실패했습니다.");
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

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "O"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "001"));
    }

}
