package com.woniuxy.k_builder.a;

// 需求：用户买电脑.

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

public class Test {
	public static void main(String[] args) {
		Computer c = new Computer();
		
		c.setCpu("i7 7700hq");
		c.setMemery("16");
		c.setHarddisk("1T");
		c.setGpu("Rtx2080ti");
		
		System.out.println(c);
		
	}
}

// 缺点：
// 1. 当一个对象的组成过程特别繁琐，特别麻烦的时候，就不应该让客户端自己来完成。(相当于你去配电脑，卖家把所有组件都给你摆出来，让你自己亲自动手组装)。
// 2. 这样客户端可能会不按正确步骤组装对象，或缺少了某一个组件。
// 3. 这样违反了迪米特法则


// 对比，工厂模式，都是只负责把对象直接实例化出来，不管对象的状态。  建造者特强侧重于把对象new出来以后，给对象的属性赋值！
