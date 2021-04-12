package com.zel.market.app.service.user;

import com.zel.commonutils.DateUtil;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.market.app.service.login.db.UserThird;
import com.zel.pojo.entity.User;
import com.zel.dbmanager.mapper.UserMapper;
import com.zel.market.config.Config;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 第三方登录
     * @param thirdUser
     * @return
     */
    public int insert(UserThird thirdUser) {
        //return thirdUserMapper.insert(thirdUser);
        return 0;
    }

    /**
     * 在线用户
     * @param userId
     */
    public void addOnlineUser(long userId) {
        long time = new Date().getTime();
        redisUtils.zAdd(Config.USER_ONLINE_KEY, time, String.valueOf(userId));
    }


    /**
     * 离线用户
     * @param userId
     */
    public void removeOnlineUser(long userId) {
        redisUtils.zRem(Config.USER_ONLINE_KEY, String.valueOf(userId));
    }

    /**
     * 在线用户
     *
     * @param start
     * @param end
     * @return
     */
    public long onlineUserNum(Date start, Date end) {
        return redisUtils.zCount(Config.USER_ONLINE_KEY, start.getTime(), end.getTime());
    }

    /**
     * 30分钟，用户在线数
     * @return
     */
    public long onlineUserNumToday() {
        Date now = new Date();
        Date dawn = DateUtil.plusMinutes(now, -30);
        return onlineUserNum(dawn, now);
    }

    /**
     * 总用户在线数
     * @return
     */
    public long onlineUserNumAll() {
        return redisUtils.zCard(Config.USER_ONLINE_KEY);
    }

    //@Transactional
    public void transactTest() {
        User user = userMapper.findByUsername("zhangsan");
        User user2 = userMapper.findByUsername("lisi");
        user.setAge(user.getAge() + 1);
        user2.setAge(user2.getAge() + 1);
        userMapper.updateById(user);
        userMapper.updateById(user2);
    }

    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMapper.insert(user);
        return user;
    }

    public User getUserByName(String username) {
        // 从数据库，redis获取
        return null;
    }

    ///**
    // * 校验登录
    // *
    // * @param username
    // * @param password
    // */
    //public User login(String username, String password) {
    //    return userMapper.findByUsernameAndPassword(username, password);
    //}

    public User findById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return userMapper.findById(id);
    }

    /**
     * 第三方登录
     * @param id
     * @return
     */
    public User findByOpenId(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return userMapper.findById(id);
    }


    //@Transactional
    public int updateUser(String id, String username) {

        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(username)) {
            return 0;
        }
        return userMapper.updateUser(id, username);
    }

    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        return all;
    }

    public Object findTest() {
        return userMapper.findTest();
    }
}
