
public class PowerUp extends BoundedRectangle
{
	public int specialValue;
	public int timer;
	public PowerUp(int x, int y,int val)
	{
		super(x,y,30,30);
		if(y<0)
			moveVertTo(0);
		if(y>470)
			moveVertTo(470);
		specialValue=val;
		timer=200;
	}
	public boolean isDone()
	{
		timer--;
		if(timer<=0)
			return true;
		return false;
	}
}
