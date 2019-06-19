/*
 * ISteerable is an interface to change the direction of steerable
 * objects. All steerable objects will implement hte below functions.
 */

package com.mycompany.a2;

public interface ISteerable {
	abstract public void turnLeft();
	abstract public void turnRight();
}
