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
	private int direction;
	private int color;

	public MissileShape(Missile pewPew) {
		this.pewPew = pewPew;
		this.iX = this.pewPew.getLocationX();
		this.iY = this.pewPew.getLocationY();
		this.color = this.pewPew.getColor();
		this.direction = 90 - this.pewPew.getDirection();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.color);
		this.iShapeX = (int) Math.round(pCmpRelPrnt.getX() + (float) this.iX);
		this.iShapeY = (int) Math.round(pCmpRelPrnt.getY() + (float) this.iY);
		g.rotateRadians((float) Math.toRadians((double) this.direction), this.iShapeX, this.iShapeY);
		g.fillRect(this.iShapeX, this.iShapeY, this.pewPew.getSize()/2, this.pewPew.getSize());
		g.rotateRadians((float) -Math.toRadians((double) this.direction), this.iShapeX, this.iShapeY);
	}

	@Override
	public void update() {
		this.iX = this.pewPew.getLocationX();
		this.iY = this.pewPew.getLocationY();
	}

}
