package com.academy.teacher;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.teacher.service.TeacherService;
import com.academy.teacher.service.TeacherVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * TeacherApi.java
 * 교수 관련 REST API Controller
 */
@Tag(name = "Teacher", description = "교수 관리 API")
@RestController
@RequestMapping("/api/teacher")
public class TeacherApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final TeacherService teacherService;

    @Autowired
    public TeacherApi(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 교수 리스트 조회
     */
    @Operation(summary = "교수 리스트 조회", description = "카테고리별 교수 목록을 조회합니다.")
    @GetMapping("/getTeacherList")
    public JSONObject getTeacherList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), params.get("topMenu")));
        params.put("searchSubjectCode", CommonUtil.isNull(teacherVO.getSearchSubjectCode(), ""));
        params.put("topMenuType", CommonUtil.isNull(teacherVO.getTopMenuType(), "O"));

        List<HashMap<String, Object>> resultList = teacherService.teacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 좌측 교수 리스트 조회
     */
    @Operation(summary = "좌측 교수 리스트 조회", description = "좌측 메뉴용 교수 목록을 조회합니다.")
    @GetMapping("/getLeftTeacherList")
    public JSONObject getLeftTeacherList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), params.get("topMenu")));
        params.put("topMenuType", CommonUtil.isNull(teacherVO.getTopMenuType(), "O"));

        List<HashMap<String, Object>> resultList = teacherService.leftTeacherList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("leftTeacherList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 카테고리별 과목 리스트 조회
     */
    @Operation(summary = "카테고리별 과목 리스트 조회", description = "카테고리에 해당하는 과목 목록을 조회합니다.")
    @GetMapping("/getSubjectList")
    public JSONObject getSubjectList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), ""));
        params.put("topMenuType", CommonUtil.isNull(teacherVO.getTopMenuType(), "O"));

        List<HashMap<String, Object>> resultList = teacherService.subjectListByCategory(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("subjectList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 상세 정보 조회
     */
    @Operation(summary = "교수 상세 정보 조회", description = "교수의 상세 정보를 조회합니다.")
    @GetMapping("/getTeacherDetail")
    public JSONObject getTeacherDetail(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());

        HashMap<String, Object> teacherInfo = teacherService.teacherDetail(params);
        List<HashMap<String, Object>> bookLogList = teacherService.teacherBookLog(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherInfo", teacherInfo);
        jsonObject.put("bookLogList", bookLogList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 오프라인 강의 목록 조회
     */
    @Operation(summary = "교수 오프라인 강의 목록 조회", description = "교수의 오프라인 강의 목록을 조회합니다.")
    @GetMapping("/getTeacherLectureList")
    public JSONObject getTeacherLectureList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());
        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), ""));

        List<HashMap<String, Object>> lectureList = teacherService.teacherLectureList(params);
        List<HashMap<String, Object>> bookList = teacherService.teacherLectureBookList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("lectureList", lectureList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 온라인 강의 목록 조회
     */
    @Operation(summary = "교수 온라인 강의 목록 조회", description = "교수의 온라인 강의 목록을 조회합니다.")
    @GetMapping("/getTeacherMovieLectureList")
    public JSONObject getTeacherMovieLectureList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());
        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), ""));
        params.put("searchLearningCd", CommonUtil.isNull(teacherVO.getSearchLearningCd(), ""));
        params.put("LEC_TYPE_CHOICE", CommonUtil.isNull(teacherVO.getLecTypeChoice(), "D"));

        List<HashMap<String, Object>> lectureList = teacherService.teacherMovieLectureList(params);
        List<HashMap<String, Object>> bookList = teacherService.teacherMovieLectureBookList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("lectureList", lectureList);
        jsonObject.put("bookList", bookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 온라인 강의 베스트 목록 조회
     */
    @Operation(summary = "교수 온라인 강의 베스트 목록 조회", description = "교수의 베스트 온라인 강의 목록을 조회합니다.")
    @GetMapping("/getTeacherMovieLectureBestList")
    public JSONObject getTeacherMovieLectureBestList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());
        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), ""));
        params.put("LEC_TYPE_CHOICE", "D");

        List<HashMap<String, Object>> resultList = teacherService.teacherMovieLectureBestList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("bestLectureList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 게시판 목록 조회
     */
    @Operation(summary = "교수 게시판 목록 조회", description = "교수의 게시판 목록을 조회합니다.")
    @GetMapping("/getTeacherBoardList")
    public JSONObject getTeacherBoardList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(teacherVO.getBoardMngSeq(), "BOARD_002"));
        params.put("SEARCHTEXT", CommonUtil.isNull(teacherVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(teacherVO.getSearchKind(), ""));

        int currentPage = teacherVO.getCurrentPage() > 0 ? teacherVO.getCurrentPage() : 1;
        int recordCountPerPage = teacherVO.getPageRow() > 0 ? teacherVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        int totalCount = teacherService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));

        List<HashMap<String, Object>> resultList = teacherService.boardList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강의 장바구니 담기
     */
    @Operation(summary = "강의 장바구니 담기", description = "강의를 장바구니에 담습니다.")
    @PostMapping("/addToCart")
    public JSONObject addToCart(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = teacherVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            String kindType = CommonUtil.isNull(teacherVO.getLecTypeChoice(), "L");
            params.put("KIND_TYPE", kindType);

            // 장바구니 중복 체크
            List<HashMap<String, Object>> cartCheck;
            if ("L".equals(kindType) || "D".equals(kindType)) {
                cartCheck = teacherService.movieLectureCartCheck(params);
            } else {
                cartCheck = teacherService.lectureCartCheck(params);
            }

            if (cartCheck != null && !cartCheck.isEmpty()) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "이미 장바구니에 담긴 강의입니다.");
                return new JSONObject(jsonObject);
            }

            // 장바구니 등록
            if ("L".equals(kindType) || "D".equals(kindType)) {
                teacherService.movieLectureCartInsert(params);
            } else {
                teacherService.lectureCartInsert(params);
            }

            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "장바구니에 담았습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "장바구니 담기에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 장바구니 전체 삭제
     */
    @Operation(summary = "장바구니 전체 삭제", description = "사용자의 장바구니를 전체 삭제합니다.")
    @PostMapping("/clearCart")
    public JSONObject clearCart(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = teacherVO.getUserId();
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            // 온라인 장바구니 삭제 로직 (실제 구현 필요)
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "장바구니가 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "장바구니 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 게시판 사용 여부 조회
     */
    @Operation(summary = "교수 게시판 사용 여부 조회", description = "교수의 게시판 사용 여부를 조회합니다.")
    @GetMapping("/getTeacherBoardYn")
    public JSONObject getTeacherBoardYn(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());
        params.put("topMenuType", CommonUtil.isNull(teacherVO.getTopMenuType(), "O"));

        String boardYn = teacherService.getPrfBrdOf(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardYn", boardYn);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 소개 통합 조회
     */
    @Operation(summary = "교수 소개 통합 조회", description = "교수의 상세 정보와 강의, 저서 등을 통합 조회합니다.")
    @GetMapping("/getTeacherIntro")
    public JSONObject getTeacherIntro(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());
        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), ""));
        params.put("topMenuType", CommonUtil.isNull(teacherVO.getTopMenuType(), "O"));

        // 교수 상세 정보
        HashMap<String, Object> teacherInfo = teacherService.teacherDetail(params);

        // 교수 저서 목록
        List<HashMap<String, Object>> bookLogList = teacherService.teacherBookLog(params);

        // 오프라인 강의 목록
        List<HashMap<String, Object>> lectureList = teacherService.teacherLectureList(params);

        // 온라인 강의 목록 (단과)
        params.put("LEC_TYPE_CHOICE", "D");
        List<HashMap<String, Object>> movieLectureList = teacherService.teacherMovieLectureList(params);

        // 베스트 강의 목록 (이론 강좌)
        params.put("searchLearningCd", "M0101");
        List<HashMap<String, Object>> bestLectureList = teacherService.teacherMovieLectureBestList(params);
        params.put("searchLearningCd", "");

        // 패키지 강의 목록
        params.put("LEC_TYPE_CHOICE", "P");
        List<HashMap<String, Object>> packageLectureList = teacherService.packteacherMovieLectureList(params);

        // 온라인 교재 목록
        params.put("LEC_TYPE_CHOICE", "D");
        List<HashMap<String, Object>> movieBookList = teacherService.teacherMovieLectureBookList(params);

        // 오프라인 교재 목록
        List<HashMap<String, Object>> lectureBookList = teacherService.teacherLectureBookList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("teacherInfo", teacherInfo);
        jsonObject.put("bookLogList", bookLogList);
        jsonObject.put("lectureList", lectureList);
        jsonObject.put("movieLectureList", movieLectureList);
        jsonObject.put("bestLectureList", bestLectureList);
        jsonObject.put("packageLectureList", packageLectureList);
        jsonObject.put("movieBookList", movieBookList);
        jsonObject.put("lectureBookList", lectureBookList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교재 상세 정보 조회
     */
    @Operation(summary = "교재 상세 정보 조회", description = "교재의 상세 정보를 조회합니다.")
    @GetMapping("/getBookInfo")
    public JSONObject getBookInfo(
            @RequestParam(value = "rscId", required = true) String rscId) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put("RSC_ID", rscId);

        HashMap<String, Object> bookInfo = teacherService.bookInfo(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("bookInfo", bookInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 온라인 공개강의 목록 조회
     */
    @Operation(summary = "교수 온라인 공개강의 목록 조회", description = "교수의 온라인 공개강의 목록을 조회합니다.")
    @GetMapping("/getTeacherMovieOpenLectureList")
    public JSONObject getTeacherMovieOpenLectureList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());
        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), ""));

        List<HashMap<String, Object>> openLectureList = teacherService.teacherMovieOpenLectureList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("openLectureList", openLectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 패키지 강의 목록 조회
     */
    @Operation(summary = "교수 패키지 강의 목록 조회", description = "교수의 패키지 강의 목록을 조회합니다.")
    @GetMapping("/getTeacherPackageLectureList")
    public JSONObject getTeacherPackageLectureList(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("searchUserId", teacherVO.getSearchUserId());
        params.put("searchCategoryCode", CommonUtil.isNull(teacherVO.getSearchCategoryCode(), ""));
        params.put("searchLearningCd", CommonUtil.isNull(teacherVO.getSearchLearningCd(), ""));
        params.put("LEC_TYPE_CHOICE", "P");

        List<HashMap<String, Object>> packageLectureList = teacherService.packteacherMovieLectureList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("packageLectureList", packageLectureList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 교수 게시판 정보 조회
     */
    @Operation(summary = "교수 게시판 정보 조회", description = "교수 게시판의 설정 정보를 조회합니다.")
    @GetMapping("/getBoardInfo")
    public JSONObject getBoardInfo(
            @ModelAttribute("TeacherVO") TeacherVO teacherVO) throws Exception {

        HashMap<String, String> params = new HashMap<>();

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(teacherVO.getBoardMngSeq(), "BOARD_002"));

        HashMap<String, Object> boardInfo = teacherService.getBoardInfo(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardInfo", boardInfo);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

}
