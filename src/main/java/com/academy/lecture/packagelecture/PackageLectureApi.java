package com.academy.lecture.packagelecture;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.lecture.packagelecture.service.PackageLectureService;
import com.academy.lecture.packagelecture.service.PackageLectureVO;
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
 * PackageLectureApi.java
 * 패키지 강의 REST API Controller
 */
@Tag(name = "PackageLecture", description = "패키지 강의 관리 API")
@RestController
@RequestMapping("/api/lecture/package")
public class PackageLectureApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final PackageLectureService packageLectureService;

    @Autowired
    public PackageLectureApi(PackageLectureService packageLectureService) {
        this.packageLectureService = packageLectureService;
    }

    /**
     * 온라인 패키지 강의 목록 조회
     */
    @Operation(summary = "온라인 패키지 강의 목록 조회", description = "온라인 패키지 강의 목록을 조회합니다.")
    @GetMapping("/getOnlineList")
    public JSONObject getOnlineList(
            @ModelAttribute("PackageLectureVO") PackageLectureVO packageLectureVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, packageLectureVO, request);

        // 페이지네이션 설정
        int currentPage = packageLectureVO.getCurrentPage() > 0 ? packageLectureVO.getCurrentPage() : 1;
        int pageRow = packageLectureVO.getPageRow() > 0 ? packageLectureVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(pageRow);
        paginationInfo.setPageSize(10);

        params.put("startNo", paginationInfo.getFirstRecordIndex());
        params.put("endNo", paginationInfo.getFirstRecordIndex() + pageRow);

        String searchCategoryCode = CommonUtil.isNull(packageLectureVO.getSearchCategoryCode(),
                                                       CommonUtil.isNull(packageLectureVO.getTopMenu(), "001"));
        if ("MAIN".equals(searchCategoryCode)) {
            searchCategoryCode = "001";
        }

        params.put("CATEGORY_CD", searchCategoryCode);
        params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(packageLectureVO.getLecTypeChoice(), "P"));
        params.put("LEARNING_CD", CommonUtil.isNull(packageLectureVO.getLearningCd(), ""));
        params.put("SEARCHTYPE", CommonUtil.isNull(packageLectureVO.getSearchType(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(packageLectureVO.getSearchText(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 목록 조회
        List<HashMap<String, Object>> resultList = packageLectureService.packageLectureOnList(params);
        int totalCount = packageLectureService.packageLectureOnListCount(params);

        paginationInfo.setTotalRecordCount(totalCount);

        jsonObject.put("lectureList", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPages", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 온라인 패키지 강의 상세 조회
     */
    @Operation(summary = "온라인 패키지 강의 상세 조회", description = "온라인 패키지 강의 상세 정보를 조회합니다.")
    @GetMapping("/getOnlineDetail")
    public JSONObject getOnlineDetail(
            @ModelAttribute("PackageLectureVO") PackageLectureVO packageLectureVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, packageLectureVO, request);

        params.put("LECCODE", CommonUtil.isNull(packageLectureVO.getLeccode(), ""));
        params.put("discount", CommonUtil.isNull(packageLectureVO.getDiscount(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 필수 강좌 목록
        List<HashMap<String, Object>> mustList = packageLectureService.packageLectureOnDetail1(params);
        // 선택 강좌 목록
        List<HashMap<String, Object>> selectList = packageLectureService.packageLectureOnDetail2(params);

        jsonObject.put("mustList", mustList);
        jsonObject.put("selectList", selectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 패키지 강의 목록 조회
     */
    @Operation(summary = "오프라인 패키지 강의 목록 조회", description = "오프라인 패키지 강의 목록을 조회합니다.")
    @GetMapping("/getOfflineList")
    public JSONObject getOfflineList(
            @ModelAttribute("PackageLectureVO") PackageLectureVO packageLectureVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, packageLectureVO, request);

        String searchCategoryCode = CommonUtil.isNull(packageLectureVO.getSearchCategoryCode(),
                                                       CommonUtil.isNull(packageLectureVO.getTopMenu(), "001"));
        if ("MAIN".equals(searchCategoryCode)) {
            searchCategoryCode = "001";
        }

        params.put("CATEGORY_CD", searchCategoryCode);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 목록 조회
        List<HashMap<String, Object>> resultList = packageLectureService.packageLecturePassList(params);

        jsonObject.put("lectureList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 오프라인 패키지 강의 상세 조회
     */
    @Operation(summary = "오프라인 패키지 강의 상세 조회", description = "오프라인 패키지 강의 상세 정보를 조회합니다.")
    @GetMapping("/getOfflineDetail")
    public JSONObject getOfflineDetail(
            @ModelAttribute("PackageLectureVO") PackageLectureVO packageLectureVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, packageLectureVO, request);

        params.put("LECCODE", CommonUtil.isNull(packageLectureVO.getLeccode(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 상세 정보 조회
        HashMap<String, Object> detail = packageLectureService.lectureOffDetailS(params);
        // 서브 강의 목록 조회
        List<HashMap<String, Object>> subList = packageLectureService.packageLectureOffDetail(params);

        if (detail != null) {
            jsonObject.put("detail", detail);
            jsonObject.put("subList", subList);
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "강의 정보를 찾을 수 없습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 파라미터 설정
     */
    @SuppressWarnings("unchecked")
    private void setParam(HashMap<String, Object> params, PackageLectureVO packageLectureVO, HttpServletRequest request) {
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

        params.put("topMenu", CommonUtil.isNull(packageLectureVO.getTopMenu(), "001"));
        params.put("topMenuType", CommonUtil.isNull(packageLectureVO.getTopMenuType(), "F"));
        params.put("leftMenuLType", CommonUtil.isNull(packageLectureVO.getLeftMenuLType(), "M0101"));
        params.put("lecKType", CommonUtil.isNull(packageLectureVO.getLecKType(), "P"));
    }
}
