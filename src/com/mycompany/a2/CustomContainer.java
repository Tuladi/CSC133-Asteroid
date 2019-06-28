package com.mycompany.a2;

import java.util.Vector;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class CustomContainer extends Container {
	
	private Vector<GeometricShapes> worldShapes;
	
	public CustomContainer(Vector<GeometricShapes> worldShapes) {
		this.worldShapes = worldShapes;
	}
	

}
