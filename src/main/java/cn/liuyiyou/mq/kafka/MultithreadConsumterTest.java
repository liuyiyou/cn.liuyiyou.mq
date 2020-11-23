package cn.liuyiyou.mq.kafka;

/**
 * @author: liuyiyou.cn
 * @date: 2020/11/23
 * @version: V1.0
 */
public class MultithreadConsumterTest {


    public static void main(String[] args) {
        new MultithreadedKafkaConsumer("topic-demo");
    }
}
