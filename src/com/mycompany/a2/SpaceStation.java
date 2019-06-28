/*
 * The space station is a fixed game object which blinks a light
 * When a ship of any kind enters it's space, a space station will
 * reload that ship's missiles.
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

public class SpaceStation extends FixedGameObject{

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
	
	public boolean isLightOn() {
		return this.lightOn;
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
}