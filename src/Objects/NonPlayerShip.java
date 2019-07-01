/*
 * Creates a non player ship which will travel in a set direction
 * and occasionally fire missiles. This will have a non moveable missile launcher
 * and must have a size to determine when it collides with a PS/Asteroid/Missile
 */

package Objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.Ship;

import Interfaces.IDrawable;

public class NonPlayerShip extends Ship implements IDrawable {

	private MissileLauncher launcher = new MissileLauncher(this.getDirection());
	private int size;
	private static final int maxMissiles = 4;
	
	/*
	 * Non Player Ship is initialized with a random size of either 15
	 * or 25, as with a missile cargo capable of holding 4 missiles.
	 */
	public NonPlayerShip() {
		super(maxMissiles);
		this.setColor(ColorUtil.CYAN);
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
	public int getMaxMissiles() {
		return maxMissiles;
	}
	
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " size=" + this.size;
		return parentDesc + myDesc;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = (int) getLocationX();
		int yLoc = (int) getLocationY();
		g.fillArc(xLoc, yLoc, size*10, size*4, 0, 360);
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