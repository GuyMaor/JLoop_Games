/** Jloop controls the continuous loop of the game, retrieves the images, and implements the abstract methods for the Applet
 * @author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-29-13
 */


import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URL;



public abstract class JLoop extends Applet  implements Runnable, KeyListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	private URL url;
	private Image offscreen;
	private Graphics g2;
	private int screenWidth;
	private int screenHeight;
	private static final int WIDTH_SCREEN = 300;
	private static final int HEIGHT_SCREEN = 600;
	
	/*Instantiates all objects and instance variables in the game
	 */
	public void init()
	{
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}
		setScreenSize(WIDTH_SCREEN,HEIGHT_SCREEN);
		settings();
		setSize(screenWidth,screenHeight);
		Thread th = new Thread(this);
		th.start();	
		offscreen = createImage(screenWidth,screenHeight);
		g2 = offscreen.getGraphics();
		addKeyListener(this);
		addMouseListener(this);
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}
	}
	
	/*Runs the loop for the game
	 */
	public void run()
	{
		while(true)
		{
			loop();
			repaint();
			pause();
		}
	}
	
	/*Sets the screen size
	 *@param width the width of the screen
	 *@param height the height of the screen
	 */
	private void setScreenSize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
	}
	
	/*Pauses the game loop
	 */
	private void pause()
	{
		try
		{
		Thread.sleep(15);
		}
		catch(InterruptedException exc)
		{
			System.out.println("Cannot sleep");
		}			
	}	
	
	/*Instantiates the objects of the given class
	 */
	public abstract void settings();
	
	/*Represents the loop for the game
	 */
	public abstract void loop();
	
	/*Draws the objects in the game
	 */	
	public abstract void art(Graphics g);	
		
	/*Retrieves the images
	 *@param str the image to be retrieved
	 */
	public Image retrieveImage(String str)
	{
		return getImage(url,str);
	}
	
	/*Paints the objects in the game
	 */
	public void paint(Graphics g)
	{
		art(g2);
		g.drawImage(offscreen,0,0,this);
	}
	
	/*Updates the objects in the game
	 */
	public void update(Graphics g){paint(g);}
	
	/*Determines what happens if a key is pressed
	 *@param e the key that is pressed
	 */
	public void keyTyped(KeyEvent e){}
	
	/*Determines what happens if the mouse is clicked
	 *@param e the mouse
	 */
	public void mouseClicked(MouseEvent e) {}
	
	/*Determines what happens if the mouse enters
	 *@param e the mouse
	 */
	public void mouseEntered(MouseEvent e) {}
	
	/*Determines what happens if the mouse exits
	 *@param e the mouse
	 */
	public void mouseExited(MouseEvent e) {}
	
	/*Determines what happens if the mouse is pressed
	 *@param e the mouse
	 */
	public void mousePressed(MouseEvent e) {}
}