package com.woniuxy.k_builder.c;

// 针对于b包的缺点，重构代码如下：

// 1. 针对于不同需求，有不同的建造者。
// 2. 客户端仍然不知道建造电脑的过程。

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

class AdvancedComputerBuilder {
	private Computer c = new Computer();
	
	public Computer build() {
		c.setCpu("i9 8700hk");
		c.setMemery("64");
		c.setHarddisk("2T+512固态硬盘");
		c.setGpu("双卡交火 Rtx2080ti");
		return c;
	}
}

class MiddleComputerBuilder {
	private Computer c = new Computer();
	
	public Computer build() {
		c.setCpu("i7 7700hq");
		c.setMemery("16");
		c.setHarddisk("1T+256固态");
		c.setGpu("GTX1070");
		return c;
	}
}

class LowComputerBuilder {
	private Computer c = new Computer();
	
	public Computer build() {
		c.setCpu("i5 3320m");
		c.setMemery("8");
		c.setHarddisk("500g");
		c.setGpu("集成显卡");
		return c;
	}
}


// ==================================================================

public class Test {
	public static void main(String[] args) {
		AdvancedComputerBuilder acb = new AdvancedComputerBuilder();
		MiddleComputerBuilder mcb = new MiddleComputerBuilder();
		LowComputerBuilder lcb = new LowComputerBuilder();
		
		// 学习
		Computer c = mcb.build();
		System.out.println(c);
		
		// 办公、娱乐
		Computer c2 = lcb.build();
		System.out.println(c2);
				
				
		// 游戏
		Computer c3 = acb.build();
		System.out.println(c3);
	}
}

// 缺点：
// 1. 无论是高 中 低哪一种配置的电脑，配置的过程都是一样的，只不过细节不一样。 既然有重复，那就有了坏味道。
// 2. 这些重复的安装步骤，不稳定，万一哪一个建造者中漏了某一个步骤，编译期也不会报错！