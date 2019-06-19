package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddNPSCommand extends Command {
	private GameWorld gw;
	public AddNPSCommand(GameWorld gw)
	{
		super("Add NPS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getKeyEvent() != -1) {
			gw.addNonPlayerShip();
			System.out.println("Add NPS command is invoked");
		}
	}
}
