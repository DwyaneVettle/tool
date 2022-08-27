package com.woniuxy.e_demeter.postive;

// 作者
class Computer {
	private void saveCrruentTask() {
		System.out.println("保存当前正在运行的任务");
	}
	
	private void logout() {
		System.out.println("注销用户");
	}
	
	private void shutdownScreen() {
		System.out.println("关闭屏幕");
	}
	
	private void shutdownPower() {
		System.out.println("关闭电源");
	}
	
	public void shutdown() {
		saveCrruentTask();
		logout();
		shutdownScreen();
		shutdownPower();
	}
}
