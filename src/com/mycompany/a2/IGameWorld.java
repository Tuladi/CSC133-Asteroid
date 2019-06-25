package com.mycompany.a2;

public interface IGameWorld {
	IIterator getIterator();
	void addGameObject(GameObject o);
	boolean removeGameObject (GameObject o);
	int getPlayerScore();
}
