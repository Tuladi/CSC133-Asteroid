package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class SteerPSLeft extends Command{
	private GameWorld gw;
	
	public SteerPSLeft(GameWorld gw)
	{
		super("Turn PS Left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.turnPSL();
		System.out.println("Turn PS Left command is invoked");
	}
}