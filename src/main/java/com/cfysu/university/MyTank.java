package com.cfysu.university;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
public class MyTank extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTank tank=new MyTank();
	}
	

	public MyTank(){
		mp=new MyPanel();
		this.add(mp);
		//ע�������
		this.addKeyListener(mp);
		//����mp�߳�
		Thread th=new Thread(mp);
		th.start();
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

}

//�ҵ����
class MyPanel extends JPanel implements KeyListener,Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
			Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
			//�ж��Ƿ���е���̹��
			for(int i=0;i<hero.bb.size();i++){
				Bullet b=hero.bb.get(i);
				if(b.isAlive){
					for(int j=0;j<ets.size();j++){
						Enemy tank=ets.get(j);
						isHit(b,tank);
					}
				}
			}
			//�ж��Ƿ�����ҷ�̹��
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
			
			//�ػ�
			this.repaint();
			
		}
	}
	//�ж��Ƿ����̹��
	public void isHit(Bullet b,Tank et){
		switch(et.direct){
		
		case 0:
		case 2:
			if(b.isAlive&&et.isAlive&&b.getY()<et.getY()+30&&b.getY()>et.getY()&&b.getX()>et.getX()&&b.getX()<et.getX()+20){
				//����
				b.isAlive=false;
				et.isAlive=false;
			}
			break;
		case 1:
		case 3:
			if(b.isAlive&&et.isAlive&&b.getY()<et.getY()+20&&b.getY()>et.getY()&&b.getX()>et.getX()&&b.getX()<et.getX()+30){
				//����
				b.isAlive=false;
				et.isAlive=false;
			}
			break;
		}
		
	}
	
	
	//����һ���ҵ�̹��
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
		//�ػ�
		repaint();
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public MyPanel(){
		//�����ҵ�̹��
		hero=new Hero(200,200);
		//��������̹����
		for(int i=0;i<etsCount;i++){
			Enemy et=new Enemy((i+1)*100,0);
			Thread th1=new Thread(et);
			th1.start();
			ets.add(et);
		}
	
	}
	//��дpaint
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		if(hero.isAlive){
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
		}
		//�������˵�̹��
		for(int i=0;i<ets.size();i++){
			Enemy eTank=ets.get(i);
			if(eTank.isAlive)
			{
			this.drawTank(eTank.getX(),eTank.getY(),g,eTank.getDirect(),1);
			}
		}
		//�����ҷ�̹�˵��ӵ�
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
		//�����з�̹�˵��ӵ�
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
	//����̹��
	public void drawTank(int x,int y,Graphics g,int direc,int type){
		
		//̹������
		switch(type){
		case 0:
			g.setColor(Color.GREEN);
			break;
		case 1:
			g.setColor(Color.RED);
			break;
		}
		//����
		switch(direc){
		//����
		case 0:
			
			//����̹��
			//1.������ߵľ���
			
			g.fill3DRect(x, y, 5, 30,false);
			//2.�ұ�
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.�м�
			g.fill3DRect(x+5, y+5, 10, 20,false);
			
			//4.Բ��
			g.fillOval(x+5, y+10, 10, 10);
			
			//5.��Ͳ
			g.drawLine(x+9, y+15, x+9, y);
			break;
			//����
		case 1:
			g.fill3DRect(x, y, 30, 5, false);
			
			g.fill3DRect(x, y+15, 30, 5, false);
			
			g.fill3DRect(x+5, y+5, 20, 10, false);
			
			g.fillOval(x+10, y+5, 10, 10);
			
			g.drawLine(x+15, y+10,x+30 , y+10);
			
			break;
			//����
		case 2:
			//1.left
			g.fill3DRect(x, y, 5, 30,false);
			//2.�ұ�
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.�м�
			g.fill3DRect(x+5, y+5, 10, 20,false);
			
			//4.Բ��
			g.fillOval(x+5, y+10, 10, 10);
			
			//5.��Ͳ
			g.drawLine(x+9, y+15, x+9, y+30);
			break;
			//����
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


