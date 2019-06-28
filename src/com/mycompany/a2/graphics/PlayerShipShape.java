package com.mycompany.a2.graphics;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.GeometricShapes;
import com.mycompany.a2.PlayerShip;
import com.mycompany.a2.SteerableMissileLauncher;

public class PlayerShipShape extends GeometricShapes {
	private PlayerShip mFalcon;
	private SteerableMissileLauncher turret;
	private double iX;
	private double iY;
	private int iShapeX;
	private int iShapeY;
	private int size;
	private int color;
	private int colorML;
	private int direction;

	public PlayerShipShape(PlayerShip playerShip) {
		this.mFalcon = playerShip;
		this.turret = this.mFalcon.getML();
		this.iX = this.mFalcon.getLocationX();
		this.iY = this.mFalcon.getLocationY();
		this.color = this.mFalcon.getColor();
		this.colorML = this.turret.getColor();
		this.direction = this.mFalcon.getDirection();
		this.size = this.mFalcon.getSize();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.color);
		this.iShapeX = (int) Math.round(pCmpRelPrnt.getX() + (float) this.iX);
		this.iShapeY = (int) Math.round(pCmpRelPrnt.getY() + (float) this.iY);
		g.rotateRadians((float) Math.toRadians((double) this.direction), iShapeX, iShapeY);
		
		//draw the nose
		g.fillTriangle(iShapeX + size/2, iShapeY, iShapeX + size/6, iShapeY + size/2, iShapeX + size*5/6, iShapeY + size/2);
		
		//draw the gap in the ship
		g.setColor(ColorUtil.WHITE);
		g.fillRect(iShapeX + size*7/16, iShapeY, size/8, size*3/8);
		g.fillRect(iShapeX, iShapeY, size, size/7);
		
		//adjust iShapeY since the rest of the body of the ship is much lower
		this.iShapeY += size*5/14;
		
		//draw the ship body
		g.setColor(this.color);
		g.fillArc(iShapeX, iShapeY, size, size, 0, 360);
		g.fillRect(iShapeX + size*5/6, iShapeY, size/6, size/2);
		
		//draw the turret
		g.rotateRadians((float) -Math.toRadians((double) this.direction), iShapeX, iShapeY);
		
	}

	@Override
	public void update() {
		this.iX = this.mFalcon.getLocationX();
		this.iY = this.mFalcon.getLocationY();
	}

}
