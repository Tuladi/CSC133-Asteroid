package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class DecreasePSSpeed extends Command {
private GameWorld gw;
	
	public DecreasePSSpeed(GameWorld gw)
	{
		super("Decreased PS Speed");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.decreasePSSpeed();
		System.out.println("Decrease PS Speed is invoked");
	}
}