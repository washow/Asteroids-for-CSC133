package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class DecreaseCommand extends Command {
	private GameWorld gwCmd;
	
	public DecreaseCommand(GameWorld gw) {
		super("Decrease Speed");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.decreaseSpeed();
		System.out.println("Decrease Speed");
	}
}
