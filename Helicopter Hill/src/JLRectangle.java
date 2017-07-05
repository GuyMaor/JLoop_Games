
public class JLRectangle
{
	private double xPos;
	private double yPos;
	private double width;
	private double height;
	/**
	 * Creates a jLRectangle class
	 * @param the x position of the rectangle
	 * @param the y position of the rectangle
	 * @param the width of the rectangle
	 * @param the height of the rectangle
	 */
	public JLRectangle(double x,double y, double w,double h)
	{
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}
	/**
	 * Determines whether this Rectangle collided with another
	 * @param the other rectangle to test
	 * @return true if the rectangles collided
	 */
	public boolean didCollide(JLRectangle rect)
	{
		if(xPos<=rect.getX()+rect.getWidth()&&
				rect.getX()<=xPos+width&&
				yPos<=rect.getY()+rect.getHeight()&&
				rect.getY()<=yPos+height)
			return true;
		return false;
	}
	/**
	 * Increases the width by a given amount.
	 * @param the amount the width will increase.
	 */
	public void expandX(double x){width+=x;}
	/**
	 * Increases the height by a given amount.
	 * @param the amount the height will increase.
	 */
	public void expandY(double y){height+=y;}
	/**
	 * Changes the width to a given width.
	 * @param the width that is to be changed to.
	 */
	public void changeWidthTo(double w){width = w;}
	/**
	 * Changes the height to a given height.
	 * @param the height that is to be changed to.
	 */
	public void changeHeightTo(double h){height = h;}
	/**
	 * Moves the rectangle up or down
	 * @param the amount of units to move
	 */
	public void moveVert(double y){yPos+=y;}
	/**
	 * Moves the rectangle left or right
	 * @param the amount of units to move
	 */
	public void moveHor(double x){xPos+=x;}
	/**
	 * Moves the rectangle to the X location
	 * @param the X location to move
	 */
	public void moveHorTo(double x){xPos=x;}
	/**
	 * Moves the rectangle to the Y location
	 * @param the Y location to move
	 */
	public void moveVertTo(double y){yPos=y;}
	/**
	 * Gets the X location of the rectangle
	 * @return the X location of the rectangle
	 */
	public double getX(){return xPos;}
	/**
	 * Gets the Y location of the rectangle
	 * @return the Y location of the rectangle
	 */
	public double getY(){return yPos;}
	/**
	 * Gets the Width of the rectangle
	 * @return the width of the rectangle
	 */
	public double getWidth(){return width;}
	/**
	 * Gets the height of the rectangle
	 * @return the height of the rectangle
	 */
	public double getHeight(){return height;}
}