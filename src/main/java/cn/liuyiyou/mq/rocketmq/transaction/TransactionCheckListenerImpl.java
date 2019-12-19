package cn.liuyiyou.mq.rocketmq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 未决事务，服务器回查客户端
 * @author: liuyiyou.cn
 * @date: 2019/12/19
 * @version: V1.0
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);


    @Override
    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
        System.out.println("server checking TrMsg " + msg.toString());

        int value = transactionIndex.getAndIncrement();
        if (value == 0) {
            throw new RuntimeException("Could not find db");
        }
        else if (value == 1) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        else if (value == 2) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }

        return LocalTransactionState.UNKNOW;
    }
}