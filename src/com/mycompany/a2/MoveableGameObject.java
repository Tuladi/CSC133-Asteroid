/*
 * Constructs a game object which can move. These objects have to have
 * a speed and direction which they can move in. The speed is a random
 * value between 0 and 20, and the direction is a random value between
 * 0 and 359
 */

package com.mycompany.a2;

import Interfaces.IMoveable;

public abstract class MoveableGameObject extends GameObject implements IMoveable {
	private int speed;
	private int direction;
	
	public MoveableGameObject()
	{
		super();
		this.speed = R.nextInt(21);
		this.direction = R.nextInt(360);
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public int getDirection()
	{
		return this.direction;
	}
	
	public void setSpeed(int s)
	{
		this.speed = s;
	}
	
	public void setDirection(int d)
	{
		this.direction =  d;
	}
	

	public void move(int elapsedMilliSecs)
	{
		int theta = 90 - getDirection();
		double distance = (double) getSpeed()* ((double) elapsedMilliSecs/1000.00);
		double deltaX = Math.cos(theta)*distance;
		double deltaY = Math.sin(theta)*distance;
		this.setLocation(this.getLocationX() + deltaX, this.getLocationY() + deltaY);
	}
	
	public String toString()
	{
		String parentDesc = super.toString();
		String myDesc = " speed=" + this.speed + " dir=" + this.direction;
		return parentDesc + myDesc;
	}
}
