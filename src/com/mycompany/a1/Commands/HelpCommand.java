package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class HelpCommand extends Command {
	
	private GameWorld gwCmd;
	
	public HelpCommand(GameWorld gw) {
		super("Help");
		gwCmd = gw;
	}

	public void actionPerformed(ActionEvent e) {
		String msg = "Help command stuff";
		Dialog.show("Info: ", msg, "OK", null);
	}
}
