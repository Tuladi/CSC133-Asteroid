package com.mycompany.a2;

import java.util.Iterator;

public interface IGameWorld {
	Iterator getIterator();
	void addGameObject(GameObject o);
	boolean removeGameObject (GameObject o);
	int getPlayerScore();
}
