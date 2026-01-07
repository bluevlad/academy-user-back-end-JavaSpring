package com.academy.mypage.cart;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mypage.cart.service.MypageCartService;
import com.academy.mypage.cart.service.MypageCartVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Tag(name = "Cart", description = "장바구니 API")
@RestController("mypageCartApi")
@RequestMapping("/api/mypage/cart")
public class MypageCartApi extends CORSFilter {

    private final MypageCartService cartService;

    @Autowired
    public MypageCartApi(MypageCartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "온라인 강의 장바구니 목록 조회", description = "온라인 강의 장바구니 목록을 조회합니다.")
    @GetMapping("/getLectureOnList")
    public JSONObject getLectureOnList(
            @ModelAttribute("CartVO") MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());

            List<HashMap<String, Object>> list = cartService.cartLectureOnList(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 교재 장바구니 목록 조회", description = "온라인 교재 장바구니 목록을 조회합니다.")
    @GetMapping("/getBookOnList")
    public JSONObject getBookOnList(
            @ModelAttribute("CartVO") MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());

            List<HashMap<String, Object>> list = cartService.cartBookOnList(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 강의 장바구니 목록 조회", description = "오프라인 강의 장바구니 목록을 조회합니다.")
    @GetMapping("/getLectureOffList")
    public JSONObject getLectureOffList(
            @ModelAttribute("CartVO") MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());

            List<HashMap<String, Object>> list = cartService.cartLectureOffList(params);

            jsonObject.put("data", list);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 장바구니 전체 삭제", description = "온라인 장바구니의 모든 항목을 삭제합니다.")
    @DeleteMapping("/clearOnCartAll")
    public JSONObject clearOnCartAll(
            @RequestBody MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());

            cartService.clearOnCartAll(params);

            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 장바구니 항목 삭제 (순번)", description = "온라인 장바구니에서 순번으로 항목을 삭제합니다.")
    @DeleteMapping("/deleteOnCartSeq")
    public JSONObject deleteOnCartSeq(
            @RequestBody MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());
            params.put("cartIdx", cartVO.getCartIdx());

            cartService.deleteOnCartSeq(params);

            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 장바구니 강의 삭제", description = "온라인 장바구니에서 강의코드로 항목을 삭제합니다.")
    @DeleteMapping("/deleteOnCartLecCode")
    public JSONObject deleteOnCartLecCode(
            @RequestBody MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());
            params.put("lecCode", cartVO.getLecCode());

            cartService.deleteOnCartLecCode(params);

            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "온라인 장바구니 교재 삭제", description = "온라인 장바구니에서 교재를 삭제합니다.")
    @DeleteMapping("/deleteOnCartBook")
    public JSONObject deleteOnCartBook(
            @RequestBody MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());
            params.put("bookSeq", cartVO.getBookSeq());

            cartService.deleteOnCartBook(params);

            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 장바구니 전체 삭제", description = "오프라인 장바구니의 모든 항목을 삭제합니다.")
    @DeleteMapping("/clearOffCartAll")
    public JSONObject clearOffCartAll(
            @RequestBody MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());

            cartService.clearOffCartAll(params);

            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 장바구니 항목 삭제 (순번)", description = "오프라인 장바구니에서 순번으로 항목을 삭제합니다.")
    @DeleteMapping("/deleteOffCartSeq")
    public JSONObject deleteOffCartSeq(
            @RequestBody MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());
            params.put("cartIdx", cartVO.getCartIdx());

            cartService.deleteOffCartSeq(params);

            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "오프라인 장바구니 강의 삭제", description = "오프라인 장바구니에서 강의코드로 항목을 삭제합니다.")
    @DeleteMapping("/deleteOffCartLecCode")
    public JSONObject deleteOffCartLecCode(
            @RequestBody MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());
            params.put("lecCode", cartVO.getLecCode());

            cartService.deleteOffCartLecCode(params);

            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "회원 정보 조회", description = "장바구니에서 사용할 회원 정보를 조회합니다.")
    @GetMapping("/getMemberInfo")
    public JSONObject getMemberInfo(
            @ModelAttribute("CartVO") MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());

            HashMap<String, Object> memberInfo = cartService.getMemberInfo(params);

            jsonObject.put("data", memberInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "교재 포인트 조회", description = "장바구니 교재의 포인트 정보를 조회합니다.")
    @GetMapping("/getCartBookPoint")
    public JSONObject getCartBookPoint(
            @ModelAttribute("CartVO") MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());

            String point = cartService.getCartBookPoint(params);

            jsonObject.put("data", point);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "강의 포인트 조회", description = "장바구니 강의의 포인트 정보를 조회합니다.")
    @GetMapping("/getCartLecPoint")
    public JSONObject getCartLecPoint(
            @ModelAttribute("CartVO") MypageCartVO cartVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("userId", cartVO.getUserId());

            String point = cartService.getCartLecPoint(params);

            jsonObject.put("data", point);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
