package com.mycompany.a1.Objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a1.Interface.ICollider;
import com.mycompany.a1.Interface.IDrawable;
import com.mycompany.a1.Interface.IMovable;
import com.mycompany.a1.Objects.GameObject;
import com.mycompany.a1.Objects.ObjectLocation;

public class Asteroid extends MovableObject implements IMovable, IDrawable, ICollider {
	//private int size = (6 + rand.nextInt(30));
	//private int heading = rand.nextInt(359);
	//private int speed = rand.nextInt(10);
	private int len = getSize() / 2;
	public Asteroid() {	
		super();
		super.setRandSpeed();
		super.setRandSize();
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
		ObjectLocation top = new ObjectLocation(mapOriginX+currentX, mapOriginY+currentY + (this.getSize()/3)); 
		ObjectLocation bottomLeft = new ObjectLocation(mapOriginX+currentX - (this.getSize()/3), mapOriginY+currentY  - (this.getSize()/3)); 
		ObjectLocation bottomRight = new ObjectLocation(mapOriginX+currentX + (this.getSize()/3), mapOriginY+currentY  - (this.getSize()/3)); 
	     
	    int [] xPts = new int [] {(int) top.getX(), (int) bottomLeft.getX(), (int)bottomRight.getX()}; 
	    int [] yPts = new int [] {(int) top.getY(), (int) bottomLeft.getY(), (int)bottomRight.getY()}; 
	    g.fillPolygon(xPts, yPts, 3);
	}
	
	public String toString() {	
		int color = getColor();
		
		String asteroidString= "Asteroid: " + "loc = " + Math.round(getX()) + " ," + Math.round(getY()) 
								+ "Color = [" + ColorUtil.red(color) + ", " + ColorUtil.green(color) + ", "
								+ ColorUtil.blue(color) + "] " + "Speed = " + getSpeed() + " Heading = " 
								+ getHeading();
		return asteroidString;
	}

	public boolean collidesWith(ICollider otherObj) {
		boolean result = false; 
		int thisCenterX = (int) (this.getLocation().getX() + (this.getSize()/2)); // find centers 
		int thisCenterY = (int) (this.getLocation().getY() + (this.getSize()/2)); 
		int otherCenterX = (int) (((GameObject)otherObj).getLocation().getX() + ((GameObject)otherObj).getSize()/2); 
		int otherCenterY = (int) (((GameObject)otherObj).getLocation().getY() + (((GameObject)otherObj).getSize()/2));  
		int dx = thisCenterX - otherCenterX; 
		int dy = thisCenterY - otherCenterY; 
		int distBetweenCentersSqr = (dx*dx + dy*dy); // find square of sum of radii 
		int thisRadius = this.getSize()/2; 
		int otherRadius = ((GameObject)otherObj).getSize()/2; 
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius); 
		if (distBetweenCentersSqr <= radiiSqr)  
			result = true;  
		return result; 
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
		
		
	}
}
