/*
 * Program to initialize the Asteroid class, which
 * will create asteroid objects whose sole purpose
 * is to collide with other objects in the game space
 * and be destroyed for points
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

//Asteroid is a concrete class of MoveableGameObject

public class Asteroid extends MoveableGameObject {
	private int size;
	
	/*
	 * Construct the asteroid as a MoveableGameObject
	 * Size has to be introduced as it is not part of the 
	 * parent constructors
	 */
	public Asteroid(int size)
	{
		super();
		this.setColor(ColorUtil.BLACK);
		this.size = size;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	@Override
	public String toString()
	{
		String parentDesc = super.toString();
		String myDesc = " size=" + this.size;
		return parentDesc + myDesc;
	}
}
