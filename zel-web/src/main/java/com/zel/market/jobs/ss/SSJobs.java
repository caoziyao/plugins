package com.zel.market.jobs.ss;

import com.zel.commonutils.DateUtil;
import com.zel.commonutils.ExceptionUtil;
import com.zel.commonutils.FileUtils;
import com.zel.commonutils.JsonHelper;
import com.zel.commonutils.crypto.Md5Utils;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.market.common.enumcom.ERedisKey;
import com.zel.market.config.Config;
import com.zel.market.config.kafka.KafkaTopic;
import com.zel.market.dto.SpiderJobsDTO;
import com.zel.market.service.mail.MailService;
import com.zel.market.service.ss.SSService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * 1, 10 分钟下载html，解析需要的内容（仅仅HMTL）。
 * 2, 生成 md5 存在 redis，key 为 URL
 * 3，对比 redis 是否存在并且相等，相等则直接返回
 * 4，不相等则写入文件（备份），放到 kafka 消费
 */
@Component
public class SSJobs {

    @Value("${PATH_CACHE}")
    private String PATH_CACHE;

    private static final Logger log = LoggerFactory.getLogger(SSJobs.class);

    private static final long timeout = TimeUnit.MINUTES.toMillis(10);

    private ConcurrentHashMap<String, String> mapURL = new ConcurrentHashMap<>();

    public final static String URL = "https://github.com/Alvin9999/new-pac/wiki/ss%E5%85%8D%E8%B4%B9%E8%B4%A6%E5%8F%B7";

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SSService ssService;

    @Autowired
    private MailService mailService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // long s = TimeUnit.MINUTES.toMinutes(10);
    @Async
    @Scheduled(fixedRate = 10 * DateUtil.MINUTE * DateUtil.MILLISECOND)
    public void reportCurrentTime() {
        if (!Config.ENABLE_SS_ACCOUNT_REQUEST) {
            return;
        }
        log.info("SSHtmlJobs time is now {}", DateUtil.format(new Date(), DateUtil.HMS));

        // 1, 获取需要 html
        Document document = null;
        try {
            document = Jsoup.parse(new URL(URL), 30000);
        } catch (IOException e) {
            log.error("Jsoup.parse|ERROR|url={}, {}", URL, ExceptionUtil.getStackTrace(e));
            return;
        }
        // 2, 对比 md5
        String content = document.html();

        Elements elements = document.select("table[role=table]");
        String html = elements.html();
        String md5 = Md5Utils.md5(html);

        String key = ERedisKey.HTML_SS.getKey();
        String cacheMd5 = (String) redisUtils.hGet(key, URL);

        if (!md5.equals(cacheMd5)) {
            redisUtils.hSet(key, URL, md5);
            write(PATH_CACHE + "/ss.html", content);
            // 3，通知 kafka
            SpiderJobsDTO dto = new SpiderJobsDTO();
            dto.setCreateTime(new Date());
            dto.setType("ss");
            dto.setUrl(URL);
            kafkaTemplate.send(KafkaTopic.spider, URL, JsonHelper.write(dto));
        }
    }

    private void write(String filePath, String content) {
        try {
            FileUtils.write(filePath, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
