


import edu.princeton.cs.introcs.StdDraw;

public class Driver{
	
	public static void main (String[] args) {
		
		int col = 20;
		int row = 20;
		int numBombos = (row*col)/10;
		Logic L = new Logic (row, col, numBombos);
		
		L.setCanvas(1000, 1000);
	 
		L.initGame();
		
		while (true) {
			if (L.getLifeStatus() == true) {
				L.play();
			}
		}
		
	}
	

}
