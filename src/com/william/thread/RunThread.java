package com.william.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RunThread {

	public static void main(String[] args) {
		MyLocal local=new MyLocal();
		new MyThread(0,local).start();
		new MyThread(1,local).start();
		
		new Thread(new MyRunnable(2,local)).start();
		new Thread(new MyRunnable(3,local)).start();
		
		MyCallable callable01=new MyCallable(4, local);
		MyCallable callable02=new MyCallable(5, local);
		FutureTask<String> futureTask01=new FutureTask<String>(callable01);
		FutureTask<String> futureTask02=new FutureTask<String>(callable02);
		new Thread(futureTask01).start();
		try {
			System.out.println(futureTask01.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(futureTask02.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(futureTask02).start();
		
		
	}

}
