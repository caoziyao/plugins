package com.zel.dbmanager.service;

import com.zel.pojo.entity.SSAccount;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/12
 */
//public class SSAccountService extends DBManager {
//    private static SSAccountMapper ssAccountMapper;
//    private static SqlSession session;
//
//    static {
//        session = sqlSessionFactory.openSession();
//        ssAccountMapper = session.getMapper(SSAccountMapper.class);
//    }
//
//    public void save2(SSAccount ssAccount) {
//        // Assert.notNull(ssAccount);
//        if (ssAccount == null) {
//            System.out.println("ssAccount null");
//            return;
//        }
//        ssAccountMapper.insertDemo(ssAccount.getIp(), ssAccount.getLocation());
//        session.commit();
//    }
//
//    public void save(SSAccount ssAccount) {
//        if (ssAccount == null) {
//            System.out.println("ssAccount null");
//            return;
//        }
//        //ssAccountMapper.insertAccount(ssAccount);
//        ssAccountMapper.insert(ssAccount);
//        session.commit();
//    }
//}
