package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddSpaceStation extends Command{
	private GameWorld gw;
	public AddSpaceStation(GameWorld gw)
	{
		super("Add Space Station");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getKeyEvent() != -1) {
			gw.addSpaceStation();
			System.out.println("Add Space Station command is invoked");
		}
	}
}
