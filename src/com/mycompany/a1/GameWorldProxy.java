package com.mycompany.a1;

import java.util.Observable;

import com.mycompany.a1.Interface.IGameWorld;
import com.mycompany.a1.Objects.GameCollection;

public class GameWorldProxy extends Observable implements IGameWorld{
	private GameWorld realGW;

	public GameWorldProxy(GameWorld gw) {
		realGW = gw;
	}
	
	public int getTime() {
		// TODO Auto-generated method stub
		return realGW.getTime();
	}

	public int getLives() {
		// TODO Auto-generated method stub
		return realGW.getLives();
	}

	public int getAsteroidCount() {
		// TODO Auto-generated method stub
		return realGW.getAsteroidCount();
	}
	
	public int getMissileCount() {
		return realGW.getMissileCount();
	}

	public GameCollection getGameObjects() {
		return realGW.getGameObjects();
		
	}
	


}
