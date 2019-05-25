package com.mycompany.a1.Objects;

import java.util.Random;

import com.codename1.ui.Graphics;
import com.mycompany.a1.Interface.ICollider;
import com.mycompany.a1.Interface.IDrawable;
import com.mycompany.a1.Interface.IMovable;

public class FlyingSaucers extends MovableObject implements IDrawable, IMovable, ICollider {
	
	public FlyingSaucers() {
		super();
	}

	public boolean collidesWith(ICollider obj) {
		boolean result = false; 
		int thisCenterX = (int) (this.getLocation().getX() + (this.getSize()/2)); // find centers 
		int thisCenterY = (int) (this.getLocation().getY() + (this.getSize()/2)); 
		int otherCenterX = (int) (((MovableObject)obj).getLocation().getX() + ((MovableObject)obj).getSize()/2); 
		int otherCenterY = (int) (((MovableObject)obj).getLocation().getY() + (((MovableObject)obj).getSize()/2)); // find dist between centers (use square, to avoid taking roots) 
		int dx = thisCenterX - otherCenterX; 
		int dy = thisCenterY - otherCenterY; 
		int distBetweenCentersSqr = (dx*dx + dy*dy); // find square of sum of radii 
		int thisRadius = this.getSize()/2; 
		int otherRadius = ((MovableObject)obj).getSize()/2; 
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius); 
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true; 
			} 
		return result ; 
	}

	public void handleCollision(ICollider otherObj) {
		if (otherObj instanceof Asteroid) 
			this.setFlag(false);
		
		if (otherObj instanceof Missiles)
			this.setFlag(true);
		
		if (otherObj instanceof SpaceStation) 
			this.setFlag(false);
		
	}

	public void draw(Graphics g, ObjectLocation pCmpRelPrnt) {
		move();
		int mapOriginX = (int)pCmpRelPrnt.getX();
		int mapOriginY = (int)pCmpRelPrnt.getY();
		int currentX = ((int)this.getLocation().getX());
		int currentY = ((int)this.getLocation().getY());
		g.setColor(this.getColor());
		ObjectLocation top = new ObjectLocation(pCmpRelPrnt.getX()+currentX, pCmpRelPrnt.getY()+currentY + (this.getSize()/2)); 
		ObjectLocation bottomLeft = new ObjectLocation(pCmpRelPrnt.getX()+currentX - (this.getSize()/2), pCmpRelPrnt.getY()+currentY  - (this.getSize()/2)); 
		ObjectLocation bottomRight = new ObjectLocation(pCmpRelPrnt.getX()+currentX + (this.getSize()/2), pCmpRelPrnt.getY()+currentY  - (this.getSize()/2)); 
	     
	      
	    int [] xPts = new int [] {(int) top.getX(), (int) bottomLeft.getX(), (int)bottomRight.getX()}; 
	    int [] yPts = new int [] {(int) top.getY(), (int) bottomLeft.getY(), (int)bottomRight.getY()}; 
	    g.fillPolygon(xPts, yPts, 3);
	    
	    //test line offsets center_of_objectX-mapOriginX to the center_of_object
	    g.drawLine((int)top.getX()-mapOriginX, (int)top.getY()-(this.getSize()/2), (int)top.getX(), (int)top.getY()-(this.getSize()/2));
	}
	
}
