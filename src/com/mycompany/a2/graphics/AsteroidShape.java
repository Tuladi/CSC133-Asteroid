package com.mycompany.a2.graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a2.Asteroid;
import com.mycompany.a2.GeometricShapes;

public class AsteroidShape extends GeometricShapes{
	private Asteroid asteroid;
	private double iX;
	private double iY;
	private int iShapeX;
	private int iShapeY;
	private int size;
	private int color;
	
	public AsteroidShape(Asteroid roid) {
		this.asteroid = roid;
		this.iX = Math.round(this.asteroid.getLocationX());
		this.iY = Math.round(this.asteroid.getLocationY());
		this.color = this.asteroid.getColor();
		this.size = this.asteroid.getSize();
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.color);
		this.iShapeX = (int) Math.round(pCmpRelPrnt.getX() + (float) this.iX);
		this.iShapeY = (int) Math.round(pCmpRelPrnt.getY() + (float) this.iY);
		g.drawArc(this.iShapeX, this.iShapeY, this.size, this.size, 0, 360);
	}
	
	@Override
	public void update() {
		this.iX = this.asteroid.getLocationX();
		this.iY = this.asteroid.getLocationY();
	}
}
