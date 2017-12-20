package com.william.io.bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileinputStreamTest {

	public static void main(String[] args) throws IOException {
		File file = new File("C:/D_DISK/my_workspace/ssmTest/pom.xml");  
		FileInputStream fs =new FileInputStream(file);
		int b=fs.read();
		System.out.print((char)b);
		
		byte[] buffer=new byte[1024];
		int flag=0;
		flag=fs.read(buffer);
		System.out.println(flag);
		
		
		fs.read(buffer, 5, 100);
		for(byte bb:buffer){
			System.out.print((char)bb);
		}
		fs.close();
	}

}
