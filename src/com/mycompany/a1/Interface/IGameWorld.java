package com.mycompany.a1.Interface;

import com.mycompany.a1.Objects.GameCollection;


public interface IGameWorld {
	public int getTime();
	public int getLives();
	public int getAsteroidCount();
	public int getMissileCount();
	public GameCollection getGameObjects();
	
}
