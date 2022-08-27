package com.woniuxy.m_decorator.e;

import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

// 根据已有功能，做出更强大的功能！
class MyBufferedReader extends FilterReader {

	private Reader in;
	
	protected MyBufferedReader(Reader in) {
		super(in);
		this.in = in;
	}
	
	public String readLine() throws IOException {
		
		StringBuilder sb = new StringBuilder("");
		int n = 0;
		while((n = in.read()) != -1) {
			if(n == '\r' ) {
				continue;
			}
			if(n == '\n' ) {
				break;
			}
			sb.append((char)n);
		}
		
		// 流程能走到这里，n有几种可能？ 一个是-1,  是\n
		return n == -1  ? null: sb.toString();
		
	}
	
	@Override
	public void close() throws IOException {
		in.close();
	}
	
	
	
}

public class Test {
	public static void main(String[] args) throws Exception {
		Reader in = new FileReader("F:\\1.txt");
		MyBufferedReader mbr = new MyBufferedReader(in);
		
		String line = null;
		
		while((line = mbr.readLine()) != null) {
			System.out.println(line);
		}
		mbr.close();
		
	}
}
