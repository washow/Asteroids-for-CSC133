package com.mycompany.a1.Objects;

public class ObjectLocation {
	private float x;
	private float y;
	
	public ObjectLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setX(float newX) {
		x = newX;
	}
	
	public void setY(float newY) {
		y = newY;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setLocation(float newX, float newY) {
		setX(newX);
		setY(newY);
	}
	
}
