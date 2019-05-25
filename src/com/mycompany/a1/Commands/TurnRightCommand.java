package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class TurnRightCommand extends Command{
	private GameWorld gwCmd;
	
	public TurnRightCommand(GameWorld gw) {
		super("Turn Right");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.turnRight();
		System.out.println("Turn right executed");
	}
}
