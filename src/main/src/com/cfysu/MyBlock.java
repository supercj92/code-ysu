package com.cfysu;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MyBlock extends JFrame implements KeyListener{
	private static final long serialVersionUID = -887458152228722775L;
	static MyBlock mb=new MyBlock();
	static int game_body[][]=new int[13][13];
	static int sign_x[]=new int[10];
	static int sign_y[]=new int[10];
	static int blocknumber=1;
		MyBlock(){
			addKeyListener(this);
		}
		public void paint(Graphics g){
			g.setColor(Color.black);
			g.fill3DRect(0, 0, 520, 520, true);
			for(int i=0;i<13;i++){
				for(int j=0;j<13;j++){
					if(game_body[i][j]==1){
					g.setColor(Color.red);
					g.fill3DRect(40*i,40*j,40,40,true);
					}
					if(game_body[i][j]==2){
						g.setColor(Color.magenta);
						g.fill3DRect(40*i,40*j,40,40,true);
					}
			}
		}
		}
		public void dingwei(){
			int k=0;
			qingdingwei();
			for(int i=0;i<13;i++){
				for(int j=0;j<13;j++){
					if(game_body[i][j]==1){
						
							sign_x[k]=i;
							sign_y[k]=j;
							k++;
					}
				}
			}
		}
		public void qingdingwei(){
			for(int k=0;k<4;k++){
				sign_x[k]=0;
				sign_y[k]=0;
			}
		}
		public void qinggame_body(){//game_body数组清零
			for(int i=0;i<13;i++){
				for(int j=0;j<13;j++){
					if(game_body[i][j]==2){
						game_body[i][j]=2;
					}
					else{
						game_body[i][j]=0;
					}
				}
			}
		}
		public void changecolor(){
			for(int k=0;k<4;k++){
				game_body[sign_x[k]][sign_y[k]]=2;
			}
		}
		public void xiaochu(){//满行消除
			for(int j=0;j<13;j++){
				int m=0;
				for(int i=0;i<13;i++){
					if(game_body[i][j]==2){
						m++;
					}
				}
				if(m==13){
					
					for(int x=j;x>=1;x--){
					 for(int k = 0;k < 13;k++)
					  {
					   game_body[k][x] = game_body[k][x-1];
					  }
					}
						
				}
			}
			
		}
		public void xia(){//下降
			dingwei();
			int m=0;
			for(int k=0;k<4;k++){
			if(sign_y[k]==12||game_body[sign_x[k]][sign_y[k]+1]==2){
				m=1;
				changecolor();
				xiaochu();
				
				repaint();
				chansheng();
			}
			}
			if(m==0){
			qinggame_body();
			for(int k=0;k<4;k++){
			game_body[sign_x[k]][sign_y[k]+1]=1;
			repaint();
			}
			}
		}
		public void zuo(){
			int m=0;
			dingwei();
			for(int k=0;k<4;k++){
				if(sign_x[k]==0||game_body[sign_x[k]-1][sign_y[k]]==2){
					m=1;
				}
			}
			if(m==0){
				qinggame_body();
				for(int k=0;k<4;k++){
					game_body[sign_x[k]-1][sign_y[k]]=1;
				}
				repaint();
			}
		}
		public void you(){
			int m=0;
			dingwei();
			for(int k=0;k<4;k++){
				if(sign_x[k]==12||game_body[sign_x[k]+1][sign_y[k]]==2){
					m=1;
				}
			}
			if(m==0){
				qinggame_body();
				for(int k=0;k<4;k++){
					game_body[sign_x[k]+1][sign_y[k]]=1;
				}
				repaint();
			}
		}
		 public void chansheng(){//产生block
			 int num;
			  num = (int)(Math.random() * 3+1);//产生0~3之间的随机整数，包括0不包括3
			  
			switch(num){
			case 1:block1();break;
			case 2:block2();break;
			case 3:block3();break;
			}
		}
		 public void block1(){//长条
			game_body[6][0]=1;
			game_body[6][1]=1;
			game_body[6][2]=1;
			game_body[6][3]=1;
		}
	    public void block2(){//方块
			game_body[6][0]=1;
			game_body[6][1]=1;
			game_body[7][0]=1;
			game_body[7][1]=1;
		}
		 public void block3(){
			game_body[6][0]=1;
			game_body[6][1]=1;
			game_body[5][1]=1;
			game_body[7][1]=1;
		}
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_DOWN){
				this.xia();
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT){
				this.zuo();
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT){
				this.you();
			}
			
		}
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		public static void main(String args[]){
			MyBlock mb=new MyBlock();
			mb.setSize(520,520);
			mb.setVisible(true);
			mb.addWindowListener(new WindowAdapter()  //为了关闭窗口
			  {
			   public void windowClosing(WindowEvent e)
			   {
			       System.exit(0);
			   }
			  });

			game_body[6][0]=1;
			game_body[6][1]=1;
			game_body[5][1]=1;
			game_body[7][1]=1;
			cj t=new cj(mb);
			t.start();
		}
}
class cj extends Thread{
	MyBlock mb;
	cj(MyBlock mb){
		this.mb=mb;
	}
	public void run(){
		while(true){
			try {
				Thread.sleep(1000);
				mb.xia();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
