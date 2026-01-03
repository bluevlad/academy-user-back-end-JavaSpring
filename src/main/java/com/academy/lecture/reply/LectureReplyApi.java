package com.academy.lecture.reply;

import com.academy.common.CommonUtil;
import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.lecture.reply.service.LectureReplyService;
import com.academy.lecture.reply.service.LectureReplyVO;
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
 * LectureReplyApi.java
 * 강의 수강후기 REST API Controller
 */
@Tag(name = "LectureReply", description = "강의 수강후기 관리 API")
@RestController
@RequestMapping("/api/lecture/reply")
public class LectureReplyApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final LectureReplyService lectureReplyService;

    @Autowired
    public LectureReplyApi(LectureReplyService lectureReplyService) {
        this.lectureReplyService = lectureReplyService;
    }

    /**
     * 수강후기 목록 조회
     */
    @Operation(summary = "수강후기 목록 조회", description = "강의 수강후기 목록을 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("LectureReplyVO") LectureReplyVO lectureReplyVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, lectureReplyVO, request);

        // 페이지네이션 설정
        int currentPage = lectureReplyVO.getCurrentPage() > 0 ? lectureReplyVO.getCurrentPage() : 1;
        int pageRow = lectureReplyVO.getPageRow() > 0 ? lectureReplyVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(pageRow);
        paginationInfo.setPageSize(10);

        params.put("startNo", paginationInfo.getFirstRecordIndex());
        params.put("endNo", paginationInfo.getFirstRecordIndex() + pageRow);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 목록 조회
        List<HashMap<String, Object>> resultList = lectureReplyService.lectureReplyList(params);
        int totalCount = lectureReplyService.lectureReplyListCount(params);

        paginationInfo.setTotalRecordCount(totalCount);

        jsonObject.put("replyList", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPages", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 메인 수강후기 목록 조회
     */
    @Operation(summary = "메인 수강후기 목록 조회", description = "메인 페이지에 표시할 수강후기 목록을 조회합니다.")
    @GetMapping("/getMainList")
    public JSONObject getMainList(
            @ModelAttribute("LectureReplyVO") LectureReplyVO lectureReplyVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, lectureReplyVO, request);

        int pageRow = lectureReplyVO.getPageRow() > 0 ? lectureReplyVO.getPageRow() : 10;
        params.put("endNo", pageRow);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> resultList = lectureReplyService.lectureReplyMainList(params);

        jsonObject.put("replyList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 강의별 수강후기 상세 목록 조회
     */
    @Operation(summary = "강의별 수강후기 상세 목록 조회", description = "특정 강의의 수강후기 목록을 조회합니다.")
    @GetMapping("/getDetailList")
    public JSONObject getDetailList(
            @ModelAttribute("LectureReplyVO") LectureReplyVO lectureReplyVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, lectureReplyVO, request);

        params.put("LECCODE", CommonUtil.isNull(lectureReplyVO.getLeccode(), ""));

        // 페이지네이션 설정
        int currentPage = lectureReplyVO.getCurrentPage() > 0 ? lectureReplyVO.getCurrentPage() : 1;
        int pageRow = lectureReplyVO.getPageRow() > 0 ? lectureReplyVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(pageRow);
        paginationInfo.setPageSize(10);

        params.put("startNo", paginationInfo.getFirstRecordIndex());
        params.put("endNo", paginationInfo.getFirstRecordIndex() + pageRow);

        HashMap<String, Object> jsonObject = new HashMap<>();

        // 목록 조회
        List<HashMap<String, Object>> resultList = lectureReplyService.detailReplyList(params);
        int totalCount = lectureReplyService.detailReplyListCount(params);

        paginationInfo.setTotalRecordCount(totalCount);

        jsonObject.put("replyList", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPages", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 수강후기 작성 가능 여부 체크
     */
    @Operation(summary = "수강후기 작성 가능 여부 체크", description = "해당 강의에 수강후기 작성 가능 여부를 확인합니다.")
    @GetMapping("/checkCanWrite")
    public JSONObject checkCanWrite(
            @ModelAttribute("LectureReplyVO") LectureReplyVO lectureReplyVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, lectureReplyVO, request);

        params.put("LECCODE", CommonUtil.isNull(lectureReplyVO.getLeccode(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = (String) params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("canWrite", false);
            jsonObject.put("message", "로그인이 필요합니다.");
            jsonObject.put("retMsg", "FAIL");
            return new JSONObject(jsonObject);
        }

        int count = lectureReplyService.lectureIsReply(params);

        if (count > 0) {
            jsonObject.put("canWrite", true);
            jsonObject.put("message", "수강후기 작성이 가능합니다.");
        } else {
            jsonObject.put("canWrite", false);
            jsonObject.put("message", "해당 강의를 수강한 이력이 없습니다.");
        }
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 과목 목록 조회
     */
    @Operation(summary = "과목 목록 조회", description = "수강후기 필터링을 위한 과목 목록을 조회합니다.")
    @GetMapping("/getSubjectList")
    public JSONObject getSubjectList(
            @ModelAttribute("LectureReplyVO") LectureReplyVO lectureReplyVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, lectureReplyVO, request);

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> resultList = lectureReplyService.getSubjectList(params);

        jsonObject.put("subjectList", resultList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 수강후기 등록
     */
    @Operation(summary = "수강후기 등록", description = "수강후기를 등록합니다.")
    @PostMapping("/insertReply")
    public JSONObject insertReply(
            @ModelAttribute("LectureReplyVO") LectureReplyVO lectureReplyVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, lectureReplyVO, request);

        params.put("LECCODE", CommonUtil.isNull(lectureReplyVO.getLeccode(), ""));
        params.put("TITLE", CommonUtil.isNull(lectureReplyVO.getTitle(), ""));
        params.put("CONTENT", CommonUtil.isNull(lectureReplyVO.getContent(), ""));
        params.put("CHOICE_POINT", CommonUtil.isNull(lectureReplyVO.getChoicePoint(), "5"));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = (String) params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        // 수강 여부 확인
        int count = lectureReplyService.lectureIsReply(params);
        if (count == 0) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "해당 강의를 수강한 이력이 없습니다.");
            return new JSONObject(jsonObject);
        }

        try {
            lectureReplyService.insertReply(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "수강후기가 등록되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "수강후기 등록에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 수강후기 삭제
     */
    @Operation(summary = "수강후기 삭제", description = "수강후기를 삭제합니다.")
    @PostMapping("/deleteReply")
    public JSONObject deleteReply(
            @ModelAttribute("LectureReplyVO") LectureReplyVO lectureReplyVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        setParam(params, lectureReplyVO, request);

        params.put("DELETE_LECCODE", CommonUtil.isNull(lectureReplyVO.getDeleteLeccode(), ""));
        params.put("DELETE_SEQ", CommonUtil.isNull(lectureReplyVO.getDeleteSeq(), ""));

        HashMap<String, Object> jsonObject = new HashMap<>();

        String userId = (String) params.get("USER_ID");
        if (userId == null || userId.isEmpty()) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "로그인이 필요합니다.");
            return new JSONObject(jsonObject);
        }

        try {
            lectureReplyService.replyDelete(params);
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "수강후기가 삭제되었습니다.");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", "수강후기 삭제에 실패했습니다.");
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 파라미터 설정
     */
    @SuppressWarnings("unchecked")
    private void setParam(HashMap<String, Object> params, LectureReplyVO lectureReplyVO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            params.put("USER_ID", "");
            params.put("USER_NM", "");
        } else {
            HashMap<String, String> loginInfo = (HashMap<String, String>) session.getAttribute("userInfo");
            if (loginInfo != null && !loginInfo.isEmpty()) {
                params.put("USER_ID", loginInfo.get("USER_ID"));
                params.put("USER_NM", loginInfo.get("USER_NM"));
            } else {
                params.put("USER_ID", "");
                params.put("USER_NM", "");
            }
        }

        params.put("topMenu", CommonUtil.isNull(lectureReplyVO.getTopMenu(), ""));
        params.put("topMenuType", CommonUtil.isNull(lectureReplyVO.getTopMenuType(), ""));
        params.put("ISSUBMAIN", CommonUtil.isNull(lectureReplyVO.getIsSubMain(), ""));
        params.put("SUBJECT_TEACHER", CommonUtil.isNull(lectureReplyVO.getSubjectTeacher(), ""));
        params.put("SEARCHSUBJECT", CommonUtil.isNull(lectureReplyVO.getSearchSubject(), ""));
        params.put("SEARCHTEAC", CommonUtil.isNull(lectureReplyVO.getSearchTeacher(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(lectureReplyVO.getSearchKind(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(lectureReplyVO.getSearchText(), ""));
    }
}
