package cn.liuyiyou.mq.kafka;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author: liuyiyou.cn
 * @date: 2020/11/23
 * @version: V1.0
 */
@Slf4j
public class HelloTask  extends Task{

    public HelloTask(final List<ConsumerRecord<String, String>> records) {
        super(records);
    }


}
