package Interfaces;

import com.mycompany.a2.GameObject;

public interface IIterator {
	public boolean hasNext();
	public GameObject getNext();
	public void remove(GameObject obj);
}
