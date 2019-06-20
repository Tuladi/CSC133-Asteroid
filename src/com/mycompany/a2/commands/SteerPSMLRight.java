package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class SteerPSMLRight extends Command{
	private GameWorld gw;
	
	public SteerPSMLRight(GameWorld gw)
	{
		super("Turn PS Misile Launcher Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.turnPSMLRight();
		System.out.println("Turn PS ML Right command is invoked");
	}
}