package com.zel.market.controller.enote;

import com.zel.market.common.Response;
import com.zel.market.service.note.NoteService;
import com.zel.pojo.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *      bitwarden
 * @author csy
 * @version 1.0.0
 * @since 2020/11/4
 */
@RestController
@RequestMapping(value = "/enote")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping(value = "/add")
    public Response add(@RequestBody NoteReqVO body) {

        Note note = new Note();
        note.setTitle(body.getTitle());
        note.setContent(body.getContent());
        note.setCreateTime(new Date());
        note.setUpdateTime(new Date());

        noteService.insert(note);

        return Response.ok(note);
    }

    @GetMapping(value = "/all")
    public Response all() {

        List<Note> all = noteService.all();
        NoteRspVO vo = new NoteRspVO();
        vo.setNotes(all);
        return Response.ok(vo);
    }
}
