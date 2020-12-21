package com.zel.market.jobs;

import com.zel.commonutils.DateUtil;
import com.zel.commonutils.FileUtils;
import com.zel.commonutils.JacksonHelper;
import com.zel.commonutils.crypto.Md5Utils;
import com.zel.dbmanager.entity.SSAccount;
import com.zel.market.config.Config;
import com.zel.market.service.mail.MailService;
import com.zel.market.service.ss.SSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Description: 定时任务
 * 1. 直接用 MySQL 存储，超时时间戳加索引
 * 2. 周期性从 MySQL 中将超时的任务批量取出，缓存在内存中
 * 3. worker 直接从内存中拿定时器任务，进行处理；处理完毕后更新 mysql 中的状态
 */
@Component
public class Jobs {
    @Value("${ss.path}")
    private String ssPath;

    private static final Logger log = LoggerFactory.getLogger(Jobs.class);
    private final String email = "984529803@qq.com";

    private static final long timeout = TimeUnit.MINUTES.toMillis(10);

    private ConcurrentHashMap<String, List<SSAccount>> map = new ConcurrentHashMap<>();

    @Autowired
    private SSService ssService;

    @Autowired
    private MailService mailService;

    // long s = TimeUnit.MINUTES.toMinutes(10);
    @Async
    @Scheduled(fixedRate = 10 * DateUtil.MINUTE * DateUtil.MILLISECOND)
    public void reportCurrentTime() {
        if (!Config.ENABLE_SS_ACCOUNT_REQUEST) {
            return;
        }
        log.info("The time is now {}", DateUtil.format(new Date(), DateUtil.HMS));

        List<SSAccount> account = ssService.getAccountWithThreadCallable();
        if (account == null || account.size() == 0) {
            log.error("ss请求为空");
            return;
        }
        String content = JacksonHelper.writeBeautiful(account);
        String key = Md5Utils.md5(content);
        if (map.containsKey(key)) {
            log.info("已经存在");
        } else {
            map.put(key, account);
            String s1 = ssFromFile();
            if (!s1.equals(content)) {
                write(content);
                mailService.sendHtmlMail(email, "ss 账号", content);
                log.info("发送email通知");
            } else {
                log.info("无改动");
            }
        }
    }

    private void write(String content) {
        try {
            FileUtils.write(ssPath, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String ssFromFile() {
        String data = "";
        try {
            data = FileUtils.read(ssPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
