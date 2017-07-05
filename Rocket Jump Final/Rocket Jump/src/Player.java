
public class Player extends BoundingRectangle
{
	private double velocityY;
	private int timer;
	final static double ACCELERATION = 0.2;
	
	public Player(int x,int y, int w,int h)
	{
		super(x, y, w, h);
		velocityY= 0;
		timer=0;
	}
	
	public void moveY()
	{
		if(timer>0)
			timer--;
		velocityY += ACCELERATION;
		moveVert((int)velocityY);
	}
	
	public void bounce(int b)
	{
		velocityY = -b;
		moveVert(-10);
	}
	public boolean isMovingDown()
	{
		return velocityY>0;
	}
	public void startTimer()
	{
		timer=800;
	}
	public boolean isProtected()
	{
		return timer>0;
	}
	
	
}
