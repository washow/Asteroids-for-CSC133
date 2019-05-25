package com.mycompany.a1.Objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a1.Interface.ICollider;
import com.mycompany.a1.Interface.IDrawable;
import com.mycompany.a1.Interface.IMovable;
import com.mycompany.a1.Interface.ISteerable;
import com.mycompany.a1.Objects.GameObject;
import com.mycompany.a1.Objects.ObjectLocation;

public class Ships extends MovableObject implements ISteerable, IMovable, IDrawable, ICollider {
	private int missileCount;
	private int maxSpeed;
	private boolean flag = false;
	
	public Ships() {
		maxSpeed = 20;
		missileCount = 12;
		this.flag = false;
	}
		
	public void turnLeft() {
		this.setHeading(this.getHeading() + 10);
	}
	
	public void turnRight() {
		this.setHeading(this.getHeading() - 10);
	}
		
	public void increaseSpeed() {
		int curSpeed = getSpeed();
			if (curSpeed < maxSpeed) {
				curSpeed++;
				setSpeed(curSpeed);
			}
			else
				System.out.println("Already at max speed");
			
	}
	
	public void decreaseSpeed() {
		int curSpeed = getSpeed();
		if (getSpeed() > 0) {
			curSpeed--;
			setSpeed(curSpeed);
		}
		else
			System.out.println("Already at minimum speed");
	}

	public boolean shoot() {
		if (getMissileCount() > 0) {
			setMissileCount(getMissileCount() - 1);
			return true;
		}
		else
			return false;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag(boolean f) {
		this.flag = f;
	}
	
	public int getMissileCount() {
		return this.missileCount;
	}
	
	public void setMissileCount(int count) {
		this.missileCount = count;
	}
	
	public void reload() {
		setMissileCount(12);
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
	
	public void draw(Graphics g, ObjectLocation pCmpRelPrnt) {
		move();
		int mapOriginX = (int)pCmpRelPrnt.getX();
		int mapOriginY = (int)pCmpRelPrnt.getY();
		int currentX = ((int)this.getLocation().getX());
		int currentY = ((int)this.getLocation().getY());
		g.setColor(this.getColor());
		ObjectLocation top = new ObjectLocation(mapOriginX+currentX, mapOriginY+currentY + (this.getSize()/2)); 
		ObjectLocation bottomLeft = new ObjectLocation(mapOriginX+currentX - (this.getSize()/2), mapOriginY+currentY  - (this.getSize()/2)); 
		ObjectLocation bottomRight = new ObjectLocation(mapOriginX+currentX + (this.getSize()/2), mapOriginY+currentY  - (this.getSize()/2)); 
	     
	    int [] xPts = new int [] {(int) top.getX(), (int) bottomLeft.getX(), (int)bottomRight.getX()}; 
	    int [] yPts = new int [] {(int) top.getY(), (int) bottomLeft.getY(), (int)bottomRight.getY()}; 
	    g.fillPolygon(xPts, yPts, 3);
	}
	
	
	
	
	public String toString() {
		int color = getColor();
		
		String shipString = "Ship: loc = " + Math.round(getX()) + " ," + Math.round(getY()) + " ," + " color = [" + 
							ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) 
							+ "]" + "speed = " + getSpeed() + " Heading = " + getHeading() + 
							" missiles = " + getMissileCount();  
		
		return shipString;
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
		if (otherObj instanceof Asteroid) 
			this.setFlag(true);
		
		if (otherObj instanceof Missiles)
			this.setFlag(false);
		
		if (otherObj instanceof SpaceStation) {
			this.setFlag(false);
			this.reload();
		}
		
		if (otherObj instanceof Ships) {
			System.out.println(flag);
			this.setFlag(false);
		}
			
		if (otherObj instanceof FlyingSaucers) 
			this.setFlag(true);
	}
	
	
}
