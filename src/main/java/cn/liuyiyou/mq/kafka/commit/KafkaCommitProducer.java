package cn.liuyiyou.mq.kafka.commit;

import java.time.Instant;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaCommitProducer {


    public static Properties initConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "commit-test.id");
        //配置所有的副本都收到消息才返回提交消息成功
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        return props;
    }

    public static void main(String[] args) throws InterruptedException {
        String topic = "commit-test";
        Producer<String, String> producer = new KafkaProducer<>(initConfig());
        while (true) {
            Thread.sleep(10000L);
            System.out.println("--------------------------");
            String value = Instant.now().toString();
            System.out.println("发送请求::" + value);
            final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "key", value);
            try {
                producer.send(producerRecord);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
