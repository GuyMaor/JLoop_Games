
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URL;

public abstract class JLoop extends Applet  implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	private static final long serialVersionUID = 1L;
	URL url;
	private Image offscreen;
	private Graphics g2;
	private int screenWidth;
	private int screenHeight;
	private int pauseTime;
	private JLCamera camera;	
	private boolean[] ASCII;
	private Point clickPoint;
	public static final int KEY_BACKSPACE = 8;
	public static final int KEY_ENTER = 10;
	public static final int KEY_SHIFT = 16;
	public static final int KEY_CTRL = 17;
	public static final int KEY_ALT = 18;
	public static final int KEY_CAPSLOCK = 20;	
	public static final int KEY_SPACE = 32;
	public static final int KEY_LEFT = 37;		
	public static final int KEY_UP = 38;
	public static final int KEY_RIGHT = 39;
	public static final int KEY_DOWN = 40;
	public static final int KEY_DASH = 45;		
	public static final int KEY_0 = 48;
	public static final int KEY_1 = 49;
	public static final int KEY_2 = 50;
	public static final int KEY_3 = 51;
	public static final int KEY_4 = 52;
	public static final int KEY_5 = 53;
	public static final int KEY_6 = 54;
	public static final int KEY_7 = 55;
	public static final int KEY_8 = 56;
	public static final int KEY_9 = 57;
	public static final int KEY_EQUALS = 61;
	public static final int KEY_A = 65;
	public static final int KEY_B = 66;
	public static final int KEY_C = 67;
	public static final int KEY_D = 68;
	public static final int KEY_E = 69;
	public static final int KEY_F = 70;
	public static final int KEY_G = 71;
	public static final int KEY_H = 72;
	public static final int KEY_I = 73;
	public static final int KEY_J = 74;
	public static final int KEY_K = 75;
	public static final int KEY_L = 76;
	public static final int KEY_M = 77;
	public static final int KEY_N = 78;
	public static final int KEY_O = 79;
	public static final int KEY_P = 80;
	public static final int KEY_Q = 81;
	public static final int KEY_R = 82;
	public static final int KEY_S = 83;
	public static final int KEY_T = 84;
	public static final int KEY_U = 85;
	public static final int KEY_V = 86;
	public static final int KEY_W = 87;
	public static final int KEY_Y = 88;
	public static final int KEY_X = 89;
	public static final int KEY_Z = 90;

	/**
	 * determains what's in the applet.
	 */
	public void init()
	{
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}
		camera = null;
		ASCII = new boolean[256];
		clickPoint = null;
		setScreenSize(100,100);
		setPauseTime(40);
		settings();
		setSize(screenWidth,screenHeight);
		Thread th = new Thread(this);
		th.start();	
		offscreen = createImage(screenWidth,screenHeight);
		g2 = offscreen.getGraphics();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}
	}
	/**
	 * starts the loop.
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
	/**
	 * sets a camera
	 * @param the camera to be set.
	 */
	public void setCamera(JLCamera jLC)
	{
		camera = jLC;
	}
	/**
	 * sets the size of the screen
	 * @param the width of the screen
	 * @param the height of the screen
	 */
	public void setScreenSize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
	}
	/**
	 * sets the duration of pause in milliseconds
	 * @param the duration of pause
	 */
	public void setPauseTime(int t)
	{
		pauseTime = t;
	}
	/**
	 * pauses
	 */
	private void pause()
	{
		try
		{
		Thread.sleep(pauseTime);
		}
		catch(InterruptedException exc)
		{
			System.out.println("Cannot sleep");
		}			
	}
	/**
	 * Instantiates everything and sets up the Applet.
	 */
	public abstract void settings();
	/**
	 * changes variables each duration.
	 */
	public abstract void loop();
	/**
	 * paints what is going to be on the screen.
	 * @param the graphics to paint with.
	 */
	public abstract void art(Graphics g);
	/**
	 * paints an object that is visible in the
	 * camera if there is one.
	 * @param the graphic to paint with.
	 * @param an image to paint.
	 * @param the object to base the location
	 * and size of the image.
	 */
	public void paintObject(Graphics g, Image i, GameObject gO)
	{
		if(camera==null)
			g.drawImage(i,(int)gO.getX(),(int)gO.getY(),(int)gO.getWidth(),(int)gO.getHeight(),this);
		else if(gO.didCollide(camera))
		{
			double infRatio = camera.getInflationRatio();
			g.drawImage(i,
			(int)((gO.getX()-camera.getX())/infRatio),
			(int)((gO.getY()-camera.getY())/infRatio),
			(int)(gO.getWidth()/infRatio),
			(int)(gO.getHeight()/infRatio),
			this);			
		}			
	}
	/**
	 * gets an image from a folder.
	 * @param the location of the image in the form
	 * of a String
	 * @return the image found.
	 */
	public Image retrieveImage(String str){return getImage(url,str);}
	/**
	 * Paints the entire image.
	 */
	public void paint(Graphics g)
	{
		art(g2);
		g.drawImage(offscreen,0,0,this);
	}
	/**
	 * Determines if a certain key is pressed
	 * @param keyCode ASCII value of the key
	 * that is requested to know if it is pressed.
	 * @return true if the key requested is pressed.
	 */
	public boolean isKeyPressed(int keyCode)
	{
		if(keyCode>0&&keyCode<ASCII.length) return ASCII[keyCode];
		return false;
	}
	public void update(Graphics g){paint(g);}
	public void mouseWheelMoved(MouseWheelEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	/**
	 * Changes a value in 
	 * an ASCII array to true if a key
	 * is pressed. The location of the 
	 * value corresponds with the ASCII
	 * value of the key that is pressed.
	 * @param the key that is pressed.
	 */
	public void keyPressed(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		ASCII[keyCode]=true;
	}
	/**
	 * Changes a value in an
	 * ASCII array to false if a key
	 * is released. The location of the
	 * value corresponds with the ASCII
	 * value of the key that is released.
	 * @param the key that is released.
	 */
	public void keyReleased(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		ASCII[keyCode]=false;
	}
	public Point getClickPoint()
	{
		Point p = clickPoint;
		clickPoint = null;
		return p;
	}
	public void keyTyped(KeyEvent e){}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e)
	{
		clickPoint = new Point(e.getX(),e.getY());
	}
}