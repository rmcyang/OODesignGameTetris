package lab12;

import java.util.List;

import sedgewick.StdDraw;

/**
 * Draw a shape like following:
 */
public class mShape extends Shape {
	
	private double movePerCall;
	
	public mShape(double x, int msecs, int showPauseTime) {
		this.x = x;
		this.y = 1 - Game.cellRadius;
		this.color = Game.genRandomColor();
		
		int numCallsToThisMethod = msecs / showPauseTime;
		this.movePerCall = 1.0 / numCallsToThisMethod;
		
	}
	@Override
	public void right() {
		if(Math.abs(x - 1.+3*Game.cellRadius) > 1e-6){
			x = x + Game.cellRadius*2;
		}
	}
	@Override
	public void left() {
		if(Math.abs(x - Game.cellRadius*3) > 1e-6){
			x = x - 2*Game.cellRadius;
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
					double nx = (i*2+1)*Game.cellRadius, ny = (j*2+3)*Game.cellRadius;
					
					if ((Math.abs(this.x - nx) < 1e-6 || 
						Math.abs(this.x-Game.cellRadius*2 - nx) < 1e-6 || 
						Math.abs(this.x+Game.cellRadius*2 - nx) < 1e-6)
						&& Math.abs(this.y-Game.cellRadius*2 - ny) < 1e-6)
				    {
						System.out.println("m collides at Y direction at "+i+", " + j);
						return true;
					}
				}
			}
		}
		return false;
	}
	@Override
	public boolean checkCollisionX(background bg, boolean left) {
		int k = left ? 1 : -1;
		for (int i = 0; i < bg.rows; i++) {
			for (int j = 0; j < bg.cols; j++) {
				if (bg.getCellCondition(i, j)) {					
					
					if ((Math.abs(this.x - (i*2+1+k*2)*Game.cellRadius) < 1e-6 && Math.abs(this.y - (j*2+1)*Game.cellRadius) < Game.cellRadius)
						|| (Math.abs(this.x-(i*2+1+2*k*2)*Game.cellRadius) < 1e-6 && Math.abs(this.y-(j*2+1+2)*Game.cellRadius) < Game.cellRadius)
						  ) {
						System.out.println("m collides at X direction at "+i+", " + j);
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
		int n_col = ((int)Math.ceil((this.y / (Game.cellRadius))) - 1)/2;
		
		System.out.println("m added to background: "+n_row+", " + n_col);
		
		bg.setCoord(n_row, n_col, this.color);
		bg.setCoord(n_row, n_col-1, this.color);
		bg.setCoord(n_row-1, n_col-1, this.color);
		bg.setCoord(n_row+1, n_col-1, this.color);
		
	}

	@Override
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(x, y, Game.cellRadius);
		StdDraw.filledSquare(x+Game.cellRadius*2, y-Game.cellRadius*2, Game.cellRadius);
		StdDraw.filledSquare(x-Game.cellRadius*2, y-Game.cellRadius*2, Game.cellRadius);
		StdDraw.filledSquare(x, y-Game.cellRadius*2, Game.cellRadius);
		this.y = this.y - movePerCall;
	}

	@Override
	public boolean isDone() {
		if (this.y <= 3*Game.cellRadius) { 
			return true;
		}
		else {
			return false;
		}
	}

}
