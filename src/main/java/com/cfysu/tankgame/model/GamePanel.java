package com.cfysu.tankgame.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;

import com.cfysu.tankgame.util.DirectionEnum;
import com.cfysu.tankgame.util.DrawTankUtil;

public class GamePanel extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	private Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
	private HeroTank heroTank;
	private int enemyNum = 3;
	public GamePanel(){
		this.setBackground(Color.BLACK);
		this.repaint();
		//生成敌人坦克
		generateEnemy(enemyTanks);
		//生成自己坦克
		heroTank = new HeroTank(200, 200);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}
	
	@Override
	public void paint(Graphics painter) {
		super.paint(painter);
		//画出坦克
		for(EnemyTank enemyTank : enemyTanks){
			DrawTankUtil.drawTank(painter, enemyTank);
		}
		
		//画出自己的坦克
		DrawTankUtil.drawTank(painter, heroTank);
		//System.out.println(JSON.toJSONString(tank));
		
		//System.out.println(JSON.toJSONString(heroTank));
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			heroTank.move(DirectionEnum.UP);
			break;
		case KeyEvent.VK_DOWN:
			heroTank.move(DirectionEnum.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			heroTank.move(DirectionEnum.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			heroTank.move(DirectionEnum.RIGHT);
			break;
		case KeyEvent.VK_F:
			heroTank.fire();
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
	
	private void generateEnemy(Vector<EnemyTank> enemyTanks){
		for(int i = 0;i < this.enemyNum;i++){
			EnemyTank enemyTank = new EnemyTank(100 + i*100, 100);
			Thread enemyThread = new Thread(enemyTank);
			enemyThread.start();
			enemyTanks.add(enemyTank);
		}
	}

}
