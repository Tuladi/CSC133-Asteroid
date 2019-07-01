package Interfaces;

import com.mycompany.a2.GameObject;

public interface ICollection {
	public void add(GameObject newObject);
	public IIterator getIterator();
}
