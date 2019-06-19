package com.mycompany.a2.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddPSCommand extends Command {
	private GameWorld gw;
	public AddPSCommand(GameWorld gw)
	{
		super("Add Playership");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try {
			gw.addPlayerShip();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Add Playership command is invoked");
	}
}
