package com.academy.mypage.passlecture;

import com.academy.common.CORSFilter;
import com.academy.mypage.passlecture.service.PassLectureService;
import com.academy.mypage.passlecture.service.PassLectureVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Tag(name = "PassLecture", description = "합격생 실강 API")
@RestController
@RequestMapping("/api/mypage/passlecture")
public class PassLectureApi extends CORSFilter {

    private final PassLectureService passLectureService;

    @Autowired
    public PassLectureApi(PassLectureService passLectureService) {
        this.passLectureService = passLectureService;
    }

    /**
     * 수강중인 강의 목록 조회
     */
    @Operation(summary = "수강중인 강의 목록 조회", description = "학원실강 수강중인 강의 목록을 조회합니다.")
    @GetMapping("/getIngList")
    public JSONObject getIngList(@ModelAttribute("PassLectureVO") PassLectureVO passLectureVO,
                                 HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("USER_ID", passLectureVO.getUserId());

            List<HashMap<String, Object>> list = passLectureService.passLectureIngList(params);
            int totalCount = passLectureService.passLectureIngCount(params);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 수강종료 강의 목록 조회
     */
    @Operation(summary = "수강종료 강의 목록 조회", description = "학원실강 수강종료 강의 목록을 조회합니다.")
    @GetMapping("/getEndList")
    public JSONObject getEndList(@ModelAttribute("PassLectureVO") PassLectureVO passLectureVO,
                                 HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("USER_ID", passLectureVO.getUserId());

            List<HashMap<String, Object>> list = passLectureService.passLectureEndList(params);
            int totalCount = passLectureService.passLectureEndCount(params);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 무료특강 목록 조회
     */
    @Operation(summary = "무료특강 목록 조회", description = "학원실강 무료특강 목록을 조회합니다.")
    @GetMapping("/getFreeList")
    public JSONObject getFreeList(@ModelAttribute("PassLectureVO") PassLectureVO passLectureVO,
                                  HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("USER_ID", passLectureVO.getUserId());

            List<HashMap<String, Object>> list = passLectureService.passLectureFreeList(params);

            jsonObject.put("list", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 교재 목록 조회
     */
    @Operation(summary = "교재 목록 조회", description = "강의별 교재 목록을 조회합니다.")
    @GetMapping("/getBookList")
    public JSONObject getBookList(@ModelAttribute("PassLectureVO") PassLectureVO passLectureVO,
                                  HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("USERID", passLectureVO.getUserId());
            params.put("LECCODE", passLectureVO.getLecCode());

            List<HashMap<String, Object>> list = passLectureService.passBookList(params);

            jsonObject.put("list", list);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
