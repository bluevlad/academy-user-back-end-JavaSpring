package com.academy.board.examGuide;

import com.academy.board.examGuide.service.BoardExamGuideService;
import com.academy.board.examGuide.service.BoardExamGuideVO;
import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
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
 * BoardExamGuideApi.java
 * 시험안내 게시판 REST API Controller
 */
@Tag(name = "BoardExamGuide", description = "시험안내 게시판 API")
@RestController
@RequestMapping("/api/board/examGuide")
public class BoardExamGuideApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final BoardExamGuideService boardExamGuideService;

    @Autowired
    public BoardExamGuideApi(BoardExamGuideService boardExamGuideService) {
        this.boardExamGuideService = boardExamGuideService;
    }

    /**
     * 시험안내 게시판 목록 조회
     */
    @Operation(summary = "시험안내 게시판 목록 조회", description = "시험안내 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("BoardExamGuideVO") BoardExamGuideVO boardExamGuideVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardExamGuideVO.getBoardMngSeq(), ""));
        params.put("BOARDTYPE", CommonUtil.isNull(boardExamGuideVO.getBoardType(), ""));
        params.put("SEARCHTEXT", CommonUtil.isNull(boardExamGuideVO.getSearchText(), ""));
        params.put("SEARCHKIND", CommonUtil.isNull(boardExamGuideVO.getSearchKind(), ""));
        params.put("GUIDEGUBUN", CommonUtil.isNull(boardExamGuideVO.getGuideGubun(), "1"));
        params.put("SCH_EXAM_TYPE", CommonUtil.isNull(boardExamGuideVO.getSchExamType(), ""));
        params.put("SCH_EXAM_AREA", CommonUtil.isNull(boardExamGuideVO.getSchExamArea(), ""));

        String boardType = params.get("BOARDTYPE");

        // 게시판 타입이 1, 2, question인 경우에만 목록 조회
        if (!"1".equals(boardType) && !"2".equals(boardType) && !"question".equals(boardType)) {
            HashMap<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "해당 게시판 타입에서는 목록 조회를 지원하지 않습니다.");
            return new JSONObject(jsonObject);
        }

        int currentPage = boardExamGuideVO.getCurrentPage() > 0 ? boardExamGuideVO.getCurrentPage() : 1;
        int recordCountPerPage = boardExamGuideVO.getPageRow() > 0 ? boardExamGuideVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = boardExamGuideService.boardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = boardExamGuideService.boardList(params);
        HashMap<String, Object> boardInfo = boardExamGuideService.getBoardInfo(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("boardInfo", boardInfo);
        jsonObject.put("list", resultList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());

        // NOTICE_008 게시판인 경우 공고 구분 정보 추가
        if ("NOTICE_008".equals(params.get("BOARD_MNG_SEQ"))) {
            HashMap<String, Object> codeParams = new HashMap<>();
            codeParams.put("SYS_CD", "PUB_TYPE");
            jsonObject.put("pubGubun", boardExamGuideService.getPubGubun(codeParams));
            codeParams.put("SYS_CD", "PUB_AREA");
            jsonObject.put("pubArea", boardExamGuideService.getPubGubun(codeParams));
        }

        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 게시물 상세 조회
     */
    @Operation(summary = "게시물 상세 조회", description = "시험안내 게시물 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("BoardExamGuideVO") BoardExamGuideVO boardExamGuideVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        setParam(params, request);

        params.put("BOARD_SEQ", CommonUtil.isNull(boardExamGuideVO.getBoardSeq(), ""));
        params.put("BOARD_MNG_SEQ", CommonUtil.isNull(boardExamGuideVO.getBoardMngSeq(), ""));
        params.put("PARENT_BOARD_SEQ", CommonUtil.isNull(boardExamGuideVO.getParentBoardSeq(), ""));

        // 조회수 증가
        boardExamGuideService.updateBoardHits(params);

        HashMap<String, Object> detailView = boardExamGuideService.boardView(params);
        List<HashMap<String, Object>> attachFiles = boardExamGuideService.boardAttachFile(params);
        List<HashMap<String, Object>> attachImgs = boardExamGuideService.boardAttachFileImg(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("detailView", detailView);
        jsonObject.put("attachFiles", attachFiles);
        jsonObject.put("attachImgs", attachImgs);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 공고 구분 코드 목록 조회
     */
    @Operation(summary = "공고 구분 코드 목록 조회", description = "공고 구분 코드 목록을 조회합니다.")
    @GetMapping("/getPubGubun")
    public JSONObject getPubGubun(
            @RequestParam(value = "sysCd", required = false, defaultValue = "PUB_TYPE") String sysCd,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> params = new HashMap<>();
        params.put("SYS_CD", sysCd);

        List<HashMap<String, Object>> pubGubun = boardExamGuideService.getPubGubun(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", pubGubun);
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

        params.put("topMenuType", CommonUtil.isNull(request.getParameter("topMenuType"), "F"));
        params.put("topMenu", CommonUtil.isNull(request.getParameter("topMenu"), "MAIN"));
    }
}
