package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class IncreasePSSpeed extends Command {
private GameWorld gw;
	
	public IncreasePSSpeed(GameWorld gw)
	{
		super("Increased PS Speed");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.increasePSSpeed();
		System.out.println("Increase PS Speed is invoked");
	}
}
