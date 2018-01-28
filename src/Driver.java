import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.princeton.cs.introcs.StdDraw;

public class Driver {
	
	public static void main (String[] args) {
		
		
		 JFrame frame = new JFrame("Test");
		 frame.setVisible(true);
		 frame.setSize(500, 500);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 JPanel panel = new JPanel();
		 frame.add(panel);
		 JButton button = new JButton("Hello");
		 panel.add(button);
		 button.addActionListener (new ActionListen());
		 
		 
		 Logic L = new Logic (5, 5, 0.2);
		 
		 L.newGame();
		 L.printGame();
		
		
		
	}
	
	public static class ActionListen implements ActionListener {
		 public void actionPerformed (ActionEvent e) {     
			 System.out.println(e.getActionCommand());
		 }
		
		
	}

}
