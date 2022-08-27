package com.woniuxy.k_builder.f;

import com.woniuxy.k_builder.f.Computer.ComputerBuilder;

// 当一个类的属性特别多的时候，构造器的参数也就特别多，尤其是构造器的多个参数类型还都一样。
// 那么客户端代码就很容易出错。

class Computer {
	private String cpu;
	private String harddisk;
	private String memery;
	private String gpu;
	private String power;
	private String motherboard; // 主板
	private String cdrom;
	private String fan;
	private String netcard;
//	public Computer(String cpu, String harddisk, String memery, String gpu, String power, String motherboard,
//			String cdrom, String fan, String netcard) {
//		super();
//		this.cpu = cpu;
//		this.harddisk = harddisk;
//		this.memery = memery;
//		this.gpu = gpu;
//		this.power = power;
//		this.motherboard = motherboard;
//		this.cdrom = cdrom;
//		this.fan = fan;
//		this.netcard = netcard;
//	}
	
	public Computer() {
	}
	
	private Computer(ComputerBuilder cb) {
		this.cpu = cb.cpu;
		this.harddisk = cb.harddisk;
		this.memery = cb.memery;
		this.gpu = cb.gpu;
		this.power = cb.power;
		this.netcard = cb.netcard;
		this.motherboard = cb.motherboard;
		this.fan = cb.fan;
		this.cdrom = cb.cdrom;
	}
	
	// 以上的构造器，使用起来确实很麻烦！ 也许有小伙伴会这样向，我们可以对构造器进行重载，把参数减少, 事实证明，这也很2....
	
	// 最好的解决方法是使用建造这设计模式的第二种形态
	// 1. 建造者必须是产品的一个静态内部类
	// 2. 建造者的属性完全与产品的所有属性一致
	// 3. 为建造者的每一个属性都添加一个setter方法，且该setter方法必须返回建造者对象本身
	// (以前的javabean的setter方法，返回值是void)
	
	public static class ComputerBuilder {
		private String cpu;
		private String harddisk;
		private String memery;
		private String gpu;
		private String power;
		private String motherboard; // 主板
		private String cdrom;
		private String fan;
		private String netcard;
		public ComputerBuilder setCpu(String cpu) {
			this.cpu = cpu;
			return this;
		}
		public ComputerBuilder setHarddisk(String harddisk) {
			this.harddisk = harddisk;
			return this;
		}
		public ComputerBuilder setMemery(String memery) {
			this.memery = memery;
			return this;
		}
		public ComputerBuilder setGpu(String gpu) {
			this.gpu = gpu;
			return this;
		}
		public ComputerBuilder setPower(String power) {
			this.power = power;
			return this;
		}
		public ComputerBuilder setMotherboard(String motherboard) {
			this.motherboard = motherboard;
			return this;
		}
		public ComputerBuilder setCdrom(String cdrom) {
			this.cdrom = cdrom;
			return this;
		}
		public ComputerBuilder setFan(String fan) {
			this.fan = fan;
			return this;
		}
		public ComputerBuilder setNetcard(String netcard) {
			this.netcard = netcard;
			return this;
		}
		
		public Computer build() {
			System.out.println("组装" + cpu + "," + gpu + "," + memery);
			return new Computer(this);
		}
	}

	@Override
	public String toString() {
		return "Computer [cpu=" + cpu + ", harddisk=" + harddisk + ", memery=" + memery + ", gpu=" + gpu + ", power="
				+ power + ", motherboard=" + motherboard + ", cdrom=" + cdrom + ", fan=" + fan + ", netcard=" + netcard
				+ "]";
	}

	public Computer setCpu(String cpu) {
		this.cpu = cpu;
		return this;
	}

	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}

	public void setMemery(String memery) {
		this.memery = memery;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public void setMotherboard(String motherboard) {
		this.motherboard = motherboard;
	}

	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}

	public void setFan(String fan) {
		this.fan = fan;
	}

	public void setNetcard(String netcard) {
		this.netcard = netcard;
	}
	
}


public class Test {
	public static void main(String[] args) {
		ComputerBuilder cb = new ComputerBuilder();
		Computer c = cb.setMotherboard("华硕主板").setCpu("酷睿8代").setFan("cpu自带的风扇").setHarddisk("500g").build();
		System.out.println(c);
	}
}

// 建造者设计模式的第二种形态： 参考effective java 第二条2 ：当类的属性过多时，就使用建造者设计模式的第二种形态  