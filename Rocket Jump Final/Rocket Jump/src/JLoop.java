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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private URL url;
	private Image offscreen;
	private Graphics g2;
	private int screenWidth;
	private int screenHeight;
	public void init()
	{
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}
		setScreenSize(300,600);
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
	public void run()
	{
		while(true)
		{
			loop();
			repaint();
			pause();
		}
	}
	private void setScreenSize(int width, int height)
	{
		screenWidth = width;
		screenHeight = height;
	}
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
	public abstract void settings();
	public abstract void loop();	
	public abstract void art(Graphics g);	
	public Image retrieveImage(String str){return getImage(url,str);}
	public void paint(Graphics g)
	{
		art(g2);
		g.drawImage(offscreen,0,0,this);
	}
	public void update(Graphics g){paint(g);}
	public void keyTyped(KeyEvent e){}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}