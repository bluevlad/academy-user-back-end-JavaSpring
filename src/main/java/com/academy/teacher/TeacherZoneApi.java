package com.academy.teacher;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
import com.academy.teacher.service.TeacherZoneService;
import com.academy.teacher.service.TeacherZoneVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * TeacherZoneApi.java
 * 교수 관리 페이지(Teacher Zone) REST API Controller
 */
@Tag(name = "TeacherZone", description = "교수 관리 페이지 API")
@RestController
@RequestMapping("/api/teacher/zone")
public class TeacherZoneApi extends CORSFilter {

    private final TeacherZoneService teacherZoneService;

    @Autowired
    public TeacherZoneApi(TeacherZoneService teacherZoneService) {
        this.teacherZoneService = teacherZoneService;
    }

    /**
     * 대시보드 정보 조회
     */
    @Operation(summary = "대시보드 정보 조회", description = "교수 대시보드 정보를 조회합니다.")
    @GetMapping("/dashboard")
    public JSONObject getDashboard(
            @ModelAttribute("TeacherZoneVO") TeacherZoneVO teacherZoneVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("LOG_CNT", "5");

        // 로그인 IP 이력
        List<HashMap<String, Object>> loginIpList = teacherZoneService.loginIp(params);
        HashMap<String, Object> logOne = null;
        if (loginIpList != null && !loginIpList.isEmpty()) {
            logOne = loginIpList.get(0);
        }

        // 강의 수강 인원 통계
        List<HashMap<String, Object>> offLecSum = null;
        List<HashMap<String, Object>> onLecSum = null;

        if (params.get("USER_ID") != null && !params.get("USER_ID").isEmpty()) {
            offLecSum = teacherZoneService.offLectureSum(params);
            onLecSum = teacherZoneService.onLectureSum(params);
        }

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("logOne", logOne);
        jsonObject.put("loginIpList", loginIpList);
        jsonObject.put("offLecSum", offLecSum);
        jsonObject.put("onLecSum", onLecSum);
        jsonObject.put("userIp", request.getRemoteAddr());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 목록 조회
     */
    @Operation(summary = "강의 목록 조회", description = "교수의 강의 목록을 조회합니다.")
    @GetMapping("/lectures")
    public JSONObject getLectureList(
            @ModelAttribute("TeacherZoneVO") TeacherZoneVO teacherZoneVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        // VO 파라미터 매핑
        String topMenuType = CommonUtil.isNull(teacherZoneVO.getTopMenuType(), "F");
        String lecType = CommonUtil.isNull(teacherZoneVO.getLecType(), "D");

        params.put("topMenuType", topMenuType);
        params.put("LecType", lecType);
        params.put("searchCategory", CommonUtil.isNull(teacherZoneVO.getSearchCategory(), ""));
        params.put("searchLearningCD", CommonUtil.isNull(teacherZoneVO.getSearchLearningCd(), ""));
        params.put("searchLecType", CommonUtil.isNull(teacherZoneVO.getSearchLecType(), ""));
        params.put("searchSubject", CommonUtil.isNull(teacherZoneVO.getSearchSubject(), ""));
        params.put("searchSubjectCD", CommonUtil.isNull(teacherZoneVO.getSearchSubjectCd(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(teacherZoneVO.getSearchText(), ""));

        // 페이징
        int currentPage = teacherZoneVO.getCurrentPage();
        int pageRow = teacherZoneVO.getPageRow();

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(pageRow);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(pageRow));

        List<HashMap<String, Object>> list = null;
        int listCount = 0;

        // 강의 유형에 따라 분기
        if ("F".equals(topMenuType)) { // 오프라인 강의
            if ("D".equals(lecType)) { // 단과
                list = teacherZoneService.offLectureList(params);
                listCount = teacherZoneService.offLectureListCount(params);
            } else { // 종합반
                // offPkgLectureList 구현 필요
                list = null;
                listCount = 0;
            }
        } else { // 온라인 강의
            if ("D".equals(lecType)) { // 단과
                list = teacherZoneService.onLectureList(params);
                listCount = teacherZoneService.onLectureListCount(params);
            } else if ("Y".equals(lecType)) { // 프리패스
                // onYearLectureList 구현 필요
                list = null;
                listCount = 0;
            } else { // 종합반
                // onPkgLectureList 구현 필요
                list = null;
                listCount = 0;
            }
        }

        paginationInfo.setTotalRecordCount(listCount);

        // 과목 목록
        List<HashMap<String, Object>> subjectList = teacherZoneService.getTeacherSubject(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", list);
        jsonObject.put("totalCount", listCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("pageRow", pageRow);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("subjectList", subjectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 상세 정보 조회
     */
    @Operation(summary = "강의 상세 정보 조회", description = "강의의 상세 정보와 수강생 목록을 조회합니다.")
    @GetMapping("/lecture/{lecCode}")
    public JSONObject getLectureDetail(
            @PathVariable("lecCode") String lecCode,
            @ModelAttribute("TeacherZoneVO") TeacherZoneVO teacherZoneVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("LECCODE", lecCode);
        params.put("topMenuType", CommonUtil.isNull(teacherZoneVO.getTopMenuType(), "O"));
        params.put("LecType", CommonUtil.isNull(teacherZoneVO.getLecType(), "D"));
        params.put("searchViewStartDate", CommonUtil.isNull(teacherZoneVO.getSearchViewStartDate(), ""));
        params.put("searchViewEndDate", CommonUtil.isNull(teacherZoneVO.getSearchViewEndDate(), ""));
        params.put("searchUser", CommonUtil.isNull(teacherZoneVO.getSearchUser(), ""));
        params.put("MST_LECCODE", CommonUtil.isNull(teacherZoneVO.getMstLecCode(), ""));

        HashMap<String, Object> view = null;
        List<HashMap<String, Object>> stdList = null;

        String topMenuType = params.get("topMenuType");
        String lecType = params.get("LecType");

        if ("F".equals(topMenuType)) { // 오프라인
            if ("D".equals(lecType)) { // 단과
                view = teacherZoneService.offLectureView(params);
                stdList = teacherZoneService.offLectureStdList(params);
            } else { // 종합반
                // offPkgLectureView 구현 필요
            }
        } else { // 온라인
            view = teacherZoneService.onLectureView(params);
            if ("D".equals(lecType)) { // 단과
                stdList = teacherZoneService.onLectureStdList(params);
            } else if ("Y".equals(lecType)) { // 프리패스
                // onYearLectureStdList 구현 필요
            } else { // 종합반
                // onPkgLectureStdList 구현 필요
            }
        }

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("view", view);
        jsonObject.put("stdList", stdList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 동영상 강의 목록 조회
     */
    @Operation(summary = "동영상 강의 목록 조회", description = "교수의 동영상 강의 목록을 조회합니다.")
    @GetMapping("/movies")
    public JSONObject getMovieLectureList(
            @ModelAttribute("TeacherZoneVO") TeacherZoneVO teacherZoneVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("searchCategory", CommonUtil.isNull(teacherZoneVO.getSearchCategory(), ""));
        params.put("searchLearningCD", CommonUtil.isNull(teacherZoneVO.getSearchLearningCd(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(teacherZoneVO.getSearchText(), ""));
        params.put("lMenuType", CommonUtil.isNull(teacherZoneVO.getlMenuType(), "1"));

        // 페이징
        int currentPage = teacherZoneVO.getCurrentPage();
        int pageRow = teacherZoneVO.getPageRow();

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(pageRow);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(pageRow));

        List<HashMap<String, Object>> list = teacherZoneService.movieLectureList(params);
        int listCount = teacherZoneService.movieLectureListCount(params);

        paginationInfo.setTotalRecordCount(listCount);

        // 과목 목록
        List<HashMap<String, Object>> subjectList = teacherZoneService.getTeacherSubject(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", list);
        jsonObject.put("totalCount", listCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("pageRow", pageRow);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("subjectList", subjectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 동영상 목록 조회
     */
    @Operation(summary = "강의 동영상 목록 조회", description = "특정 강의의 동영상 목록을 조회합니다.")
    @GetMapping("/lecture/{lecCode}/movies")
    public JSONObject getMovieList(
            @PathVariable("lecCode") String lecCode,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("LECCODE", lecCode);

        HashMap<String, Object> view = teacherZoneService.lectureView(params);
        List<HashMap<String, Object>> movieList = teacherZoneService.movieList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("view", view);
        jsonObject.put("movieList", movieList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 주문 통계 조회
     */
    @Operation(summary = "주문 통계 조회", description = "교수의 강의 주문 통계를 조회합니다.")
    @GetMapping("/order-stats")
    public JSONObject getOrderStats(
            @ModelAttribute("TeacherZoneVO") TeacherZoneVO teacherZoneVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        List<HashMap<String, Object>> statList = teacherZoneService.onOrderStat(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("statList", statList);
        jsonObject.put("retMsg", "OK");

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
            params.put("searchUserId", "");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
                params.put("searchUserId", loginInfo.get("USER_ID"));
            } else {
                params.put("USER_ID", "");
                params.put("searchUserId", "");
            }
        }

        // 기본 날짜 설정 (현재 월의 1일 ~ 마지막 날)
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        String lastDay = String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        SimpleDateFormat sDate = new SimpleDateFormat("yyyyMM01");
        SimpleDateFormat eDate = new SimpleDateFormat("yyyyMM" + lastDay);
        String startDay = sDate.format(date);
        String endDay = eDate.format(date);

        params.put("searchStartDate", CommonUtil.isNull(request.getParameter("searchStartDate"), startDay));
        params.put("searchEndDate", CommonUtil.isNull(request.getParameter("searchEndDate"), endDay));
    }
}
