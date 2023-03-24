package com.ada.rocket.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * <b><code></code></b>
 * <p/>
 * RocketMQ之本地监听工具类
 * 
 * RocketMQ事务消息的三种状态:
 * 1.ROLLBACK_MESSAGE：回滚事务
 * 2. COMMIT_MESSAGE： 提交事务
 * 3. UNKNOW： broker会定时的回查Producer消息状态，直到彻底成功或失败
 *
 * <p/>
 * <b>Creation Time:</b> 2023/3/24 14:43
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since gnete 1.0.0.1
 */
public class TransactionListenerLocal implements TransactionListener {

    private Map<String, Boolean> results = new ConcurrentHashMap<>();

    //执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("开始执行本地事务：" + o.toString()); //o
        String orderId = o.toString();
        //模拟数据库保存(成功/失败)
        boolean result = Math.abs(Objects.hash(orderId)) % 2 == 0;
        results.put(orderId, result); //
        // 这个返回状态表示告诉broker这个事务消息是否被确认，允许给到consumer进行消费
        // LocalTransactionState.ROLLBACK_MESSAGE 回滚
        //LocalTransactionState.UNKNOW 未知
        return result ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.UNKNOW;
    }

    //提供给事务执行状态检查的回调方法，给broker用的(异步回调）
    //如果回查失败，消息就丢弃
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String orderId = messageExt.getKeys();
        System.out.println("执行事务回调检查： orderId:" + orderId);
        boolean rs = results.get(orderId);
        System.out.println("数据的处理结果：" + rs); //只有成功/失败
        return rs ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.ROLLBACK_MESSAGE;
    }
}
