package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class FireCommand extends Command {
	private GameWorld gwCmd;
	
	public FireCommand(GameWorld gw) {
		super("Fire");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.shootMissile();
		System.out.println("Missile shot");
	}
}
