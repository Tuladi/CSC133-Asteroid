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
	//this should be over-ridden by child attributes
	private int size = 1;
	
	public MoveableGameObject(int size)
	{
		super();
		this.size=size;
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
	
	public int getSize()
	{
		return this.size;
	}
	
	@Override
	public void move(double width, double height)
	{
		int oldDirection = this.getDirection();
		// reverse the movement direction if the image reaches an edge
		if ( (this.getLocationX()+this.size >= width) || (this.getLocationX() < 0) )
			this.setDirection(this.getDirection() + 180);
		if ( (this.getLocationY()+this.size >= height) || (this.getLocationY() < 0) )
			this.setDirection(180 - this.getDirection());
		
		int theta = 90 - this.getDirection();
		double deltaX = Math.cos(theta)*this.getSpeed();
		double deltaY = Math.sin(theta)*this.getSpeed();
		
		this.setLocation(this.getLocationX() + deltaX, this.getLocationY() + deltaY);
		if(this instanceof Ship) {
			Ship temp = (Ship) this;
			try {
				temp.setDirectionML(temp.getDirectionML() + (this.getDirection() - oldDirection));
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public String toString()
	{
		String parentDesc = super.toString();
		String myDesc = " speed=" + this.speed + " dir=" + this.direction;
		return parentDesc + myDesc;
	}
}
