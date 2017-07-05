/** BoundingRectangle represents a rectangle with a location and size and determines collisions
 *@author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-29-13
 */
 
public class BoundingRectangle
{
	private double xPos;
	private double yPos;
	private int width;
	private int height;
	private int special;
	/**
	 * Creates a BoundedRectangle object
	 * @param the x position of the rectangle
	 * @param the y position of the rectangle
	 * @param the width of the rectangle
	 * @param the height of the rectangle
	 */
	public BoundingRectangle(double x,double y, int w,int h)
	{
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		special = 0;
	}
	/**
	 * Determines whether this BoundedRectangle collided with another
	 * @param the other rectangle to test
	 * @return true if the rectangles collided
	 */
	public boolean didCollide(BoundingRectangle rect)
	{
		if(xPos<=rect.getX()+rect.getWidth()&&
				rect.getX()<=xPos+width&&
				yPos<=rect.getY()+rect.getHeight()&&
				rect.getY()<=yPos+height)
			return true;
		return false;
	}
	/**
	 * Moves the rectangle up or down
	 * @param the amount of units to move
	 */
	public void moveVert(double y)
	{
		yPos+=y;
	}
	/**
	 * Moves the rectangle left or right
	 * @param the amount of units to move
	 */
	public void moveHor(double x)
	{
		xPos+=x;
	}
	/**
	 * Moves the rectangle to the X location
	 * @param the X location to move
	 */
	public void moveHorTo(int x)
	{
		xPos=x;
	}
	/**
	 * Gets the X location of the rectangle
	 * @return the X location of the rectangle
	 */
	public double getX()
	{
		return xPos;
	}
	/**
	 * Gets the Y location of the rectangle
	 * @return the Y location of the rectangle
	 */
	public double getY()
	{
		return yPos;
	}
	/**
	 * Gets the Width of the rectangle
	 * @return the width of the rectangle
	 */
	public int getWidth()
	{
		return width;
	}
	/**
	 * Gets the height of the rectangle
	 * @return the height of the rectangle
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Sets a special integer to determine if the platform is a trampoline, only used for platforms
	 * @param the special integer to which the platform is set
	 */
	public void setSpecial(int s)
	{
		special = s;
	}
	/**
	 * Gets the special integer to determines if the platform is a trampoline
	 * @return the special integer to determine if the platform is a trampoline
	 */
	public int getSpecial()
	{
		return special;
	}

}