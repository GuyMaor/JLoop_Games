
public class Key extends Rock
{
	private boolean isPickedUp;
	public Key(int xC, int yC)
	{
		super(xC,yC);
		isPickedUp = false;
	}
	public void pickUp()
	{
		isPickedUp = true;
	}
	public boolean isPickedUp()
	{
		return isPickedUp;
	}
}
