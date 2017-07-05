/** Bounded Rectangle represents a rectangle with a location and size and determines collisions
 * @author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-03-13
 */
public class BoundedRectangle
{
	public double xPos;
	public double yPos;
	public int width;
	public int height;
	/**
	 * Creates a BoundedRectangle class
	 * @param the x position of the rectangle
	 * @param the y position of the rectangle
	 * @param the width of the rectangle
	 * @param the height of the rectangle
	 */
	public BoundedRectangle(int x,int y, int w,int h)
	{
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}
	/*
	 * Determines whether this BoundedRectangle collided with another
	 * @param the other rectangle to test
	 * @return true if the rectangles collided
	 */
	public boolean didCollide(BoundedRectangle rect)
	{
		if(xPos<=rect.xPos+rect.width&&
				rect.xPos<=xPos+width&&
				yPos<=rect.yPos+rect.height&&
				rect.yPos<=yPos+height)
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
	 * Moves the rectangle to the Y location
	 * @param the Y location to move
	 */
	public void moveVertTo(int y)
	{
		yPos = y;
	}
}