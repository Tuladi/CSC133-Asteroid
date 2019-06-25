package com.mycompany.a2;

import java.util.Iterator;
import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld{
	private GameWorld realGameWorld ;
	public GameWorldProxy (GameWorld gw)
	{ 
		realGameWorld = gw; 
		gw.init();
	}
	
	public IIterator getIterator ()
	{ 
		return realGameWorld.getIterator(); 
	}
	
	public void addGameObject(GameObject o)
	{ 
		realGameWorld.addGameObject(o) ; 
	}
	
	public boolean removeGameObject (GameObject o)
	{ 
		return false ; 
	}
	
	@Override
	public int getPlayerScore() {
		// TODO Auto-generated method stub
		return 0;
	}

}
