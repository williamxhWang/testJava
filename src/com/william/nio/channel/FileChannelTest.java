package com.william.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {

	public static void main(String[] args) {
		File file = new File("C:/D_DISK/my_workspace/ssmTest/pom.xml");  
		try {
			FileChannel fc=new FileInputStream(file).getChannel();
			MappedByteBuffer mbb=fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			Charset charset=Charset.forName("UTF-8");
			CharsetDecoder decoder=charset.newDecoder();
			CharBuffer cb=decoder.decode(mbb);
			System.out.println(cb);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
