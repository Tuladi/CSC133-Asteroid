package com.mycompany.a2;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import Interfaces.IGameWorld;
import Interfaces.IIterator;

public class GameWorldProxy extends Observable implements IGameWorld{
	private GameWorld realGameWorld;

	public GameWorldProxy (GameWorld gw)
	{ 
		realGameWorld = gw; 
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

	
	public void toggleSound() {
		realGameWorld.setSound(!realGameWorld.getSound());
		setChanged();
		notifyObservers();
	}
	
	public int getPlayerScore () 
	{
		return realGameWorld.getPlayerScore();
	}
	
	public int getNumLives () 
	{ 
		return realGameWorld.getNumLives(); 
	}
	 
	public int getMissileCount () 
	{
		return realGameWorld.getMissileCount(); 
	}
	
	public int getElapsedTime () 
	{ 
		return realGameWorld.getElapsedTime(); 
	}
	
	public String getSoundState ()
	{
		if (realGameWorld.getSound() == true)
			return "ON";
		else { return "OFF"; }
	}

	public boolean getSound() {
		return realGameWorld.getSound();
	}

	public GameObjectCollection getGameCollection() {
		return realGameWorld.getGameCollection();
	}
	
	/*public void notifyObservers()
	{
		GameWorldProxy proxy = new GameWorldProxy(this);
		for (Observer o : myObserverList)
		{
			o.update((Observable)proxy, null);
		}
	}
	*/
}
