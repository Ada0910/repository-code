package com.ada.rocket.simple;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

/**
 *
 * <b><code></code></b>
 * <p/>
 * Java使用RocketMQ之消费者示例
 *
 * consumerGroup:同一个group中可以配置多个consumer，可以提高消费端的并发消费能力以及容灾
 * 和kafka一样，多个consumer会对消息做负载均衡，意味着同一个topic下的不同messageQueue会分发给同一个group中的不同consumer
 * 同时，如果我们希望消息能够达到广播的目的，那么只需要把consumer加入到不同的group就行
 * <p/>
 * <b>Creation Time:</b> 2023/3/23 14:44
 * @author xiewn
 * @version 1.0.0.1
 *
 * @since  1.0.0.1
 */
public class RocketMQConsumer {
	public static void main(String[] args) throws Exception {
		//消费者的组名，这个和kafka是一样,这里需要注意的是
		DefaultMQPushConsumer consumer =
				new DefaultMQPushConsumer("gp_consumer_group");
		//指定NameServer地址，多个地址以 ; 隔开
		consumer.setNamesrvAddr("127.0.0.1:9876");
		/*
		 * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
		 * 如果非第一次启动，那么按照上次消费的位置继续消费
		 */
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe("test_topic", "*");

		//consumer.registerMessageListener(new MessageListenerConcurrently() {
		//    @Override
		//    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
		//                                                    ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		//        System.out.println("Receive Message: "+list);
		//        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //签收
		//    }
		//});

		/*
		 * 这里有两种监听，MessageListenerConcurrently以及MessageListenerOrderly
		 * 前者是普通监听，后者是顺序监听
		 */
		consumer.registerMessageListener(new MessageListenerOrderly() {
			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {

				MessageExt messageExt = list.get(0);

				//写重试的代码 DLQ（通用设计）
				if (messageExt.getReconsumeTimes() == 3) {  //消息重发了三次
					//持久化 消息记录表
					//可以将对应的数据保存到数据库，以便人工干预
					System.out.println(messageExt.getMsgId() + "," + messageExt.getBody());
					return ConsumeOrderlyStatus.SUCCESS; //签收
				}
				return ConsumeOrderlyStatus.SUCCESS; //签收
			}
		});

		consumer.start();
	}
}
