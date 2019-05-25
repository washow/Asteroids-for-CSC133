package com.mycompany.a1;

import com.codename1.ui.events.ActionListener;
import com.mycompany.a1.Interface.ICollider;
import com.mycompany.a1.Interface.IGameWorld;
import com.mycompany.a1.Interface.IIterator;
import com.mycompany.a1.Objects.Asteroid;
import com.mycompany.a1.Objects.GameCollection;
import com.mycompany.a1.Objects.GameObject;
import com.mycompany.a1.Objects.Missiles;
import com.mycompany.a1.Objects.ObjectLocation;
import com.mycompany.a1.Objects.Ships;
import com.mycompany.a1.Objects.SpaceStation;
import com.mycompany.a1.Sounds.BGM;
import com.mycompany.a1.Sounds.GameSound;
import com.mycompany.a1.Views.MapView;
import com.mycompany.a1.Views.PointsView;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String; 
import java.util.Vector;
import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable implements IGameWorld {

	private Ships ship;
	private SpaceStation station;
	private Missiles missile;
	private GameCollection objCollection;
	private GameSound gameSound;
	private int gwHeight;
	private int gwWidth;
	
	private int lives = 3;
	private int gameTime = 0;
	private int asteroidCount = 0;
	private int missileCount;
	
	private Label soundStatus = new Label("OFF");
	private boolean paused;
	//
	
	
	public GameWorld() {
		gwWidth = 1024;
		gwHeight = 768;
		objCollection = new GameCollection();
		gameSound = new GameSound();
		paused = false;
		
	}
	
	public void init() {
		gameTime = 0;
		lives = 3;
		missileCount = 12;
		System.out.println("Game Started!");
		gameSound.BGMSound();
		addShip();
		addSpaceStation();
		observerUpdate();
	}
	
	///////////////////////////////////////////
	// -------- GETTER SETTER ---------------//
	//////////////////////////////////////////
	public int getAsteroidCount() {
		return this.asteroidCount;
	}
	
	public int getMissileCount() {
		return this.missileCount;
	}
	
	public int getTime() {
		return this.gameTime;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public int getHeight() {
		return gwHeight;
	}
	
	public int getWidth() {
		return gwWidth;
	}
	
	public void setHeight(int h) {
		this.gwHeight = h;
	}
	
	public void setWidth(int w) {
		this.gwWidth = w;
	}
	
	
	
	public void setCheckSound(boolean b) {
		if (b)
			soundStatus.setText("ON");
		else
			soundStatus.setText("OFF");
		
		observerUpdate();
	}
	
	////////////////////////////////////////
	
	public int startLocX() {
		double x = gwWidth;
		return (int) (x/2);
	}
	public int startLocY() {
		double y = gwHeight;
		return (int) (y/2);
	}
	
	public void observerUpdate() {
		GameWorldProxy gwProxy = new GameWorldProxy(this);
		this.setChanged();
		this.notifyObservers(gwProxy);
	}
	
	public void addShip() {
		ship = new Ships();
		ship.setColor(ColorUtil.BLUE);
		ship.setLocation(mapCenter());
		ship.setHeading(0);
		ship.setSpeed(0);
		ship.setSize(30);
		ship.setMissileCount(12);
		missileCount = ship.getMissileCount();
		
		objCollection.add(ship);
		observerUpdate();
	}

	public void addSpaceStation() {
		station = new SpaceStation();
		station.setColor(ColorUtil.MAGENTA);
		station.setSize(50);
		objCollection.add(station);
		observerUpdate();
	}

	public void eliminate() {
		
	}
	
	public void addAsteroid() {
		Asteroid a = new Asteroid();
		a.setColor(ColorUtil.LTGRAY);
		a.setSize(randomSize());
		a.setSpeed(randomSpeed());
		a.setHeading(randomHeading());
		a.setRandLocation(0, 0, this.getWidth(), this.getHeight());
		objCollection.add(a);
		observerUpdate();
	}
	
	/////////////////////////////////////////////////////////////////////
	// -------------------------- RANDOMIZERS -------------------------//
	public int randomX(){
		Random rand = new Random();
		return rand.nextInt(900);
	}
	
	public int randomY(){
		Random rand = new Random();
		return rand.nextInt(700);
	}
	
	public int randomSize() {
		Random rand = new Random();
		return rand.nextInt(60) + 20;
	}
	
	public int randomHeading() {
		Random rand = new Random();
		return rand.nextInt(359);
	}
	
	public int randomSpeed() {
		Random rand = new Random();
		return rand.nextInt(10) + 2;
	}
	// --------------------------------------------------------------//
	///////////////////////////////////////////////////////////////////

	
	//calls toString
	public void map() {
		IIterator elements = objCollection.getIIterator();
		while(elements.hasNext()) {
			GameObject gmObjVector = (GameObject) elements.getNext();
			
			if(gmObjVector instanceof Ships) {
				Ships ship = (Ships) gmObjVector;
				System.out.println(ship.toString());
			}
			
			if(gmObjVector instanceof SpaceStation) {
				SpaceStation station = (SpaceStation) gmObjVector;
				System.out.println(station.toString());
			}
			
			if (gmObjVector instanceof Asteroid) {
				Asteroid asteroid = (Asteroid) gmObjVector;
				System.out.println(asteroid.toString());
			}
		}
		
		
	}
	
	public ObjectLocation mapCenter() {
		float x = this.getWidth();
		float y = this.getHeight();
		ObjectLocation center = new ObjectLocation(x/2, y/2);
		return center;
	}

	
	
	public void about() {
		System.out.println("About...");
		
	}

	public void turnLeft() {
		IIterator elements = objCollection.getIIterator();
		
		while(elements.hasNext()) {
			GameObject gmObjVector = (GameObject) elements.getNext();
			if (gmObjVector instanceof Ships) {
				Ships ship = (Ships) gmObjVector;
				ship.turnLeft();
				break;
			}
		}
		
		System.out.println("turned left");
		
	}

	public void decreaseSpeed() {
		IIterator elements = objCollection.getIIterator();
		
		while(elements.hasNext()) {
			GameObject gmObjVector = (GameObject) elements.getNext();
			if (gmObjVector instanceof Ships) {
				Ships ship = (Ships) gmObjVector;
				ship.decreaseSpeed();
				break;
			}
		}
		
	}

	public void increaseSpeed() {
	IIterator elements = objCollection.getIIterator();
		
		while(elements.hasNext()) {
			GameObject gmObjVector = (GameObject) elements.getNext();
			if (gmObjVector instanceof Ships) {
				Ships ship = (Ships) gmObjVector;
				ship.increaseSpeed();
				break;
			}
		}
	}



	public void exit() {
		System.out.println("Donezo!");
		System.exit(0);
		
	}

	public void turnRight() {
		IIterator elements = objCollection.getIIterator();
		
		while(elements.hasNext()) {
			GameObject gmObjVector = (GameObject) elements.getNext();
			if (gmObjVector instanceof Ships) {
				Ships ship = (Ships) gmObjVector;
				ship.turnRight();
				break;
			}
		}
		System.out.println("turned right");
		
	}

	
	public void tick() {	
		gameTime++;
		blinking();
		collisionCheck();
		observerUpdate();
	}
	
	public void blinking() {
		IIterator iter = objCollection.getIIterator();
		Object currentObj = new Object();
		while (iter.hasNext()) {
			currentObj = iter.getNext();
			if (currentObj instanceof SpaceStation)
				((SpaceStation) currentObj).blink(gameTime);
		}
	}
	
	public void shootMissile() {
		if (ship.shoot()) {
			gameSound.shootSound();
			missile = new Missiles();
			missile.setColor(ColorUtil.WHITE);
			missile.setHeading(ship.getHeading());
			missile.setSpeed(ship.getSpeed() + 5);
			missile.setLocation(ship.getLocation());
			missile.setSize(3);
			objCollection.add(missile);
			missileCount = ship.getMissileCount();
		}
		else
			System.out.println("Out of ammo");
		
		observerUpdate();
	}
	
	public void jump() {
		gameSound.jumpSound();
		ship.setLocation(mapCenter());
		ship.setHeading(0);
		ship.setSpeed(0);
		
		observerUpdate();
	}
	
	
	
	public void collisionCheck() {
		IIterator iter = objCollection.getIIterator();
		while(iter.hasNext()) {
			ICollider curObj = (ICollider)iter.getNext();
			IIterator iter2 = objCollection.getIIterator();
			while(iter2.hasNext()){
				ICollider otherObj = (ICollider)iter2.getNext();
				if(otherObj != curObj) {
					if(curObj.collidesWith(otherObj)) {
						curObj.handleCollision(otherObj);
					}
				}
			}
		}
		removeCollision();
		observerUpdate();
	}
	
	/*public void removeCollision() {
		GameCollection trashCollection = new GameCollection();
		IIterator removeIter = objCollection.getIIterator();
		Object target = new Object();
		while (removeIter.hasNext()) {
			target = removeIter.getNext();
			if (((GameObject) target).getFlag() == true); {
				System.out.println(target.getClass());
				System.out.println(((GameObject) target).getFlag());
				trashCollection.add(target);
			}
		}
		
		IIterator trashIter = trashCollection.getIIterator();
		while (trashIter.hasNext()) {
			Object trash = new Object();
			trash = trashIter.getNext();
			if (trash instanceof Ships) {
				objCollection.remove(trash);
				lives--;
				if (lives > 0) {
					System.out.println(((Ships) trash).getFlag());
					System.out.println("YOU DIED");
					addShip();
				}
				else {
					System.out.println("GAME OVER");
					exit();
				}
					 
			}
			
			if (trash instanceof Asteroid) {
				objCollection.remove(trash);
				asteroidCount++;
			}
			
			if (trash instanceof Missiles) 
				objCollection.remove(trash);
			
		}
		observerUpdate();
		
	}
	*/
	public void removeCollision() {
		GameCollection trash = new GameCollection();
		IIterator removeIter = objCollection.getIIterator();
		Object obj = new Object();
		while (removeIter.hasNext()) {
			obj = removeIter.getNext();
			if (((GameObject)obj).getFlag() == true)
				trash.add(obj);
		}
		trashRemoval(trash);
		observerUpdate();
	}
	
	public void trashRemoval(GameCollection trash) {
		IIterator trashIter = trash.getIIterator();
		while (trashIter.hasNext()) {
			Object obj = trashIter.getNext();
			if (obj instanceof Asteroid) {
				objCollection.remove(obj);
				gameSound.astroidBoom();
				asteroidCount++;
			}
			
			if (obj instanceof Ships) {
				objCollection.remove(obj);
				lives--;
				if (lives > 0) {
					gameSound.shipCrashedSound();
					System.out.println("Remaining life: " + lives);
					addShip();
				}
				else {
					if(Dialog.show("Game Over", "New Game?", "Yes", "No")) {
						gameSound.gameOverSound();
						objCollection = new GameCollection();
						init();
					}
					else
						exit();
				}	
			}
			
			if (obj instanceof Missiles) 
				objCollection.remove(obj);
			
		}
	}
	

	public GameCollection getGameObjects() {
		return objCollection;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused() {
		if(paused) { 
			paused = false;
			gameSound.BGMSound();
		}
		else {
			paused = true;
			gameSound.pauseBGM();
		}
			
		
	}

	public void reloadMissiles() {
		ship.setMissileCount(12);
	}





	
}
