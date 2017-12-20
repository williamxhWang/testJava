package com.william.thread;

public class MyLocal {
	public static ThreadLocal<Long> num=new ThreadLocal<Long>(){
		@Override
		protected Long initialValue() {
			return new Long(0);
		}
	};
	public long getNext(){
		num.set(num.get()+1);
		return num.get();
	}
}
