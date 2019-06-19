/*
 * Initialize a player ship, which has a steerable missile launcher
 * The PS and PSML must be able to rotate, and the PS must be able to increase
 * and decrease its speed when commanded to do so by the player.
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

public class PlayerShip extends Ship implements ISteerable{
	private SteerableMissileLauncher launcher = new SteerableMissileLauncher(0);
	private static final int maxMissiles = 10;
	private static PlayerShip mFalcon;
	/*
	 * The player ship must be spawned in the middle of the map, facing north
	 * and with an initial speed of 0, so we declare those here to over-write
	 * the otherwise random default values it would be given.
	 */
	private PlayerShip() {
		super(maxMissiles);
		this.setColor(ColorUtil.BLUE);
		this.setSpeed(0);
		this.setDirection(0);
		this.setLocation(562, 384);
	}
	
	public static PlayerShip getPlayerShip()
	{
		if (mFalcon == null)
			mFalcon = new PlayerShip();
		return mFalcon;
	}
	public void increaseSpeed() {
		this.setSpeed(this.getSpeed() + 1);
	}
	
	public void decreaseSpeed() {
		this.setSpeed(this.getSpeed() - 1);
	}
	
	public void revolveML() {
		launcher.turnRight();
	}
	
	@Override
	public void turnLeft() {
		if (this.getDirection() > 0)
			this.setDirection(this.getDirection() - 1);
		else {
			this.setDirection(359);
		}
	}
	
	@Override
	public void turnRight() {
		this.setDirection((this.getDirection() + 1) %360);
	}
	
	@Override
	public int getMaxMissiles() {
		return maxMissiles;
	}
	
	@Override
	public int getDirectionML() {
		return launcher.getDirection();
	}
	
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " Missle launcher dir=" + this.getDirectionML();
		return parentDesc + myDesc;
	}
}