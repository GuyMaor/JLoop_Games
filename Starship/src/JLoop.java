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



public abstract class JLoop extends Applet  implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	URL url;
	private Image offscreen;
	private Graphics g2;
	private int screenWidth;
	private int screenHeight;
	private double imageMultiplier;
	private int pauseTime;
	public void init()
	{
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}
		setScreenSize(100,100);
		setImageMultiplier(1);
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
	public void setImageMultiplier(double m)
	{
		imageMultiplier = m;
	}
	public void run()
	{
		while(true)
		{
			loop();
			repaint();
			pause();
		}
	}
	public void setScreenSize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
	}
	public void setPauseTime(int t)
	{
		pauseTime = t;
	}
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
	public abstract void settings();
	public abstract void loop();	
	public abstract void art(Graphics g);	
	public void paintPicture(Graphics g,Image i, int x, int y, int w, int h)
	{
		g.drawImage(i,(int)(x*imageMultiplier),(int)(y*imageMultiplier),
				(int)(w*imageMultiplier),(int)(h*imageMultiplier),this);
	}
	public Image retrieveImage(String str){return getImage(url,str);}
	public void paint(Graphics g)
	{
		art(g2);
		g.drawImage(offscreen,0,0,this);
	}
	public void update(Graphics g){paint(g);}
	public void mouseWheelMoved(MouseWheelEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}