import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class BallComponent
{

	private double height;
	private int rad;
	private double grav;
	private double v;
	private int xCord;
	private double getHeight;

    public BallComponent(int ra, double gra,int x, double hei)
    {
    	getHeight = hei;
    	height = hei/2;
    	rad = ra;
    	grav = gra*-1;
    	v = 0;
    	xCord = x;
    }
    public void drawBall(Graphics2D g)
    {
    	g.setColor(Color.blue);
 		g.fill(new Rectangle(xCord,(int)height,rad,rad));
    }
    public void move()
    {
    	height+=(v+grav/2);
    	v+=grav;
    }
    public void changeDir()
    {
    	grav*=-1;
		v=0;//delete me
    }
    public int getTop()
    {
    	return (int)height;
    }
    public int getBot()
    {
    	return (int)(height+rad);
    }
    public int getFront()
    {
    	return xCord+rad;
    }
    public int getBack()
    {
    	return xCord;
    }
    public void restart()
    {
    	height = getHeight/2;
    	if(grav<0)
    		grav*=-1;
    	v = 0;
    }


}