package com.cfysu.tankgame.model;

import java.awt.Color;

import com.cfysu.tankgame.util.DirectionEnum;

public class HeroTank extends BaseTank{

	public HeroTank(int position_x, int position_y) {
		super(position_x, position_y, Color.GREEN);
		super.setSpeed(4);
		super.setDirection(DirectionEnum.UP);
	}

}
