package com.academy.coop;

import com.academy.coop.service.CoopService;
import com.academy.coop.service.CoopVO;
import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * CoopApi.java
 * 제휴사 게시판 REST API Controller
 */
@Tag(name = "Coop", description = "제휴사 게시판 API")
@RestController
@RequestMapping("/api/coop")
public class CoopApi extends CORSFilter {

    @Value("${pageUnit:10}")
    private int pageUnit;

    private final CoopService coopService;

    @Autowired
    public CoopApi(CoopService coopService) {
        this.coopService = coopService;
    }

    /**
     * 제휴사 게시판 목록 조회
     */
    @Operation(summary = "제휴사 게시판 목록 조회", description = "제휴사 게시판 목록을 페이징하여 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(
            @ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("COOP_AREA", CommonUtil.isNull(coopVO.getCoopArea(), ""));
        params.put("COOP_CATE", CommonUtil.isNull(coopVO.getCoopCate(), ""));
        params.put("searchKind", CommonUtil.isNull(coopVO.getSearchKind(), ""));
        params.put("searchText", CommonUtil.isNull(coopVO.getSearchText(), ""));

        int currentPage = coopVO.getCurrentPage() > 0 ? coopVO.getCurrentPage() : 1;
        int recordCountPerPage = coopVO.getPageRow() > 0 ? coopVO.getPageRow() : pageUnit;

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(currentPage);
        paginationInfo.setRecordCountPerPage(recordCountPerPage);
        paginationInfo.setPageSize(10);

        params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
        params.put("recordCountPerPage", String.valueOf(recordCountPerPage));

        int totalCount = coopService.getCoopboardListCount(params);
        paginationInfo.setTotalRecordCount(totalCount);

        List<HashMap<String, Object>> resultList = coopService.getCoopboardList(params);

        // 지역 코드 목록 조회
        params.put("SEARCHKEYWORD", "COOP_AREA");
        List<HashMap<String, Object>> codeAreaList = coopService.getCoopCodeList(params);

        // 카테고리 코드 목록 조회
        params.put("SEARCHKEYWORD", "COOP_HSPT");
        List<HashMap<String, Object>> codeHsptList = coopService.getCoopCodeList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("list", resultList);
        jsonObject.put("codeAreaList", codeAreaList);
        jsonObject.put("codeHsptList", codeHsptList);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("currentPage", currentPage);
        jsonObject.put("totalPage", paginationInfo.getTotalPageCount());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 제휴사 공지/추천 목록 조회
     */
    @Operation(summary = "제휴사 공지/추천 목록 조회", description = "제휴사 공지사항 및 추천 목록을 조회합니다.")
    @GetMapping("/getNoticeList")
    public JSONObject getNoticeList(
            @ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        // 추천 목록 (썸네일)
        params.put("RECOMMEND", "Y");
        params.put("NOTICE_TOP_YN", "");
        params.put("cnt", coopVO.getCnt() > 0 ? coopVO.getCnt() : 50);
        List<HashMap<String, Object>> recommendList = coopService.getCoopNoticeList(params);

        // 공지사항 목록
        params.put("RECOMMEND", "N");
        params.put("NOTICE_TOP_YN", "Y");
        params.put("cnt", 3);
        List<HashMap<String, Object>> noticeList = coopService.getCoopNoticeList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("recommendList", recommendList);
        jsonObject.put("noticeList", noticeList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 제휴사 게시판 상세 조회
     */
    @Operation(summary = "제휴사 게시판 상세 조회", description = "제휴사 게시판 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(
            @ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("COOP_BOARD_SEQ", CommonUtil.isNull(coopVO.getCoopBoardSeq(), ""));

        // 조회수 증가
        coopService.clickCoopboard(params);

        HashMap<String, Object> detailView = coopService.coopboardView(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("detailView", detailView);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 제휴사 코드 목록 조회
     */
    @Operation(summary = "제휴사 코드 목록 조회", description = "제휴사 관련 코드 목록을 조회합니다.")
    @GetMapping("/getCodeList")
    public JSONObject getCodeList(
            @ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        params.put("SEARCHKEYWORD", CommonUtil.isNull(coopVO.getSearchKeyword(), "COOP_AREA"));

        List<HashMap<String, Object>> codeList = coopService.getCoopCodeList(params);

        HashMap<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("codeList", codeList);
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 쿠폰 사용 가능 여부 확인
     */
    @Operation(summary = "쿠폰 사용 가능 여부 확인", description = "쿠폰 코드의 사용 가능 여부를 확인합니다.")
    @GetMapping("/checkCoupon")
    public JSONObject checkCoupon(
            @ModelAttribute("CoopVO") CoopVO coopVO) throws Exception {

        HashMap<String, Object> params = new HashMap<>();

        String couponCode = CommonUtil.isNull(coopVO.getCouponCode(), "");
        params.put("COUPONCODE", couponCode.toUpperCase());

        HashMap<String, Object> jsonObject = new HashMap<>();

        List<HashMap<String, Object>> result = coopService.isUseCouponCheck(params);

        if (result != null && !result.isEmpty()) {
            HashMap<String, Object> couponInfo = result.get(0);
            jsonObject.put("couponInfo", couponInfo);
            jsonObject.put("isValid", "Y");
            jsonObject.put("retMsg", "OK");
        } else {
            jsonObject.put("isValid", "N");
            jsonObject.put("retMsg", "OK");
            jsonObject.put("message", "유효하지 않은 쿠폰입니다.");
        }

        return new JSONObject(jsonObject);
    }
}
