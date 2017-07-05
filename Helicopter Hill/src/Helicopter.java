
public class Helicopter extends GameObject
{
	private double xVelocity;
	private double yVelocity;
	public Helicopter(int xC, int yC)
	{
		super(xC*50,yC*50,100,50);
		stopMotion();
	}
	public void stopMotion()
	{
		xVelocity = 0;
		yVelocity = 0;
	}
	public void move()
	{
		this.moveHor(xVelocity);
		this.moveVert(yVelocity);
	}
	public void accelerateY()
	{
		yVelocity+=.1;
	}
	public void decelerateY()
	{
		yVelocity-=.1;
	}
	public void accelerateX()
	{
		xVelocity+=.1;
	}
	public void slowX()
	{
		xVelocity*=.98;
	}
	public void decelerateX()
	{
		xVelocity-=.1;
	}
	public void smallShift()
	{
		this.moveVert(-1);
	}
	public boolean isTooFast()
	{
		return yVelocity>1.5;
	}
	public boolean isMovingLeft()
	{
		return xVelocity<0;
	}
}
