package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PSMHitsNPSCommand extends Command {
	private GameWorld gw;
	
	public PSMHitsNPSCommand(GameWorld gw)
	{
		super("PS Missile Hits NPS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.missileHitNPS();
		System.out.println("Missile Hit NPS command is invoked");
	}
}
