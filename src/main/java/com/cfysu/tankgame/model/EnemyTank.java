package com.cfysu.tankgame.model;

import java.awt.Color;
import java.util.Random;

import com.cfysu.tankgame.util.DirectionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnemyTank extends BaseTank implements Runnable{

	private static final Logger LOGGER = LoggerFactory.getLogger(EnemyTank.class);
	public EnemyTank(int position_x, int position_y) {
		super(position_x, position_y, Color.RED);
		//设置速度
		super.setSpeed(2);
		super.setDirection(DirectionEnum.DOWN);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//1.方向
			Random random = new Random();
			int num = random.nextInt(100);
			if(num < 30){
				super.move(DirectionEnum.LEFT);
			}else if (num < 60) {
				super.move(DirectionEnum.RIGHT);
			}else {
				super.move(DirectionEnum.DOWN);
			}
			LOGGER.info(String.format(Thread.currentThread().getName() + ":bulletsVector.size%d", this.getBullets().size()));

			//2.是否开火
			//todo 为什么vector.size()返回的是活动线程的数量，而不是实际线程的数量（包括死亡的线程）
			//if(this.getBullets().size() < 6){
			if(this.bulletNumPer < 6){
				LOGGER.info(String.format("进来了:" + Thread.currentThread().getName() + ":子弹数量%d", this.getBullets().size()));
				super.fire();
			}
		}
		
	}

}
