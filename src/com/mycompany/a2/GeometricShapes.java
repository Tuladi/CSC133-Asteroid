package com.mycompany.a2;

import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;

abstract public class GeometricShapes {
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	public abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
}

