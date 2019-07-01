/*
 * The space station is a fixed game object which blinks a light
 * When a ship of any kind enters it's space, a space station will
 * reload that ship's missiles.
 */

package Objects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.FixedGameObject;

import Interfaces.IDrawable;

public class SpaceStation extends FixedGameObject implements IDrawable {
	
	private double blinkRate;
	private boolean lightOn = false;
	private int size = 100;
	/*
	 * Upon construction, a space station only needs to have a blink rate and a bool 
	 * to control whether or not the light is on (it's off by default)
	 */
	public SpaceStation() {
		super();
		this.setColor(ColorUtil.LTGRAY);
		this.blinkRate =  ((double) R.nextInt(6) + 1.0) ;

	}
	
	public void toggleLight() {
		this.lightOn = !this.lightOn;
		String s;
		if(this.lightOn) s = "on"; else s = "off";
		System.out.println("Turning Space Station " + this.getID() + " light " + s);
	}
	
	public double getBlinkRate() {
		return this.blinkRate;
	}
	
	@Override
	public String toString()
	{
		String parentDesc = super.toString();
		String myDesc = " rate=" + this.blinkRate;
		return parentDesc + myDesc;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = (int) getLocationX();
		int yLoc = (int) getLocationY();
		//g.fillRect(xLoc, yLoc, 100, 100);
		if(isSelected()) {
			g.fillArc(xLoc, yLoc, size, size, 0, 360);
			g.setColor(ColorUtil.GREEN);
			g.fillArc(xLoc+size/4, yLoc+size/4, size/2, size/2, 0, 360);
		}
		else{
			g.fillArc(xLoc, yLoc, size, size, 0, 360);
		}
		
	}
	public boolean isSelected() { return lightOn; }
}