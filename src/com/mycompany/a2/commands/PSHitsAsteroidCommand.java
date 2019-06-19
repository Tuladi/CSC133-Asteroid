package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PSHitsAsteroidCommand extends Command {
	private GameWorld gw;
	
	public PSHitsAsteroidCommand(GameWorld gw)
	{
		super("PS Hits Asteroid");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.asteroidHitPS();
		System.out.println("PS Hit Asteroid command is invoked");
	}
}