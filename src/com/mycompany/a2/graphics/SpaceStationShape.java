package com.mycompany.a2.graphics;

import com.codename1.charts.util.ColorUtil;
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
	private int color;
	private boolean isOn;
	private int size;

	public SpaceStationShape(SpaceStation deathStar) {
		this.deathStar = deathStar;
		this.iX = this.deathStar.getLocationX();
		this.iY = this.deathStar.getLocationY();
		this.color = this.deathStar.getColor();
		this.isOn = this.deathStar.isLightOn();
		this.size = 30;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.color);
		this.iShapeX = (int) Math.round(pCmpRelPrnt.getX() + (float) this.iX);
		this.iShapeY = (int) Math.round(pCmpRelPrnt.getY() + (float) this.iY);
		g.fillArc(this.iShapeX, this.iShapeY, this.size, this.size, 0, 360);
		g.setColor(ColorUtil.GREEN);
		if(this.isOn)
			g.fillArc(iShapeX + this.size*3/5, iShapeY + this.size/4, this.size/4, this.size/4, 0, 360);
		else
			g.drawArc(iShapeX + this.size*3/5, iShapeY + this.size/4, this.size/4, this.size/4, 0, 360);
		
	}

	@Override
	public void update() {
		this.isOn = this.deathStar.isLightOn();
	}

}
