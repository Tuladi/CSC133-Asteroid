package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class ObserverList extends Observable {
	private Vector<Observer> myObserverList = new Vector<Observer>();
	
	public Vector<Observer> getObserverList()
	{
		return myObserverList;
	}
}
