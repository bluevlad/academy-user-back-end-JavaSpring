package com.academy.index;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.index.service.IndexService;
import com.academy.index.service.IndexVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * IndexApi.java
 * 메인/인덱스 페이지 REST API Controller
 */
@Tag(name = "Index", description = "메인/인덱스 페이지 API")
@RestController
@RequestMapping("/api/index")
public class IndexApi extends CORSFilter {

    private final IndexService indexService;

    @Autowired
    public IndexApi(IndexService indexService) {
        this.indexService = indexService;
    }

    /**
     * 카테고리 목록 조회
     */
    @Operation(summary = "카테고리 목록 조회", description = "직종 카테고리 목록을 조회합니다.")
    @GetMapping("/getCategory")
    public JSONObject getCategory(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenuType", CommonUtil.isNull(indexVO.getTopMenuType(), "O"));

        List<HashMap<String, Object>> categoryList = indexService.getCategory(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("categoryList", categoryList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 과목 목록 조회
     */
    @Operation(summary = "과목 목록 조회", description = "카테고리별 과목 목록을 조회합니다.")
    @GetMapping("/getSubjectList")
    public JSONObject getSubjectList(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(indexVO.getCategoryCd(), ""));

        List<HashMap<String, Object>> subjectList = indexService.getSubjectList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectList", subjectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강좌 목록 조회
     */
    @Operation(summary = "강좌 목록 조회", description = "과목별 강좌 목록을 조회합니다.")
    @GetMapping("/getLectureList")
    public JSONObject getLectureList(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("CATEGORY_CD", CommonUtil.isNull(indexVO.getCategoryCd(), ""));
        params.put("SUBJECT_SJT_CD", CommonUtil.isNull(indexVO.getSubjectCd(), ""));

        List<HashMap<String, Object>> lectureList = indexService.getLectureList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("lectureList", lectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 메인 공지사항 조회
     */
    @Operation(summary = "메인 공지사항 조회", description = "메인 페이지용 공지사항 목록을 조회합니다.")
    @GetMapping("/getMainNotice")
    public JSONObject getMainNotice(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(indexVO.getBoardMngSeq(), "NOTICE_000"));

        List<HashMap<String, Object>> noticeList = indexService.getMainNotice(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("noticeList", noticeList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 메인 추천 공지 조회
     */
    @Operation(summary = "메인 추천 공지 조회", description = "메인 페이지용 추천 공지 목록을 조회합니다.")
    @GetMapping("/getMainRecommendNotice")
    public JSONObject getMainRecommendNotice(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(indexVO.getBoardMngSeq(), "NOTICE_000"));

        List<HashMap<String, Object>> recommendList = indexService.getMainRecommendNotice(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("recommendList", recommendList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 카테고리별 동영상 과목 조회
     */
    @Operation(summary = "카테고리별 동영상 과목 조회", description = "카테고리별 동영상 강의 과목 목록을 조회합니다.")
    @GetMapping("/getMovieSubjectByCategory")
    public JSONObject getMovieSubjectByCategory(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(indexVO.getTopMenu(), ""));
        params.put("topMenuType", CommonUtil.isNull(indexVO.getTopMenuType(), "O"));
        params.put("SEARCHSERIESCODE", CommonUtil.isNull(indexVO.getSearchSeriesCode(), ""));

        List<HashMap<String, Object>> subjectList = indexService.getMovieSubjectByCategory(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectList", subjectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 과목별 동영상 강사 조회
     */
    @Operation(summary = "과목별 동영상 강사 조회", description = "과목별 동영상 강의 강사 목록을 조회합니다.")
    @GetMapping("/getMovieTeacherBySubject")
    public JSONObject getMovieTeacherBySubject(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(indexVO.getTopMenu(), ""));

        List<HashMap<String, Object>> teacherList = indexService.getMovieTeacherBySubject(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherList", teacherList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 시험 D-Day 조회
     */
    @Operation(summary = "시험 D-Day 조회", description = "시험 D-Day 목록을 조회합니다.")
    @GetMapping("/getExamDday")
    public JSONObject getExamDday(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(indexVO.getTopMenu(), "MAIN"));

        List<HashMap<String, Object>> ddayList = indexService.getExamDday(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("ddayList", ddayList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 신간 도서 목록 조회
     */
    @Operation(summary = "신간 도서 목록 조회", description = "신간 도서 목록을 조회합니다.")
    @GetMapping("/getNewBookList")
    public JSONObject getNewBookList(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("BOOKTYPE", CommonUtil.isNull(indexVO.getBookType(), "NEW"));

        List<HashMap<String, Object>> bookList = indexService.getNewBookList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("bookList", bookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 과목 목록 조회
     */
    @Operation(summary = "교수 과목 목록 조회", description = "교수 과목 목록을 조회합니다.")
    @GetMapping("/getTeacherSubject")
    public JSONObject getTeacherSubject(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        List<HashMap<String, Object>> subjectList = indexService.getTeacherSubject(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectList", subjectList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 목록 조회
     */
    @Operation(summary = "교수 목록 조회", description = "교수 목록을 조회합니다.")
    @GetMapping("/getTeacherList")
    public JSONObject getTeacherList(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenuType", CommonUtil.isNull(indexVO.getTopMenuType(), "O"));

        List<HashMap<String, Object>> subjectList = indexService.getTeacherSubjectList(params);
        List<HashMap<String, Object>> teacherList = indexService.getQuickTeacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectList", subjectList);
        jsonObject.put("teacherList", teacherList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 이슈 목록 조회
     */
    @Operation(summary = "이슈 목록 조회", description = "이슈 목록(이벤트+공지)을 조회합니다.")
    @GetMapping("/getIssueList")
    public JSONObject getIssueList(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(indexVO.getTopMenu(), ""));

        List<HashMap<String, Object>> issueList = indexService.getIssueList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("issueList", issueList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 서브메인 강좌 목록 조회
     */
    @Operation(summary = "서브메인 강좌 목록 조회", description = "서브메인 페이지용 강좌 목록을 조회합니다.")
    @GetMapping("/getSubMainLectureList")
    public JSONObject getSubMainLectureList(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenu", CommonUtil.isNull(indexVO.getTopMenu(), ""));
        params.put("SUBLECTYPE", CommonUtil.isNull(indexVO.getSubLecType(), ""));

        List<HashMap<String, Object>> lectureList = indexService.getSubMainLectureList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("lectureList", lectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 팝업 목록 조회
     */
    @Operation(summary = "팝업 목록 조회", description = "활성화된 팝업 목록을 조회합니다.")
    @GetMapping("/getPopupList")
    public JSONObject getPopupList(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenuType", CommonUtil.isNull(indexVO.getTopMenuType(), "O"));
        params.put("topMenu", CommonUtil.isNull(indexVO.getTopMenu(), "MAIN"));

        List<HashMap<String, Object>> popupList = indexService.getPopupList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("popupList", popupList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 팝업 조회수 증가
     */
    @Operation(summary = "팝업 조회수 증가", description = "팝업 조회수를 증가시킵니다.")
    @PostMapping("/updatePopupViewCount")
    public JSONObject updatePopupViewCount(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("SEQ", CommonUtil.isNull(indexVO.getSeq(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            indexService.updatePopupViewCount(params);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 배너 목록 조회
     */
    @Operation(summary = "배너 목록 조회", description = "활성화된 배너 목록을 조회합니다.")
    @GetMapping("/getBannerList")
    public JSONObject getBannerList(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("topMenuType", CommonUtil.isNull(indexVO.getTopMenuType(), "O"));
        params.put("topMenu", CommonUtil.isNull(indexVO.getTopMenu(), "MAIN"));
        params.put("SCREEN_GUBUN", CommonUtil.isNull(indexVO.getScreenGubun(), "M"));
        params.put("CATEGORY_CD", CommonUtil.isNull(indexVO.getCategoryCd(), ""));
        params.put("BANNER_NO", CommonUtil.isNull(indexVO.getBannerNo(), ""));

        List<HashMap<String, Object>> bannerList = indexService.getBannerList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("bannerList", bannerList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 배너 클릭수 증가
     */
    @Operation(summary = "배너 클릭수 증가", description = "배너 클릭수를 증가시킵니다.")
    @PostMapping("/updateBannerViewCount")
    public JSONObject updateBannerViewCount(
            @ModelAttribute("IndexVO") IndexVO indexVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, request);

        params.put("SEQ", CommonUtil.isNull(indexVO.getBannerSeq(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int result = indexService.updateBannerViewCount(params);
            if (result > 0) {
                jsonObject.put("retMsg", "OK");
            } else {
                jsonObject.put("retMsg", "FAIL");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 파라미터 설정
     */
    @SuppressWarnings("unchecked")
    private void setParam(HashMap<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            params.put("USER_ID", "");
            params.put("USER_NM", "");
            params.put("REG_ID", "");
            params.put("UPD_ID", "");
            params.put("ISLOGIN", "N");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
                params.put("USER_NM", loginInfo.get("USER_NM"));
                params.put("USER_ROLE", loginInfo.get("USER_ROLE"));
                params.put("REG_ID", loginInfo.get("USER_ID"));
                params.put("UPD_ID", loginInfo.get("USER_ID"));
                params.put("ISLOGIN", "Y");
            } else {
                params.put("USER_ID", "");
                params.put("USER_NM", "");
                params.put("REG_ID", "");
                params.put("UPD_ID", "");
                params.put("ISLOGIN", "N");
            }
        }

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "O"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "MAIN"));
    }
}
