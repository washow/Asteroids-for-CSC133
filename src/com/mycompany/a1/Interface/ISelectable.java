package com.mycompany.a1.Interface;

import com.codename1.ui.Graphics;
import com.mycompany.a1.Objects.ObjectLocation;

public interface ISelectable {
	public void setSelected(boolean s);
	public boolean isSelected();
	public boolean contains(ObjectLocation pPtrRelPrnt, ObjectLocation pCmpRelPrnt);
	public void draw(Graphics g, ObjectLocation pCmpRelPrnt);
}
