package com.zel.mq;



/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
//@Component
//@EnableBinding(Sink.class)
//public class ReceiveMessageListener {
//    @Value("${server.port}")
//    private String serverPort;
//
//    @Autowired
//    private MailService mailService;
//
//    /**
//     * 接受消息
//     * @param message
//     */
//    @StreamListener(Sink.INPUT)
//    public void input(Message<String> message) {
//        String msg = message.getPayload();
//
//        ReceiveDTO data = JsonTool.loads(msg, ReceiveDTO.class);
//        if (data == null) {
//            System.out.println("mail 消费者收到 null ");
//            return;
//        }
//
//        String to =  data.getTo();
//        String subject = data.getSubject();
//        String content = data.getContent();
//        Date createTime = data.getCreateTime();
//
//        if (StringUtils.isBlank(to) || StringUtils.isBlank(subject) || StringUtils.isBlank(content) || createTime == null) {
//            System.out.println("mail 消费者收到 null content");
//            return;
//        }
//
//        String dateString = DateTool.strFormatWithDate(createTime);
//
//        content = content + " at: " + dateString;
//
//        mailService.sendSimpleMail(to, subject, content);
//        System.out.println("mail 消费者收到：" + to + ":" + content + ":" + subject + ":" + dateString);
//    }
//}
