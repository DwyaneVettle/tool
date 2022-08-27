package com.woniuxy.k_builder.e;

// 针对于d包的缺点，重构代码如下：

// 1. 创建一个指挥者（Director），接受不同的建造者，完成建造过程


interface ComputerBuilder {
	void setCpu();
	void setMemery();
	void setHarddisk();
	void setGpu();
	Computer build();
}


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

class AdvancedComputerBuilder implements ComputerBuilder{
	private Computer c = new Computer(); 
	@Override
	public void setCpu() {
		c.setCpu("i9 8700hk");
	}
	@Override
	public void setMemery() {
		c.setMemery("64g");
	}
	@Override
	public void setHarddisk() {
		c.setHarddisk("2T + 515固态");
	}
	@Override
	public void setGpu() {
		c.setGpu("rtx2080ti");
	}
	@Override
	public Computer build() {
		return c;
	}
}

class MiddleComputerBuilder implements ComputerBuilder{
	private Computer c = new Computer(); 
	@Override
	public void setCpu() {
		c.setCpu("i7 7700hq");
	}
	@Override
	public void setMemery() {
		c.setMemery("16g");
	}
	@Override
	public void setHarddisk() {
		c.setHarddisk("1T + 256固态");
	}
	@Override
	public void setGpu() {
		c.setGpu("gtx1070");
	}
	@Override
	public Computer build() {
		return c;
	}
}

class LowComputerBuilder implements ComputerBuilder{
	private Computer c = new Computer(); 
	@Override
	public void setCpu() {
		c.setCpu("i5 3320m");
	}
	@Override
	public void setMemery() {
		c.setMemery("8");
	}
	@Override
	public void setHarddisk() {
		c.setHarddisk("500g");
	}
	@Override
	public void setGpu() {
		c.setGpu("集成显卡");
	}
	@Override
	public Computer build() {
		return c;
	}
}


class Director {
	public Computer build(ComputerBuilder cb) {
		cb.setCpu();
		cb.setHarddisk();
		cb.setMemery();
		cb.setGpu();
		return cb.build();
	}
}



// ==================================================================

public class Test {
	public static void main(String[] args) {
		
		AdvancedComputerBuilder acb = new AdvancedComputerBuilder();
		MiddleComputerBuilder mcb = new MiddleComputerBuilder();
		
		Director director = new Director();
		Computer c = director.build(mcb);
		
		System.out.println(c);
	}
}


// 至此，建造者设计模式的第一种形态，推演完毕！

// 该形态的缺点是，客户端无法灵活控制组件的细节，比如，客户端无法直接指定cpu或者硬盘的型号。
// 所以才有了建造者模式的第二种形态，而第二种形态又不仅仅是解决以上提到的问题的。


