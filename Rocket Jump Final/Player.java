/** Player represents a character in the CSJump Game
 * @author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-29-13
 */
 
public class Player extends BoundingRectangle
{
	private double velocityY; //y velocity
	private int timer; //keeps track of how long the player is protected by the shield
	private static final double ACCELERATION = 0.2; //acceleration of the y velocity
	private static final int VERT_MOVE = -10; //distance for vertical movement
	private static final int START_TIMER = 800; //start time
	
	/**
	 * Creates a Player object
	 * @param x the x position of the player
	 * @param y the y position of the player
	 * @param w the width of the player
	 * @param h the height of the player
	 */
	public Player(int x,int y, int w,int h)
	{
		super(x, y, w, h);
		velocityY= 0;
		timer=0;
	}
	
	/**
	 * Moves the player vertically
	 */
	public void moveY()
	{
		if(timer>0)
			timer--;
		velocityY += ACCELERATION;
		moveVert((int)velocityY);
	}
	
	/**
	 * Simulates a bounce of the player object
	 */
	public void bounce(int b)
	{
		velocityY = -b;
		moveVert(VERT_MOVE);
	}
	
	/**
	 * Returns whether the player is moving down
	 * @return whether the player is moving down
	 */
	public boolean isMovingDown()
	{
		return velocityY>0;
	}
	
	/**
	 * Starts the timer to simulate a shield protecting the player
	 */
	public void startTimer()
	{
		timer= START_TIMER;
	}
	
	/**
	 * Returns whether the player is currently protectly by a shield
	 *@return whether the player is protected
	 */
	public boolean isProtected()
	{
		return timer>0;
	}
	
	
}
