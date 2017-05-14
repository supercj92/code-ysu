package com.cfysu.tankgame.model;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private int height;
	private int width;
	
	public GameFrame(int height, int width){
		this.setHeight(height);
		this.setWidth(width);
		GamePanel gamePanel = new GamePanel();
		Thread panel = new Thread(gamePanel);
		panel.start();
		this.add(gamePanel);
		this.addKeyListener(gamePanel);
	}
	
	public void startGame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(this.getHeight(), this.getWidth());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
