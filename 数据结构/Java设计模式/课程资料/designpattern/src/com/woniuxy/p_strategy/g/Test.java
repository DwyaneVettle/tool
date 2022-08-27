package com.woniuxy.p_strategy.g;

import java.io.File;

interface Processor {
	public void process(File file);
}

class A implements Processor {
	@Override
	public void process(File file) {
		System.out.println(file);
	}
	
}

class B implements Processor {
	@Override
	public void process(File file) {
		if(file.getName().endsWith("txt")) {
			System.out.println(file);
		}
	}
	
}

class C implements Processor {

	@Override
	public void process(File file) {
		file.delete();
	}
	
}

// =====================================================

class D implements Processor {

	@Override
	public void process(File file) {
		System.out.println(file.getAbsolutePath() + ","  + file.length());
	}
	
}

class E implements Processor {
	private String ext;
	
	public E(String ext) {
		this.ext = ext;
	}

	@Override
	public void process(File file) {
		if(file.getName().endsWith(ext)) {
			System.out.println(file);
		}
	}
	
}

public class Test {
	private Processor p;

	public Test() {
		p = new A();
	}
	
	public  void walk(File file) {
		try {
			// 获取文件夹中的根下的所有子文件
			File[] files = file.listFiles();
			
			for (File f : files) {
				p.process(f);
				if(f.isDirectory()) {
					walk(f);
				}
			}
		} catch (Exception e) {
			// swollow 吞异常 
		}
	}
	
	public void setP(Processor p) {
		this.p = p;
	}
	
	public static void main(String[] args) {
	
//		File[] roots = File.listRoots();
//		
//		for (File root : roots) {
//			if(root.getAbsolutePath().startsWith("C:\\")) {
//				continue;
//			}
//			walk(root);
//		}
		
		
		// 查询所有文件
		Test t = new Test();
		t.walk(new File("F:\\"));
		
		
		System.out.println("=====================================");
		
		t.setP(new E("txt"));
		// 查询所有文本文件
		t.walk(new File("F:\\"));
		
		System.out.println("################################");
		
		t.setP(new E("doc"));
		t.walk(new File("F:\\"));
		
		
	}
}
