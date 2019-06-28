package com.mycompany.a2.graphics;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.GeometricShapes;
import com.mycompany.a2.NonPlayerShip;

public class NonPlayerShipShape extends GeometricShapes{
	private NonPlayerShip tiFighter;
	private double iX;
	private double iY;
	private int iShapeX;
	private int iShapeY;
	private int size;
	private int color;

	public NonPlayerShipShape(NonPlayerShip tiFighter) {
		this.tiFighter = tiFighter;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		this.iX = this.tiFighter.getLocationX();
		this.iY = this.tiFighter.getLocationY();
	}

}
