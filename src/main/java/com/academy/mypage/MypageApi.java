package com.academy.mypage;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.mypage.service.MypageService;
import com.academy.mypage.service.MypageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * MypageApi.java
 * 마이페이지 관련 REST API Controller
 */
@Tag(name = "Mypage", description = "마이페이지 관리 API")
@RestController
@RequestMapping("/api/mypage")
public class MypageApi extends CORSFilter {

    private final MypageService mypageService;

    @Autowired
    public MypageApi(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    /**
     * 내 정보 조회
     */
    @Operation(summary = "내 정보 조회", description = "로그인한 사용자의 개인 정보를 조회합니다.")
    @GetMapping("/getMyInfo")
    public JSONObject getMyInfo(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        HashMap<String, Object> myInfo = mypageService.getMyInfo(params);

        jsonObject.put("myInfo", myInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 내 정보 수정
     */
    @Operation(summary = "내 정보 수정", description = "로그인한 사용자의 개인 정보를 수정합니다.")
    @PostMapping("/updateMyInfo")
    public JSONObject updateMyInfo(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            params.put("USER_ID", userId);
            params.put("REG_ID", userId);
            params.put("USER_NM", CommonUtil.isNull(mypageVO.getUserNm(), ""));
            params.put("TEL_NO", CommonUtil.isNull(mypageVO.getTelNo(), ""));
            params.put("PHONE_NO", CommonUtil.isNull(mypageVO.getPhoneNo(), ""));
            params.put("EMAIL", CommonUtil.isNull(mypageVO.getEmail(), ""));
            params.put("ADDRESS1", CommonUtil.isNull(mypageVO.getAddress1(), ""));
            params.put("ADDRESS2", CommonUtil.isNull(mypageVO.getAddress2(), ""));
            params.put("ZIP_CODE", CommonUtil.isNull(mypageVO.getZipCode(), ""));
            params.put("JOB", CommonUtil.isNull(mypageVO.getJob(), ""));
            params.put("ISOK_SMS", CommonUtil.isNull(mypageVO.getIsokSms(), "N"));
            params.put("ISOK_EMAIL", CommonUtil.isNull(mypageVO.getIsokEmail(), "N"));

            mypageService.infoUpdate(params);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "정보가 수정되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "정보 수정에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 비밀번호 변경
     */
    @Operation(summary = "비밀번호 변경", description = "사용자의 비밀번호를 변경합니다.")
    @PostMapping("/updatePassword")
    public JSONObject updatePassword(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            params.put("USER_ID", userId);
            params.put("REG_ID", userId);
            params.put("USER_PWD", CommonUtil.isNull(mypageVO.getUserPwd(), ""));
            mypageService.pwdUpdate(params);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "비밀번호가 변경되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "비밀번호 변경에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 회원 탈퇴
     */
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 처리합니다.")
    @PostMapping("/secession")
    public JSONObject secession(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            params.put("USER_ID", userId);
            params.put("REG_ID", userId);
            mypageService.secessionDelete(params);

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "회원 탈퇴가 완료되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "회원 탈퇴에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 내 학습 정보 조회
     */
    @Operation(summary = "내 학습 정보 조회", description = "사용자의 학습 현황 정보를 조회합니다.")
    @GetMapping("/getMyStudyInfo")
    public JSONObject getMyStudyInfo(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        HashMap<String, Object> studyInfo = mypageService.myStudyInfo(params);

        jsonObject.put("studyInfo", studyInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 월별 학습 현황 조회
     */
    @Operation(summary = "월별 학습 현황 조회", description = "사용자의 월별 학습 현황을 조회합니다.")
    @GetMapping("/getMyStudyForMonth")
    public JSONObject getMyStudyForMonth(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        List<HashMap<String, Object>> studyList = mypageService.myStudyforMonth(params);
        List<HashMap<String, Object>> mockList = mypageService.myMockforMonth(params);

        jsonObject.put("studyList", studyList);
        jsonObject.put("mockList", mockList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 내 강의 리스트 조회
     */
    @Operation(summary = "내 강의 리스트 조회", description = "사용자의 수강 강의 목록을 조회합니다.")
    @GetMapping("/getMyLectureList")
    public JSONObject getMyLectureList(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        List<HashMap<String, Object>> lectureList = mypageService.myLectureList(params);

        jsonObject.put("lectureList", lectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 내 강의 코스 리스트 조회
     */
    @Operation(summary = "내 강의 코스 리스트 조회", description = "사용자의 코스별 수강 현황을 조회합니다.")
    @GetMapping("/getMyLectureCourseList")
    public JSONObject getMyLectureCourseList(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        List<HashMap<String, Object>> courseList = mypageService.myLectureCourseList(params);

        jsonObject.put("courseList", courseList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 수료증 강의 리스트 조회
     */
    @Operation(summary = "수료증 강의 리스트 조회", description = "수료증 발급 가능한 강의 목록을 조회합니다.")
    @GetMapping("/getMyCertLectureList")
    public JSONObject getMyCertLectureList(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        List<HashMap<String, Object>> certLectureList = mypageService.myCertLectureList(params);

        jsonObject.put("certLectureList", certLectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 수료증 상세 조회
     */
    @Operation(summary = "수료증 상세 조회", description = "수료증 상세 정보를 조회합니다.")
    @GetMapping("/getMyCertDetail")
    public JSONObject getMyCertDetail(
            @ModelAttribute("MypageVO") MypageVO mypageVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("ORDERNO", mypageVO.getOrderNo());
        params.put("MGNTNO", mypageVO.getMgntNo());
        params.put("PACKAGE_NO", CommonUtil.isNull(mypageVO.getPackageNo(), ""));
        params.put("LECTURE_NO", CommonUtil.isNull(mypageVO.getLectureNo(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = mypageVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        params.put("USER_ID", userId);

        HashMap<String, Object> certDetail;
        String packageNo = mypageVO.getPackageNo();
        if (packageNo != null && !packageNo.isEmpty()) {
            certDetail = mypageService.myCertPackageView(params);
        } else {
            certDetail = mypageService.myCertLectureView(params);
        }

        jsonObject.put("certDetail", certDetail);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

}
