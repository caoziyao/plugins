package com.zel.dbmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zel.pojo.entity.Note;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/11/16
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {
}
