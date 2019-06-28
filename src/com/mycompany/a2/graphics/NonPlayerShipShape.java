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
	private int direction;
	private int color;

	public NonPlayerShipShape(NonPlayerShip tiFighter) {
		this.tiFighter = tiFighter;
		this.iX = this.tiFighter.getLocationX();
		this.iY = this.tiFighter.getLocationY();
		this.color = this.tiFighter.getColor();
		this.direction = this.tiFighter.getDirection();
		this.size = this.tiFighter.getSize();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.color);
		this.iShapeX = (int) Math.round(pCmpRelPrnt.getX() + (float) this.iX);
		this.iShapeY = (int) Math.round(pCmpRelPrnt.getY() + (float) this.iY);
		g.rotateRadians((float) Math.toRadians((double) this.direction), this.iShapeX, this.iShapeY);
		g.fillArc(this.iShapeX, this.iShapeY + this.size/2, this.size, this.size, 0, 360);
		g.drawLine(this.iShapeX, this.iShapeY, this.iShapeX, this.iShapeY + this.size*2);
		g.drawLine(this.iShapeX + this.size, this.iShapeY, this.iShapeX + this.size, this.iShapeY + this.size*2);
		g.rotateRadians((float) -Math.toRadians((double) this.direction), this.iShapeX, this.iShapeY);
	}

	@Override
	public void update() {
		this.iX = this.tiFighter.getLocationX();
		this.iY = this.tiFighter.getLocationY();
	}

}
