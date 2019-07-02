/*
 * Steerable Missile Launchers a special subset of missile launchers
 * only found on player ships. The player can rotate the SML.
 */

package Objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import Interfaces.IDrawable;
import Interfaces.ISteerable;
 
/*
 * Initialize the launcher with a direction it inherits from the player
 * ship that initializes it.
 */
public class SteerableMissileLauncher extends MissileLauncher implements ISteerable, IDrawable {
	public SteerableMissileLauncher(int d) {
		super(d);
		this.setColor(ColorUtil.MAGENTA);
	}
	
	@Override
	public void turnLeft() {
		this.setDirection(this.getDirection() - 1);
	}
	
	@Override
	public void turnRight() {
		this.setDirection(this.getDirection() + 1);
	}
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " Steerable Missle launcher dir=" + this.getDirection();
		return parentDesc + myDesc;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = (int) getLocationX();
		int yLoc = (int) getLocationY();
		//g.fillPolygon(new int[] {xLoc+10,xLoc+30,xLoc+50}, new int[] {yLoc+100, yLoc+50, yLoc+100}, 3);
		g.fillRect(xLoc, yLoc, 15, 15);
	}
	
	@Override
	public void move(int elapsedMilliSecs)
	{
		double theta = Math.toRadians(90 - getDirection());
		double distance = (double) getSpeed()* ((double) elapsedMilliSecs/1000.00);
		double deltaX = Math.cos(theta)*distance;
		double deltaY = Math.sin(theta)*distance;
	}
}