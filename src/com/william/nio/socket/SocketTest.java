package com.william.nio.socket;

import java.util.Scanner;

public class SocketTest {

	public static void main(String[] args) throws Exception {
		// ���з�����
		MyServer.start();
		// ����ͻ������ڷ���������ǰִ�д���
		Thread.sleep(100);
		// ���пͻ���
		MyClient.start();
		while (MyClient.sendMsg(new Scanner(System.in).nextLine()));
	}

}
