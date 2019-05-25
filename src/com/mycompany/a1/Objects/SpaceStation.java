package com.mycompany.a1.Objects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a1.Interface.ICollider;
import com.mycompany.a1.Interface.IDrawable;

public class SpaceStation extends FixedObject implements IDrawable, ICollider {
	Random rand = new Random();
	private int blinkRate;
	private boolean blink;
	
	
	public SpaceStation() {
		super();
		blinkRate = rand.nextInt(6) + 1;
	}
	
	public boolean blink(int gameTime) {
		int check = gameTime % blinkRate;
		if (check == 0) 
			blink = true;
		else
			blink = false;
		
		return blink;
	}
	
	public void draw(Graphics g, ObjectLocation pCmpRelPrnt) {
		int mapOriginX = (int)pCmpRelPrnt.getX();
		int mapOriginY = (int)pCmpRelPrnt.getY();
		int currentX = ((int)this.getLocation().getX()) + mapOriginX;
		int currentY = ((int)this.getLocation().getY()) + mapOriginY;
		g.setColor(this.getColor());
		
		if (this.blink) 
			g.fillRoundRect(currentX, currentY, getSize(), getSize(), 20, 10);
		
		g.drawRoundRect(currentX, currentY, getSize(), getSize(), 20, 10);
	}
	
	public boolean collidesWith(ICollider otherObj) {
		boolean result = false; 
		int thisCenterX = (int) (this.getLocation().getX() + (this.getSize()/2));  
		int thisCenterY = (int) (this.getLocation().getY() + (this.getSize()/2)); 
		int otherCenterX = (int) (((GameObject)otherObj).getLocation().getX() + ((GameObject)otherObj).getSize()/2); 
		int otherCenterY = (int) (((GameObject)otherObj).getLocation().getY() + (((GameObject)otherObj).getSize()/2));  
		int dx = thisCenterX - otherCenterX; 
		int dy = thisCenterY - otherCenterY; 
		int distBetweenCentersSqr = (dx*dx + dy*dy); 
		int thisRadius = this.getSize()/2; 
		int otherRadius = ((GameObject)otherObj).getSize()/2; 
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius); 
		if (distBetweenCentersSqr <= radiiSqr)  
			result = true;  
		return result; 
	}
	
	public void handleCollision(ICollider otherObj) {	
		
	
	}
	
	
	public String toString() {	
		int color = getColor();
		
		String stationString = "Asteroid: " + "loc = " + Math.round(getX()) + " ," + Math.round(getY()) 
								+ "Color = [" + ColorUtil.red(color) + ", " + ColorUtil.green(color) + ", "
								+ ColorUtil.blue(color) + "] "; 
								
		return stationString;
	}

}
