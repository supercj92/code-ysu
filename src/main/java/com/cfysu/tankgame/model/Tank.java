package com.cfysu.tankgame.model;

import com.cfysu.tankgame.util.DirectionEnum;

public interface Tank {
	
	void move(DirectionEnum directionEnum);
	void fire();
}
