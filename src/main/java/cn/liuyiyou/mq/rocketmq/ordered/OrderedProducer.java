package cn.liuyiyou.mq.rocketmq.ordered;

import cn.liuyiyou.mq.rocketmq.RocketMQConfig;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author: liuyiyou.cn
 * @date: 2019/12/19
 * @version: V1.0
 */
public class OrderedProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup("consumer-group-order");
        producer.setNamesrvAddr(RocketMQConfig.NAME_ADDR);
        producer.start();
        String[] tags = new String[] { "TagA", "TagC", "TagD" };
        for (int i = 0; i < 20; i++) {
            // orderId 可以根据自己的业务来定义、 可以是订单id 或者 商户id 等、 相同的id 会有序的分到一个quenen
            int orderId = i % 2;
            long time = System.currentTimeMillis();
            // Create a message instance, specifying topic, tag and message
            // body.
            Message msg = new Message("TopicTest111", tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ " + time).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, (mqs, msg1, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, orderId);

            System.out.printf("%s%n", sendResult);
        }
        // server shutdown
        producer.shutdown();
    }
}
