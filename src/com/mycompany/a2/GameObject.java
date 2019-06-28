/*
 * The master class of which all game objects will be children
 * We implement basic getters and setters which all objects may need
 * and initialize variables which all objects will need, such as location and color
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import java.util.Random;

public abstract class GameObject {
	private Point2D location = new Point2D(0, 0);
	private int color;
	private boolean remove = false;
	protected static final Random R = new Random();
	
	/*
	 * Initialize a random location for game objects to spawn at. Game object with
	 * a more specific spawn location can re-set their location on their constructors.
	 * The object's color can be initialized on the child's constructor, since most objects
	 * will have unique colors.
	 */
	public GameObject() 
	{
		this.location.setX(0 + R.nextDouble()*1024);
		this.location.setY(0 + R.nextDouble()*768);
	}
	
	public String getLocation()
	{
		return this.location.toString();
	}
	
	public double getLocationX()
	{
		return this.location.getX();
	}
	
	public double getLocationY()
	{
		return this.location.getY();
	}
	
	public int getColor()
	{
		return this.color;
	}
	
	public void setLocation(double X, double Y)
	{
		this.location.setX(X);
		this.location.setY(Y);
	}
	
	public void setColor(int c)
	{
		this.color = c;
	}
	
	@Override
	public String toString()
	{
		@SuppressWarnings("deprecation")
		String myDesc = this.getClass().getSimpleName() + ": loc=[" + Math.round(this.location.getX()*10.0)/10.0 
															  + ", " + Math.round(this.location.getY()*10.0)/10.0 
															  + "], color=["  
															  + ColorUtil.red(this.color) + ","
															  + ColorUtil.green(this.color) + ","
															  + ColorUtil.blue(this.color) + "]";
		
		return myDesc;
	}
}
