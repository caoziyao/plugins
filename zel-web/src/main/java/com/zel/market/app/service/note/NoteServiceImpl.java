package com.zel.market.app.service.note;

//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.zel.dbmanager.mapper.NoteMapper;
import com.zel.pojo.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

/**
 * Description:
 *  https://www.cnblogs.com/l-y-h/p/12859477.html
 *
 * Wrapper  条件构造抽象类
 * -- AbstractWrapper 查询条件封装，用于生成 sql 中的 where 语句。
 * -- QueryWrapper Entity 对象封装操作类，用于查询。
 * -- UpdateWrapper Update 条件封装操作类，用于更新。
 * -- AbstractLambdaWrapper 使用 Lambda 表达式封装 wrapper
 * -- LambdaQueryWrapper 使用 Lambda 语法封装条件，用于查询。
 * -- LambdaUpdateWrapper 使用 Lambda 语法封装条件，用于更新。
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/16
 */
@Service
public class NoteServiceImpl implements NoteService {

    //@Autowired
    //private NoteMapper noteMapper;

    ///**
    // * @return
    // */
    //public Note add(Note note) {
    //    note.setUpdateTime(new Date());
    //    saveOrUpdate(note);
    //
    //    return note;
    //}
    //
    //public List<Note> all() {
    //    Map<String, Object> map = new HashMap<>();
    //    QueryWrapper<Note> queryWrapper = new QueryWrapper<>();
    //    queryWrapper.orderByDesc("update_time");
    //
    //    List<Note> notes = list(queryWrapper);
    //    //List<Note> notes = noteMapper.selectByMap(map);
    //    //List<Note> notes = noteMapper.findAll(map);
    //    return notes;
    //
    //}
    //
    //public List<Note> find(String id) {
    //    //Map<String, Object> map = new HashMap<>();
    //    //map.put("id", id);
    //    //List<Note> notes = noteMapper.selectByMap(map);
    //    QueryWrapper<Note> queryWrapper = new QueryWrapper<>();
    //    queryWrapper.eq("id", id);
    //
    //    List<Note> notes = list(queryWrapper);
    //    return notes;
    //}
    //
    //@Override
    //public boolean saveBatch(Collection<Note> entityList, int batchSize) {
    //    return false;
    //}
    //
    //@Override
    //public boolean saveOrUpdateBatch(Collection<Note> entityList, int batchSize) {
    //    return false;
    //}
    //
    //@Override
    //public boolean updateBatchById(Collection<Note> entityList, int batchSize) {
    //    return false;
    //}
    //
    //@Override
    //public boolean saveOrUpdate(Note entity) {
    //    //if (entity.getId() != null) {
    //    //    noteMapper.updateById(entity);
    //    //} else {
    //    //    entity.setCreateTime(new Date());
    //    //    noteMapper.insert(entity);
    //    //}
    //    return false;
    //}
    //
    //@Override
    //public Note getOne(Wrapper<Note> queryWrapper, boolean throwEx) {
    //    return null;
    //}
    //
    //@Override
    //public Map<String, Object> getMap(Wrapper<Note> queryWrapper) {
    //    return null;
    //}
    //
    //@Override
    //public <V> V getObj(Wrapper<Note> queryWrapper, Function<? super Object, V> mapper) {
    //    return null;
    //}
    //
    //@Override
    //public BaseMapper<Note> getBaseMapper() {
    //    //return noteMapper;
    //    return null;
    //}
}
