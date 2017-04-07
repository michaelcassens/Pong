package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainPanel extends JPanel implements KeyListener, Runnable {

	ImageIcon greenBar = new ImageIcon("./src/images/Green.png");
	ImageIcon orangeBar = new ImageIcon("./src/images/Orange.png");
	ImageIcon ball = new ImageIcon("./src/images/ball.png");
	Timer myTimer = new Timer(1000/60, new timerListener());
	int greenY = 50;
	int orangeY = 50;
	final int HEIGHT = 1536;
	final int WIDTH = 2048;
	int ballX = 1000;
	int ballY = 500;
	int ballVelocity = 5;
	int orangeX = 1950;
	int greenX = 50;
	int barWidth = 50;
	int barHeight = 200;
	int ballWidth = 33;
	int ballHeight = 33;
	int direction = 1;
	public enum currentGreenDirection {Up, Down};
	public enum currentOrangeDirection {Up, Down};
	currentOrangeDirection myOrangeDirection;
	currentGreenDirection myGreenDirection;
	int changeInBallY = 0;
	boolean isUpPressed = false, 
			isDownPressed = false, 
			isWPressed = false, 
			isSPressed = false;
	int deltaY = 1;
	public MainPanel()
	{
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		setBackground(Color.BLUE);
		addKeyListener(this);
		setFocusable(true);
		myTimer.start();
		 new Thread(this).start();
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		
		page.drawImage(greenBar.getImage(), greenX,greenY, null);
		page.drawImage(orangeBar.getImage(), orangeX,orangeY, null);
		page.drawImage(ball.getImage(), ballX, ballY, null);
		
		page.setFont(new Font("Arial", Font.BOLD, 80));
		if(ballX < 0)
		{
			page.drawString("Orange wins!", WIDTH/2-200, HEIGHT/2);
			myTimer.stop();
		}
		else if(ballX > WIDTH)
		{
			page.drawString("Green wins!", WIDTH/2-200, HEIGHT/2);
			myTimer.stop();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode())
		{
		    case KeyEvent.VK_UP: 
		    	isUpPressed = true; 
		    	myOrangeDirection = currentOrangeDirection.Up;
		    	break;
		    case KeyEvent.VK_DOWN: 
		    	isDownPressed = true; 
		    	myOrangeDirection = currentOrangeDirection.Down;
		    	break;
		    case KeyEvent.VK_W:
		    	myGreenDirection = currentGreenDirection.Up;
		    	isWPressed = true; 
		    	break;
		    case KeyEvent.VK_S: 
		    	isSPressed = true; 
		    	myGreenDirection = currentGreenDirection.Down;
		    	break;
		}
		
	//	repaint();

		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode())
		{
		    case KeyEvent.VK_UP: 
		    	isUpPressed = false;
		    	
		    break;
		    case KeyEvent.VK_DOWN: 
		    	isDownPressed = false; 
		    	break;
		    case KeyEvent.VK_W: isWPressed = false; break;
		    case KeyEvent.VK_S: isSPressed = false; break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class timerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			collidesWithOrangeBar();
			collidesWithGreenBar();
			ballX+=5 * direction;
			ballY+=changeInBallY * deltaY;
			if((ballY+33 > HEIGHT &&  ballX+33 >= 0 )|| (ballY <=0 && ballX <= WIDTH))
			{
				deltaY*=-1;
			}
			
			repaint();
		}
		
	}
	
	public void collidesWithOrangeBar()
	{
		int topLeftX1 = orangeX;
		int bottomRightX1 = orangeX + barWidth;
		int topLeftY1 = orangeY;
		int bottomRightY1 = orangeY + barHeight;
		
		int topLeftX = ballX;
		int bottomRightX = ballX + ballWidth;
		int topLeftY = ballY;
		int bottomRightY = ballY + ballHeight;
		
		if (areRectsColliding(topLeftX, bottomRightX, topLeftY,
				bottomRightY, topLeftX1, bottomRightX1, topLeftY1,
				bottomRightY1)) 
		{
			direction *=-1;
			
			if(myOrangeDirection == currentOrangeDirection.Up)
			{
				changeInBallY+=1;
			}
			if(myOrangeDirection == currentOrangeDirection.Down)
			{
				changeInBallY-=1;
				
			}
			
		}
		
		
	}
	
	
	public void collidesWithGreenBar()
	{
		int topLeftX1 = greenX;
		int bottomRightX1 = greenX + barWidth;
		int topLeftY1 = greenY;
		int bottomRightY1 = greenY + barHeight;
		
		int topLeftX = ballX;
		int bottomRightX = ballX + ballWidth;
		int topLeftY = ballY;
		int bottomRightY = ballY + ballHeight;
		
		if (areRectsColliding(topLeftX, bottomRightX, topLeftY,
				bottomRightY, topLeftX1, bottomRightX1, topLeftY1,
				bottomRightY1)) 
		{
			direction *=-1;
			if(myGreenDirection == currentGreenDirection.Up)
			{
				changeInBallY+=1;
			}
			if(myGreenDirection == currentGreenDirection.Down)
			{
				changeInBallY-=1;
			}
		}
		
		
	}
	
	private boolean areRectsColliding(int r1TopLeftX, int r1BottomRightX,
			int r1TopLeftY, int r1BottomRightY, int r2TopLeftX,
			int r2BottomRightX, int r2TopLeftY, int r2BottomRightY) {

		if (r1TopLeftX < r2BottomRightX && r1BottomRightX > r2TopLeftX
				&& r1TopLeftY < r2BottomRightY && r1BottomRightY > r2TopLeftY) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void run() {
		 while(true) {
	            try {
	            	if(isUpPressed)
	            	{
	            		if(orangeY >=10)
	        			{
	        				orangeY-=10;
	        			}
	            	}
	            	if(isDownPressed)
	            	{
	            		if(orangeY + 200 <= HEIGHT)
	        			{
	        				orangeY+=10;
	        			}	
	            		
	            	}
	            	if(isWPressed)
	            	{
	            		if(greenY >=10)
	        			{
	        				greenY-=10;
	        			}
	            	}
	            	if(isSPressed)
	            	{
	            		if(greenY + 200 <= HEIGHT)
	        			{
	        				greenY+=10;
	        			}
	            	}

	                Thread.sleep(20);
	                repaint();
	            } catch(Exception exc) {
	                exc.printStackTrace();
	                break;
	            }
	        }
		
	}
}
