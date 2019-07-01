package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import Interfaces.IGameWorld;

public class PointsView extends Container implements Observer {
	// Instantiate text labels
	private Label pNScoreLabel;
	private Label pNMissileLabel;
	private Label pNLivesLabel;
	private Label pNSoundLabel;
	private Label pNTimeLabel;
	
	public PointsView() 
	{
		//Container pvContainer = new Container();
		this.setLayout(new FlowLayout(CENTER));
		Font newFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
		
		//Labels
		Label pTextLabel = new Label("Score: 	");
		Label pMCLabel = new Label("Missile Count:	");
		Label pLivesLabel = new Label("Lives:	");
		Label pSoundLabel = new Label("Sound:	");
		Label pTimeLabel = new Label("Time:		");
		
		pNScoreLabel = new Label("000   ");
		pNMissileLabel = new Label("10   ");
		pNLivesLabel = new Label("3   ");
		pNSoundLabel = new Label("ON   ");
		pNTimeLabel = new Label("000   ");
		
		//Colors
		pTextLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pMCLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pLivesLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pSoundLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pTimeLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		
		//Font for labels
		pTextLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		pMCLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		pLivesLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		pSoundLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		pTimeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		
		pNScoreLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
		pNMissileLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
		pNLivesLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
		pNSoundLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
		pNTimeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
		
		//Adding Containers
		this.add(pTextLabel);
		this.add(pNScoreLabel);
		this.add(pMCLabel);
		this.add(pNMissileLabel);
		this.add(pLivesLabel);
		this.add(pNLivesLabel);
		this.add(pSoundLabel);
		this.add(pNSoundLabel);
		this.add(pTimeLabel);
		this.add(pNTimeLabel);
		
		//this.add(pvContainer);
	}
	
	@Override
	public void update(Observable realObject, Object data)
	{
		IGameWorld gw = (IGameWorld) data;
		this.pNScoreLabel.setText("" + ((IGameWorld) data).getPlayerScore());
		this.pNMissileLabel.setText("" + ((IGameWorld) data).getMissileCount());
		this.pNLivesLabel.setText("" + ((IGameWorld) data).getNumLives());
		this.pNSoundLabel.setText("" + ((IGameWorld) data).getSoundState());
		this.pNTimeLabel.setText("" + ((IGameWorld) data).getElapsedTime()/50);
	}
}
