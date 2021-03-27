package com.zel.market.kafka;

import com.zel.commonutils.ExceptionUtil;
import com.zel.commonutils.FileUtils;
import com.zel.commonutils.JsonHelper;
import com.zel.market.config.kafka.KafkaConsumerGroup;
import com.zel.market.config.kafka.KafkaTopic;
import com.zel.market.dto.MailTaskDTO;
import com.zel.market.dto.SpiderJobsDTO;
import com.zel.market.app.service.mail.MailService;
import com.zel.market.app.service.ss.SSService;
import com.zel.pojo.entity.SSAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Description:  不同消费者组 实现广播效果
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/1
 */
@Slf4j
@Component
public class KafkaConsumer {

    @Value("${PATH_CACHE}")
    private String PATH_CACHE;

    private final String email = "984529803@qq.com";

    @Autowired
    private SSService ssService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Resource
    private KafkaListenerEndpointRegistry registry;

    @Autowired
    private MailService mailService;

    /**
     * 插入数据监听ID
     */
    private static final String LISTENER_ID_INSERT = "insert_taskdata_listener";

    /**
     * 使用 topicPartitions 指定 topic、parition、offset
     * 注意：topics 和 topicPartitions 不能同时使用。
     *      一个消费组中的消费者只能和一个分区一一对应
     * @param record
     */
    //@KafkaListener(groupId = KafkaConsumerGroup.group1,
    //        topicPartitions = @TopicPartition(topic = KafkaTopic.topic1, partitions = "0"))
    //public void onMessage2(ConsumerRecord<?, ?> record) {
    //    // 消费的哪个topic、partition的消息,打印出消息内容
    //    log.info("简单消费0：" + record.topic() + "-" + record.partition() + "-" + record.value());
    //}

    // 消费监听
    //@KafkaListener(topics = {KafkaTopic.topic1}, groupId = KafkaConsumerGroup.group1)
    //@KafkaListener(groupId = KafkaConsumerGroup.group1,
    //        topicPartitions = @TopicPartition(topic = KafkaTopic.topic1, partitions = "1"))
    //public void onMessage1(ConsumerRecord<?, ?> record) {
    //    // 消费的哪个topic、partition的消息,打印出消息内容
    //    log.info("简单消费1：" + record.topic() + "-" + record.partition() + "-" + record.value());
    //}

     // 消费监听
    @KafkaListener(topics = {KafkaTopic.spider}, groupId = KafkaConsumerGroup.group1)
    public void onMessage1(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        String content = (String)record.value();
        SpiderJobsDTO dto = JsonHelper.read(content, SpiderJobsDTO.class);
        String type = dto.getType();
        if ("ss".equals(type)) {
            String html = "";
            try {
                html = FileUtils.read(PATH_CACHE + "/ss.html");
            } catch (IOException e) {
                log.error("onMessage1|error|e={}", ExceptionUtil.getStackTrace(e));
                return;
            }

            Document document = Jsoup.parse(html);
            List<SSAccount> ssAccounts = ssService.accountFromDocument(document);

            // 发送 mail
            MailTaskDTO task = new MailTaskDTO();
            task.setTo(email);
            task.setSubject("ss 账号");
            task.setContent(JsonHelper.writeBeautiful(ssAccounts));
            kafkaTemplate.send(KafkaTopic.mail, JsonHelper.write(task));
        }

        log.info("spider消费1：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    @KafkaListener(id = LISTENER_ID_INSERT, topics = {KafkaTopic.mail}, groupId = KafkaConsumerGroup.group1)
    public void onMail(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容

        String taskStr = (String)record.value();

        MailTaskDTO task =  JsonHelper.read(taskStr, MailTaskDTO.class);
        String to = task.getTo();
        String content = task.getContent();
        String subject = task.getSubject();

        log.info("mail消费：" + record.topic() + "-" + record.partition() + "-" + task);
        // 发送邮件
        mailService.sendSimpleMail(to, subject, content);
    }

    /**
     * 开启消费者监听数据
     */
    public void start() {
        System.out.println("开启订阅数据监听|start linster...");
        try {
            registry.getListenerContainer(LISTENER_ID_INSERT).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭消费者监听数据
     */
    public void stop() {
        System.out.println("停止订阅数据监听|stop linster...");
        try {
            registry.getListenerContainer(LISTENER_ID_INSERT).stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
