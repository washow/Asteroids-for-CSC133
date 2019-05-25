package com.mycompany.a1.Commands;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a1.GameWorld;

public class SoundCommand extends Command {
	private GameWorld gwCmd;
	
	public SoundCommand(GameWorld gw) {
		super("Sound");
		gwCmd = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (((CheckBox)e.getComponent()).isSelected()) {
			gwCmd.setCheckSound(true);
			System.out.println("Sound ON");
		}
		else {
			gwCmd.setCheckSound(false);
			System.out.println("Sound OFF");
		}
	}
}
