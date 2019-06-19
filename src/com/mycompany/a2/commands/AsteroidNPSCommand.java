package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AsteroidNPSCommand extends Command{
	private GameWorld gw;
	
	public AsteroidNPSCommand(GameWorld gw)
	{
		super("Asteroid hits NPS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.asteroidHitNPS();
		System.out.println("Asteroid hits NPS is invoked");
	}
}
