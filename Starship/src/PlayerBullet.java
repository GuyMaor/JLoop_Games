public class PlayerBullet extends BoundedRectangle
{
	int dirCode;
	boolean isPower;
	Direction direction;
	public PlayerBullet(int dir, int x, int y,boolean iP)
	{
		super(x,y,5,5);
		dirCode=dir;
		isPower=iP;
	}
	public void moveBullet()
	{
		if(direction==null)
		{
			if(dirCode==0)
				moveHor(-5);
			if(dirCode==2)
				moveHor(5);
			moveVert(-13);
		}
		else
		{
			moveHor(direction.xDir);
			moveVert(direction.yDir);
		}
	}
	public void setDirection(double dir)
	{
		direction = new Direction(dir);
	}
	private class Direction
	{
		public double xDir;
		public double yDir;
		public Direction(double angle)
		{
			xDir = Math.cos(angle)*5;
			yDir = Math.sin(angle)*5;
		}
	}
}
