package com.william.hessian.client;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.william.hessian.service.HessianService;

public class HessianClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String url = "http://localhost:8080/HessianWeb/hello";
        String url = "http://localhost:8080/userServiceurl";
        HessianProxyFactory factory = new HessianProxyFactory();
        HessianService basic=null;
		try {
			basic = (HessianService)factory.create(HessianService.class, url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(basic.queryName("hoffman"));
	}

}
