package com.academy.mypage.mylecture;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mypage.mylecture.service.MyLectureService;
import com.academy.mypage.mylecture.service.MyLectureVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Tag(name = "MyLecture", description = "내 강의 API")
@RestController
@RequestMapping("/api/mypage/mylecture")
public class MyLectureApi extends CORSFilter {

    private final MyLectureService myLectureService;

    @Autowired
    public MyLectureApi(MyLectureService myLectureService) {
        this.myLectureService = myLectureService;
    }

    /**
     * 나의 학습정보 조회
     */
    @Operation(summary = "나의 학습정보 조회", description = "나의 학습 현황 정보를 조회합니다.")
    @GetMapping("/getMyStudyInfo")
    public JSONObject getMyStudyInfo(@ModelAttribute("MyLectureVO") MyLectureVO myLectureVO,
                                     HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("USER_ID", myLectureVO.getUserId());
            params.put("searchYear", myLectureVO.getSearchYear());
            params.put("searchMonth", myLectureVO.getSearchMonth());

            HashMap<String, Object> myStudyInfo = myLectureService.myStudyInfo(params);
            List<HashMap<String, Object>> myStudyforMonth = myLectureService.myStudyforMonth(params);
            List<HashMap<String, Object>> myMockforMonth = myLectureService.myMockforMonth(params);

            jsonObject.put("myStudyInfo", myStudyInfo);
            jsonObject.put("myStudyforMonth", myStudyforMonth);
            jsonObject.put("myMockforMonth", myMockforMonth);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 나의 강의 목록 조회
     */
    @Operation(summary = "나의 강의 목록 조회", description = "수강중인 강의 목록을 조회합니다.")
    @GetMapping("/getMyLectureList")
    public JSONObject getMyLectureList(@ModelAttribute("MyLectureVO") MyLectureVO myLectureVO,
                                       HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int currentPage = myLectureVO.getCurrentPage();
            int pageRow = myLectureVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            HashMap<String, Object> params = new HashMap<>();
            params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
            params.put("recordCountPerPage", String.valueOf(pageRow));
            params.put("USER_ID", myLectureVO.getUserId());
            params.put("pageCmd", myLectureVO.getPageCmd());

            List<HashMap<String, Object>> list = myLectureService.getMyLectureList(params);
            int totalCount = myLectureService.getMyLectureListCount(params);

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
     * 모의고사 등록
     */
    @Operation(summary = "모의고사 등록", description = "모의고사를 등록합니다.")
    @PostMapping("/insertMock")
    public JSONObject insertMock(@RequestBody HashMap<String, Object> requestBody,
                                 HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            requestBody.put("REG_ID", requestBody.get("userId"));
            requestBody.put("UPD_ID", requestBody.get("userId"));

            // 기존 모의고사 정보 확인
            HashMap<String, Object> mockInfo = myLectureService.getMyMockInfo(requestBody);

            if (mockInfo == null || mockInfo.isEmpty()) {
                myLectureService.insertMock(requestBody);
            }

            HashMap<String, Object> mockMap = myLectureService.getMyMockInfo(requestBody);

            jsonObject.put("data", mockMap);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 시험 등록
     */
    @Operation(summary = "시험 등록", description = "시험을 등록합니다.")
    @PostMapping("/insertExam")
    public JSONObject insertExam(@RequestBody HashMap<String, Object> requestBody,
                                 HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            requestBody.put("REG_ID", requestBody.get("userId"));
            requestBody.put("UPD_ID", requestBody.get("userId"));

            // 기존 시험 정보 확인
            HashMap<String, Object> examInfo = myLectureService.getMyMockInfo(requestBody);

            if (examInfo == null || examInfo.isEmpty()) {
                myLectureService.insertExam(requestBody);
            }

            HashMap<String, Object> examMap = myLectureService.getMyMockInfo(requestBody);

            jsonObject.put("data", examMap);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
