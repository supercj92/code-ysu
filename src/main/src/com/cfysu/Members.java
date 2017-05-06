package com.cfysu;
import java.util.*;
//坦克类
class Tank{
	
	//坦克横纵坐标
	int x=0;
	int y=0;
	int direct=0;
	int speed=3;
	int color;
	boolean isAlive=true;
	Vector<Bullet> bb=new Vector<Bullet>();

	Bullet b=null;
	
	public void fire(int derict){
		//创建子弹
		switch(derict){
		case 0:
		b=new Bullet(x+9,y,derict);
		bb.add(b);
		break;
		case 1:
		b=new Bullet(x+30,y+9,derict);
		bb.add(b);
		break;
		case 2:
		b=new Bullet(x+9,y+30,derict);
		bb.add(b);
		break;
		case 3:
		b=new Bullet(x,y+9,derict);
		bb.add(b);
		break;
		}
		Thread th=new Thread(b);
		th.start();
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public void MoveUp(){
		y-=speed;
	}
	public void MoveRight(){
		x+=speed;
	}
	public void MoveDown(){
		y+=speed;	
	}
	public void MoveLeft(){
		x-=speed;
	}
	
	
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
}

//我的坦克
class Hero extends Tank{
	
	
	
	public Hero(int x,int y){
		super(x,y);
	}
	
}
//敌人的坦克
class Enemy extends Tank implements Runnable{
	
	public Enemy(int x,int y){
		super(x,y);
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
			Thread.sleep(1000);
			}catch(Exception e){e.printStackTrace();};
			//移动
			int dire=(int)((Math.random())*100);
			if(dire<=25){
				if(isAlive){
				super.MoveLeft();
				fire(3);
				super.setDirect(3);
				}
			}else if(dire<=50){
				if(isAlive){
				super.MoveRight();
				super.fire(1);
				super.setDirect(1);
				}
			}else if(dire<=53){
				if(isAlive){
				super.MoveUp();
				super.fire(0);
				super.setDirect(0);
				}
			}else{
				if(isAlive){
				super.MoveDown();
				super.fire(2);
				super.setDirect(2);
				}
			}
			/*switch(dire){
			case 0:
				super.setDirect(0);
				super.MoveDown();
				break;
			case 1:
				super.setDirect(1);
				super.MoveLeft();
				break;
			case 2:
				super.setDirect(2);
				super.MoveRight();
				break;
			case 3:
				super.setDirect(3);
				super.MoveUp();
				break;
			}*/
			//System.out.print(""+dire);
			//super.MoveDown();
		
		}
	}
	
}
//子弹
class Bullet implements Runnable{
	
	
	int x;
	int y;
	boolean isAlive=true;
	int derict;
	//子弹速度
	int speed=2;
	
	
	public Bullet(int x,int y,int derict ){
		this.x=x;
		this.y=y;
		this.derict=derict;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
		
		while(true){
			
			try{
				Thread.sleep(50);
				}catch(Exception e){
					e.printStackTrace();
				}
			
			switch(derict){
			case 0:
				y-=speed;
				break;
			case 1:
				x+=speed;
				break;
			case 2:
				y+=speed;
				break;
			case 3:
				x-=speed;
				break;
			}
			System.out.println("x坐标:"+x+"y坐标:"+y);
			if(x>400||x<0||y>300||y<0){
				isAlive=false;
				System.out.println("------------------------------->break");
				break;
			}
		}
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public int getDerict() {
		return derict;
	}
	public void setDerict(int derict) {
		this.derict = derict;
	}
	
	
}








