package com.william.io.bytestream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ErrorStreamTest {

	public static void main(String[] args) throws Exception {
		Process p=Runtime.getRuntime().exec("java -version");
		BufferedReader br=new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String buffer =null;
		while((buffer=br.readLine())!=null){
			System.out.println(buffer);
		}
	}

}
