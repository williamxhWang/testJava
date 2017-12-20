package com.william.nio.buffer;

import java.nio.CharBuffer;

public class CharBufferTest {

	public static void main(String[] args) {
		CharBuffer  cb=CharBuffer.allocate(8);
		System.out.println(cb.capacity());
		System.out.println(cb.limit());
		System.out.println(cb.position());
		
		cb.put('a').put('b').put('c');
		System.out.println(cb.position());
		cb.flip();
		System.out.println(cb.limit());
		System.out.println(cb.position());
		
		System.out.println(cb.get());
		System.out.println(cb.limit());
		System.out.println(cb.position());
		
		cb.clear();
		System.out.println(cb.limit());
		System.out.println(cb.position());
	}

}
