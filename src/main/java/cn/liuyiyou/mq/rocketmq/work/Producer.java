/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.liuyiyou.mq.rocketmq.work;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("work_group");
        producer.setNamesrvAddr("192.168.0.165:9876");
//        producer.setVipChannelEnabled(false);
        producer.start();
        try {
            final byte[] message = "Hello RocketMQ".getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message msg = new Message("TopicTest-LYY", "TagA", message);
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(1000);
        } finally {
            producer.shutdown();
        }
    }
}
