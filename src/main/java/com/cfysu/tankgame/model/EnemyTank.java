package com.cfysu.tankgame.model;

import java.awt.Color;
import java.util.Random;

import com.cfysu.tankgame.util.DirectionEnum;

public class EnemyTank extends BaseTank implements Runnable{

	public EnemyTank(int position_x, int position_y) {
		super(position_x, position_y, Color.RED);
		//���õз�̹���ٶ�
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
			//1.����ƶ�
			Random random = new Random();
			int num = random.nextInt(100);
			if(num < 30){
				super.move(DirectionEnum.LEFT);
			}else if (num < 60) {
				super.move(DirectionEnum.RIGHT);
			}else {
				super.move(DirectionEnum.DOWN);
			}
			
			//2.�����ӵ�
			if(this.getBullets().size() < 6){
				super.fire();
			}
			
		}
		
	}

}
