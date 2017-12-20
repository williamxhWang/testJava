package com.william.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
	private long tmp;
	private MyLocal local;
	public MyCallable(long temp,MyLocal local){
		this.tmp=temp;
		this.local=local;
	}
	
	@Override
	public String call() throws Exception {
		for(int i=0;i<3;i++){
			System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["+ local.getNext() + "]");
		}
		return Thread.currentThread().getName() + " : " + tmp;
	}

}
