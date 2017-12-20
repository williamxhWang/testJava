package com.william.kafka;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JavaKafkaConsumerHighAPI implements Runnable {

	/**
	 * Kafka�������Ѷ���
	 */
	private ConsumerConnector consumer;

	/**
	 * Kafka Topic����
	 */
	private String topic;

	/**
	 * �߳�������һ�����Topic�ķ�������
	 */
	private int numThreads;

	/**
	 * �̳߳�
	 */
	private ExecutorService executorPool;

	/**
	 * ���캯��
	 * 
	 * @param topic
	 *            Kafka��ϢTopic����
	 * @param numThreads
	 *            �������ݵ��߳���/�������ΪTopic�ķ�����
	 * @param zookeeper
	 *            Kafka��Zookeeper�����ַ���
	 * @param groupId
	 *            ������������group ID��ֵ
	 */
	public JavaKafkaConsumerHighAPI(String topic, int numThreads,
			String zookeeper, String groupId) {
		// 1. ����Kafka������
		this.consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig(zookeeper,groupId));
		// 2. ���ݸ�ֵ
		this.topic = topic;
		this.numThreads = numThreads;
	}

	@Override
	public void run() {
		// 1. ָ��Topic
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(this.topic, this.numThreads);

		// 2. ָ�����ݵĽ�����
		StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
		StringDecoder valueDecoder = new StringDecoder(
				new VerifiableProperties());

		// 3. ��ȡ�������ݵĵ��������󼯺�
		/**
		 * Key: Topic���� Value: ��ӦTopic����������ȡ������С��topicCountMap��ָ����topic��С
		 */
		Map<String, List<KafkaStream<String, String>>> consumerMap = this.consumer
				.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);

		// 4. �ӷ��ؽ���л�ȡ��Ӧtopic��������������
		List<KafkaStream<String,String>> streams = consumerMap.get(this.topic);

		// 5. �����̳߳�
		this.executorPool = Executors.newFixedThreadPool(this.numThreads);

		// 6. ���������������
		int threadNumber = 0;
		for (final KafkaStream<String, String> stream : streams) {
			this.executorPool.submit(new ConsumerKafkaStreamProcesser(stream,threadNumber));
			threadNumber++;
		}
	}

	public void shutdown() {
		// 1. �رպ�Kafka�����ӣ������ᵼ��stream.hashNext����false
		if (this.consumer != null) {
			this.consumer.shutdown();
		}

		// 2. �ر��̳߳أ���ȴ��̵߳�ִ�����
		if (this.executorPool != null) {
			// 2.1 �ر��̳߳�
			this.executorPool.shutdown();

			// 2.2. �ȴ��ر����, �ȴ�����
			try {
				if (!this.executorPool.awaitTermination(5, TimeUnit.SECONDS)) {
					System.out.println("Timed out waiting for consumer threads to shut down, exiting uncleanly!!");
				}
			} catch (InterruptedException e) {
				System.out.println("Interrupted during shutdown, exiting uncleanly!!");
			}
		}

	}

	/**
	 * ���ݴ����zk��������Ϣ��groupID��ֵ������Ӧ��ConsumerConfig����
	 * 
	 * @param zookeeper
	 *            zk��������Ϣ�������ڣ�<br/>
	 *            hadoop-senior01.ibeifeng.com:2181,hadoop-senior02.ibeifeng.com
	 *            :2181/kafka
	 * @param groupId
	 *            ��kafka consumer������group id��ֵ�� group idֵһ����kafka
	 *            consumer����и��ؾ���
	 * @return Kafka������Ϣ
	 */
	private ConsumerConfig createConsumerConfig(String zookeeper, String groupId) {
		// 1. �������Զ���
		Properties prop = new Properties();
		// 2. ����������
		prop.put("group.id", groupId); // ָ������id
		prop.put("zookeeper.connect", zookeeper); // ָ��zk������url
		prop.put("zookeeper.session.timeout.ms", "40000"); //
		prop.put("zookeeper.sync.time.ms", "200");
		prop.put("auto.commit.interval.ms", "1000");
		prop.put("rebalance.max.retries", "10");  
		prop.put("rebalance.backoff.ms", "5000");
		// 3. ����ConsumerConfig����
		return new ConsumerConfig(prop);
	}

	/**
	 * Kafka���������ݴ����߳�
	 */
	public static class ConsumerKafkaStreamProcesser implements Runnable {
		// Kafka������
		private KafkaStream<String,String> stream;
		// �߳�ID���
		private int threadNumber;

		public ConsumerKafkaStreamProcesser(KafkaStream<String,String> stream,
				int threadNumber) {
			this.stream = stream;
			this.threadNumber = threadNumber;
		}

		@Override
		public void run() {
			// 1. ��ȡ���ݵ�����
			ConsumerIterator<String,String> iter = this.stream.iterator();
			// 2. �����������
			while (iter.hasNext()) {
				// 2.1 ��ȡ����ֵ
				MessageAndMetadata value = iter.next();

				// 2.2 ���
				System.out.println(this.threadNumber + ":" 
						+ value.offset()+ ":" + value.key() + ":" + value.message());
			}
			// 3. ��ʾ��ǰ�߳�ִ�����
			System.out.println("Shutdown Thread:" + this.threadNumber);
		}
	}

}
