package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PSMHitsAsteroidCommand extends Command {
	private GameWorld gw;
	
	public PSMHitsAsteroidCommand(GameWorld gw)
	{
		super("PS Missile Hits Asteroid");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.missileHitAsteroid();
		System.out.println("PS Missile Hit Asteroid command is invoked");
	}
}