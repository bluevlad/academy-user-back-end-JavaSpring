package com.academy.mypage.note.service;

import com.academy.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final NoteMapper noteMapper;

    @Autowired
    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<HashMap<String, Object>> getNoteList(Map<String, Object> params) {
        return noteMapper.getNoteList(params);
    }

    public int getNoteListCount(Map<String, Object> params) {
        return noteMapper.getNoteListCount(params);
    }

    public List<HashMap<String, Object>> getReceiveNoteList(Map<String, Object> params) {
        return noteMapper.getReceiveNoteList(params);
    }

    public int getReceiveNoteListCount(Map<String, Object> params) {
        return noteMapper.getReceiveNoteListCount(params);
    }

    public List<HashMap<String, Object>> getSendNoteList(Map<String, Object> params) {
        return noteMapper.getSendNoteList(params);
    }

    public int getSendNoteListCount(Map<String, Object> params) {
        return noteMapper.getSendNoteListCount(params);
    }

    public HashMap<String, Object> getNoteDetail(Map<String, Object> params) {
        return noteMapper.getNoteDetail(params);
    }

    public int insertNote(Map<String, Object> params) {
        return noteMapper.insertNote(params);
    }

    public int updateNoteRead(Map<String, Object> params) {
        return noteMapper.updateNoteRead(params);
    }

    public int deleteNote(Map<String, Object> params) {
        return noteMapper.deleteNote(params);
    }

    public int deleteAllNote(Map<String, Object> params) {
        return noteMapper.deleteAllNote(params);
    }

    public int getUnreadNoteCount(Map<String, Object> params) {
        return noteMapper.getUnreadNoteCount(params);
    }

    public int replyNote(Map<String, Object> params) {
        return noteMapper.replyNote(params);
    }
}
