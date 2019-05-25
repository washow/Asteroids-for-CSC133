package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class ExitCommand extends Command {
	private GameWorld gwCmd;
	
	public ExitCommand(GameWorld gw) {
		super("Exit");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		String msg = "Exit game?";
		Boolean exitOK = Dialog.show("Confirm exit", msg, "OK", "Cancel");
		if (exitOK) 
			gwCmd.exit();
	}

}
