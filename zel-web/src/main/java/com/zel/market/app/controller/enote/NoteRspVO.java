package com.zel.market.app.controller.enote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zel.pojo.entity.Note;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/16
 */
@Setter
@Getter
public class NoteRspVO {

    @JsonProperty("notes")
    List<Note> notes;
}
