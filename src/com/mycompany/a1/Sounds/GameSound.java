package com.mycompany.a1.Sounds;

public class GameSound {
	private Sounds reload, noMissile, jump, gameOver, shipCrashed, shoot, asteroid;
	private BGM bgm;
	
	public GameSound() {
		reload = new Sounds("reload.wav");
		gameOver = new Sounds("gameover.wav");
		bgm = new BGM("bgm.wav");
		jump = new Sounds("jump.wav");
		shipCrashed = new Sounds("crash.wav");
		shoot = new Sounds("shoot.wav");
		asteroid = new Sounds("boop.wav");
	}
	
	public void reloadSound() {
		reload.play();
	}
	
	public void astroidBoom() {
		asteroid.play();
	}
	
	public void gameOverSound() {
		gameOver.play();
	}
	
	public void BGMSound() {
		bgm.play();
	}
	
	public void pauseBGM() {
		bgm.pause();
	}
	
	
	public void jumpSound() {
		jump.play();
	}
	
	public void shipCrashedSound() {
		shipCrashed.play();
	}

	public void shootSound() {
		shoot.play();
	}
}
