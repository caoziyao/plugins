package com.zel.market.service.note;

import com.zel.dbmanager.mapper.NoteMapper;
import com.zel.pojo.entity.Article;
import com.zel.pojo.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/16
 */
@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    /**
     * @return
     */
    public Note insert(Note note) {
        noteMapper.insert(note);
        return note;
    }

    public List<Note> all() {
        Map<String, Object> map = new HashMap<>();
        List<Note> notes = noteMapper.selectByMap(map);
        return notes;
    }
}
