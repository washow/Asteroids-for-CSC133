package com.mycompany.a1.Objects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	private ObjectLocation location;
	private int size;
	private int color;
	private int speed;
	private int heading;
	private boolean flag;
	Random rand;

	public GameObject() {
		//ObjectLocation newLoc = new ObjectLocation(randomX(), randomY());
		ObjectLocation newLoc = new ObjectLocation(randInt(0, 700), randInt(0, 900));	
		int sp = randInt(5, 20);
		int s = randInt(15, 40);
		int h = randInt(0, 360);
		location = newLoc;
		size = s;
		speed = sp;
		heading = h;
	}
		
	public GameObject(int size, ObjectLocation location, int color) {
		this.size = size;
		this.location = location;
		this.color = color;
		
	}
	
/////////////////////////////////////////////////////////////////////
// -------------------------- RANDOMIZER -------------------------//
	public int randInt(int min, int max) {
		int randNum;
		Random rand = new Random();
		randNum = rand.nextInt(max + 1 - min) + min;
		return randNum;
	}
	
// --------------------------------------------------------------//
///////////////////////////////////////////////////////////////////
	
	
	public boolean getFlag() {
		return flag;
	}
	
	public ObjectLocation getLocation() {
		return this.location;
	}
	
	public float getX() {
		return this.location.getX();
	}
	
	public float getY() {
		return this.location.getY();
	}
	
	public int getColor() {
		return this.color;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setRandSize() {
		int randNum = randInt(15, 40);
		this.size = randNum;
	}
	
	public void setRandSpeed() {
		int randNum = randInt(10, 30);
		this.speed = randNum;
	}
	
	public void setFlag(boolean f) {
		this.flag = f;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public void setLocation(ObjectLocation location) {
		this.location = location;
	}
	
	public void setRandLocation(int wBottom, int hBottom, int wTop, int hTop) {
		int x = randInt(wBottom, wTop);
		int y = randInt(hBottom, hTop);
		ObjectLocation newLoc = new ObjectLocation(x, y);
		this.setLocation(newLoc);
	}
	
	public void setX(float x) {
		this.location.setX(x);
	}
	
	public void setY(float y) {
		this.location.setY(y);
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
}
