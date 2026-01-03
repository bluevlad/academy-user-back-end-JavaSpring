package com.academy.mypage.note;

import com.academy.common.CORSFilter;
import com.academy.common.PaginationInfo;
import com.academy.mypage.note.service.NoteService;
import com.academy.mypage.note.service.NoteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Note", description = "쪽지 API")
@RestController
@RequestMapping("/api/mypage/note")
public class NoteApi extends CORSFilter {

    private final NoteService noteService;

    @Autowired
    public NoteApi(NoteService noteService) {
        this.noteService = noteService;
    }

    @Operation(summary = "전체 쪽지 목록 조회", description = "전체 쪽지 목록을 조회합니다.")
    @GetMapping("/getNoteList")
    public JSONObject getNoteList(
            @ModelAttribute("NoteVO") NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int currentPage = noteVO.getCurrentPage();
            int pageRow = noteVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            Map<String, Object> params = new HashMap<>();
            params.put("userId", noteVO.getUserId());
            params.put("searchType", noteVO.getSearchType());
            params.put("searchText", noteVO.getSearchText());
            params.put("firstIndex", paginationInfo.getFirstRecordIndex());
            params.put("recordCountPerPage", pageRow);

            List<HashMap<String, Object>> list = noteService.getNoteList(params);
            int totalCnt = noteService.getNoteListCount(params);

            paginationInfo.setTotalRecordCount(totalCnt);

            jsonObject.put("list", list);
            jsonObject.put("totalCnt", totalCnt);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "받은 쪽지 목록 조회", description = "받은 쪽지 목록을 조회합니다.")
    @GetMapping("/getReceiveNoteList")
    public JSONObject getReceiveNoteList(
            @ModelAttribute("NoteVO") NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int currentPage = noteVO.getCurrentPage();
            int pageRow = noteVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            Map<String, Object> params = new HashMap<>();
            params.put("userId", noteVO.getUserId());
            params.put("searchType", noteVO.getSearchType());
            params.put("searchText", noteVO.getSearchText());
            params.put("firstIndex", paginationInfo.getFirstRecordIndex());
            params.put("recordCountPerPage", pageRow);

            List<HashMap<String, Object>> list = noteService.getReceiveNoteList(params);
            int totalCnt = noteService.getReceiveNoteListCount(params);

            paginationInfo.setTotalRecordCount(totalCnt);

            jsonObject.put("list", list);
            jsonObject.put("totalCnt", totalCnt);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "보낸 쪽지 목록 조회", description = "보낸 쪽지 목록을 조회합니다.")
    @GetMapping("/getSendNoteList")
    public JSONObject getSendNoteList(
            @ModelAttribute("NoteVO") NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            int currentPage = noteVO.getCurrentPage();
            int pageRow = noteVO.getPageRow();

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(currentPage);
            paginationInfo.setRecordCountPerPage(pageRow);
            paginationInfo.setPageSize(10);

            Map<String, Object> params = new HashMap<>();
            params.put("userId", noteVO.getUserId());
            params.put("searchType", noteVO.getSearchType());
            params.put("searchText", noteVO.getSearchText());
            params.put("firstIndex", paginationInfo.getFirstRecordIndex());
            params.put("recordCountPerPage", pageRow);

            List<HashMap<String, Object>> list = noteService.getSendNoteList(params);
            int totalCnt = noteService.getSendNoteListCount(params);

            paginationInfo.setTotalRecordCount(totalCnt);

            jsonObject.put("list", list);
            jsonObject.put("totalCnt", totalCnt);
            jsonObject.put("paginationInfo", paginationInfo);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "쪽지 상세 조회", description = "쪽지의 상세 내용을 조회합니다.")
    @GetMapping("/getNoteDetail")
    public JSONObject getNoteDetail(
            @ModelAttribute("NoteVO") NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("noteIdx", noteVO.getNoteIdx());
            params.put("userId", noteVO.getUserId());

            HashMap<String, Object> noteDetail = noteService.getNoteDetail(params);

            jsonObject.put("data", noteDetail);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "쪽지 발송", description = "새 쪽지를 발송합니다.")
    @PostMapping("/insertNote")
    public JSONObject insertNote(
            @RequestBody NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("senderId", noteVO.getUserId());
            params.put("receiverId", noteVO.getReceiverId());
            params.put("subject", noteVO.getSubject());
            params.put("content", noteVO.getContent());

            int result = noteService.insertNote(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "쪽지 읽음 처리", description = "쪽지를 읽음으로 표시합니다.")
    @PutMapping("/updateNoteRead")
    public JSONObject updateNoteRead(
            @RequestBody NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("noteIdx", noteVO.getNoteIdx());
            params.put("userId", noteVO.getUserId());

            int result = noteService.updateNoteRead(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "쪽지 삭제", description = "쪽지를 삭제합니다.")
    @DeleteMapping("/deleteNote")
    public JSONObject deleteNote(
            @RequestBody NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("noteIdx", noteVO.getNoteIdx());
            params.put("userId", noteVO.getUserId());

            int result = noteService.deleteNote(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "쪽지 전체 삭제", description = "모든 쪽지를 삭제합니다.")
    @DeleteMapping("/deleteAllNote")
    public JSONObject deleteAllNote(
            @RequestBody NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", noteVO.getUserId());
            params.put("noteType", noteVO.getNoteType());

            int result = noteService.deleteAllNote(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "읽지 않은 쪽지 개수 조회", description = "읽지 않은 쪽지의 개수를 조회합니다.")
    @GetMapping("/getUnreadNoteCount")
    public JSONObject getUnreadNoteCount(
            @ModelAttribute("NoteVO") NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", noteVO.getUserId());

            int unreadCount = noteService.getUnreadNoteCount(params);

            jsonObject.put("data", unreadCount);
            jsonObject.put("retMsg", "OK");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "쪽지 답장", description = "쪽지에 답장합니다.")
    @PostMapping("/replyNote")
    public JSONObject replyNote(
            @RequestBody NoteVO noteVO,
            HttpServletRequest request) throws Exception {

        HashMap<String, Object> jsonObject = new HashMap<>();

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("senderId", noteVO.getUserId());
            params.put("receiverId", noteVO.getReceiverId());
            params.put("subject", noteVO.getSubject());
            params.put("content", noteVO.getContent());
            params.put("originalNoteIdx", noteVO.getNoteIdx());

            int result = noteService.replyNote(params);

            jsonObject.put("result", result);
            jsonObject.put("retMsg", result > 0 ? "OK" : "FAIL");

        } catch (Exception e) {
            jsonObject.put("retMsg", "FAIL");
            jsonObject.put("error", e.getMessage());
        }

        return new JSONObject(jsonObject);
    }
}
