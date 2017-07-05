
public class Bullet extends BoundingRectangle
{
	private double xVel;
	private double yVel;
	public Bullet(double x,boolean xN, double y,boolean yN, double dir)
	{
		super(x,y,10,10);	
		xVel = 20*Math.cos(dir);
		yVel = 20*Math.sin(dir);
		if(xN)
			xVel*=-1;
		if(yN)
			yVel*=-1;
	}
	public void move()
	{
		moveHor(xVel);
		moveVert(yVel);
	}
	public boolean isOffScreen()
	{
		return (getX()<-10&&xVel<0)||(getX()>300&&xVel>0)||getY()<-10||getY()>600;
			
	}
}
