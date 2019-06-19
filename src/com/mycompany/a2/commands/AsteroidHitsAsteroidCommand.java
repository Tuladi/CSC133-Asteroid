package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AsteroidHitsAsteroidCommand extends Command {
	private GameWorld gw;
	
	public AsteroidHitsAsteroidCommand(GameWorld gw)
	{
		super("Asteroid Hits Asteroid");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getKeyEvent() != -1) {
			gw.asteroidHitAsteroid();
			System.out.println("Asteroid crashes into Asteroid command is invoked");
		}
	}
}