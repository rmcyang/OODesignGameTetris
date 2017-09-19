package lab12;

import java.awt.Color;
import java.util.Random;
import java.math.*;

import sedgewick.StdDraw;

/**
 * Draw a shape like the following:
 
 */

public class lShape extends Shape {

	
	private double movePerCall;
	
	public lShape(double x, int msecs, int showPauseTime) {
		this.x = x;
		this.y = 1.-Game.cellRadius;
		this.color = Game.genRandomColor();
		
		int numCallsToThisMethod = msecs / showPauseTime;
		this.movePerCall = 1.0 / numCallsToThisMethod;
		
	}
	
	@Override
	public void right() {
		if(Math.abs(x - .98) > 1e-6) {
			x = x + .04;
		}
	}
	@Override
	public void left() {
		if(Math.abs(x - .02) > 1e-6) {
			x = x - .04;
		}
	}
	

	@Override
	/**
	 * Check if the shape collides with cells that already exist on the background board.
	 */
	public boolean checkCollisionY(background bg)
	{
		for (int i = 0; i < bg.rows; i++) {
			for (int j = 0; j < bg.cols; j++) {
				if (bg.getCellCondition(i, j)) {
					if (Math.abs(this.x - (i*2+1)*Game.cellRadius) < 1e-6
						&& Math.abs(this.y-Game.cellRadius*6 - (j*2+3)*Game.cellRadius) < 1e-6)
				    {
						System.out.println("l collides at Y direction at: "+i+", " + j);
						return true;
					}
				}
			}
		}
		return false;
	}
	@Override
	public boolean checkCollisionX(background bg, boolean left)
	{
		int k = left? 1 : -1;
		
		for (int i = 0; i < bg.rows; i++) {
			for (int j = 0; j < bg.cols; j++) {
				if (bg.getCellCondition(i, j)) {
					if ((Math.abs(this.x-  (i*2+1+2*k)*Game.cellRadius) < 1e-6 ) && 
						(Math.abs(this.y - (j*2+1)*Game.cellRadius) < Game.cellRadius
						 || Math.abs(this.y - (j*2+1+2)*Game.cellRadius) < Game.cellRadius 
						 || Math.abs(this.y - (j*2+1+4)*Game.cellRadius) < Game.cellRadius
						 || Math.abs(this.y - (j*2+1+6)*Game.cellRadius) < Game.cellRadius)) {
						System.out.println("l collides at X direction at: "+i+", " + j);
						return true;
					}
						
				}
			}
		}
		return false;
	}
	
	@Override
	public void addToBackground(background bg) {
		int n_row = ((int)(this.x / (Game.cellRadius)) - 1)/2;
		int n_col = ((int)(Math.ceil(this.y / (Game.cellRadius))) - 1)/2;
		
		System.out.println("l added to background: "+n_row+", " + n_col);
		
		bg.setCoord(n_row, n_col, this.color);
		bg.setCoord(n_row, n_col-1, this.color);
		bg.setCoord(n_row, n_col-2, this.color);
		bg.setCoord(n_row, n_col-3, this.color);
	}
	
	@Override
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(x, y, .02);
		StdDraw.filledSquare(x, y-.04, .02);
		StdDraw.filledSquare(x, y-.08, .02);
		StdDraw.filledSquare(x, y-.12, .02);		
		
		this.y = this.y - this.movePerCall;
	}

	@Override
	public boolean isDone() {
		if (this.y <= Game.cellRadius*7){ 
			return true;
		}
		else {
			return false;
		}
	}
	
	

}
