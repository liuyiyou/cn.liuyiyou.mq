package cn.liuyiyou.mq.kafka.transaction;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.time.Duration;
import java.util.Properties;

/**
 * @author: liuyiyou@yanglaoban.com
 * @date: 2018/10/29
 * @version: V1.0
 * @Copyright: 2018 yanglaoban.com Inc. All rights reserved.
 */
public class KafkaTransactionsExample {

    public static void main(String args[]) {

        Properties consumerConfig = new Properties();
        consumerConfig.put("bootstrap.servers", "localhost:9092");
        consumerConfig.put("group.id", "test");
        consumerConfig.put("enable.auto.commit", "true");
        consumerConfig.put("auto.commit.interval.ms", "1000");
        consumerConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        Properties producerConfig = new Properties();
        producerConfig.put("bootstrap.servers", "localhost:9092");
        producerConfig.put("acks", "all");
        producerConfig.put("retries", 0);
        producerConfig.put("batch.size", 16384);
        producerConfig.put("linger.ms", 1);
        producerConfig.put("buffer.memory", 33554432);
        producerConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfig);
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerConfig);
        producer.initTransactions();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()) {
//                producer.beginTransaction();
//                List<ProducerRecord<String, String>> outputRecords = processRecords(records);
//                for (ProducerRecord<String, String> outputRecord : outputRecords) {
//                    producer.send(outputRecord);
//                }
//                sendOffsetsResult = producer.sendOffsetsToTransaction(getUncommittedOffsets());
//                producer.endTransaction();
            }
        }
    }
}
