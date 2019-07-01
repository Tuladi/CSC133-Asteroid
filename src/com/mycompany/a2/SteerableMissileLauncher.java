/*
 * Steerable Missile Launchers a special subset of missile launchers
 * only found on player ships. The player can rotate the SML.
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

import Interfaces.ISteerable;
import Objects.MissileLauncher;
 
/*
 * Initialize the launcher with a direction it inherits from the player
 * ship that initializes it.
 */
public class SteerableMissileLauncher extends MissileLauncher implements ISteerable{
	public SteerableMissileLauncher(int d) {
		super(d);
		this.setColor(ColorUtil.CYAN);
	}
	
	@Override
	public void turnLeft() {
		this.setDirection(this.getDirection() - 1);
	}
	
	@Override
	public void turnRight() {
		this.setDirection(this.getDirection() + 1);
	}
	
	
}