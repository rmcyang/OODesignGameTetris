package lab12;

import java.awt.Color;
import sedgewick.StdDraw;

public class background implements Anim {
	
	// A 25x25-cell board. cell (0, 0) is at the lower left corner.
	public final  int rows = (int)(1./(Game.cellRadius*2));
	public final  int cols = (int)(1./(Game.cellRadius*2));
	
	
	// the cell is occupied?
	private boolean[][] coord_occupied = new boolean[rows][cols];
	
	// color of the cell
	private Color[][]   coord_colors   = new Color[rows][cols];
	
	private boolean isDone = false;
	
	
	
	public background() {
		StdDraw.setXscale(.0, 1.0);
		StdDraw.setYscale(.0, 1.16);
		this.reset();
	}
	
	public void reset() {
		for (int i = 0; i < this.rows; ++i){
			for (int j = 0; j < this.cols; j++){
				coord_occupied[i][j] = false;
				this.coord_colors[i][j] = Color.BLACK;
			}
		}
	}
	
	public boolean getCellCondition(int row, int col) {
		if (row < 0 || row > this.rows || col < 0 || col > this.cols) {
			throw new IllegalArgumentException("row or col is illegal.");
		}
			
		return this.coord_occupied[row][col];
	}
	
	public int tryRemoveRow() {
		//System.out.println("try remove row");
		int rem_rows = 0;
		// cols: y, rows: x
		for (int j = 0; j < this.cols; j++) {
			int sum = 0;
			for(int i = 0; i < this.rows; i++){
				sum = sum + (this.coord_occupied[i][j]? 1 : 0);
			}
			//System.out.println("sum = " +sum);
			// 25 cells in the row j, thus it is full.
			// remove row j
			if(sum == cols){
				System.out.println("Remove row "+j);
				
				if (j == this.cols-1) { // the top row
					for (int k = 0; k < rows; k++) {
						this.coord_occupied[k][j] = false;
						this.coord_colors[k][j] = Color.BLACK;
					}
				}
				else {
					for (int m = j; m < this.cols-1; ++m) {
						for (int k = 0; k < this.rows; k++) {
							this.coord_occupied[k][m] = this.coord_occupied[k][m+1];
							this.coord_colors[k][m] = this.coord_colors[k][m+1];
						}
					}
					j--;
				}
				rem_rows++;
			}
		}		
		return rem_rows;
	}
	
	public int getMaxHeight() {
		for (int i = this.cols-1; i >= 0; i--) // check row by row
			for (int j = 0; j < this.rows; j++) {
				if (this.coord_occupied[j][i]) {
					return i;
				}
			}
		return 0;
	}
	
	public void setCoord(int i, int j, Color color) {
		this.coord_occupied[i][j] = true;
		this.coord_colors[i][j] = color;
		
	}
	
	public void setIsDone() {
		isDone = true;
	}
	
	
	@Override
	public void draw() {
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledSquare(0.5, 0.5, 0.5);  // all black
		
		// Draw cells
		for (int i = 0; i < this.rows; ++i){
			for (int j = 0; j < this.cols; j++){
				if(this.coord_occupied[i][j]){
					StdDraw.setPenColor(this.coord_colors[i][j]);
					StdDraw.filledSquare((i*2+1)*Game.cellRadius, (j*2+1)*Game.cellRadius, Game.cellRadius);
				}
			}
		}
	}
	
	@Override
	public boolean isDone() {
		return isDone;
	}

}
