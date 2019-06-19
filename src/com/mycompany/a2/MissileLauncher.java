/*
 * Initialize a ship's missile launcher. It has no unique attributes
 * but needs to exist so that a PS can fire a missile in a direction
 * different from that of the PS. For consistency's sake, NPS will
 * have missile launchers as well (constructed here) which must inherit
 * their direction from their owner ship.
 */

package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

public class MissileLauncher extends MoveableGameObject{

	public MissileLauncher(int d) {
		super();
		this.setColor(ColorUtil.YELLOW);
		this.setDirection(d);
	}
}