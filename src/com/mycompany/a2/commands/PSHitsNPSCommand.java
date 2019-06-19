package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PSHitsNPSCommand extends Command {
	private GameWorld gw;
	
	public PSHitsNPSCommand(GameWorld gw)
	{
		super("PS Hits Asteroid");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.npsHitPS();
		System.out.println("NPS crashes into PS command is invoked");
	}
}