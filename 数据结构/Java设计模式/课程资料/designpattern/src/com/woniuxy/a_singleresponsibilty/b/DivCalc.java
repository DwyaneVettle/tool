package com.woniuxy.a_singleresponsibilty.b;

public class DivCalc extends Calc {

	@Override
	public double calc(double a, double b) {
		if(b == 0) {
			return 0;
		}
		return a / b;
	}

}
