package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class ReloadCommand extends Command {
	private GameWorld gwCmd;
	
	public ReloadCommand(GameWorld gw) {
		super("Reload");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gwCmd.reloadMissiles();
	}

}
