package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class SteerPSMLLeft extends Command{
	private GameWorld gw;
	
	public SteerPSMLLeft(GameWorld gw)
	{
		super("Turn PS Misile Launcher Left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.turnPSMLLeft();
		System.out.println("Turn PS ML Left command is invoked");
	}
}