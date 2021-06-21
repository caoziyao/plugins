package com.zel.dbjooq;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    DSLContext dslContext;


    @Override
    public void create(int id, int authorId, String title) {

    }

    //public Book1 selectById(int id) {
    //    return create.select(USER.NAME,USER.AGE).from(USER).where(USER.ID.eq(id)).fetchInto(User.class).get(0);
    //}

    public static void main(String[] args) {
        //dslContext
    }
}
