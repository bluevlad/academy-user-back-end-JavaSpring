package com.academy.mocktest.offerer;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mocktest.mouigosa.service.MouigosaService;
import com.academy.mocktest.offerer.service.OffererService;
import com.academy.mocktest.offerer.service.OffererVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Tag(name = "Offerer", description = "모의고사 접수 API")
@RestController
@RequestMapping("/api/mocktest/offerer")
public class OffererApi extends CORSFilter {

    private final OffererService offererService;
    private final MouigosaService mouigosaService;

    @Autowired
    public OffererApi(OffererService offererService, MouigosaService mouigosaService) {
        this.offererService = offererService;
        this.mouigosaService = mouigosaService;
    }

    /**
     * 모의고사 접수 목록 조회
     */
    @Operation(summary = "모의고사 접수 목록 조회", description = "사용자의 모의고사 접수 목록을 조회합니다.")
    @GetMapping("/getList")
    public JSONObject getList(@ModelAttribute("OffererVO") OffererVO offererVO,
                             HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int currentPage = offererVO.getCurrentPage();
            int pageRow = offererVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            HashMap<String, Object> params = new HashMap<>();
            params.put("firstIndex", String.valueOf(paginationInfo.getFirstRecordIndex()));
            params.put("recordCountPerPage", String.valueOf(pageRow));
            params.put("USER_ID", offererVO.getUserId());
            params.put("GUBN", "listTake");
            params.put("PSTATUS", "PAY_PREV");
            params.put("ESTATUS", "CMPLT_PREV");

            String currentTime = offererService.offererCurrentdate(params);
            List<HashMap<String, Object>> list = offererService.offererTakeList(params);
            int totalCount = offererService.offererTakeListCount(params);

            paginationInfo.setTotalRecordCount(totalCount);

            jsonObject.put("list", list);
            jsonObject.put("totalCount", totalCount);
            jsonObject.put("currentTime", currentTime);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 접수 정보 조회
     */
    @Operation(summary = "모의고사 접수 정보 조회", description = "모의고사 접수 정보를 조회합니다.")
    @GetMapping("/getInfo")
    public JSONObject getInfo(@ModelAttribute("OffererVO") OffererVO offererVO,
                             HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("MOCKCODE", offererVO.getMockCode());
            params.put("USER_ID", offererVO.getUserId());
            params.put("FSTATUS", "");

            HashMap<String, Object> mouigosaInfo = offererService.offererMouiInfo(params);
            List<HashMap<String, Object>> classList = offererService.offererMouiAddClassInfo(params);
            List<HashMap<String, Object>> subjectList = offererService.offererMouiClsSeriesSbjtList(params);
            String currentTime = offererService.offererCurrentdate(params);

            jsonObject.put("mouigosaInfo", mouigosaInfo);
            jsonObject.put("classList", classList);
            jsonObject.put("subjectList", subjectList);
            jsonObject.put("currentTime", currentTime);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 접수 등록
     */
    @Operation(summary = "모의고사 접수 등록", description = "모의고사 접수를 등록합니다.")
    @PostMapping("/insertRequest")
    public JSONObject insertRequest(@RequestBody HashMap<String, Object> requestBody,
                                    HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            requestBody.put("REG_ID", requestBody.get("userId"));
            requestBody.put("UPD_ID", requestBody.get("userId"));

            // 주문번호 생성
            String tempOrderNo = requestBody.get("EXAMTYPE") +
                               ((String) requestBody.get("EXAMYEAR")).substring(2);
            requestBody.put("TEMP_ORDERNO", tempOrderNo);

            // 응시번호 생성
            String examRound = (String) requestBody.get("EXAMROUND");
            String identyId = examRound.length() < 2 ? "0" + examRound : examRound;
            identyId += requestBody.get("CLASSSERIESCODE");
            requestBody.put("TEMP_IDENTYID", identyId);

            int result = offererService.offererRequestInsert(requestBody);

            if (result > 0) {
                jsonObject.put("data", requestBody);
                jsonObject.put("retMsg", "OK");
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
     * 모의고사 접수 확인
     */
    @Operation(summary = "모의고사 접수 확인", description = "결제 전 접수 정보를 확인합니다.")
    @GetMapping("/confirmRequest")
    public JSONObject confirmRequest(@ModelAttribute("OffererVO") OffererVO offererVO,
                                    HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("FSTATUS", "CONFIRM");
            params.put("IDENTYID", offererVO.getIdentyId());
            params.put("MOCKCODE", offererVO.getMockCode());
            params.put("USER_ID", offererVO.getUserId());

            HashMap<String, Object> view = offererService.offererReqMouiInfo(params);
            List<HashMap<String, Object>> subjectList = offererService.offererSubjectList(params);
            String currentTime = offererService.offererCurrentdate(new HashMap<>());

            jsonObject.put("view", view);
            jsonObject.put("subjectList", subjectList);
            jsonObject.put("currentTime", currentTime);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 접수 삭제
     */
    @Operation(summary = "모의고사 접수 삭제", description = "결제 전 접수 정보를 삭제합니다.")
    @DeleteMapping("/deleteRequest")
    public JSONObject deleteRequest(@RequestBody HashMap<String, Object> requestBody,
                                   HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            String identyId = (String) requestBody.get("identyId");
            String mockCode = (String) requestBody.get("mockCode");

            if (identyId != null && !identyId.equals("")) {
                HashMap<String, Object> params = new HashMap<>();
                params.put("IDENTYID", identyId);
                params.put("MOCKCODE", mockCode);

                int result = offererService.offererDeletes(params);

                jsonObject.put("retMsg", "OK");
                jsonObject.put("message", "삭제되었습니다.");
            } else {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "응시번호가 없습니다.");
            }
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 접수 수정 정보 조회
     */
    @Operation(summary = "모의고사 접수 수정 정보 조회", description = "수정할 접수 정보를 조회합니다.")
    @GetMapping("/getModifyInfo")
    public JSONObject getModifyInfo(@ModelAttribute("OffererVO") OffererVO offererVO,
                                   HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("GUBN", "modify");
            params.put("FSTATUS", "CONFIRM");
            params.put("IDENTYID", offererVO.getIdentyId());
            params.put("MOCKCODE", offererVO.getMockCode());
            params.put("USER_ID", offererVO.getUserId());

            HashMap<String, Object> mouigosaInfo = offererService.offererMoui(params);
            List<HashMap<String, Object>> classList = offererService.offererMouiClassInfo(params);
            HashMap<String, Object> view = offererService.offererView(params);
            List<HashMap<String, Object>> subjectList = offererService.offererMouiSubjectList(params);
            String currentTime = offererService.offererCurrentdate(new HashMap<>());

            jsonObject.put("mouigosaInfo", mouigosaInfo);
            jsonObject.put("classList", classList);
            jsonObject.put("view", view);
            jsonObject.put("subjectList", subjectList);
            jsonObject.put("currentTime", currentTime);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 접수 수정
     */
    @Operation(summary = "모의고사 접수 수정", description = "모의고사 접수 정보를 수정합니다.")
    @PutMapping("/updateRequest")
    public JSONObject updateRequest(@RequestBody HashMap<String, Object> requestBody,
                                   HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            requestBody.put("UPD_ID", requestBody.get("userId"));

            int result = offererService.offererRequestUpdate(requestBody);

            if (result > 0) {
                jsonObject.put("data", requestBody);
                jsonObject.put("retMsg", "OK");
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
     * 모의고사 접수 상세 조회
     */
    @Operation(summary = "모의고사 접수 상세 조회", description = "모의고사 접수 상세 정보를 조회합니다.")
    @GetMapping("/getView")
    public JSONObject getView(@ModelAttribute("OffererVO") OffererVO offererVO,
                             HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("GUBN", "modifyTake");
            params.put("FSTATUS", "CONFIRM");
            params.put("IDENTYID", offererVO.getIdentyId());
            params.put("MOCKCODE", offererVO.getMockCode());
            params.put("USER_ID", offererVO.getUserId());

            HashMap<String, Object> view = offererService.offererView(params);
            List<HashMap<String, Object>> subjectList = offererService.offererMouiSubjectList(params);

            jsonObject.put("view", view);
            jsonObject.put("subjectList", subjectList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 모의고사 접수 결제 후 목록 조회
     */
    @Operation(summary = "모의고사 접수 결제 후 목록 조회", description = "결제가 완료된 모의고사 목록을 조회합니다.")
    @GetMapping("/getResultList")
    public JSONObject getResultList(@ModelAttribute("OffererVO") OffererVO offererVO,
                                   HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("USER_ID", offererVO.getUserId());

            params.put("GUBN", "BEFORE");
            List<HashMap<String, Object>> beforeList = offererService.offererTakeList(params);
            int beforeCount = offererService.offererListCount(params);

            params.put("GUBN", "");
            List<HashMap<String, Object>> afterList = offererService.offererTakeList(params);
            int afterCount = offererService.offererListCount(params);

            String currentTime = offererService.offererCurrentdate(new HashMap<>());

            params.put("GUBN", "BEFORE");
            String confirmTime = offererService.offererCurrentdate(params);

            List<HashMap<String, Object>> subjectList = offererService.offererTakeSubjectList(params);

            jsonObject.put("beforeList", beforeList);
            jsonObject.put("beforeCount", beforeCount);
            jsonObject.put("afterList", afterList);
            jsonObject.put("afterCount", afterCount);
            jsonObject.put("currentTime", currentTime);
            jsonObject.put("confirmTime", confirmTime);
            jsonObject.put("subjectList", subjectList);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 응시표 출력
     */
    @Operation(summary = "응시표 출력", description = "응시표 출력 정보를 조회합니다.")
    @GetMapping("/getPrintInfo")
    public JSONObject getPrintInfo(@ModelAttribute("OffererVO") OffererVO offererVO,
                                  HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("IDENTYID", offererVO.getIdentyId());
            params.put("USER_ID", offererVO.getUserId());

            offererService.offererPrintUpdate(params);

            HashMap<String, Object> view = offererService.offererView(params);
            List<HashMap<String, Object>> subjectView = offererService.offererMouiSubjectList(params);

            jsonObject.put("view", view);
            jsonObject.put("subjectView", subjectView);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
