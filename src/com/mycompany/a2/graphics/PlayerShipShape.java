package com.mycompany.a2.graphics;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a2.GeometricShapes;
import com.mycompany.a2.PlayerShip;

public class PlayerShipShape extends GeometricShapes {
	private PlayerShip mFalcon;
	private double iX;
	private double iY;
	private int iShapeX;
	private int iShapeY;
	private int size;
	private int color;

	public PlayerShipShape(PlayerShip playerShip) {
		this.mFalcon = playerShip;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		this.iX = this.mFalcon.getLocationX();
		this.iY = this.mFalcon.getLocationY();
	}

}
