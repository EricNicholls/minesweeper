import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class Logic {

	private int [][] arr;
	private int [][] visible;
	private Random rn = new Random ();
	private int pressCounter = 0;
	private boolean alive = true;
	private int numBomb;
	private int bombsRem;
	
	private ArrayList <Point> zeroesCalled;
	
	public Logic (int r, int c, int bNum) {
		arr = new int [r][c];
		visible = new int [r][c];
		numBomb = bNum;
		bombsRem = bNum;
	}
	
	
	public void play () {
		if (StdDraw.mousePressed() == true && pressCounter == 0) {
			pressCounter = 1;
			int iX = getRow(StdDraw.mouseX());
			int iY = getCol(StdDraw.mouseY());
			
			System.out.println(iX + ", " + iY);
			if (StdDraw.isKeyPressed(KeyEvent.VK_SHIFT)) {
				System.out.println("REACHED 1");
				if (visible [iX][iY] == -1) {
					System.out.println("REACHED 2");
					visible [iX][iY] = 0;
					bombsRem ++;
					System.out.println("Bombs Left: " + bombsRem);
				}
				else if (visible [iX][iY] == 0){
					visible [iX][iY] = -1;
					bombsRem --;
					System.out.println("Bombs Left: " + bombsRem);
				}
				
				return;
			}
			
			if (arr[iX][iY] == 0) {
				zeroesCalled = new ArrayList <>();
				zeroLogic(iX, iY);
			}
			
			if (arr[iX][iY] == -1) {
				System.out.println("Game Over");
				gameOverLost (iX, iY);
				return;
			}
			if (getWinStatus()) {
				System.out.println("Yoou Win");
				gameOverWon();
				return;
			}
			
			visible [iX][iY] = 1;
			
			
		}
		if (StdDraw.mousePressed() == false) {
			pressCounter = 0;
		}
		
		drawGame();
		StdDraw.show(20);
	}
	
	public boolean getLifeStatus() {
		return alive;
	}
	
	private int getRow (double mouseX) {
		return (int) (mouseX / ((double)100/arr.length));
	}
	private int getCol (double mouseY) {
		return (int) (mouseY / ((double)100/arr[0].length));
	}
	
	private void zeroLogic (int x, int y) {
		
		for (int i = 0; i < zeroesCalled.size(); ++i) {
			if (zeroesCalled.get(i).equals(new Point (x,y))) {
				return;
			}
		}
		zeroesCalled.add(new Point (x, y ));
		
		int iMin = -1;
		int iMax = 1;
		int jMin = -1;
		int jMax = 1;
		
		if (x == 0) { iMin ++; }
		if (x == arr.length - 1) { iMax --; }
		if (y == 0) { jMin ++; }
		if (y == arr[0].length - 1) { jMax --; }
		
		for (int i = iMin; i <= iMax; ++ i) {
			for (int j = jMin; j <= jMax; ++j) {
				visible [x + i][y + j] = 1;
				if (arr[x+i][y+j] == 0 && !(i == 0 && j == 0)) {
					zeroLogic (x+i, y+j);
				}
			}
		}
	}
	
	private int checkBombNeighbors (int [][] a, int r, int c) {
		int neighbors = 0;
		
		// topLeft
		if (r-1 >= 0 && c -1 >= 0) {
			if (a[r-1][c-1] == -1) {
				neighbors ++;
			}
		}
		
		//topCenter
		if (r-1>=0) {
			if (a[r-1][c] == - 1) {
				neighbors ++;
			}
		}
		
		//topRight
		if (r-1 >=0 && c+1 < a.length) {
			if (a[r-1][c+1] == -1) {
				neighbors ++;
			}
		}
		
		// midLeft
		if (c-1 >= 0) {
			if (a[r][c-1] == -1) {
				neighbors ++;
			}
		}
		
		// midRight
		if (c+1 < a.length) {
			if (a[r][c+1] == -1) {
				neighbors ++;
			}
		}
		
		// bottomLeft
		if (r+1 < a[0].length && c-1>=0) {
			if (a[r+1][c-1] == -1) {
				neighbors ++;
			}
		}
		
		//bottomCenter
		if (r+1 < a.length) {
			if (a[r+1][c] == -1) {
				neighbors ++;
			}
		}
		
		//bottomRight
		if (r+1 < a[0].length && c+1 < a.length) {
			if (a[r+1][c+1] == -1) {
				neighbors ++;
			}
		}
		return neighbors;
	}
	
	public void setCanvas (int x, int y) {
		
		StdDraw.setCanvasSize(x, y);
		StdDraw.setXscale(0, 100);
		StdDraw.setYscale(100, 0);
	}
	public void newGame () {
		bombsRem = numBomb;
		System.out.println("Bombs Left: " + bombsRem);
		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr[0].length; j++) {
				arr [i][j] = 0;
			}
		}
		for (int i = 0; i < visible.length; ++i) {
			for (int j = 0; j < visible[0].length; j++) {
				visible [i][j] = 0;								// 0 for normal, 1 for all visible
			}
		}
		ArrayList <Point> noCopies = new ArrayList <> ();
		for (int i = 0; i < numBomb; ++i) {
			
			int randX = rn.nextInt(arr.length);
			int randY = rn.nextInt(arr[0].length);
			
			for (int j = 0; j < noCopies.size(); j++) {
				if (noCopies.get(j).equals(new Point(randX,randY))) {
					randX = rn.nextInt(arr.length);
					randY = rn.nextInt(arr[0].length);
					j=0;
				}
			}
			arr [randX][randY] = -1;
		}
				
				 
		for (int row2 = 0; row2 < arr.length; row2++) {
			 for (int col2 = 0; col2 < arr.length; col2++) {
				 // bomb
				 if (arr [row2][col2] != -1) {
					 arr [row2][col2] = checkBombNeighbors (arr, row2, col2);
				 }
			}
		}
		
	}
	public void initGame () {
		newGame();
		StdDraw.setPenRadius();
		 for (int a = 0; a < arr.length; ++a) {
			 for (int b = 0; b < arr[a].length; ++b) {
				 Color c = StdDraw.GRAY;
				 
				 StdDraw.setPenColor(c);
				 double len = 100/(double)arr.length;
				 StdDraw.filledRectangle(a*len+len/2, b*len+len/2, len/2, len/2);
				 
				 StdDraw.setPenColor(new Color (200,200,200));
				 StdDraw.rectangle(a*len+len/2, b*len+len/2, len/2, len/2);
			 }
		 }
	}
	public void drawGame () {
		 for (int a = 0; a < arr.length; ++a) {
			 for (int b = 0; b < arr[a].length; ++b) {
				 double len = 100/(double)arr.length;
				 StdDraw.setPenColor(StdDraw.WHITE);
				
				 if (visible [a][b] == 1 && arr[a][b] != 0) {		// if its revealed & if its not 0
					 StdDraw.text(a*len+len/2, b*len+len/2, Integer.toString(arr[a][b]));
					 StdDraw.setPenColor(140,140,140);
					 StdDraw.rectangle(a*len+len/2, b*len+len/2, len/2, len/2);
				 }
				 else if (visible [a][b] == 1 && arr[a][b] == 0) {
					 StdDraw.setPenColor(new Color (120,120,120));
					 StdDraw.filledRectangle(a*len+len/2, b*len+len/2, len/2, len/2);
					 StdDraw.setPenColor();
					 StdDraw.rectangle(a*len+len/2, b*len+len/2, len/2, len/2);
				 }
				 
				 if (visible[a][b] == -1) {							// Its a flag!
					 StdDraw.setPenColor(StdDraw.RED);
					 StdDraw.filledCircle (a*len+len/2, b*len+len/2, .5);
				 }
				 if (visible[a][b] == 0) {
					 Color c = StdDraw.GRAY;
					 
					 StdDraw.setPenColor(c);
					 StdDraw.filledCircle (a*len+len/2, b*len+len/2, .5);
				 }
				 
			 }
		 }
	}
	
	public boolean getWinStatus () {
		boolean win = true;
		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != -1 && visible [i][j] == 0) {
					win = false;
				}
			}
		}
		return win;
	}
	
	public void gameOverWon () {
		alive = false;
		
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(50, 50, "YOU WON!!! (Space to Play Again)");
		StdDraw.show(20);
		while (true) {
			StdDraw.setPenColor(StdDraw.BLUE);
			for (int i = 0; i < 140; i+=5) {
				StdDraw.filledCircle(50, 50, i);
				StdDraw.show(20);
			}
			if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
				initGame();
				alive = true;
				return;
			}
		}
	}
	
	public void gameOverLost (int x, int y) {
		alive = false;
	
		StdDraw.setPenColor(StdDraw.RED);
		for (int i = 0; i < 140; i+=5) {
			StdDraw.filledCircle(x*((double)100/arr.length), y*((double)100/arr[0].length), i);
			StdDraw.show(20);
		}
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(50, 50, "Click Space to Play Again");
		StdDraw.show(20);
		while (true) {
			if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
				initGame();
				alive = true;
				return;
			}
		}
	}


	public void printGame () {
		for (int row = 0; row < arr.length; row++) {
			 for (int col = 0; col < arr.length; col++) {
				 if (arr [row][col] == -1) {
					 System.out.print("b ");
				 }
				 else
				 {
					 System.out.print(arr [row][col] + " ");
				 }
			 }
			 System.out.println();
		}
	}
	
	
	
	
}
