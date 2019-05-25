package com.mycompany.a1.Views;

import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.plaf.Border;
import com.mycompany.a1.GameWorld;
import com.mycompany.a1.Interface.IDrawable;
import com.mycompany.a1.Interface.IGameWorld;
import com.mycompany.a1.Interface.IIterator;
import com.mycompany.a1.Interface.IMovable;
import com.mycompany.a1.Objects.GameCollection;
import com.mycompany.a1.Objects.GameObject;
import com.mycompany.a1.Objects.ObjectLocation;


public class MapView extends Container implements Observer{
	
	private IGameWorld gwProxy;
	GameCollection theObj;
	Form formRef;
	
	public MapView(GameWorld gamePainter, Form f) {
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLACK);
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.MAGENTA));
		gwProxy = gamePainter;
		formRef = f;
	}
	
	public void update(Observable observable, Object arg) {
		if (observable instanceof IGameWorld) {
			gwProxy = (IGameWorld)observable;
			repaint();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		theObj = gwProxy.getGameObjects();
		if (theObj == null)
			return;
		IIterator objPainter = theObj.getIIterator();
		Object currentObj = new Object();
		
		while(objPainter.hasNext()) {
			ObjectLocation rCmpRelPrnt = new ObjectLocation(this.getX(), this.getY());
			currentObj = objPainter.getNext();
			if (currentObj instanceof IMovable) {
				int x = (int) ((GameObject)currentObj).getLocation().getX();
				int y = (int) ((GameObject)currentObj).getLocation().getY();
				int rightWall = this.getX() + this.getWidth();
				int leftWall = this.getX();
				int bottomWall = this.getY() + this.getHeight();
				int topWall = this.getY();
				
				if(y <= topWall || y >= bottomWall) {
					if (y <= 0) 
						((GameObject)currentObj).setLocation(new ObjectLocation(x, bottomWall));
					if (( y + this.getY()) >= bottomWall) 
						((GameObject)currentObj).setLocation(new ObjectLocation(x, 0));
				}
				
				if( x <= leftWall || x >= rightWall) {
					if (x <= 0) {
						((GameObject)currentObj).setLocation(new ObjectLocation(this.getWidth(), y));	//works
					}
					if (x >= rightWall) {
						((GameObject)currentObj).setLocation(new ObjectLocation(0, y));
					}
				}
			}
			
			if (currentObj instanceof IDrawable) 
				((IDrawable)currentObj).draw(g, rCmpRelPrnt);
			
		}
	}
	
	
}
