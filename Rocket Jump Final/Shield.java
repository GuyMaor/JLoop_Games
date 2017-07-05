/*Shield creates the shield object used in the game
 *@author Michael Hwang, Guy Maor, Enoch Yue
 *Period: 3
 *Date: 05-29-13
 */




public class Shield extends BoundingRectangle
{
	
	private static final int Y_COORD = -60;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 45;
	
	/*Creates a shield object
	 *@param x the x coordinate of the shield object
	 */
	public Shield(int x)
	{
		super(x, Y_COORD, WIDTH, HEIGHT);
	}
}
