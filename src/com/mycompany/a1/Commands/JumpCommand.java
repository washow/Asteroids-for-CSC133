package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class JumpCommand extends Command {
	private GameWorld gwCmd;
	
	public JumpCommand(GameWorld gw) {
		super("Jump");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.jump();
		System.out.println("Jumped");
		
	}
}
