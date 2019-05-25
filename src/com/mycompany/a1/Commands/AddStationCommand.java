package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class AddStationCommand extends Command{
	private GameWorld gwCmd;
	
	public AddStationCommand(GameWorld gw) {
		super("Add Space Station");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.addSpaceStation();
		System.out.println("Space Station Added");
	}
}
