package com.cfysu.tankgame.model;

import com.cfysu.tankgame.util.DirectionEnum;

public class Bullet implements Runnable{

	private boolean alive = true;
	private DirectionEnum directionEnum;
	private int position_x;
	private int position_y;
	private int speed = 6;

	public Bullet(int position_x, int position_y, DirectionEnum directionEnum) {
		super();
		this.position_x = position_x;
		this.position_y = position_y;
		this.directionEnum = directionEnum;
	}

	@Override
	public void run() {
		
		while(isAlive()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			switch (directionEnum) {
			case UP:
				this.position_y -=this.speed;
				break;
			case DOWN:
				this.position_y +=this.speed;
				break;
			case LEFT:
				this.position_x -= this.speed;
				break;
			case RIGHT:
				this.position_x += this.speed;
				break;
			default:
				break;
			}
		}
	}

	public boolean isAlive() {
		if(this.position_x > 0 && this.position_x < 400 && this.position_y > 0 && this.position_y < 400){
			this.alive = true;
		}else {
			this.alive = false;
		}
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public DirectionEnum getDirectionEnum() {
		return directionEnum;
	}

	public void setDirectionEnum(DirectionEnum directionEnum) {
		this.directionEnum = directionEnum;
	}

	public int getPosition_x() {
		return position_x;
	}

	public void setPosition_x(int position_x) {
		this.position_x = position_x;
	}

	public int getPosition_y() {
		return position_y;
	}

	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
