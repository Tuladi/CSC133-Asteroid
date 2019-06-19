/*
 * This file will control all game actions as input by some
 * external source.
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.commands.AddAsteroidCommand;
import com.mycompany.a2.commands.AddNPSCommand;
import com.mycompany.a2.commands.AddPSCommand;
import com.mycompany.a2.commands.AddSpaceStation;
import com.mycompany.a2.commands.AsteroidHitsAsteroidCommand;
import com.mycompany.a2.commands.AsteroidNPSCommand;
import com.mycompany.a2.commands.DecreasePSSpeed;
import com.mycompany.a2.commands.IncreasePSSpeed;
import com.mycompany.a2.commands.JumpCommand;
import com.mycompany.a2.commands.LoadPSCommand;
import com.mycompany.a2.commands.NPSFireCommand;
import com.mycompany.a2.commands.NPSMHitsPSCommand;
import com.mycompany.a2.commands.PSFireCommand;
import com.mycompany.a2.commands.PSHitsAsteroidCommand;
import com.mycompany.a2.commands.PSHitsNPSCommand;
import com.mycompany.a2.commands.PSMHitsAsteroidCommand;
import com.mycompany.a2.commands.PSMHitsNPSCommand;
import com.mycompany.a2.commands.SteerPSLeft;
import com.mycompany.a2.commands.SteerPSRight;
import com.mycompany.a2.commands.TickCommand;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent; 

public class Game extends Form {
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private GameObjectCollection GameCollection;
	Toolbar toolBar = new Toolbar();
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		pv = new PointsView();
		GameCollection = new GameObjectCollection();
		
		gw.init();
		gw.addObserver(mv);
		gw.addObserver(pv);
		
		toolbar();
		controlPanel();
		mapArea();
		keyBindings();
	}
	
	private void keyBindings()
	{
		//PS Speed (+)
		IncreasePSSpeed myIncreasePSSpeedCmd = new IncreasePSSpeed(gw);
		addKeyListener(-91, myIncreasePSSpeedCmd);
		
		//PS Speed (-)
		DecreasePSSpeed myDecreasePSSpeedCmd = new DecreasePSSpeed(gw);
		addKeyListener(-92, myDecreasePSSpeedCmd);
		
		//PS Left
		SteerPSLeft mySteerPSLeftCmd = new SteerPSLeft(gw);
		addKeyListener(-93, mySteerPSLeftCmd);
		
		//PS Right
		SteerPSRight mySteerPSRightCmd = new SteerPSRight(gw);
		addKeyListener(-94, mySteerPSRightCmd);
		
		//MSL Left
		
		//MSL Right
		
		//NPS Fire
		NPSFireCommand myNPSFireCmd = new NPSFireCommand(gw);
		addKeyListener('L', myNPSFireCmd);
				
		//Load PS
		LoadPSCommand myLoadPSCmd = new LoadPSCommand(gw);
		addKeyListener('n', myLoadPSCmd);
		
		//PS Missile hits Asteroid
		PSMHitsAsteroidCommand myPSMHitsAsteroidCmd = new PSMHitsAsteroidCommand(gw);
		addKeyListener('k', myPSMHitsAsteroidCmd);
		
		//PS Missile hits NPS
		PSMHitsNPSCommand myPSMHitsNPSCmd = new PSMHitsNPSCommand(gw);
		addKeyListener('e', myPSMHitsNPSCmd);
		
		//NPS hits PS
		NPSMHitsPSCommand myNPSMHitsPSCmd = new NPSMHitsPSCommand(gw);
		addKeyListener('E', myNPSMHitsPSCmd);
		
		//PS hits Asteroid
		PSHitsAsteroidCommand myPSHitsAsteroidCmd = new PSHitsAsteroidCommand(gw);
		addKeyListener('c', myPSHitsAsteroidCmd);
		
		//PS hits NPS
		PSHitsNPSCommand myPSHitsNPSCmd = new PSHitsNPSCommand(gw);
		addKeyListener('h', myPSHitsNPSCmd);
		
		//Asteroid hits Asteroid
		AsteroidHitsAsteroidCommand myAsteroidHitsAsteroidCmd = new AsteroidHitsAsteroidCommand(gw);
		addKeyListener('x', myAsteroidHitsAsteroidCmd);
		
		//Asteroid hits NPS
		AsteroidNPSCommand myAsteroidNPS = new AsteroidNPSCommand(gw);
		addKeyListener('I', myAsteroidNPS);
		
		//Tick 
		TickCommand myTickCommand = new TickCommand(gw);
		addKeyListener('t', myTickCommand);
		
		//Quit
		addKeyListener('Q', new ActionListener() {public void actionPerformed(ActionEvent e) {quit();}});
	}
	private void quit() 
	{
		Container quitContainer = new Container(new GridLayout(2,1));
		Label myLabel=new Label("Are you sure you want to quit? (Y/N)");
		quitContainer.addComponent(myLabel);
		TextField myTextField = new TextField();
		quitContainer.addComponent(myTextField);
		this.add(BorderLayout.SOUTH, quitContainer);
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
						myTextField.remove();
						myLabel.remove();
						return;
					default:
						System.out.println("Please input \'Y\' or \'N\'");
						break;
				}
			}
		});
	}
	
	private void mapArea() {
		Container mapArea = new Container(new GridLayout(5, 5));
		
		mapArea.getAllStyles().setBgTransparency(255);
		mapArea.getAllStyles().setPadding(TOP, 5);
		mapArea.getAllStyles().setPadding(BOTTOM, 5);
		mapArea.getAllStyles().setPadding(LEFT, 5);
		mapArea.getAllStyles().setPadding(RIGHT, 5);
		mapArea.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		this.add(BorderLayout.CENTER, mapArea);
	}
	
	private void toolbar() {
		setToolbar(toolBar);
		//pv.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		toolBar.setTitleComponent(pv);
		
		//Quit Button
		Button quitGame = new Button("Quit Game");
		quitGame.getAllStyles().setBgTransparency(255);
		quitGame.getUnselectedStyle().setBgColor(ColorUtil.CYAN);
		quitGame.getAllStyles().setFgColor(ColorUtil.WHITE);
		quitGame.getAllStyles().setPadding(TOP,5);
		quitGame.getAllStyles().setPadding(BOTTOM,5);
		
		quitGame.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {quit();}});
		toolBar.addComponentToLeftSideMenu(quitGame);
	}
	
	private void controlPanel()
	{
		this.setLayout(new BorderLayout());
		Container controlPanel = new Container(new GridLayout(1, 1));
		
		//Asteroid Button
		Button addAsteroid = new Button("Add Asteroid");
		addAsteroid.getAllStyles().setBgTransparency(255);
		addAsteroid.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		addAsteroid.getAllStyles().setFgColor(ColorUtil.WHITE);
		addAsteroid.getAllStyles().setPadding(TOP,5);
		addAsteroid.getAllStyles().setPadding(BOTTOM,5);
		addAsteroid.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.LTGRAY));
		
		AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
		addAsteroid.setCommand(myAddAsteroid);
		controlPanel.add(addAsteroid);
		
		//Add NPS
		Button addNPS = new Button("Add NPS");
		addNPS.getAllStyles().setBgTransparency(255);
		addNPS.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		addNPS.getAllStyles().setFgColor(ColorUtil.WHITE);
		addNPS.getAllStyles().setPadding(TOP,5);
		addNPS.getAllStyles().setPadding(BOTTOM,5);
		addNPS.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.LTGRAY));
		
		AddNPSCommand myAddNPS = new AddNPSCommand(gw);
		addNPS.setCommand(myAddNPS);
		controlPanel.add(addNPS);
		
		//Add Space Station
		Button addSpaceStation = new Button("Add Space Station");
		addSpaceStation.getAllStyles().setBgTransparency(255);
		addSpaceStation.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		addSpaceStation.getAllStyles().setFgColor(ColorUtil.WHITE);
		addSpaceStation.getAllStyles().setPadding(TOP,5);
		addSpaceStation.getAllStyles().setPadding(BOTTOM,5);
		addSpaceStation.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.LTGRAY));
		
		AddSpaceStation myAddSS = new AddSpaceStation(gw);
		addSpaceStation.setCommand(myAddSS);
		controlPanel.add(addSpaceStation);
		
		//Add PS
		Button addPS = new Button("Add PS");
		addPS.getAllStyles().setBgTransparency(255);
		addPS.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		addPS.getAllStyles().setFgColor(ColorUtil.WHITE);
		addPS.getAllStyles().setPadding(TOP,5);
		addPS.getAllStyles().setPadding(BOTTOM,5);
		addPS.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.LTGRAY));
		
		AddPSCommand myAddPS = new AddPSCommand(gw);
		addPS.setCommand(myAddPS);
		controlPanel.add(addPS);
		
		//PS Fire
		Button firePSMissile = new Button("PS Fire");
		firePSMissile.getAllStyles().setBgTransparency(255);
		firePSMissile.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		firePSMissile.getAllStyles().setFgColor(ColorUtil.WHITE);
		firePSMissile.getAllStyles().setPadding(TOP,5);
		firePSMissile.getAllStyles().setPadding(BOTTOM,5);
		firePSMissile.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.LTGRAY));
		
		PSFireCommand myPSFireCmd = new PSFireCommand(gw);
		firePSMissile.setCommand(myPSFireCmd);
		controlPanel.add(firePSMissile);
		addKeyListener(-90, myPSFireCmd);
		
		//Jump
		Button jumpButton = new Button("PS Fire");
		jumpButton.getAllStyles().setBgTransparency(255);
		jumpButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		jumpButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		jumpButton.getAllStyles().setPadding(TOP,5);
		jumpButton.getAllStyles().setPadding(BOTTOM,5);
		jumpButton.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.LTGRAY));
		
		JumpCommand myJumpCommand = new JumpCommand(gw);
		jumpButton.setCommand(myJumpCommand);
		controlPanel.add(jumpButton);
		addKeyListener('j', myJumpCommand);
		
		this.add(BorderLayout.WEST, controlPanel);
		
		show();
	}
}
