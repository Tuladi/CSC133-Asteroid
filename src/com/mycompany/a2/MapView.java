package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BoxLayout;

import Interfaces.IDrawable;
import Interfaces.IGameWorld;
import Interfaces.IIterator;

public class MapView extends Container implements Observer{
	private GameObjectCollection gwc;
	private IGameWorld gameWorldProxy;
	private Container mvContainer;
	private static int width;
	private static int height;

	public MapView(GameWorld gw){
		mvContainer = new Container();
		mvContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.add(mvContainer);
		gameWorldProxy = gw;
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		gwc = gameWorldProxy.getGameCollection();
		IIterator iter = gwc.getIterator();
		Point pCmpRelPrnt = new Point(getX(), getY());
		while(iter.hasNext()) {
			GameObject go = (GameObject) iter.getNext();
			if(go instanceof MoveableGameObject) {		
				int x = (int) ((GameObject) go).getLocationX();
				int y = (int) ((GameObject) go).getLocationY();
				
				int leftSide   = getX();
				int topSide    = getY();
				int rightSide  = MapView.getMVWidth()+getX();
				int bottomSide = MapView.getMVHeight()+getY();
				
				if(x <= leftSide || x >= rightSide) {
					if(x <= 0)
						((GameObject)go).setLocation((double) getWidth(), (double) y);	
					if(x >= rightSide) 
						((GameObject) go).setLocation((double) 0, (double) y);
				}
				
				if(y <= topSide || y >= bottomSide) {
					if(y+getY() <= 0) 
						((GameObject) go).setLocation((double) x, (double) getHeight());
					if(y+getY() >= bottomSide) 
						((GameObject) go).setLocation((double) x, (double) 0);
				}
			}
			if(go instanceof IDrawable)
				((IDrawable) go).draw(g, pCmpRelPrnt);
		}
	}
	
	@Override
	public void update(Observable realObject, Object data) {
		width = this.getWidth();
		height = this.getHeight();
		if(realObject instanceof IGameWorld)
		{
			gameWorldProxy = (IGameWorld) data;
			repaint();
		}
	}
	
	public static int getMVWidth()
	{
		return width;
	}
	
	public static int getMVHeight()
	{
		return height;
	}
	
}
