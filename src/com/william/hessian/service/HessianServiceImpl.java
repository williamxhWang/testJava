package com.william.hessian.service;

public class HessianServiceImpl implements HessianService {

	@Override
	public String queryName(String no) {
		System.out.println("hello:"+no);
		return "hello william";
	}

}
