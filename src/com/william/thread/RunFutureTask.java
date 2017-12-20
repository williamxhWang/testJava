package com.william.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class RunFutureTask {

	public static void main(String[] args) {
		ExecutorService pool=Executors.newCachedThreadPool();
		MyLocal local=new MyLocal();
		List<Future<String>> futures=new ArrayList<Future<String>>();
		List<MyCallable> callables=new ArrayList<MyCallable>();
		MyCallable callable01=new MyCallable(0, local);
		MyCallable callable02=new MyCallable(1, local);
		MyCallable callable03=new MyCallable(2, local);
		callables.add(callable01);
		callables.add(callable02);
		callables.add(callable03);
		
		try {
			futures=pool.invokeAll(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Future f:futures){
			try {
				System.out.println(f.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
