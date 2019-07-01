/*
 * Initialize a player ship, which has a steerable missile launcher
 * The PS and PSML must be able to rotate, and the PS must be able to increase
 * and decrease its speed when commanded to do so by the player.
 */

package Objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.Ship;
import com.mycompany.a2.SteerableMissileLauncher;

import Interfaces.IDrawable;
import Interfaces.ISteerable;

public class PlayerShip extends Ship implements ISteerable, IDrawable {
	private SteerableMissileLauncher launcher = new SteerableMissileLauncher(getDirection()); 
	private static final int maxMissiles = 10;
	private static PlayerShip mFalcon;
	/*
	 * The player ship must be spawned in the middle of the map, facing north
	 * and with an initial speed of 0, so we declare those here to over-write
	 * the otherwise random default values it would be given.
	 */
	private PlayerShip() {
		super(maxMissiles);
		this.setColor(ColorUtil.rgb(255, 0, 0));
		this.setSpeed(0);
		this.setDirection(0);
		this.setLocation(562, 384);
		/*launcher = new SteerableMissileLauncher(getDirection());
		launcher.setLocation(getPlayerShip().getLocationX(),getPlayerShip().getLocationY());
		launcher.setSpeed(getPlayerShip().getSpeed());
		*/
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
	
	public void revolveMLRight() {
		launcher.turnRight();
	}
	
	public void revolveMLLeft() {
		launcher.turnLeft();
	}
	
	@Override
	public void turnRight() {
		if (this.getDirection() > 0)
			this.setDirection(this.getDirection() - 36);
		else {
			this.setDirection(359);
		}
	}
	
	@Override
	public void turnLeft() {
		this.setDirection((this.getDirection() + 36) %360);
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
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = (int) getLocationX();
		int yLoc = (int) getLocationY();
		//g.fillRect(xLoc, yLoc, 100, 100);
		g.fillPolygon(new int[] {xLoc+10,xLoc+30,xLoc+50}, new int[] {yLoc+100, yLoc+50, yLoc+100}, 3);
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