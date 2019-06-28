package com.mycompany.a2.graphics;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.GeometricShapes;
import com.mycompany.a2.Missile;

public class MissileShape extends GeometricShapes {
	private Missile pewPew;
	private double iX;
	private double iY;
	private int iShapeX;
	private int iShapeY;
	private int size;
	private int color;

	public MissileShape(Missile pewPew) {
		this.pewPew = pewPew;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		this.iX = this.pewPew.getLocationX();
		this.iY = this.pewPew.getLocationY();
	}

}
