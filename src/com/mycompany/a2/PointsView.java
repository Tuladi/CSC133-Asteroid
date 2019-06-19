package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer {
	// Instantiate text labels
	private Label pScoreLabel, pMissileLabel, pLivesLabel, pSoundLabel, pTimeLabel;
	
	public PointsView() 
	{
		Container pvContainer = new Container();
		pvContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		//Labels
		Label pTextLabel = new Label("Score: ");
		Label pMCLabel = new Label("Missile Count: ");
		Label pLivesLabel = new Label("Lives: ");
		Label pSoundLabel = new Label("Sound: ");
		Label pTimeLabel = new Label("Time: ");
		
		//Colors
		pTextLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pMCLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pLivesLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pSoundLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pTimeLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		
		//Alignment
		
		//Adding Containers
		pvContainer.add(pTextLabel);
		pvContainer.add(pMCLabel);
		pvContainer.add(pLivesLabel);	
		pvContainer.add(pSoundLabel);
		pvContainer.add(pTimeLabel);
		
		
		this.add(pvContainer);
	}
	
	@Override
	public void update(Observable observable, Object data)
	{
		IGameWorld gw = (IGameWorld) data;
		this.pScoreLabel.setText("" + gw.getPlayerScore());
		this.pMissileLabel.setText("" + gw.getPlayerScore());
		this.pLivesLabel.setText("" + gw.getPlayerScore());
		this.pSoundLabel.setText("" + gw.getPlayerScore());
		this.pTimeLabel.setText("" + gw.getPlayerScore());
		this.repaint();
	}
}
