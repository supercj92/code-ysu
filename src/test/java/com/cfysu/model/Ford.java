package com.cfysu.model;

public class Ford extends Car {
	public Ford() {
		System.out.println("Ford");
	}

	public static void main(String[] args) {
		Car ford = new Ford();
		System.out.println((ford instanceof Car));
	}
}
