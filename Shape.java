package lab12;

import java.awt.Color;

public abstract class Shape implements Anim{
	
	// Coordinates of shape
	protected double x, y;
	// Color of shape
	protected Color color;
	/**
	 * Move left
	 */
	public abstract void left();
	/**
	 * Move right
	 */
	public abstract void right();
	
	/**
	 * Check collisions at Y direction: down.
	 * @param bg
	 * @return true if there is any collision.
	 */
	public abstract boolean checkCollisionY(background bg);
	/**
	 * Check collisions at X direction: left or right.
	 * @param bg the background that contains information of cells the board.
	 * @return true if there is any collision.
	 */
	public abstract boolean checkCollisionX(background bg, boolean left);
	
	/**
	 * Add the shape onto the background.
	 * @param bg the background that contains information of cells the board.
	 */
	public abstract void addToBackground(background bg);
	
	
	public double getX(){
		 return x;
	}
	public double getY(){
		return y;
	}
	
	public void setX(double v){
		 this.x = v;;
	}
	public void setY(double v){
		this.y = v;
	}
	
	
	public Color getColor(){
		return color;
	}
}
