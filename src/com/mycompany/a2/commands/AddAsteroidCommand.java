package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddAsteroidCommand extends Command {
	private GameWorld gw;
	
	public AddAsteroidCommand(GameWorld gw)
	{
		super("Add Asteroid");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.addNewAsteroid();
		System.out.println("Add Asteroid command is invoked");
	}
	
}
