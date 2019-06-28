/*
 * Creates a non player ship which will travel in a set direction
 * and occasionally fire missiles. This will have a non moveable missile launcher
 * and must have a size to determine when it collides with a PS/Asteroid/Missile
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

public class NonPlayerShip extends Ship{

	private MissileLauncher launcher = new MissileLauncher(this.getDirection());
	private int size;
	private static final int maxMissiles = 4;
	
	/*
	 * Non Player Ship is initialized with a random size of either 15
	 * or 25, as with a missile cargo capable of holding 4 missiles.
	 */
	public NonPlayerShip() {
		super(maxMissiles);
		this.setColor(ColorUtil.BLACK);
		if (R.nextInt(2) == 1) {
			this.size = 15;
		}
		else {
			this.size = 25;
		}
	}
	
	public int getSize() {
		return this.size;
	}
	
	@Override
	public int getDirectionML() {
		return launcher.getDirection();
	}
	
	@Override
	public void setDirectionML(int direction) {
		launcher.setDirection(direction);
	}
	
	@Override
	public int getMaxMissiles() {
		return maxMissiles;
	}
	
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " size=" + this.size;
		return parentDesc + myDesc;
	}
}