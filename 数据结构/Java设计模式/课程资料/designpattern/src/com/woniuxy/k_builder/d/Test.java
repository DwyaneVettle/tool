package com.woniuxy.k_builder.d;

// 针对于c包的缺点，重构代码如下：

// 1. 稳定住，建造电脑的过程, 定义一个建造者接口
// 2. 让所有具体建造者实现这个接口，那么所有具体建造者，就必须实现这个接口中的所有方法，就不会遗漏某一个步骤。
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



// ==================================================================

public class Test {
	public static void main(String[] args) {
		
		AdvancedComputerBuilder acb = new AdvancedComputerBuilder();
		acb.setCpu();
		acb.setHarddisk();
		acb.setMemery();
		acb.setGpu();
		Computer c = acb.build();
		System.out.println(c);
		
		
		MiddleComputerBuilder mcb = new MiddleComputerBuilder();
		mcb.setCpu();
		mcb.setHarddisk();
		mcb.setMemery();
		mcb.setGpu();
		Computer c2 = mcb.build();
		System.out.println(c2);
	}
}

// 缺点：
// 1. 客户端又必须知道建造的过程了， (相当于你去配一台电脑，虽然不是自己亲自组装电脑，但是你要只会那个“小伙”，该装...该装...该装...)


