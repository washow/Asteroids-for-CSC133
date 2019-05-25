package com.mycompany.a1.Views;

import java.util.Observer;
import java.util.Observable;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Border;
import com.mycompany.a1.GameWorld;
import com.mycompany.a1.Interface.IGameWorld;

public class PointsView extends Container implements Observer {
	private Label lives;
	private Label gameTime;
	private Label sound;
	private Label asteroidsHit;
	private Label missileCount;
	private GameWorld gw;
	
	public PointsView() {
		this.getAllStyles().setBorder(Border.createLineBorder(4, null));
		this.getAllStyles().setPadding(Component.LEFT, 100);
		this.getAllStyles().setBgColor(ColorUtil.GRAY);
		
		gameTime = new Label("0");
		lives = new Label("3");
		sound = new Label("Off");
		asteroidsHit = new Label("0");
		missileCount = new Label("0");
		//gameTime.setText(Integer.toString(gw.getTime()));
		//lives.setText(Integer.toString(gw.getLives()));
		//sound.setText(gw.getSound());
		
		
		this.add(new Label("Time: "));
		this.add(gameTime);
		
		this.add(new Label("Lives: "));
		this.add(lives);
		
		this.add(new Label("Score: "));
		this.add(asteroidsHit);
		
		this.add(new Label("Missiles: "));
		this.add(missileCount);
		
		this.add(new Label("Sound: "));
		this.add(sound);
		
	}
	
	public void update(Observable observable, Object obj) {
		Integer score = ((IGameWorld) obj).getAsteroidCount();
		Integer curTime = ((IGameWorld) obj).getTime();
		Integer curLives = ((IGameWorld) obj).getLives();
		Integer curMissiles = ((IGameWorld) obj).getMissileCount();
		
		
		asteroidsHit.setText(Integer.toString(score));
		gameTime.setText(Integer.toString(curTime));
		lives.setText(Integer.toString(curLives));
		missileCount.setText(Integer.toString(curMissiles));
		/*if (((IGameWorld) obj).getSound() == true) 
			sound.setText("ON");
		else
			sound.setText("OFF");
		*/
		
	}

}
