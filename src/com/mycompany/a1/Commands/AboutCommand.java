package com.mycompany.a1.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class AboutCommand extends Command {
	private GameWorld gwCmd;
	
	public AboutCommand(GameWorld gw) {
		super("About");
		gwCmd = gw;
	}

	public void actionPerformed(ActionEvent e) {
		String msg = "ASTEROID REMAKE by Brian Lee";
		Dialog.show("About", msg, "OK", null);
	}
}
