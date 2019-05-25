package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class AddShipCommand extends Command{
	private GameWorld gwCmd;
	
	public AddShipCommand(GameWorld gw) {
		super("Add ship");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.addShip();
		System.out.println("Ship added");
	}
}
