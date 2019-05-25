package com.mycompany.a1.Objects;

import java.util.Random;

import com.mycompany.a1.Interface.IMovable;

public class MovableObject extends GameObject implements IMovable {
	Random rand = new Random();
	private ObjectLocation location;
	private int color;
	private int size;
	private int speed;
	private int heading;
	private boolean flag;
	
	public MovableObject() {
		super();
		
	}
	
	public MovableObject(ObjectLocation location, int color, int size) {
		this.location = location;
		this.color = color;
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getHeading() {
		return this.heading;
	}
	
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	public void setLocation(ObjectLocation location) {
		this.location = location;
	}
	
	public ObjectLocation getLocation() {
		return location;
	}
	
	public void move() {
		int angle = 90 - getHeading();
		float deltaX = (((float)Math.cos(Math.toRadians(angle)))* getSpeed());	
		float deltaY = (((float)Math.sin(Math.toRadians(angle)))* getSpeed());	
		float newX = Math.round(this.getLocation().getX() + deltaX);			
		float newY = Math.round(this.getLocation().getY() + deltaY);			
		ObjectLocation newLoc = new ObjectLocation(newX, newY);					
		this.setLocation(newLoc);
	}
}
