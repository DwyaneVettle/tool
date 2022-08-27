package com.woniuxy.k_builder.b;

// 针对于a包的缺点，重构代码如下：
// 1. 作者额外添加一个功能：直接封装装配电脑的工作。

class Computer {
	private String cpu;
	private String memery;
	private String harddisk;
	private String gpu;
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public void setMemery(String memery) {
		this.memery = memery;
	}
	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	@Override
	public String toString() {
		return "Computer [cpu=" + cpu + ", memery=" + memery + ", harddisk=" + harddisk + ", gpu=" + gpu + "]";
	}
}

class ComputerBuilder {
	private Computer c = new Computer();
	
	public Computer build() {
		c.setCpu("i7 7700hq");
		c.setMemery("16");
		c.setHarddisk("1T");
		c.setGpu("Rtx2080ti");
		return c;
	}
}

// ==================================================================

public class Test {
	public static void main(String[] args) {
		ComputerBuilder cb = new ComputerBuilder();
		
		// 学习 (cpu好 + 内存 )
		Computer c = cb.build();
		System.out.println(c);
		
		// 玩游戏
		Computer c2 = cb.build();
		System.out.println(c2);
		
		// 娱乐、办公
		Computer c3 = cb.build();
		System.out.println(c3);
		
	}
}

// 缺点：
// 1. 封装地太狠！ 无论客户端是什么需求，客户端没办法选择配置！ 都是由服务器端定死的配置！！



// 应该根据不同需求，配置出不同的 低端、中端、高端电脑.
