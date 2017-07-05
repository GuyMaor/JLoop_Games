import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;


public class StickComponent
{
	private double veloc;
	private int length;
	private int height;
	private int getHeight;
	private boolean isTop;
	private int getWidth;
	private double xCord;

    public StickComponent(double vel, int len, int hei, int gHei, boolean iTop, int gWid)
    {
    	veloc = vel;
    	length = len;
    	height = hei;
    	getHeight = gHei;
    	isTop = iTop;
    	getWidth = gWid;
    	xCord = getWidth-length;
    }
    public void drawStick(Graphics2D g)
    {
    	g.setColor(Color.red);
    	if(isTop)
 			g.fill(new Rectangle((int)xCord,0,length,height));
 		else
 			g.fill(new Rectangle((int)xCord,height,length,getHeight-height));
    }
    public void move()
    {
    	xCord-=veloc;

    }
    public int getTip()
    {
    		return height;
    }
    public int getFront()
    {
    	return (int)xCord;
    }
    public int getBack()
    {
    	return ((int)xCord)+length;
    }
    public boolean isTop()
    {
    	return isTop;
    }


}