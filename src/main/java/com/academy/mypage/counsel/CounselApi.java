package com.academy.mypage.counsel;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mypage.counsel.service.CounselService;
import com.academy.mypage.counsel.service.CounselVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Tag(name = "Counsel", description = "나의 상담 API")
@RestController
@RequestMapping("/api/mypage/counsel")
public class CounselApi extends CORSFilter {

    private final CounselService counselService;

    @Autowired
    public CounselApi(CounselService counselService) {
        this.counselService = counselService;
    }

    /**
     * 상담 목록 조회
     */
    @Operation(summary = "상담 목록 조회", description = "나의 상담 목록을 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(@ModelAttribute("CounselVO") CounselVO counselVO,
                             HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int currentPage = counselVO.getCurrentPage();
            int pageRow = counselVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            HashMap<String, Object> params = new HashMap<>();
            params.put("startNo", String.valueOf(paginationInfo.getFirstRecordIndex()));
            params.put("endNo", String.valueOf(paginationInfo.getLastRecordIndex()));
            params.put("REG_ID", counselVO.getUserId());
            params.put("SEARCHTEXT", counselVO.getSearchText());
            params.put("SEARCHKIND", counselVO.getSearchKind());
            params.put("topMenu", "001");
            params.put("ORDERFIELD", "REG_DT");
            params.put("ORDERVALUE", "DESC");

            List<HashMap<String, Object>> list = counselService.boardList(params);
            int totalCount = counselService.boardListCount(params);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 상담 상세 조회
     */
    @Operation(summary = "상담 상세 조회", description = "상담 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(@ModelAttribute("CounselVO") CounselVO counselVO,
                             HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("BOARD_SEQ", counselVO.getBoardSeq());
            params.put("PARENT_BOARD_SEQ", counselVO.getParentBoardSeq());

            // 조회수 증가
            counselService.updateHits(params);

            HashMap<String, Object> view = counselService.boardView(params);

            jsonObject.put("data", view);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 상담 등록
     */
    @Operation(summary = "상담 등록", description = "상담을 등록합니다.")
    @PostMapping("/insertBoard")
    public JSONObject insertBoard(@RequestBody HashMap<String, Object> requestBody,
                                  HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            requestBody.put("REG_ID", requestBody.get("userId"));
            requestBody.put("UPD_ID", requestBody.get("userId"));

            int result = counselService.insertBoard(requestBody);

            if (result > 0) {
                jsonObject.put("retMsg", "OK");
                jsonObject.put("message", "등록되었습니다.");
            } else {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "등록에 실패했습니다.");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 상담 수정
     */
    @Operation(summary = "상담 수정", description = "상담을 수정합니다.")
    @PutMapping("/updateBoard")
    public JSONObject updateBoard(@RequestBody HashMap<String, Object> requestBody,
                                  HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            requestBody.put("UPD_ID", requestBody.get("userId"));

            int result = counselService.updateBoard(requestBody);

            if (result > 0) {
                jsonObject.put("retMsg", "OK");
                jsonObject.put("message", "수정되었습니다.");
            } else {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "수정에 실패했습니다.");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 상담 삭제
     */
    @Operation(summary = "상담 삭제", description = "상담을 삭제합니다.")
    @DeleteMapping("/deleteBoard")
    public JSONObject deleteBoard(@RequestBody HashMap<String, Object> requestBody,
                                  HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int result = counselService.deleteBoard(requestBody);

            if (result > 0) {
                jsonObject.put("retMsg", "OK");
                jsonObject.put("message", "삭제되었습니다.");
            } else {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "삭제에 실패했습니다.");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
