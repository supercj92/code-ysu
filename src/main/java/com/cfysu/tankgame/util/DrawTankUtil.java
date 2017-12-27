package com.cfysu.tankgame.util;

import java.awt.Graphics;
import java.awt.Paint;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.cfysu.tankgame.model.BaseTank;
import com.cfysu.tankgame.model.Bullet;

public class DrawTankUtil {

	public static void drawTank(Graphics painter, BaseTank tank){
		//根据不同的方向,画出坦克
		switch (tank.getDirection()) {
		case UP:
			drawUpTank(painter, tank);
			break;
		case DOWN:
			drawDownTank(painter, tank);
			break;
		case LEFT:
			drawLeftTank(painter, tank);
			break;
		case RIGHT:
			drawRightTank(painter, tank);
			break;
		default:
			break;
		}
		//画出子弹
		Iterator<Bullet> iterable = tank.getBullets().iterator();
		while(iterable.hasNext()){
			Bullet bullet = iterable.next();
			if(bullet.getDirectionEnum().equals(DirectionEnum.LEFT)){
				//System.out.println(JSON.toJSONString(bullet));
			}
			if(bullet.isAlive()){
				painter.fillOval(bullet.getPosition_x(), bullet.getPosition_y(), 5, 5);
			}else {
				iterable.remove();
			}
		}
			
	}
	
	private static void drawUpTank(Graphics painter, BaseTank tank){
		//设置颜色
		painter.setColor(tank.getColor());
		
		painter.fill3DRect(tank.getPosition_x(), tank.getPosition_y(), 5, 30,false);
		//2.
		painter.fill3DRect(tank.getPosition_x()+15, tank.getPosition_y(), 5, 30,false);
		//3.
		painter.fill3DRect(tank.getPosition_x()+5, tank.getPosition_y()+5, 10, 20,false);
		
		//4.画盖子
		painter.fillOval(tank.getPosition_x()+5, tank.getPosition_y()+10, 10, 10);
		
		//5.画炮筒
		painter.drawLine(tank.getPosition_x()+9, tank.getPosition_y()+15, tank.getPosition_x()+9, tank.getPosition_y());
	}
	
	private static void drawDownTank(Graphics painter, BaseTank tank){
		painter.setColor(tank.getColor());
		//1.left
		painter.fill3DRect(tank.getPosition_x(), tank.getPosition_y(), 5, 30,false);
		//2.
		painter.fill3DRect(tank.getPosition_x()+15, tank.getPosition_y(), 5, 30,false);
		//3.
		painter.fill3DRect(tank.getPosition_x()+5, tank.getPosition_y()+5, 10, 20,false);
		
		//4.
		painter.fillOval(tank.getPosition_x()+5, tank.getPosition_y()+10, 10, 10);
		
		//5.
		painter.drawLine(tank.getPosition_x()+9, tank.getPosition_y()+15, tank.getPosition_x()+9, tank.getPosition_y()+30);
	}
	private static void drawLeftTank(Graphics painter, BaseTank tank){
		painter.setColor(tank.getColor());
		painter.fill3DRect(tank.getPosition_x(), tank.getPosition_y(), 30, 5, false);
		
		painter.fill3DRect(tank.getPosition_x(), tank.getPosition_y()+15, 30, 5, false);
		
		painter.fill3DRect(tank.getPosition_x()+5, tank.getPosition_y()+5, 20, 10, false);
		
		painter.fillOval(tank.getPosition_x()+10, tank.getPosition_y()+5, 10, 10);
		
		painter.drawLine(tank.getPosition_x()+15, tank.getPosition_y()+10,tank.getPosition_x(), tank.getPosition_y()+10);
	}
	private static void drawRightTank(Graphics painter, BaseTank tank){
		painter.setColor(tank.getColor());
		painter.fill3DRect(tank.getPosition_x(), tank.getPosition_y(), 30, 5, false);
		
		painter.fill3DRect(tank.getPosition_x(), tank.getPosition_y()+15, 30, 5, false);
		
		painter.fill3DRect(tank.getPosition_x()+5, tank.getPosition_y()+5, 20, 10, false);
		
		painter.fillOval(tank.getPosition_x()+10, tank.getPosition_y()+5, 10, 10);
		
		painter.drawLine(tank.getPosition_x()+15, tank.getPosition_y()+10,tank.getPosition_x()+30 , tank.getPosition_y()+10);
	}
	
	/*public static void drawBullet(Graphics painter ,Bullet bullet){
		painter.setColor(c)
	}*/
}
