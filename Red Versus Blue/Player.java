/** Player represents a character in the SuperSmash Coders Game
 * @author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-03-13
 */
 
public class Player extends BoundedRectangle
{
	private int health; //health of player
	private double yVelocity; //velocity in the y-direction
	private double acceleration; //acceleration of the player
	private int punchTimer; //time delay for player after punching
	private int hitTimer; //time delay for player after being hit
	private boolean isShielding; //whether the player is sheilding himself
	private boolean isMovingLeft; //whether the player is moving left
	private static final int HIT_TIMER_START = 10; //start value for the hit timer
	
	/*Creates a Player object with the given parameters
	 *@param x the x position of the player
	 *@param y the y position of the player
	 *@param w the width of the player
	 *@param h the height of the player
	 *@param movingLeft whether or not the player is moving left
	 */
	public Player(int x, int y, int w, int h,boolean movingLeft)
	{
		super(x,y,w,h);
		health = 100;
		yVelocity = 0;
		acceleration = 0;
		punchTimer=0;
		hitTimer=0;
		isShielding=false;
		isMovingLeft=movingLeft;
	}
	
	/*Moves the player up or down
	 */
	public void movePlayerVertically()
	{
		moveVert((int)yVelocity);
		yVelocity+=acceleration;
	}
	
	/*Moves the player up to simulate jumping
	 */
	public void jump()
	{
		moveVert(-5);
		yVelocity=-15;
		acceleration = 1.2;
	}
	
	/*Lands the player after the player has jumped
	 */
	public void land(int pos)
	{
		moveVertTo(pos);
		acceleration=0;
		yVelocity = 0;
	}
	
	/*Moves the player left
	 */
	public void moveLeft()
	{
		moveHor(-5);
		
	}
	
	/*Moves the player right
	 */
	public void moveRight()
	{
		moveHor(5);
	}
	
	/*Decreases the health of the player to simulate damage
	 */
	public void dealDamage()
	{
		if(isShielding)
			health-=5;
		else
			health-=20;
	}
	
	/*Returns whether the player is falling
	 *@return whether the player is falling
	 */
	public boolean isFalling()
	{
		if(acceleration>1)
			return true;
		return false;
	}
	
	/*Increments the punch timer by the parameter
	 *@param i the value to be added to the punch timer
	 */
	public void punchTimer(int i)
	{
		punchTimer +=i;
	}
	
	/*Returns the punch timer value
	 *@return punch timer value
	 */
	public int punchTimer()
	{
		return punchTimer;
	}
	
	/*Returns whether the player is moving left
	 *@return whether the player is moving left
	 */
	public boolean isMovingLeft()
	{
		return isMovingLeft;
	}
	
	/*Sets the direction of the player to moving left or right
	 *@param b whether the player is moving left
	 */
	public void isMovingLeft(boolean b)
	{
		isMovingLeft=b;
	}
	
	/*Increments the hit timer by the given value
	 *@param i the increment to be added to the hit timer
	 */
	public void hitTimer(int i)
	{
		hitTimer +=i;
	}
	
	/*Returns the value of the hit timer
	 *@return the value of the hit timer
	 */
	public int hitTimer()
	{
		return hitTimer;
	}
	
	/*Returns whether the player is shielding
	 *@return whether the player is shielding
	 */
	public boolean isShielding()
	{
		return isShielding;
	}
	
	/*Sets the variable of whether the player is shielding
	 *@param b whether the player is shielding
	 */
	public void isShielding(boolean b)
	{
		isShielding = b;
	}
	
	/*Returns the player's health value
	 *@return the player's health value
	 */
	public int health()
	{
		return health;
	}
	
	/*Sets the hit timer to the start value
	 */
	public void hitTimerStart()
	{
		hitTimer= HIT_TIMER_START;
	}
}
