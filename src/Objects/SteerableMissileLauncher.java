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
public class SteerableMissileLauncher extends MissileLauncher implements ISteerable {
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
	
}