package com.cfysu.tankgame.model;

import java.awt.Color;
import java.util.Vector;

import com.cfysu.tankgame.util.DirectionEnum;

public class BaseTank implements Tank{

	private volatile int position_x;
	private volatile int position_y;
	private volatile Color color;
	private int speed;
	private volatile DirectionEnum direction;
	private Vector<Bullet> bullets = new Vector<Bullet>();
	public BaseTank(int position_x, int position_y, Color color) {
		super();
		this.position_x = position_x;
		this.position_y = position_y;
		this.color = color;
	}
	
	public void move(DirectionEnum directionEnum){
		switch (directionEnum) {
		case UP:
			direction = DirectionEnum.UP;
			this.position_y -= this.speed; 
			break;
		case DOWN:
			direction = DirectionEnum.DOWN;
			this.position_y += this.speed;
			break;
		case LEFT:
			direction = DirectionEnum.LEFT;
			this.position_x -= this.speed;
			break;
		case RIGHT:
			direction = DirectionEnum.RIGHT;
			this.position_x += this.speed;
			break;
		default:
			break;
		}
	}
	
	public void fire(){
		Bullet bullet = null;
		switch (this.direction) {
		case UP:
			bullet = new Bullet(position_x + 9, position_y, this.direction);
			bullets.add(bullet);
			break;
		case DOWN:
			bullet = new Bullet(position_x + 9, position_y + 30, this.direction);
			bullets.add(bullet);
			break;
		case LEFT:
			bullet = new Bullet(position_x, position_y + 9, this.direction);
			bullets.add(bullet);
			break;
		case RIGHT:
			bullet = new Bullet(position_x + 30, position_y + 9, this.direction);
			bullets.add(bullet);
			break;
		default:
			break;
		}
		Thread bulletThread = new Thread(bullet);
		bulletThread.start();
	}
	
	public void getAliveBulletNum(){
		int aliveNum = 0;
		for (Bullet bullet : this.bullets) {
			if(bullet.isAlive()){
				aliveNum ++;
			}
		}
		
		System.out.println("有效的子弹个数：" + aliveNum);
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public DirectionEnum getDirection() {
		return direction;
	}

	public void setDirection(DirectionEnum direction) {
		this.direction = direction;
	}

	public Vector<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(Vector<Bullet> bullets) {
		this.bullets = bullets;
	}
	
}
