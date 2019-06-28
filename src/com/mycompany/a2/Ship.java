/*
 * Program to initialize the abstract class ship
 * which will be used when creating PlayerShip's and
 * NonPlayerShip's
 */

package com.mycompany.a2;

public abstract class Ship extends MoveableGameObject{
	private int missileCount;
	
	/*
	 * Construct the ship as a MoveableGameObject, but with
	 * a dynamic color and missileCount, which will be passed
	 * to the constructor by the sub-classes, which have unique
	 * missile capacities.
	 */
	public Ship (int missileCount)
	{
		super(/*size:*/200);
		this.missileCount = missileCount;
	}
	
	public void decrementMC()
	{
		this.missileCount -= 1;
	}
	
	public void setMC(int x)
	{
		this.missileCount = x;
	}
	
	public int getMC()
	{
		return this.missileCount;
	}
	
	//This should work as getMaxMissiles will be over-ridden.
	public void reloadMissiles() {
		try {
			this.setMC(this.getMaxMissiles());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*Throw an exception as this method should always be over-ridden by
	 *sub-class methods. If it isn't over-ridden, we have
	 *a problem, as someone initialized an incomplete sub-class.*/
	public int getMaxMissiles() throws Exception {
		throw new Exception("getMaxMissiles not implemented for this object");
	}
	
	/*Throw an exception as this method should always be over-ridden by
	 *sub-class methods. If it isn't over-ridden, we have
	 *a problem, as someone initialized an incomplete sub-class.*/
	public int getDirectionML() throws Exception {
		throw new Exception("getDirectionML not implemented for this object");
	}
	
	/*Throw an exception as this method should always be over-ridden by
	 *sub-class methods. If it isn't over-ridden, we have
	 *a problem, as someone initialized an incomplete sub-class.*/
	public void setDirectionML(int i) throws Exception{
		throw new Exception("setDirectionML not implemented for this object");
	}
	
	@Override
	public String toString()
	{
		String parentDesc = super.toString();
		String myDesc = " missles=" + this.missileCount;
		return parentDesc + myDesc;
	}

}
