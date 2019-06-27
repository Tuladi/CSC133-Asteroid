package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

public class MapView extends Container implements Observer{
	
	private double objX, objY;

	public MapView(){
		
		Container mvContainer = new Container();
		mvContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.add(mvContainer);
		
	}
	
	@Override
	public void update(Observable realObject, Object data) {
		// TODO Auto-generated method stub
		GameWorld gw = (GameWorld) realObject;
		IIterator iter = gw.getIterator();
		while(iter.hasNext()) {
			GameObject obj = (GameObject) iter.getNext();
			this.objX = obj.getLocationX();
			this.objY = obj.getLocationY();
		}
		gw.printMap();
	}

}
