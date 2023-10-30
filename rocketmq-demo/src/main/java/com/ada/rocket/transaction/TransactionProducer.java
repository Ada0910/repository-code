package com.ada.rocket.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * <b><code></code></b>
 * <p/>
 * RocketMQ之事务处理（生产者）
 *
 *
 * <p/>
 * <b>Creation Time:</b> 2023/3/24 14:43
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, InterruptedException {
        TransactionMQProducer transactionMQProducer=new
                TransactionMQProducer("tx_producer");
        transactionMQProducer.setNamesrvAddr("127.0.0.1:9876");
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        //自定义线程池，用于异步执行事务操作
        transactionMQProducer.setExecutorService(executorService);
        transactionMQProducer.setTransactionListener(new TransactionListenerLocal()); //本地事务的监听

        transactionMQProducer.start();

        for(int i=0;i<20;i++){
            String orderId= UUID.randomUUID().toString();
            String body="{'operation':'doOrder','orderId':'"+orderId+"'}";
            Message message=new Message("order_tx_topic",
                    "TagA",orderId,body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            transactionMQProducer.sendMessageInTransaction(message,orderId+"&"+i);
            Thread.sleep(1000);
        }

    }
}
