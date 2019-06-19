package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PSFireCommand extends Command{
	private GameWorld gw;
	
	public PSFireCommand(GameWorld gw)
	{
		super("PS fire a missile");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getKeyEvent() != -1) {
			gw.firePSMissile();
			System.out.println("PS fire command is invoked");
		}
	}
}
