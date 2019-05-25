package com.mycompany.a1.Interface;

public interface ICollider {
	public boolean collidesWith(ICollider otherObject);
	public void handleCollision(ICollider otherObject);
}
