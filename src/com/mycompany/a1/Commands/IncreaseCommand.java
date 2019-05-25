package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class IncreaseCommand extends Command {
	private GameWorld gwCmd;
	
	public IncreaseCommand(GameWorld gw) {
		super("Increase Speed");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.increaseSpeed();
		System.out.println("Increase speed");
	}

}
