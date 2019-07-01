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

	private int blinkRate;
	private boolean lightOn = false;
	
	/*
	 * Upon construction, a space station only needs to have a blink rate and a bool 
	 * to control whether or not the light is on (it's off by default)
	 */
	public SpaceStation() {
		super();
		this.setColor(ColorUtil.LTGRAY);
		this.blinkRate = R.nextInt(6) + 1;
	}
	
	public void toggleLight() {
		this.lightOn = !this.lightOn;
		String s;
		if(this.lightOn) s = "on"; else s = "off";
		System.out.println("Turning Space Station " + this.getID() + " light " + s);
	}
	
	public int getBlinkRate() {
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
		g.fillRect(xLoc, yLoc, 100, 100);
		//g.fillPolygon(new int[] {10,30,50}, new int[] {100, 50, 100}, 3);
	}
}