package com.headfirstpaddy;

abstract class Animal{
	String name;
	
	public void sleep(){
		System.out.println(this.name + " fell asleep.");
	}
}