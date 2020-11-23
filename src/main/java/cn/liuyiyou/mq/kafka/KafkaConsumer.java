//package cn.liuyiyou.mq.kafka;
//
//import cn.liuyiyou.mq.ApplicationConstant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author: liuyiyou.cn
// * @date: 2020/11/23
// * @version: V1.0
// */
//@Component
//public class KafkaConsumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
//
//    @KafkaListener(groupId = ApplicationConstant.GROUP_ID_STRING, topics = ApplicationConstant.TOPIC_NAME,
//        containerFactory = ApplicationConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
//    public void receivedMessage(String message) {
//        logger.info("Message Received using Kafka listener " + message);
//    }
//}
