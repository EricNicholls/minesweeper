import java.util.Random;

public class Logic {

	private int [][] arr;
	private double difficulty;
	private Random rn = new Random ();
	
	public Logic (int r, int c, double diffPerc) {
		arr = new int [r][c];
		difficulty = diffPerc;
	}
	
	public void newGame () {
		for (int row = 0; row < arr.length; row++) {
			 for (int col = 0; col < arr.length; col++) {
				 // bomb
				 if (rn.nextDouble() < difficulty) {
					 arr [row][col] = -1;
				 }
					 
			}
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
	
	public int checkBombNeighbors (int [][] a, int r, int c) {
		int neighbors = 0;
		
		System.out.println("(" + r + ", " + c +")");
		// topLeft
		if (r-1 >= 0 && c -1 >= 0) {
			if (a[r-1][c-1] == -1) {
				System.out.println("tL");
				neighbors ++;
			}
		}
		
		//topCenter
		if (r-1>=0) {
			if (a[r-1][c] == - 1) {
				System.out.println("tC");
				neighbors ++;
			}
		}
		
		//topRight
		if (r-1 >=0 && c+1 < a.length) {
			if (a[r-1][c+1] == -1) {
				System.out.println("tR");
				neighbors ++;
			}
		}
		
		// midLeft
		if (c-1 >= 0) {
			if (a[r][c-1] == -1) {
				System.out.println("mL");
				neighbors ++;
			}
		}
		
		// midRight
		if (c+1 < a.length) {
			if (a[r][c+1] == -1) {
				System.out.println("tL");
				neighbors ++;
			}
		}
		
		// bottomLeft
		if (r+1 < a[0].length && c-1>=0) {
			if (a[r+1][c-1] == -1) {
				System.out.println("bL");
				neighbors ++;
			}
		}
		
		//bottomCenter
		if (r+1 < a.length) {
			System.out.println(r+1 + ", " + c);
			System.out.println(a[r+1][c]);
			if (a[r+1][c] == -1) {
				System.out.println("bC");
				neighbors ++;
			}
		}
		
		//bottomRight
		if (r+1 < a[0].length && c+1 < a.length) {
			if (a[r+1][c+1] == -1) {
				System.out.println("bR");
				neighbors ++;
			}
		}
		return neighbors;
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
	
	public void drawGame () {
		
	}
	
	
	
}
