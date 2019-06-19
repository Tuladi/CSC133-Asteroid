/*
 * Constructs a game object which can move. These objects have to have
 * a speed and direction which they can move in. The speed is a random
 * value between 0 and 20, and the direction is a random value between
 * 0 and 359
 */

package com.mycompany.a2;

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
	
	@Override
	public void move()
	{
		int theta = 90 - this.getDirection();
		double deltaX = Math.cos(theta)*this.getSpeed();
		double deltaY = Math.sin(theta)*this.getSpeed();
		
		this.setLocation(this.getLocationX() + deltaX, this.getLocationY() + deltaY);
	}
	
	public String toString()
	{
		String parentDesc = super.toString();
		String myDesc = " speed=" + this.speed + " dir=" + this.direction;
		return parentDesc + myDesc;
	}
}
