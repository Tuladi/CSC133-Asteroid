package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class LoadPSCommand extends Command {
	private GameWorld gw;
	
	public LoadPSCommand(GameWorld gw)
	{
		super("Load PS with Missiles");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.reload();
		System.out.println("Load PS Missiles command is invoked");
	}
}
