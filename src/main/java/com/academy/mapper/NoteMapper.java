package com.academy.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface NoteMapper {

    /**
     * 쪽지 목록 조회
     */
    List<HashMap<String, Object>> getNoteList(Map<String, Object> params);

    /**
     * 쪽지 목록 카운트
     */
    int getNoteListCount(Map<String, Object> params);

    /**
     * 받은 쪽지 목록 조회
     */
    List<HashMap<String, Object>> getReceiveNoteList(Map<String, Object> params);

    /**
     * 받은 쪽지 목록 카운트
     */
    int getReceiveNoteListCount(Map<String, Object> params);

    /**
     * 보낸 쪽지 목록 조회
     */
    List<HashMap<String, Object>> getSendNoteList(Map<String, Object> params);

    /**
     * 보낸 쪽지 목록 카운트
     */
    int getSendNoteListCount(Map<String, Object> params);

    /**
     * 쪽지 상세 조회
     */
    HashMap<String, Object> getNoteDetail(Map<String, Object> params);

    /**
     * 쪽지 발송
     */
    int insertNote(Map<String, Object> params);

    /**
     * 쪽지 읽음 처리
     */
    int updateNoteRead(Map<String, Object> params);

    /**
     * 쪽지 삭제
     */
    int deleteNote(Map<String, Object> params);

    /**
     * 쪽지 전체 삭제
     */
    int deleteAllNote(Map<String, Object> params);

    /**
     * 읽지 않은 쪽지 개수 조회
     */
    int getUnreadNoteCount(Map<String, Object> params);

    /**
     * 쪽지 답장
     */
    int replyNote(Map<String, Object> params);
}
