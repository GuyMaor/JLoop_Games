/* 
 * Keeps information on an object in the game.
 * @author Guy Maor
 * Last Updated: 8/20/2013
 */

public class GameObject extends JLRectangle
{
	private ObjectWorld objectWorld;
	/**
	 * Creates a GameObject object.
	 * @param the initial x location of the object.
	 * @param the initial y location of the object.
	 * @param the initial width of the object.
	 * @param the initial height of the object.
	 */
	public GameObject(double x,double y, double w,double h)
	{
		super(x,y,w,h);
		objectWorld = null;
	}
	/**
	 * adds the world that this object is in.
	 * @param the world it is in.
	 */
	protected void addWorld(ObjectWorld oW)
	{
		objectWorld = oW;
	}
	/**
	 * gets the world that it is in.
	 * @return the world that this object is in.
	 */
	public ObjectWorld getWorld()
	{
		return objectWorld;
	}
}