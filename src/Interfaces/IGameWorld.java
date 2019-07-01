package Interfaces;

import com.mycompany.a2.GameObject;
import com.mycompany.a2.GameObjectCollection;

public interface IGameWorld {	

	public int getPlayerScore();
	public int getNumLives();
	public int getMissileCount ();
	public int getElapsedTime();
	public boolean getSound();
	public boolean removeGameObject (GameObject o);
	
	public String getSoundState();
	
	public IIterator getIterator();
	
	public GameObjectCollection getGameCollection();
}
