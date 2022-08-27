package com.woniuxy.p_strategy.f;

interface Weapon {
	public void hit();
}

class Sword implements Weapon {
	@Override
	public void hit() {
		System.out.println("ÁùÂöÉñ½£");
	}
}

class Kinfe implements Weapon {
	@Override
	public void hit() {
		System.out.println("ÎÒÆşËÀÄã");
	}
}

class Fist implements Weapon {
	@Override
	public void hit() {
		System.out.println("½µÁúÊ®°ËÕÆ");
	}
}

class Role {
	public String name;
	public int hp;
	
	public Weapon weapon;
	
	public void attck() {
		weapon.hit();
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}



class King extends Role {
	public King() {
		weapon = new Sword();
	}
}

class Queen extends Role {
	public Queen() {
		weapon = new Kinfe();
	}
}


public class Test {

	public static void main(String[] args) {
		Queen q = new Queen();
		q.attck();
		
		q.setWeapon(new Sword());
		
		q.attck();
	}

}
