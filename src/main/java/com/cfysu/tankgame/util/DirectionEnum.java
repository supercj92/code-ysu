package com.cfysu.tankgame.util;

public enum DirectionEnum {
	UP(1, "ио"), DOWN(2, "об"), LEFT(3, "вС"), RIGHT(4, "ср");
	private int key;
	private String direction;
	private DirectionEnum(int key, String direction) {
		this.key = key;
		this.direction = direction;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

}
