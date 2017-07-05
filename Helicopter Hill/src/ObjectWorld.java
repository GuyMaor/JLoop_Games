
import java.util.*;
public class ObjectWorld
{
	private Map<String,ArrayList<GameObject>> gameObjects;
	/**
	 * creates an ObjectWorld object.
	 */
	public ObjectWorld()
	{
		gameObjects = new TreeMap<String,ArrayList<GameObject>>();
	}
	/**
	 * adds a type that could be added to the world
	 * @param the name of the type.
	 */
	public void addObjectType(String objType)
	{
		gameObjects.put(objType, new ArrayList<GameObject>());
	}
	/**
	 * Adds a GameObject to the world
	 * @param The type the GameObject is.
	 * @param The GameObject.
	 */
	public void addObject(String objType, GameObject obj)
	{
		obj.addWorld(this);
		gameObjects.get(objType).add(obj);
	}
	/**
	 * gets an arrayList of objects of a certain type
	 * @param the type that is requested.
	 * @return an arrayList of objects of the requested type.
	 */
	public ArrayList<GameObject> getObjectOf(String objType)
	{
		return gameObjects.get(objType);
	}
	/** clears all GameObjects in the world
	 * but doesn't clear the types that are possible.
	 */
	public void clearObjects()
	{
		Iterator<String> objTypes = gameObjects.keySet().iterator();
		while(objTypes.hasNext())
		{
			gameObjects.get(objTypes.next()).clear();
		}
	}
	/**
	 * clears both Game objects and their types.
	 */
	public void clearTypes()
	{
		gameObjects.clear();
	}
}
