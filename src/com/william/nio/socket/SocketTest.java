package com.william.nio.socket;

import java.util.Scanner;

public class SocketTest {

	public static void main(String[] args) throws Exception {
		// 运行服务器
		MyServer.start();
		// 避免客户端先于服务器启动前执行代码
		Thread.sleep(100);
		// 运行客户端
		MyClient.start();
		while (MyClient.sendMsg(new Scanner(System.in).nextLine()));
	}

}
