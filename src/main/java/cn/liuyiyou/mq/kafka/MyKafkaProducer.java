package cn.liuyiyou.mq.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDateTime;
import java.util.Properties;

public class MyKafkaProducer {


    public static Properties initConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "myclient.id");
        return props;
    }

    public static void main(String[] args) {
        String topic = "topic-demo";
        Producer<String, String> producer = new KafkaProducer<>(initConfig());
        final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "生产者key", "生产者value"+LocalDateTime.now());
        try {
            producer.send(producerRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        producer.close();
    }
}
