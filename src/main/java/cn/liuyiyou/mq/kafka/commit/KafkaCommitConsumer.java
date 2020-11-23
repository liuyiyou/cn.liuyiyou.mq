package cn.liuyiyou.mq.kafka.commit;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaCommitConsumer {

    @KafkaListener
    private static Properties initConfig() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "commit-test.id");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group.demo");
        //禁止自动提交位移，避免多线程消费出现消息丢失
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 100);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 30);
        //控制单次调用call方法能够返回的记录数量，帮助控制在轮询里需要处理的数据量。
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        return props;
    }

    public static void main(String[] args) {
        String topic = "commit-test";
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
                System.out.println("取出数量" + records.count());
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("key=" + record.key() + "\tvalue" + record.value());
                }
                consumer.commitSync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
