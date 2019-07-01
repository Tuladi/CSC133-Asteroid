package com.mycompany.a2;
import java.util.Vector;

import Interfaces.ICollection;
import Interfaces.IIterator;

public class GameObjectCollection implements ICollection {
	private Vector<GameObject> ObjectCollection;
	
	public GameObjectCollection() {
		ObjectCollection = new Vector<GameObject>();
	}
	
	public Vector<GameObject> getObjects()
	{
		return ObjectCollection;
	}
	
	public void add(GameObject newObject) {
		ObjectCollection.addElement(newObject);
	}
	
	public IIterator getIterator() {
		return new SpaceVectorIterator();
	}
	
	private class SpaceVectorIterator implements IIterator {
		private int currElementIndex;
		
		public SpaceVectorIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (ObjectCollection.size() <= 0)
				return false;
			if (currElementIndex == ObjectCollection.size() - 1)
				return false;
			return true;
		}
		
		public GameObject getNext() {
			currElementIndex++;
			return(ObjectCollection.elementAt(currElementIndex));
		}

		@Override
		public void remove(GameObject obj) {
			// TODO Auto-generated method stub
			ObjectCollection.remove(obj);
			
		}
	}
}
