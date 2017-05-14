package com.cfysu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
public class MyTank extends JFrame{
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTank tank=new MyTank();
	}
	

	public MyTank(){
		mp=new MyPanel();
		this.add(mp);
		//注册监听器
		this.addKeyListener(mp);
		//启动mp线程
		Thread th=new Thread(mp);
		th.start();
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

}

//我的面板
class MyPanel extends JPanel implements KeyListener,Runnable{
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
			Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
			//判断是否击中敌人坦克
			for(int i=0;i<hero.bb.size();i++){
				Bullet b=hero.bb.get(i);
				if(b.isAlive){
					for(int j=0;j<ets.size();j++){
						Enemy tank=ets.get(j);
						isHit(b,tank);
					}
				}
			}
			//判断是否击中我方坦克
			for(int i=0;i<ets.size();i++){
				Enemy eTank=ets.get(i);
				for(int j=0;j<eTank.bb.size();j++){
					Bullet b=eTank.bb.get(j);
					if(b.isAlive){
						isHit(b,hero);
					}
				}
			}
			
			if(!hero.isAlive){
				System.exit(0);
			}
			
			//重绘
			this.repaint();
			
		}
	}
	//判断是否击中坦克
	public void isHit(Bullet b,Tank et){
		switch(et.direct){
		
		case 0:
		case 2:
			if(b.isAlive&&et.isAlive&&b.getY()<et.getY()+30&&b.getY()>et.getY()&&b.getX()>et.getX()&&b.getX()<et.getX()+20){
				//击中
				b.isAlive=false;
				et.isAlive=false;
			}
			break;
		case 1:
		case 3:
			if(b.isAlive&&et.isAlive&&b.getY()<et.getY()+20&&b.getY()>et.getY()&&b.getX()>et.getX()&&b.getX()<et.getX()+30){
				//击中
				b.isAlive=false;
				et.isAlive=false;
			}
			break;
		}
		
	}
	
	
	//定义一个我的坦克
	Hero hero=null;
	Vector<Enemy> ets=new Vector<Enemy>();
	int etsCount=3;
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("in");
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
			hero.MoveUp();
			hero.setDirect(0);
				break;
			case KeyEvent.VK_DOWN:
				hero.MoveDown();
				hero.setDirect(2);
				break;
			case KeyEvent.VK_LEFT:
				hero.MoveLeft();
				hero.setDirect(3);
				break;
			case KeyEvent.VK_RIGHT:
				hero.MoveRight();
				hero.setDirect(1);
				break;
		}
		if(e.getKeyCode()==KeyEvent.VK_F){
			if(hero.bb.size()<4){
			hero.fire(hero.getDirect());
			}
		}
		//重绘
		repaint();
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public MyPanel(){
		//创建我的坦克
		hero=new Hero(200,200);
		//创建敌人坦克组
		for(int i=0;i<etsCount;i++){
			Enemy et=new Enemy((i+1)*100,0);
			Thread th1=new Thread(et);
			th1.start();
			ets.add(et);
		}
	
	}
	//重写paint
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		if(hero.isAlive){
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
		}
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++){
			Enemy eTank=ets.get(i);
			if(eTank.isAlive)
			{
			this.drawTank(eTank.getX(),eTank.getY(),g,eTank.getDirect(),1);
			}
		}
		//画出我方坦克的子弹
		for(int i=0;i<hero.bb.size();i++){
			Bullet b=hero.bb.get(i);
		
			if(b!=null&&b.isAlive){
				g.setColor(Color.GREEN);
				g.fillOval(b.getX(), b.getY(), 3, 3);
		}
			if(!b.isAlive){
				hero.bb.remove(b);
			}
		}
		//画出敌方坦克的子弹
		for(int i=0;i<ets.size();i++){
			Enemy eTank=ets.get(i);
			for(int j=0;j<eTank.bb.size();j++){
				Bullet b=eTank.bb.get(j);
				if(b!=null&&b.isAlive){
					g.setColor(Color.RED);
					g.fillOval(b.getX(), b.getY(), 3, 3);
				}
			}
		}
		
		
		
		
		
		
	}
	//画出坦克
	public void drawTank(int x,int y,Graphics g,int direc,int type){
		
		//坦克类型
		switch(type){
		case 0:
			g.setColor(Color.GREEN);
			break;
		case 1:
			g.setColor(Color.RED);
			break;
		}
		//方向
		switch(direc){
		//向上
		case 0:
			
			//画出坦克
			//1.画出左边的矩形
			
			g.fill3DRect(x, y, 5, 30,false);
			//2.右边
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.中间
			g.fill3DRect(x+5, y+5, 10, 20,false);
			
			//4.圆形
			g.fillOval(x+5, y+10, 10, 10);
			
			//5.炮筒
			g.drawLine(x+9, y+15, x+9, y);
			break;
			//向右
		case 1:
			g.fill3DRect(x, y, 30, 5, false);
			
			g.fill3DRect(x, y+15, 30, 5, false);
			
			g.fill3DRect(x+5, y+5, 20, 10, false);
			
			g.fillOval(x+10, y+5, 10, 10);
			
			g.drawLine(x+15, y+10,x+30 , y+10);
			
			break;
			//向下
		case 2:
			//1.left
			g.fill3DRect(x, y, 5, 30,false);
			//2.右边
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.中间
			g.fill3DRect(x+5, y+5, 10, 20,false);
			
			//4.圆形
			g.fillOval(x+5, y+10, 10, 10);
			
			//5.炮筒
			g.drawLine(x+9, y+15, x+9, y+30);
			break;
			//向左
		case 3:
			g.fill3DRect(x, y, 30, 5, false);
			
			g.fill3DRect(x, y+15, 30, 5, false);
			
			g.fill3DRect(x+5, y+5, 20, 10, false);
			
			g.fillOval(x+10, y+5, 10, 10);
			
			g.drawLine(x+15, y+10,x, y+10);
			
			break;
			
		}
	}
}


