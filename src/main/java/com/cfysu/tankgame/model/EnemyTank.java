package com.cfysu.tankgame.model;

import java.awt.Color;
import java.util.Random;

import com.cfysu.tankgame.util.DirectionEnum;

public class EnemyTank extends BaseTank implements Runnable{

	public EnemyTank(int position_x, int position_y) {
		super(position_x, position_y, Color.RED);
		//设置敌方坦克速度
		super.setSpeed(2);
		super.setDirection(DirectionEnum.DOWN);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//1.随机移动
			Random random = new Random();
			int num = random.nextInt(100);
			if(num < 30){
				super.move(DirectionEnum.LEFT);
			}else if (num < 60) {
				super.move(DirectionEnum.RIGHT);
			}else {
				super.move(DirectionEnum.DOWN);
			}
			
			//2.发射子弹
			if(this.getBullets().size() < 6){
				super.fire();
			}
			
		}
		
	}

}
