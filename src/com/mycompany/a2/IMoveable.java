/*
 * IMoveable is an interface implemented by all objects which
 * can move. (I.E: Objects which have a speed and direction)
 * Objects that are moveable will support the move function,
 * which will "move" the object according to its speed and direction
 * across the game map.
 */

package com.mycompany.a2;

public interface IMoveable {
	abstract public void move(double width, double height);
}
