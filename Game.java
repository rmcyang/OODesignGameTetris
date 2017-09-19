package lab12;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import sedgewick.StdDraw;

public class Game {	
	// The background that consists of square cells.
	private background bg = new background();
	
	// The information block board.
	private InfoBlock infoblock = new InfoBlock(0, 120);
	
	private final int showPauseTime = 50;
	
	// Current time of mseconds elapsed.
	private int clockmsec = 0;
	
	// Radius of one unit cell.
	public final static double cellRadius = .02;
	
	public static void main(String[] args) {
		
		new Game().start();
	}
	
	public void start() {
		
		List<Anim> scene1 = new LinkedList<Anim>();	
		
		scene1.add(bg);
		scene1.add(infoblock);
		
		Shape sp = getNewShape();
		Shape sp_next = getNewShape();
		
		this.infoblock.setNextShape(sp_next);
		
		Anim am = (Anim)sp;
		scene1.add(am);

		while (true) {
			// clear all
			StdDraw.clear();
			
			for (Anim a : scene1) {
				if (a == am) {
					if (!am.isDone() && !sp.checkCollisionY(bg)) {
						char key = ' ';
						if(StdDraw.hasNextKeyTyped()) {
							key = StdDraw.nextKeyTyped();
						}
						if (key == 'a' && !sp.checkCollisionX(bg, true)) {
							sp.left();
						}
						else if (key == 'd' && !sp.checkCollisionX(bg, false)) {
							sp.right();
						}
						am.draw();
					}
				}
				else {
					a.draw();
				}
			}			
			
			if (am.isDone() || sp.checkCollisionY(bg)) {
				
				sp.addToBackground(bg);
				int n = bg.tryRemoveRow();
				if (n > 0) this.infoblock.addScore(n*10);
								
				scene1.remove(am);
				sp = sp_next;
				sp_next = getNewShape();
				this.infoblock.setNextShape(sp_next);
				am = (Anim)sp;
				scene1.add(am);
			}
			
			if (this.clockmsec >= 1000) { // Clock ticks.
				this.infoblock.clockTick();
				this.clockmsec = 0;
			}
			
			if(this.isGameStopped()) {
				break;
			}		
			
			StdDraw.show(showPauseTime);
			this.clockmsec += this.showPauseTime;
		}				
		
	}
	
	/**
	 * Check if one of the three stop conditions meets. And if yes, show 'Game Over!'.
	 * @return true if the game is stopped, otherwise false.
	 */
	public boolean isGameStopped() {
		if (this.infoblock.getSecondsLeft() < 0 || this.bg.getMaxHeight()==this.bg.cols-1 || 
				this.infoblock.getScore()>=100) {
			
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.text(0.5, 0.5, "Game Over!");
			StdDraw.show();
			return true;
		}
		else {
			return false;
		}
	}
	

	/**
	 * Randomly generate a new shape (i.e. lShape or mShape).
	 * @return the newly created shape.
	 */
	public Shape getNewShape() {
		double k = Math.random();
		Shape sp;
		if (k > 0.5) {
			sp = new lShape(0.5, 5000, showPauseTime);
		}
		else {
			sp = new mShape(0.5, 5000, showPauseTime);
		}
		return sp;
	}
	
	/**
	 * Generates a random Color.
	 * @return a Color with a random amount of red, green, blue.
	 */
	public static Color genRandomColor() {
		Random r = new Random();
		
		int red = r.nextInt(230)+10;
		int green = r.nextInt(230)+10;
		int blue = r.nextInt(230)+10;
		return new Color(red, green, blue);
	}
}
