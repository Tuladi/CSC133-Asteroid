/*
 * Program to initialize Game Objects which can never move.
 * The biggest example of this is the SpaceStation
 */

package com.mycompany.a2;
import com.codename1.ui.geom.Point2D;

public abstract class FixedGameObject extends GameObject{
	
	private int id;
	private static int helper = 0;		//as a static int, helper can permanently track the largest ID used
	final Point2D location = new Point2D(0 + R.nextDouble()*1024, 0 + R.nextDouble()*768);
	
	public FixedGameObject() {
		super();
		this.id = this.getNextID();
	}
	
	public int getNextID() {
		FixedGameObject.helper += 1;	//increment helper so we don't re-use old id's
		return (FixedGameObject.helper);
	}
	
	public int getID() {
		return this.id;
	}
	
	public String toString()
	{
		String parentDesc = super.toString();
		String myDesc = " id=" + this.id;
		return parentDesc + myDesc;
	}
}