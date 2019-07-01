/*
 * Program to initialize the Asteroid class, which
 * will create asteroid objects whose sole purpose
 * is to collide with other objects in the game space
 * and be destroyed for points
 */

package Objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.MoveableGameObject;

import Interfaces.IDrawable;

//Asteroid is a concrete class of MoveableGameObject

public class Asteroid extends MoveableGameObject implements IDrawable {
	private int size;
	
	/*
	 * Construct the asteroid as a MoveableGameObject
	 * Size has to be introduced as it is not part of the 
	 * parent constructors
	 */
	public Asteroid(int size)
	{
		super();
		this.setColor(ColorUtil.YELLOW);
		this.size = size;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	@Override
	public String toString()
	{
		String parentDesc = super.toString();
		String myDesc = " size=" + this.size;
		return parentDesc + myDesc;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = (int) getLocationX();
		int yLoc = (int) getLocationY();
		g.fillArc(xLoc, yLoc, size*10, size*10, 0, 360);
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
