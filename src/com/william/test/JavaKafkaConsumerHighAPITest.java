package com.william.test;

import com.william.kafka.JavaKafkaConsumerHighAPI;

public class JavaKafkaConsumerHighAPITest {
	public static void main(String[] args) {
        String zookeeper = "172.31.3.117:2181,172.31.3.117:2182,172.31.3.117:2183";
        String groupId = "group1";
        String topic = "test1";
        int threads = 3;

        JavaKafkaConsumerHighAPI example = new JavaKafkaConsumerHighAPI(topic, threads, zookeeper, groupId);
        new Thread(example).start();

        // 执行10秒后结束
        int sleepMillis = 600000;
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭
        example.shutdown();
    }
}
