package com.mycompany.a2;
import java.util.Vector;

public class GameObjectCollection implements ICollection {
	private Vector<GameObject> store;
	
	public GameObjectCollection() {
		store = new Vector<GameObject>();
	}
	
	public Vector<GameObject> getObjects()
	{
		return store;
	}
	
	public void add(GameObject newObject) {
		store.addElement(newObject);
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
			if (store.size() <= 0)
				return false;
			if (currElementIndex == store.size() - 1)
				return false;
			return true;
		}
		
		public GameObject getNext() {
			currElementIndex++;
			return(store.elementAt(currElementIndex));
		}
	}
}
