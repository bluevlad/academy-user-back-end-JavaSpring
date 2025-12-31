package com.academy.lecture;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.lecture.service.CategoryService;
import com.academy.lecture.service.CategoryVO;
import io.swagger.v3.oas.annotations.Operation;
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
            @ModelAttribute("CategoryVO") CategoryVO categoryVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, categoryVO, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(categoryVO.getSearchCategoryCode(), params.get("topMenu")));
        params.put("SERIES_CD", CommonUtil.isNull(categoryVO.getSearchSeriesCode(), ""));

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
            @ModelAttribute("CategoryVO") CategoryVO categoryVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, categoryVO, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(categoryVO.getSearchCategoryCode(), params.get("topMenu")));
        params.put("SERIES_CD", CommonUtil.isNull(categoryVO.getSearchSeriesCode(), ""));
        params.put("SUBJECT_CD", CommonUtil.isNull(categoryVO.getSearchSubjectCode(), ""));

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
            @ModelAttribute("CategoryVO") CategoryVO categoryVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, categoryVO, request);

        params.put("SEARCHCAT", CommonUtil.isNull(categoryVO.getSearchCat(), ""));

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
            @ModelAttribute("CategoryVO") CategoryVO categoryVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, categoryVO, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(categoryVO.getSearchCategoryCode(), params.get("topMenu")));

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
            @ModelAttribute("CategoryVO") CategoryVO categoryVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, categoryVO, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(categoryVO.getSearchCategoryCode(), params.get("topMenu")));

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
    private void setParam(HashMap<String, String> params, CategoryVO categoryVO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String link = CommonUtil.isNull(categoryVO.getLink(), "");

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

        params.put("topMenuType", CommonUtil.isNull(categoryVO.getTopMenuType(), "F"));
        params.put("topMenu", CommonUtil.isNull(categoryVO.getTopMenu(), "001"));
        params.put("leftMenuLType", CommonUtil.isNull(categoryVO.getLeftMenuLType(), "M0101"));
        params.put("currentPage", String.valueOf(categoryVO.getCurrentPage() > 0 ? categoryVO.getCurrentPage() : 1));
        params.put("pageRow", String.valueOf(categoryVO.getPageRow() > 0 ? categoryVO.getPageRow() : pageUnit));
    }

}
