package com.william.rmi.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiserviceImpl extends UnicastRemoteObject implements RmiService {

	public RmiserviceImpl() throws RemoteException {
		super();
	}

	@Override
	public String queryName(String no) throws RemoteException {
		 System.out.println("hello" + no);  
		 return String.valueOf(System.currentTimeMillis());  
	}

}
