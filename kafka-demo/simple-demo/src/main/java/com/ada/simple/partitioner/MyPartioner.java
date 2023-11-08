package com.ada.simple.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * <b><code></code></b>
 * <p/>  
 * 自定义Partitioner
 * <p/>
 *
 * @Date: 2023/3/14 23:49
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class MyPartioner implements Partitioner {
	@Override
	public int partition(String topic, Object key, byte[] bytes, Object value, byte[] bytes1, Cluster cluster) {
		System.out.println("enter");
		List<PartitionInfo> list = cluster.partitionsForTopic(topic);
		int len = list.size();
		if (key == null) {
			Random random = new Random();
			return random.nextInt(len);
		}
		return Math.abs(key.hashCode()) % len;
	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> map) {

	}
}
