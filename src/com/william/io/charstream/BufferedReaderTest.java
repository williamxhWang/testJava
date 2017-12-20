package com.william.io.charstream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {
	public static void main(String[] args) throws IOException{
		File file = new File("C:/D_DISK/my_workspace/ssmTest/pom.xml");
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String line=br.readLine();
		System.out.println(line);
	}
}
