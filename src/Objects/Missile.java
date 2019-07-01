/*
 * Constructs a missile, and assigns it to whichever
 * ship fired the missile. Each missile has it's own owner
 * and it's own fuelLevel. When fuelLevel hits 0, it will
 * be removed from the game world
 */

package Objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.MoveableGameObject;
import com.mycompany.a2.Ship;

import Interfaces.IDrawable;

public class Missile extends MoveableGameObject implements IDrawable{
	
	private int fuelLevel = 30;
	private Ship owner;
	
	/*
	 * Construct the missile by assigning its color, its owner
	 * and set its direction equal to its owner ship's launcher,
	 * its speed equal to its parent ship's speed +2 (so they don't
	 * collide immediately after firing), and its location to its parent
	 * ship's location
	 */
	public Missile(Ship ship) {
		super();
		this.setColor(ColorUtil.GRAY);
		this.owner = ship;
		try {
			this.setDirection(ship.getDirectionML());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setSpeed(ship.getSpeed() + 30);
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
	
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " fuel=" + this.fuelLevel;
		return parentDesc + myDesc; 
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = (int) getLocationX();
		int yLoc = (int) getLocationY();
		g.drawRect(xLoc, yLoc, 10, 20);
	}
	
	@Override
	public void move(int elapsedMilliSecs)
	{
		double theta = Math.toRadians(90 - getDirection());
		double distance = (double) getSpeed()* ((double) elapsedMilliSecs/1000.00);
		double deltaX = Math.cos(theta)*distance;
		double deltaY = Math.sin(theta)*distance;
		this.setLocation(this.getLocationX() + deltaX, this.getLocationY() + deltaY);
	}
}