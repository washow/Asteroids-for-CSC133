package com.mycompany.a1.Objects;

import com.codename1.ui.Graphics;
import com.mycompany.a1.Interface.ICollider;
import com.mycompany.a1.Interface.IDrawable;
import com.mycompany.a1.Objects.GameObject;

public class FixedObject extends GameObject {
	private static int idNum;
	private int size;
	
	public FixedObject() {
		super();
		this.setHeading(0);
		this.setSpeed(0);
		this.setSize(30);
	}
	
	public void setID(int id) {
		this.idNum = id;
	}
	
	public int getID() {
		return this.idNum;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}


}
               