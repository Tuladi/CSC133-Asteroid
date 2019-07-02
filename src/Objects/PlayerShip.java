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
	private double centerX = MapView.getMVWidth()/2;
	private double centerY = MapView.getMVHeight()/2;
	
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
		this.setLocation(centerX, centerY);
		launcher = new SteerableMissileLauncher(180);
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
	/*
	@Override 
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		
		int xLoc = (int) pCmpRelPrnt.getX() + (int) this.getLocationX();
		int yLoc = (int) pCmpRelPrnt.getY() + (int) this.getLocationY();
		g.fillPolygon(new int[] {xLoc+10,xLoc+30,xLoc+50}, new int[] {yLoc+100, yLoc+50, yLoc+100}, 3);
		launcher.draw(g, pCmpRelPrnt);
	}*/
	
	@Override 
    public void draw(Graphics g, Point pCmpRelPrnt) {
        g.setColor(this.getColor());
        int iShapeX = (int) pCmpRelPrnt.getX() + (int) this.getLocationX();
        int iShapeY = (int) pCmpRelPrnt.getY() + (int) this.getLocationY();


        g.rotateRadians((float) Math.toRadians((double) 180 - this.getDirection()), iShapeX, iShapeY);

        //draw the nose
        g.fillTriangle(iShapeX + 20/2, iShapeY, iShapeX + 20/6, iShapeY + 20/2, iShapeX + 205/6, iShapeY + 20/2);

        //draw the gap in the ship
        g.setColor(ColorUtil.WHITE);
        g.fillRect(iShapeX + 207/16, iShapeY, 20/8, 203/8);
        g.fillRect(iShapeX, iShapeY, 20, 20/7);

        //adjust iShapeY since the rest of the body of the ship is much lower
        iShapeY += 205/14;

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