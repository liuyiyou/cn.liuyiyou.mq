package cn.liuyiyou.mq.kafka;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.Future;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

public class MyKafkaProducer {


    public static Properties initConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "myclient.id");
        //配置所有的副本都收到消息才返回提交消息成功
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        return props;
    }

    public static void main(String[] args) throws InterruptedException {
        String topic = "topic-name";
        Producer<String, String> producer = new KafkaProducer<>(initConfig());
        while (true){
            Thread.sleep(1000L);
            System.out.println("--------------------------");
//            for(int i = 0 ;i<100;i++){
                final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "key", Instant.now().toString());
                try {
                    final RecordMetadata recordMetadata = producer.send(producerRecord).get();
                    System.out.println(recordMetadata.timestamp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
//            }

        }
    }
}
