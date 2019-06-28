package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BoxLayout;

public class MapView extends Container implements Observer{
	
	private Vector<GeometricShapes> worldShapes;
	private Container mvContainer;

	public MapView(Vector<GeometricShapes> worldShapes){
		
		this.worldShapes = worldShapes;
		this.mvContainer = new Container();
		mvContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.add(mvContainer);
		
	}
	
	public void update(Observable realObject, Object data) {
		GameWorld gw = (GameWorld) realObject;
		this.drawObjs();
		gw.printMap();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		for (int i = 0; i < worldShapes.size(); i++) {
			this.worldShapes.elementAt(i).update();
			this.worldShapes.elementAt(i).draw(g, pCmpRelPrnt);
		}
	}
	
	public void drawObjs() {
		repaint();
	}

}
