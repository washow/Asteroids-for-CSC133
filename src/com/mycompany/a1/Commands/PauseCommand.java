package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.UITimer;
import com.mycompany.a1.Game;
import com.mycompany.a1.GameWorld;

public class PauseCommand extends Command {
	UITimer currentTime;
	GameWorld currentGw;
	Game currentGame;
	
	public PauseCommand(UITimer time, GameWorld gw, Game g) {
		super("Pause");
		currentTime = time;
		currentGw = gw;
		currentGame = g;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (currentGw.isPaused()) {
			currentGame.startTime(currentTime);
			currentGw.setPaused();
			currentGame.notPaused();
		}
		else {
			currentGame.stopTime(currentTime);
			currentGw.setPaused();
			currentGame.isPaused();
		}
	}
}
