package com.william.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.william.rmi.service.RmiserviceImpl;

public class RmiServer {

	public static void main(String[] args) {
		// 注册管理器
		Registry registry = null;
		try {
			// 创建一个服务注册管理器
			registry = LocateRegistry.createRegistry(8088);
		} catch (RemoteException e) {
		}
		try {
			// 创建一个服务
			RmiserviceImpl server = new RmiserviceImpl();
			// 将服务绑定命名
			registry.rebind("vince", server);
			System.out.println("bind server");
		} catch (RemoteException e) {
		}

	}

}
