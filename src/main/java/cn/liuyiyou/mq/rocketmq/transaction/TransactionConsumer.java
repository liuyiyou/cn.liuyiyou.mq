package cn.liuyiyou.mq.rocketmq.transaction;

import cn.liuyiyou.mq.rocketmq.RocketMQConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author: liuyiyou.cn
 * @date: 2019/12/19
 * @version: V1.0
 */
public class TransactionConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup("transaction");
        consumer.setNamesrvAddr(RocketMQConfig.NAME_ADDR);
        consumer.subscribe("topicTest-t", "TagA || TagB || TagC || TagD || TagE");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.printf(Thread.currentThread().getName() + " consumerB Receive New Messages: " + msgs + "%n");
            System.out.printf( "consumerB Receive New Messages: body " + new String(msgs.get(0).getBody()) + "%n");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.printf("Broadcast ConsumerB Started.%n");
    }
}
