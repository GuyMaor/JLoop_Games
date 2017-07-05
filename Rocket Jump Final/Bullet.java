/** Bullet represents a bullet object in the CSJump game
 *@author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-29-13
 */
 
public class Bullet extends BoundingRectangle
{
	private double xVel; //x velocity
	private double yVel; //y velocity
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	private static final double SPEED = 20;
	private static final int WIDTH_SCREEN = 300;
	private static final int HEIGHT_SCREEN = 600;
	
	/**
	 * Creates a Bullet object
	 * @param x the x position of the bullet
	 * @param xN whether the x velocity is negative
	 * @param y the y position of the bullet
	 * @param yN whether the y velocity is negative
	 * @param dir the angle of the bullet
	 */
	public Bullet(double x,boolean xN, double y,boolean yN, double dir)
	{
		super(x,y,WIDTH,HEIGHT);	
		xVel = SPEED*Math.cos(dir);
		yVel = SPEED*Math.sin(dir);
		if(xN)
			xVel*=-1;
		if(yN)
			yVel*=-1;
	}
	
	/**
	 * Moves the bullet horizontally and vertically
	 */
	public void move()
	{
		moveHor(xVel);
		moveVert(yVel);
	}
	
	/**
	 * Returns whether the bullet is off the screen
	 *@return whether the bullet is off the screen
	 */
	public boolean isOffScreen()
	{
		return (getX()<-10&&xVel<0)||(getX()>WIDTH_SCREEN&&xVel>0)||getY()<-10||getY()>HEIGHT_SCREEN;
			
	}
}
