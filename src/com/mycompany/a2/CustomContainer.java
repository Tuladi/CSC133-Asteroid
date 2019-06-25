package com.mycompany.a2;

import java.util.Vector;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class CustomContainer extends Container {
	Vector<GeometricShapes> worldShapes;
	public CustomContainer(Vector<GeometricShapes> worldShapes) {
		this.worldShapes = worldShapes;
	}
	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		for (int i = 0; i < worldShapes.size(); i++)
			worldShapes.elementAt(i).draw(g, pCmpRelPrnt);
	}
}
