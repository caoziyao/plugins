package com.zel.market.controller.cart.bussiness;

import com.zel.market.controller.cart.dto.CartDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    /**
     * 入库  mergeInsert 判断表中有没有符合on（）条件中的数据，有了就更新数据，没有就插入数据。
     *
     * 有一个表T，有两个字段a、b，我们想在表T中做Insert/Update，如果条件满足，则更新T中b的值，否则在T中插入一条记录
     *
     * merge into 目标表 a
     * using 源表 b
     * on(a.条件字段1=b.条件字段1 and a.条件字段2=b.条件字段2 ……)
     * when matched then update set a.更新字段=b.字段
     * when  not matched then insert into a(字段1,字段2……)values(值1,值2……)
     *
     * @param cartDetail
     */
    @Transactional
    public void add(CartDetail cartDetail) {
        //
        //cartDao.mergeInsert(cartDetail);
    }

    @Transactional
    public void removeGoods(long userId, List<Long> goodIds) {
        //cartDao.deleteGoods(userId, goodIds);
    }
}
