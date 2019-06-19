package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer {
	// Instantiate text labels
	private Label pNScoreLabel, pNMissileLabel, pNLivesLabel, pNSoundLabel, pNTimeLabel;
	
	public PointsView() 
	{
		Container pvContainer = new Container();
		pvContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		Font newFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
		
		//Labels
		Label pTextLabel = new Label("Score:");
		Label pMCLabel = new Label("Missile Count:");
		Label pLivesLabel = new Label("Lives:");
		Label pSoundLabel = new Label("Sound:");
		Label pTimeLabel = new Label("Time:");
		
		pNScoreLabel = new Label("_");
		pNMissileLabel = new Label("_");
		pNLivesLabel = new Label("_");
		pNSoundLabel = new Label("_");
		pNTimeLabel = new Label("_");
		
		//Colors
		pTextLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pMCLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pLivesLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pSoundLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pTimeLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		
		//Fonts
		pTextLabel.getAllStyles().setFont(newFont);
		pNScoreLabel.getAllStyles().setFont(newFont);
		pMCLabel.getAllStyles().setFont(newFont);
		pNMissileLabel.getAllStyles().setFont(newFont);
		pLivesLabel.getAllStyles().setFont(newFont);
		pNLivesLabel.getAllStyles().setFont(newFont);
		pSoundLabel.getAllStyles().setFont(newFont);
		pNSoundLabel.getAllStyles().setFont(newFont);
		pTimeLabel.getAllStyles().setFont(newFont);
		pNTimeLabel.getAllStyles().setFont(newFont);
		
		//Alignment
		
		//Adding Containers
		pvContainer.add(pTextLabel);
		pvContainer.add(pNScoreLabel);
		pvContainer.add(pMCLabel);
		pvContainer.add(pNMissileLabel);
		pvContainer.add(pLivesLabel);
		pvContainer.add(pNLivesLabel);
		pvContainer.add(pSoundLabel);
		pvContainer.add(pNSoundLabel);
		pvContainer.add(pTimeLabel);
		pvContainer.add(pNTimeLabel);
		
		this.add(pvContainer);
	}
	
	@Override
	public void update(Observable realObject, Object data)
	{
		GameWorld gw = (GameWorld) realObject;
		this.pNScoreLabel.setText("" + gw.getPlayerScore());
		this.pNMissileLabel.setText("" + gw.getMissileCount());
		this.pNLivesLabel.setText("" + gw.getNumLives());
		this.pNSoundLabel.setText("" + gw.getSoundState());
		this.pNTimeLabel.setText("" + gw.getElapsedTime());
		this.repaint();
	}
}
