package cn.liuyiyou.mq.rocketmq.filter;

import cn.liuyiyou.mq.rocketmq.RocketMQConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.util.List;

/**
 * @author: liuyiyou.cn
 * @date: 2019/12/19
 * @version: V1.0
 */
public class FilterConsumer {



    public static void main(String[] args) throws MQClientException, IOException {
        DefaultMQPushConsumer consumer = new  DefaultMQPushConsumer();
        consumer.setNamesrvAddr(RocketMQConfig.NAME_ADDR);
        consumer.setConsumerGroup("filter");
        // only subsribe messages have property a, also a >=0 and a <= 3
        consumer.subscribe("topic-filter", MessageSelector.bySql("a >=0 and a <= 3"));
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt message : msgs) {
                    // Print approximate delay time period
                    System.out.println("filter Receive message[msgId=" + message.getMsgId() + "] "
                            + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms filter");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Broadcast ConsumerB Started.%n");
    }
}
