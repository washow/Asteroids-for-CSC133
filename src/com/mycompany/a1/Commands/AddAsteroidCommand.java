package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class AddAsteroidCommand extends Command{
	private GameWorld gwCmd;
	
	public AddAsteroidCommand(GameWorld gw) {
		super("Add Asteroid");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.addAsteroid();
		System.out.println("Asteroid Added");
	}
}
