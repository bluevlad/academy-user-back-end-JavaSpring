package com.academy.mocktest.stats;

import com.academy.common.CORSFilter;
import com.academy.mocktest.mouigosa.service.MouigosaService;
import com.academy.mocktest.stats.service.StatsService;
import com.academy.mocktest.stats.service.StatsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Tag(name = "Stats", description = "모의고사 통계 API")
@RestController
@RequestMapping("/api/mocktest/stats")
public class StatsApi extends CORSFilter {

    private final StatsService statsService;
    private final MouigosaService mouigosaService;

    @Autowired
    public StatsApi(StatsService statsService, MouigosaService mouigosaService) {
        this.statsService = statsService;
        this.mouigosaService = mouigosaService;
    }

    /**
     * 과목별 문항 분석
     */
    @Operation(summary = "과목별 문항 분석", description = "과목별 문항 분석 데이터를 조회합니다.")
    @GetMapping("/getSubjectStats")
    public JSONObject getSubjectStats(@ModelAttribute("StatsVO") StatsVO statsVO,
                                      HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("IDENTYID", statsVO.getIdentyId());
            params.put("MOCKCODE", statsVO.getMockCode());

            int checkCnt = statsService.statsTotalCnt(params);

            if (checkCnt < 1) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "통계 데이터가 없습니다.");
                return new JSONObject(jsonObject);
            }

            List<HashMap<String, Object>> subjectList = statsService.statsSubjectList(params);
            List<HashMap<String, Object>> subjectAnswerList = statsService.statsSubjectAnswerList(params);
            List<HashMap<String, Object>> subjectInfoList = statsService.statsSubjectInfoList(params);
            List<HashMap<String, Object>> mockInfo = mouigosaService.getUpdateRegistrationDetail(params);

            jsonObject.put("subjectList", subjectList);
            jsonObject.put("subjectAnswerList", subjectAnswerList);
            jsonObject.put("subjectInfoList", subjectInfoList);
            jsonObject.put("mockInfo", mockInfo.isEmpty() ? null : mockInfo.get(0));
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 전체 성적 분석
     */
    @Operation(summary = "전체 성적 분석", description = "전체 성적 분석 데이터를 조회합니다.")
    @GetMapping("/getTotalStats")
    public JSONObject getTotalStats(@ModelAttribute("StatsVO") StatsVO statsVO,
                                    HttpServletRequest request) throws Exception {
        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("IDENTYID", statsVO.getIdentyId());
            params.put("MOCKCODE", statsVO.getMockCode());

            int checkCnt = statsService.statsTotalCnt(params);

            if (checkCnt < 1) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "통계 데이터가 없습니다.");
                return new JSONObject(jsonObject);
            }

            List<HashMap<String, Object>> totalInfoList = statsService.statsTotalInfoList(params);
            if (!totalInfoList.isEmpty()) {
                HashMap<String, Object> firstInfo = totalInfoList.get(0);
                params.put("CLASSCODE", firstInfo.get("CLASSCODE"));
                params.put("CLASSSERIESCODE", firstInfo.get("CLASSSERIESCODE"));
            }

            List<HashMap<String, Object>> totalInfo1 = statsService.statsTotalInfo1(params);
            List<HashMap<String, Object>> totalInfo2 = statsService.statsTotalInfo2(params);
            List<HashMap<String, Object>> totalInfo2TblH = statsService.statsTotalInfo2_TblH(params);

            // 그래프 1 데이터 처리
            HashMap<String, Object> totalInfo1Map = processTotalInfo1(totalInfo1);

            // 그래프 2 데이터 처리 (점수 분포)
            HashMap<String, Object> totalInfo2Map = processTotalInfo2(totalInfo2);

            jsonObject.put("totalInfoList", totalInfoList);
            jsonObject.put("totalInfo1Map", totalInfo1Map);
            jsonObject.put("totalInfo2Map", totalInfo2Map);
            jsonObject.put("totalInfo2TblH", totalInfo2TblH);
            jsonObject.put("retMsg", "OK");
        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("message", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    /**
     * 전체 성적 정보 1 데이터 처리
     */
    private HashMap<String, Object> processTotalInfo1(List<HashMap<String, Object>> totalInfo1) {
        HashMap<String, Object> map = new HashMap<>();

        for (HashMap<String, Object> item : totalInfo1) {
            String tit = String.valueOf(item.get("TIT"));
            switch (tit) {
                case "총점":
                    map.put("T_O", item.get("VAL1"));
                    map.put("T_A", item.get("VAL2"));
                    break;
                case "평균":
                    map.put("PA_O", item.get("VAL1"));
                    map.put("PA_A", item.get("VAL2"));
                    break;
                case "전체평균":
                    map.put("TA_O", item.get("VAL1"));
                    map.put("TA_A", item.get("VAL2"));
                    break;
                case "석차":
                    map.put("R_O", item.get("VAL1"));
                    map.put("R_A", item.get("VAL2"));
                    break;
                case "응시인원":
                    map.put("TO_O", item.get("VAL1"));
                    map.put("TO_A", item.get("VAL2"));
                    break;
                case "상위점수":
                    map.put("TO_SO", item.get("VAL1"));
                    map.put("TO_SA", item.get("VAL2"));
                    break;
            }
        }

        // 상위 퍼센트 계산
        if (map.containsKey("R_O") && map.containsKey("TO_O")) {
            float topperO = (Float.parseFloat(String.valueOf(map.get("R_O"))) /
                             Float.parseFloat(String.valueOf(map.get("TO_O")))) * 100;
            map.put("TOPPER_O", String.valueOf(Math.round(topperO)));
        }

        if (map.containsKey("R_A") && map.containsKey("TO_A")) {
            float topperA = (Float.parseFloat(String.valueOf(map.get("R_A"))) /
                             Float.parseFloat(String.valueOf(map.get("TO_A")))) * 100;
            map.put("TOPPER_A", String.valueOf(Math.round(topperA)));
        }

        return map;
    }

    /**
     * 점수 분포 데이터 처리
     */
    private HashMap<String, Object> processTotalInfo2(List<HashMap<String, Object>> totalInfo2) {
        HashMap<String, Object> map = new HashMap<>();

        // 초기화
        for (int i = 5; i <= 100; i += 5) {
            map.put("val_" + i, 0);
        }

        // 점수 분포 계산
        for (HashMap<String, Object> item : totalInfo2) {
            int curScore = Integer.parseInt(String.valueOf(item.get("AVER")));
            int range = ((curScore - 1) / 5 + 1) * 5;

            if (range >= 5 && range <= 100) {
                String key = "val_" + range;
                map.put(key, (Integer) map.get(key) + 1);
            }
        }

        return map;
    }
}
