package com.academy.openLecture;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.openLecture.service.OpenLectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * OpenLectureApi.java
 * 공개강의 관련 REST API Controller
 */
@Tag(name = "OpenLecture", description = "공개강의 관리 API")
@RestController
@RequestMapping("/api/openLecture")
public class OpenLectureApi extends CORSFilter {

    private final OpenLectureService openLectureService;

    @Autowired
    public OpenLectureApi(OpenLectureService openLectureService) {
        this.openLectureService = openLectureService;
    }

    /**
     * 공개강의 목록 조회
     */
    @Operation(summary = "공개강의 목록 조회", description = "공개강의 목록을 조회합니다.")
    @GetMapping("/getOpenLectureList")
    public JSONObject getOpenLectureList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String categoryCd,
            @Parameter(description = "분류 검색") @RequestParam(required = false) String searchBunru,
            @Parameter(description = "검색 유형") @RequestParam(required = false) String searchType,
            @Parameter(description = "검색어") @RequestParam(required = false) String searchText,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("CATEGORY_CD", CommonUtil.isNull(categoryCd, ""));
        params.put("SEARCHBUNRU", CommonUtil.isNull(searchBunru, ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(searchType, ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(searchText, ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> openLectureList = openLectureService.openlectureOnList(params);

        jsonObject.put("openLectureList", openLectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 공개강의 상세 조회
     */
    @Operation(summary = "공개강의 상세 조회", description = "공개강의 상세 정보를 조회합니다.")
    @GetMapping("/getOpenLectureDetail")
    public JSONObject getOpenLectureDetail(
            @Parameter(description = "공개강의 코드", required = true) @RequestParam String openLecCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("OPENLECCODE", openLecCode);

        HashMap<String, Object> jsonObject = new HashMap<>();

        HashMap<String, Object> openLectureDetail = openLectureService.openlectureOnDetail(params);

        if (openLectureDetail != null) {
            jsonObject.put("openLectureDetail", openLectureDetail);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "공개강의 정보를 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

}
