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

        Note note = body.getNote();
        noteService.add(note);

        return Response.ok(note);
    }

    @GetMapping(value = "/all")
    public Response all() {
        List<Note> all = noteService.all();
        NoteRspVO vo = new NoteRspVO();
        vo.setNotes(all);
        System.out.println("test");
        return Response.ok(vo);
    }

    @GetMapping(value = "/one/{id}")
    public Response getNote(@PathVariable String id) {

        List<Note> all = noteService.find(id);

        Note note = null;
        if (all != null && all.size() > 0) {
            note =  all.get(0);
        }

        return Response.ok(note);
    }
}
