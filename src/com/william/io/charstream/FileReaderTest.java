package com.william.io.charstream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;

public class FileReaderTest {

	public static void main(String[] args) throws IOException {
		File file = new File("C:/D_DISK/my_workspace/ssmTest/pom.xml");
		FileReader fr=new FileReader(file);

		CharBuffer cb=CharBuffer.allocate(120);
		fr.read(cb);
		cb.flip();
		System.out.print(cb.get());
	}

}
