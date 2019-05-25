package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class TurnLeftCommand extends Command{
	private GameWorld gwCmd;
	
	public TurnLeftCommand(GameWorld gw) {
		super("Turn Left");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.turnLeft();
		System.out.println("Turn left executed");
	}
}
