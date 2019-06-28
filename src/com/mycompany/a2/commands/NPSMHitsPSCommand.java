package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class NPSMHitsPSCommand extends Command {
	private GameWorld gw;
	
	public NPSMHitsPSCommand(GameWorld gw)
	{
		super("NPS Missile Hits PS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.explodedPS();
		System.out.println("Missile Hit PS command is invoked");
	}
}
