package com.mycompany.a1.Objects;

import com.codename1.ui.Graphics;
import com.mycompany.a1.Interface.ICollider;
import com.mycompany.a1.Interface.IDrawable;
import com.mycompany.a1.Interface.IMovable;
import com.mycompany.a1.Objects.GameObject;
import com.mycompany.a1.Objects.ObjectLocation;

public class Missiles extends MovableObject implements IMovable, IDrawable, ICollider {
	private int fuel;
	private int maxFuel = 35;
	private boolean hasFuel;
	
	
	public Missiles() {
		super();
		fuel = maxFuel;
	}
	
	public int getFuel() {
		return fuel;
	}
	
	public void setFuel() {
		this.fuel = maxFuel;
	}
	
	public void consume() {
		if (getFuel() == 0) {
			hasFuel = false;
			this.setFlag(true);
		}
		else {
			this.setSpeed(this.getSpeed()+1);
			fuel--;
			hasFuel = true;
		}
	}
	
	public void move() {
		int angle = 90 - getHeading();
		float deltaX = (((float)Math.cos(Math.toRadians(angle)))* getSpeed());	
		float deltaY = (((float)Math.sin(Math.toRadians(angle)))* getSpeed());	
		float newX = Math.round(this.getLocation().getX() + deltaX);			
		float newY = Math.round(this.getLocation().getY() + deltaY);			
		ObjectLocation newLoc = new ObjectLocation(newX, newY);					
		this.setLocation(newLoc);
		this.consume();
		System.out.println(fuel);
		System.out.println(hasFuel);
		
	}
	
	public void draw(Graphics g, ObjectLocation pCmpRelPrnt) {
		move();
		g.setColor(this.getColor());
		
		int mapOriginX = (int)pCmpRelPrnt.getX();
		int mapOriginY = (int)pCmpRelPrnt.getY();
		int currentX = ((int)this.getLocation().getX()) + mapOriginX;
		int currentY = ((int)this.getLocation().getY()) + mapOriginY;
		
		ObjectLocation topLeft = new ObjectLocation(currentX - 10, currentY - 10);
		ObjectLocation topRight = new ObjectLocation(currentX + 10, currentY -10);
		ObjectLocation bottomLeft = new ObjectLocation(currentX-10, currentY +10);
		ObjectLocation bottomRight = new ObjectLocation(currentX +10, currentY +10);
		
		g.fillRect(currentX - (this.getSize() / 4), currentY - (this.getSize() / 2),
										this.getSize() / 2, this.getSize());
		
	}
	
	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX() + (this.getSize()/2)); // find centers 
		int thisCenterY = (int) (this.getLocation().getY() + (this.getSize()/2)); 
		int otherCenterX = (int) (((GameObject)obj).getLocation().getX() + ((GameObject)obj).getSize()/2); 
		int otherCenterY = (int) (((GameObject)obj).getLocation().getY() + (((GameObject)obj).getSize()/2)); // find dist between centers (use square, to avoid taking roots) 
		int dx = thisCenterX - otherCenterX; 
		int dy = thisCenterY - otherCenterY; 
		int distBetweenCentersSqr = (dx*dx + dy*dy); // find square of sum of radii 
		int thisRadius = this.getSize()/2; 
		int otherRadius = ((GameObject)obj).getSize()/2; 
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius); 
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true; 
			} 
		return result ; 
		
	}

	public void handleCollision(ICollider otherObj) {
		if (otherObj instanceof Ships) 
			this.setFlag(false);
		
		if (otherObj instanceof Asteroid)
			this.setFlag(true);
		
		if (otherObj instanceof Missiles) 
			this.setFlag(false);
		
		if (otherObj instanceof SpaceStation) 
			this.setFlag(false);
		
		if (otherObj instanceof FlyingSaucers)
			this.setFlag(true);
		
	}

	public boolean hasFuel() {
		return hasFuel;
	}
	

	
}
