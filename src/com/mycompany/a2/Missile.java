/*
 * Constructs a missile, and assigns it to whichever
 * ship fired the missile. Each missile has it's own owner
 * and it's own fuelLevel. When fuelLevel hits 0, it will
 * be removed from the game world
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

public class Missile extends MoveableGameObject{
	
	private int fuelLevel = 15;
	private Ship owner;
	private int size = 16;
	
	/*
	 * Construct the missile by assigning its color, its owner
	 * and set its direction equal to its owner ship's launcher,
	 * its speed equal to its parent ship's speed +2 (so they don't
	 * collide immediately after firing), and its location to its parent
	 * ship's location
	 */
	public Missile(Ship ship) {
		super(/*size:*/16);
		this.setColor(ColorUtil.rgb(255, 0, 0));
		this.owner = ship;
		try {
			this.setDirection(ship.getDirectionML());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setSpeed(ship.getSpeed() + 2);
		this.setLocation(ship.getLocationX(), ship.getLocationY());
	}
	
	public void decrementFuelLevel() {
		this.fuelLevel = this.fuelLevel - 1;
	}
	
	public Ship getShipType() {
		return this.owner;
	}
	
	public int getFuelLevel() {
		return this.fuelLevel;
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " fuel=" + this.fuelLevel;
		return parentDesc + myDesc; 
	}
}