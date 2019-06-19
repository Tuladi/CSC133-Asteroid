/*
 * This file will control all game actions as input by some
 * external source.
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.commands.AddAsteroidCommand;
import com.mycompany.a2.commands.AddNPSCommand;
import com.mycompany.a2.commands.AddPSCommand;
import com.mycompany.a2.commands.AddSpaceStation;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent; 

public class Game extends Form {
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private GameObjectCollection GameCollection;
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		pv = new PointsView();
		GameCollection = new GameObjectCollection();
		
		gw.init();
		play(); 
		gw.addObserver(mv);
		gw.addObserver(pv);
		
		setLayout(new BorderLayout());
		pv.getAllStyles().setPadding(Component.TOP, 150);
		pv.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		add(BorderLayout.NORTH,pv);
		
		controlPanel();
	}
	
	private void keyBindings()
	{
		
	}
	private void quit(Label label, TextField textField) 
	{
		Label myLabel=new Label("Are you sure you want to quit? (Y/N)"); 
		TextField myTextField = new TextField();
		this.replace(label, myLabel, null);
		this.replace(textField, myTextField, null);
		this.show();
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				if(sCommand.length() == 0) {
					return;
				}
				myTextField.clear();
				switch(sCommand.charAt(0)) {
					case 'Y':
						System.exit(0);
						break;
					case 'N':
						revert(myLabel, label, myTextField, textField);
						return;
					default:
						System.out.println("Please input \'Y\' or \'N\'");
						break;
				}
			}
		});
	}
	
	private void revert(Label label, Label newLabel, TextField textField, TextField newTextField) {
		this.replace(label, newLabel, null);
		this.replace(textField, newTextField, null);
		this.show();
	}
	
	private void controlPanel(
			)
	{
		Toolbar controlPanel = new Toolbar();
		setToolbar(controlPanel);
		
		//Asteroid Button
		Button addAsteroid = new Button("Add Asteroid");
		addAsteroid.getAllStyles().setBgTransparency(255);
		addAsteroid.getUnselectedStyle().setBgColor(ColorUtil.CYAN);
		addAsteroid.getAllStyles().setFgColor(ColorUtil.WHITE);
		addAsteroid.getAllStyles().setPadding(TOP,15);
		addAsteroid.getAllStyles().setPadding(BOTTOM,5);
		
		AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
		addAsteroid.setCommand(myAddAsteroid);
		
		controlPanel.addCommandToLeftSideMenu(myAddAsteroid);
		
		//Add NPS
		Button addNPS = new Button("Add NPS");
		addNPS.getAllStyles().setBgTransparency(255);
		addNPS.getUnselectedStyle().setBgColor(ColorUtil.CYAN);
		addNPS.getAllStyles().setFgColor(ColorUtil.WHITE);
		addNPS.getAllStyles().setPadding(TOP,5);
		addNPS.getAllStyles().setPadding(BOTTOM,5);
		
		AddNPSCommand myAddNPS = new AddNPSCommand(gw);
		addNPS.setCommand(myAddNPS);
		
		controlPanel.addCommandToLeftSideMenu(myAddNPS);
		
		//Add Space Station
		Button addSpaceStation = new Button("Add Space Station");
		addSpaceStation.getAllStyles().setBgTransparency(255);
		addSpaceStation.getUnselectedStyle().setBgColor(ColorUtil.CYAN);
		addSpaceStation.getAllStyles().setFgColor(ColorUtil.WHITE);
		addSpaceStation.getAllStyles().setPadding(TOP,5);
		addSpaceStation.getAllStyles().setPadding(BOTTOM,5);
		
		AddSpaceStation myAddSS = new AddSpaceStation(gw);
		addSpaceStation.setCommand(myAddSS);
		
		controlPanel.addCommandToLeftSideMenu(myAddSS);
		
		//Add PS
		Button addPS = new Button("Add PS");
		addPS.getAllStyles().setBgTransparency(255);
		addPS.getUnselectedStyle().setBgColor(ColorUtil.CYAN);
		addPS.getAllStyles().setFgColor(ColorUtil.WHITE);
		addPS.getAllStyles().setPadding(TOP,5);
		addPS.getAllStyles().setPadding(BOTTOM,5);
		
		AddPSCommand myAddPS = new AddPSCommand(gw);
		addPS.setCommand(myAddPS);
		
		controlPanel.addCommandToLeftSideMenu(myAddPS);
		show();
	}
	
	private void play() 
	{
		Label myLabel=new Label("Enter a Command:"); this.addComponent(myLabel);
			final TextField myTextField=new TextField();
			this.addComponent(myTextField);
			this.show();
			myTextField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				if(sCommand.length() == 0) {
					return;
				}
				myTextField.clear();
				switch (sCommand.charAt(0)) {
					case 'a':
						gw.addNewAsteroid();
						System.out.println("Asteroid added");
						break;
					case 'y':
						gw.addNonPlayerShip();
						System.out.println("NPS added");
						break;
					case 's':
						try {
							gw.addPlayerShip();
							System.out.println("PS added");
						}
						catch(Exception e) {
							System.out.println(e);
						}
						break;
					case 'b':
						gw.addSpaceStation();
						System.out.println("Space Station added");
						break;
					case 'f':
						gw.firePSMissile();
						break;
					case 'L':
						gw.fireNPSMissile();
						break;
					case 'm':
						gw.printMap();
						break;
					case 'p':
						gw.displayScores();
						break;
					case 'i':
						gw.increasePSSpeed();
						break;
					case 'd':
						gw.decreasePSSpeed();
						break;
					case 'l':
						gw.turnPSL();
						break;
					case 'r':
						gw.turnPSR();
						break;
					case '>':
						gw.turnPSML();
						break;
					case 'j':
						gw.jump();
						break;
					case 'n':
						gw.reload();
						break;
					case 'k':
						gw.missileHitAsteroid();
						break;
					case 'e':
						gw.missileHitNPS();
						break;
					case 'E':
						gw.explodedPS();
						break;	
					case 'c':
						gw.asteroidHitPS();
						break;	
					case 'h':
						gw.npsHitPS();
						break;	
					case 'x':
						gw.asteroidHitAsteroid();
						break;	
					case 'I':
						gw.asteroidHitAsteroid();
						break;	
					case 't':
						gw.tick();
						break;	
					case 'q':
						quit(myLabel, myTextField);
						break;
					default:
						System.out.println("Error, invalid input");
						break;
				} //switch
			} //actionPerformed
			} //new ActionListener()
			); //addActionListener
			
	}
}
