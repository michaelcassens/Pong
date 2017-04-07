package Main;
/*
 * Michael Cassens
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

public class Driver {

	/**
	 * This is where the program starts
	 */
	static final int HEIGHT = 2048;
	static final int WIDTH = 1536;
	public static void main(String[] args) {

		// create the frame
		JFrame myFrame = new JFrame("Pong");
		centreWindow(myFrame);
		// set up the close operation
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// create panel
		MainPanel myPanel = new MainPanel();
		// add panel
		myFrame.getContentPane().add(myPanel);
		// pack
		myFrame.pack();
		// set visibility to true
		myFrame.setVisible(true);
	}
	
	
	public static void centreWindow(Window frame) 
	{
		
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - WIDTH)/ 2 - 250);
	    int y = (int) ((dimension.getHeight() - HEIGHT) / 2 + 100);
	    frame.setLocation(x, y);
	    
	}
	
}
