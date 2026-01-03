package com.academy.mypage.privateinfo;

import com.academy.common.CORSFilter;
import com.academy.mypage.privateinfo.service.PrivateInfoService;
import com.academy.mypage.privateinfo.service.PrivateInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "PrivateInfo", description = "개인정보 관리 API")
@RestController
@RequestMapping("/api/mypage/privateinfo")
public class PrivateInfoApi extends CORSFilter {

    private final PrivateInfoService privateInfoService;

    @Autowired
    public PrivateInfoApi(PrivateInfoService privateInfoService) {
        this.privateInfoService = privateInfoService;
    }

    @Operation(summary = "회원 정보 조회", description = "회원의 개인정보를 조회합니다.")
    @GetMapping("/getUserInfo")
    public JSONObject getUserInfo(
            @ModelAttribute("PrivateInfoVO") PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());

            HashMap<String, Object> userInfo = privateInfoService.getUserInfo(params);

            jsonObject.put("data", userInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "회원 정보 수정", description = "회원의 개인정보를 수정합니다.")
    @PutMapping("/updateUserInfo")
    public JSONObject updateUserInfo(
            @RequestBody PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());
            params.put("userNm", privateInfoVO.getUserNm());
            params.put("userEmail", privateInfoVO.getUserEmail());
            params.put("phoneNo", privateInfoVO.getPhoneNo());
            params.put("zipCode", privateInfoVO.getZipCode());
            params.put("address", privateInfoVO.getAddress());
            params.put("addressDetail", privateInfoVO.getAddressDetail());

            int result = privateInfoService.updateUserInfo(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "비밀번호 변경", description = "회원의 비밀번호를 변경합니다.")
    @PutMapping("/updatePassword")
    public JSONObject updatePassword(
            @RequestBody PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());
            params.put("currentPassword", privateInfoVO.getCurrentPassword());
            params.put("newPassword", privateInfoVO.getNewPassword());

            int result = privateInfoService.updatePassword(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "비밀번호 확인", description = "현재 비밀번호가 맞는지 확인합니다.")
    @PostMapping("/checkPassword")
    public JSONObject checkPassword(
            @RequestBody PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());
            params.put("password", privateInfoVO.getCurrentPassword());

            int result = privateInfoService.checkPassword(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "프로필 이미지 변경", description = "회원의 프로필 이미지를 변경합니다.")
    @PutMapping("/updateProfileImage")
    public JSONObject updateProfileImage(
            @RequestBody PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());
            params.put("profileImage", privateInfoVO.getProfileImage());

            int result = privateInfoService.updateProfileImage(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "회원 탈퇴", description = "회원을 탈퇴 처리합니다.")
    @DeleteMapping("/deleteUser")
    public JSONObject deleteUser(
            @RequestBody PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());
            params.put("password", privateInfoVO.getCurrentPassword());

            int result = privateInfoService.deleteUser(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "이메일 중복 확인", description = "이메일 주소의 중복 여부를 확인합니다.")
    @GetMapping("/checkEmailDuplicate")
    public JSONObject checkEmailDuplicate(
            @ModelAttribute("PrivateInfoVO") PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userEmail", privateInfoVO.getUserEmail());
            params.put("userId", privateInfoVO.getUserId());

            int result = privateInfoService.checkEmailDuplicate(params);

            jsonObject.put("isDuplicate", result > 0);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "전화번호 중복 확인", description = "전화번호의 중복 여부를 확인합니다.")
    @GetMapping("/checkPhoneDuplicate")
    public JSONObject checkPhoneDuplicate(
            @ModelAttribute("PrivateInfoVO") PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("phoneNo", privateInfoVO.getPhoneNo());
            params.put("userId", privateInfoVO.getUserId());

            int result = privateInfoService.checkPhoneDuplicate(params);

            jsonObject.put("isDuplicate", result > 0);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "마케팅 수신 동의 변경", description = "마케팅 수신 동의 여부를 변경합니다.")
    @PutMapping("/updateMarketingAgree")
    public JSONObject updateMarketingAgree(
            @RequestBody PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());
            params.put("marketingAgree", privateInfoVO.getMarketingAgree());

            int result = privateInfoService.updateMarketingAgree(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "회원 상태 변경", description = "회원의 상태를 변경합니다.")
    @PutMapping("/updateUserStatus")
    public JSONObject updateUserStatus(
            @RequestBody PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());
            params.put("userStatus", privateInfoVO.getUserStatus());

            int result = privateInfoService.updateUserStatus(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "최근 로그인 시간 업데이트", description = "회원의 최근 로그인 시간을 업데이트합니다.")
    @PutMapping("/updateLastLogin")
    public JSONObject updateLastLogin(
            @RequestBody PrivateInfoVO privateInfoVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", privateInfoVO.getUserId());

            int result = privateInfoService.updateLastLogin(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
