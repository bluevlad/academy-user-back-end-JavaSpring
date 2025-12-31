package com.academy.lecture;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.lecture.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * CategoryApi.java
 * 강의 카테고리 관련 REST API Controller
 */
@Tag(name = "Category", description = "강의 카테고리 관리 API")
@RestController
@RequestMapping("/api/lecture/category")
public class CategoryApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final CategoryService categoryService;

    @Autowired
    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 직렬-과목 리스트 조회
     */
    @Operation(summary = "직렬-과목 리스트 조회", description = "카테고리와 직렬에 따른 과목 목록을 조회합니다.")
    @GetMapping("/getSeriesSubject")
    public JSONObject getSeriesSubject(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            @Parameter(description = "직렬 코드") @RequestParam(required = false) String searchSeriesCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));
        params.put("SERIES_CD", CommonUtil.isNull(searchSeriesCode, ""));

        List<HashMap<String, Object>> resultList = categoryService.selectSeriesSubject(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectBySeries", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 직렬-과목-교수 리스트 조회
     */
    @Operation(summary = "직렬-과목-교수 리스트 조회", description = "카테고리, 직렬, 과목에 따른 교수 목록을 조회합니다.")
    @GetMapping("/getSeriesProf")
    public JSONObject getSeriesProf(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            @Parameter(description = "직렬 코드") @RequestParam(required = false) String searchSeriesCode,
            @Parameter(description = "과목 코드") @RequestParam(required = false) String searchSubjectCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));
        params.put("SERIES_CD", CommonUtil.isNull(searchSeriesCode, ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(searchSubjectCode, ""));

        List<HashMap<String, Object>> resultList = categoryService.selectSeriesProf(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("profBySeries", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 카테고리 트리 리스트 조회
     */
    @Operation(summary = "카테고리 트리 리스트 조회", description = "카테고리 트리 구조 목록을 조회합니다.")
    @GetMapping("/getCategoryTree")
    public JSONObject getCategoryTree(
            @Parameter(description = "검색할 카테고리 코드") @RequestParam(required = false) String searchCat,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("SEARCHCAT", CommonUtil.isNull(searchCat, ""));

        List<HashMap<String, Object>> resultList = categoryService.getSeriesCate(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("categoryTree", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 서브 메인화면 과목 목록 조회
     */
    @Operation(summary = "서브 메인화면 과목 목록 조회", description = "서브 메인화면에 표시할 과목 목록을 조회합니다.")
    @GetMapping("/getSubMainSubjectList")
    public JSONObject getSubMainSubjectList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));

        List<HashMap<String, Object>> resultList = categoryService.selectSubMainCateSubjectList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 서브 메인화면 교수 목록 조회
     */
    @Operation(summary = "서브 메인화면 교수 목록 조회", description = "서브 메인화면에 표시할 교수 목록을 조회합니다.")
    @GetMapping("/getSubMainTeacherList")
    public JSONObject getSubMainTeacherList(
            @Parameter(description = "카테고리 코드") @RequestParam(required = false) String searchCategoryCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(searchCategoryCode, params.get("topMenu")));

        List<HashMap<String, Object>> resultList = categoryService.selectSubMainCateTeacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 파라미터 설정
     */
    @SuppressWarnings("unchecked")
    private void setParam(HashMap<String, String> params, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String link = CommonUtil.isNull(request.getParameter("link"), "");

        if (link.equals("link")) {
            params.put("USER_ID", "");
            params.put("REG_ID", "");
            params.put("UPD_ID", "");
        } else {
            if (session == null) {
                params.put("USER_ID", "");
                params.put("REG_ID", "");
                params.put("UPD_ID", "");
            } else {
                HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
                if (loginInfo != null && !loginInfo.isEmpty()) {
                    params.put("USER_ID", loginInfo.get("USER_ID"));
                    params.put("REG_ID", loginInfo.get("USER_ID"));
                    params.put("UPD_ID", loginInfo.get("USER_ID"));
                } else {
                    params.put("USER_ID", "");
                    params.put("REG_ID", "");
                    params.put("UPD_ID", "");
                }
            }
        }

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "F"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "001"));
        params.put("leftMenuLType", CommonUtil.isNull(request.getParameter("leftMenuLType"), "M0101"));
        params.put("currentPage", CommonUtil.isNull(request.getParameter("currentPage"), "1"));
        params.put("pageRow", CommonUtil.isNull(request.getParameter("pageRow"), String.valueOf(pageUnit)));
    }

}
