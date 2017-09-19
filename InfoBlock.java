package lab12;

import java.awt.Color;
import sedgewick.StdDraw;

/**
 * The information block consists of time left, next shape and score.
 *
 */
public class InfoBlock implements Anim {
	
	private int scorenum = 0;
	
	private int stopsec = 60;
	
	private Shape nextshape;
	
	public InfoBlock() {
		
	}
	
	public InfoBlock(int score, int stopsec) {
		this.scorenum = score;
		this.stopsec = stopsec;
	}
	
	public void clockTick() {
		this.stopsec--;
	}
	
	public int getSecondsLeft() {
		return this.stopsec;
	}
	public int getScore() {
		return this.scorenum;
	}
	public void addScore(int v) {
		this.scorenum += v;
	}
	
	public void setNextShape(Shape sp) {
		this.nextshape = sp;
	}
	
	@Override
	public void draw() {
		StdDraw.setPenColor(Color.RED);
		StdDraw.rectangle(0.5, 1.08, 0.5, 0.07);
		
		// draw time left
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(Game.cellRadius*7, 1.16-Game.cellRadius*4, "Time left: "+this.stopsec);
		
		// draw next shape
		double prex = this.nextshape.getX(), prey = this.nextshape.getY();
		this.nextshape.setX(0.5);
		if (this.nextshape instanceof lShape) {
			this.nextshape.setY(1.16-Game.cellRadius);
		}
		else if (this.nextshape instanceof mShape) {
			this.nextshape.setY(1.16-Game.cellRadius*3);
		}
		this.nextshape.draw();
		this.nextshape.setX(prex);
		this.nextshape.setY(prey);
		
		// draw score
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(1.-Game.cellRadius*5, 1.16-Game.cellRadius*4, "Score: "+this.scorenum);
	}
	
	@Override
	public boolean isDone()
	{
		return false;
	}
}
