package com.william.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.william.rmi.service.RmiserviceImpl;

public class RmiServer {

	public static void main(String[] args) {
		// ע�������
		Registry registry = null;
		try {
			// ����һ������ע�������
			registry = LocateRegistry.createRegistry(8088);
		} catch (RemoteException e) {
		}
		try {
			// ����һ������
			RmiserviceImpl server = new RmiserviceImpl();
			// �����������
			registry.rebind("vince", server);
			System.out.println("bind server");
		} catch (RemoteException e) {
		}

	}

}
