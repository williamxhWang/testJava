package com.william.thread;

public class MyRunnable implements Runnable{
	private long tmp;
	private MyLocal local;
	public MyRunnable(long temp,MyLocal local){
		this.tmp=temp;
		this.local=local;
	}
	public void run(){
		System.out.println("thread[" + Thread.currentThread().getName() +" ±äÁ¿Îª: "+tmp);
		for(int i=0;i<3;i++){
			System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["+ local.getNext() + "]");
		}
	}
}
