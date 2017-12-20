package com.william.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiService extends Remote {
	public String queryName(String no) throws RemoteException ;
}
