package com.mycompany.a2.graphics;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.GeometricShapes;
import com.mycompany.a2.SpaceStation;

public class SpaceStationShape extends GeometricShapes {
	private SpaceStation deathStar;
	private double iX;
	private double iY;
	private int iShapeX;
	private int iShapeY;
	private int size;
	private int color;

	public SpaceStationShape(SpaceStation deathStar) {
		this.deathStar = deathStar;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		this.iX = this.deathStar.getLocationX();
		this.iY = this.deathStar.getLocationY();
	}

}
