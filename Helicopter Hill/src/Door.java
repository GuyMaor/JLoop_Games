
public class Door extends Rock
{
	private boolean isOpen;
	public Door(int xC, int yC)
	{
		super(xC,yC);
		isOpen = false;
	}
	public void open()
	{
		isOpen = true;
	}
	public boolean isOpen()
	{
		return isOpen;
	}
}
