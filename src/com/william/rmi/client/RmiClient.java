package com.william.rmi.client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.william.rmi.service.RmiService;

public class RmiClient {
	public static void main(String[] args) {
		// ע�������
		Registry registry = null;
		try {
			// ��ȡ����ע�������
			registry = LocateRegistry.getRegistry("127.0.0.1", 8088);
			// �г�����ע��ķ���
			String[] list = registry.list();
			for (String s : list) {
				System.out.println(s);
			}
		} catch (RemoteException e) {
		}
		try {
			// ����������ȡ����
			RmiService server = (RmiService) registry.lookup("vince");
			// ����Զ�̷���
			String result = server.queryName("ha ha ha ha");
			// ������ý��
			System.out.println("result from remote : " + result);
		} catch (AccessException e) {
		} catch (RemoteException e) {
		} catch (NotBoundException e) {
		}
	}

}
