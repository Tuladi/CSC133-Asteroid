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
import com.mycompany.a2.MapView;

import Interfaces.IDrawable;
import Interfaces.ISteerable;

public class PlayerShip extends Ship implements ISteerable, IDrawable {
	private SteerableMissileLauncher launcher;
	private static final int maxMissiles = 10;
	private static PlayerShip mFalcon;
	
	private int iShapeX;
	private int iShapeY;
	
	/*
	 * The player ship must be spawned in the middle of the map, facing north
	 * and with an initial speed of 0, so we declare those here to over-write
	 * the otherwise random default values it would be given.
	 */
	private PlayerShip() {
		super(maxMissiles);
		this.setColor(ColorUtil.rgb(255, 0, 0));
		this.setSpeed(0);
		this.setDirection(180);
		this.setLocation(MapView.getMVWidth()/2, MapView.getMVHeight()/2);
		launcher  = new SteerableMissileLauncher(180);
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
		if (launcher.getDirection() > 0)
			launcher.setDirection(launcher.getDirection() - 10);
		else {
			launcher.setDirection(359);
		}
	}
	
	
	public void revolveMLLeft() {
		launcher.setDirection((launcher.getDirection() + 10) %360);
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
		String myDesc = " Missle launcher dir=" + launcher.getDirection();
		return parentDesc + myDesc;
	}
	@Override 
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		this.iShapeX = (int) getLocationX();
		this.iShapeY = (int) getLocationY();
		
		
		g.rotateRadians((float) Math.toRadians((double) 180 - this.getDirection()), iShapeX, iShapeY);
		
		//draw the nose
		g.fillTriangle(iShapeX + 20/2, iShapeY, iShapeX + 20/6, iShapeY + 20/2, iShapeX + 20*5/6, iShapeY + 20/2);
		
		//draw the gap in the ship
		g.setColor(ColorUtil.WHITE);
		g.fillRect(iShapeX + 20*7/16, iShapeY, 20/8, 20*3/8);
		g.fillRect(iShapeX, iShapeY, 20, 20/7);
		
		//adjust iShapeY since the rest of the body of the ship is much lower
		this.iShapeY += 20*5/14;
		
		//draw the ship body
		g.setColor(this.getColor());
		g.fillArc(iShapeX, iShapeY, 20, 20, 0, 360);
		g.fillRect(iShapeX + 20*5/6, iShapeY, 20/6, 20/2);
		
		g.rotateRadians((float) -Math.toRadians((double) 180 - this.getDirection()), iShapeX, iShapeY);
		launcher.draw(g, pCmpRelPrnt);
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