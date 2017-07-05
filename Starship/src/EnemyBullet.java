public class EnemyBullet extends BoundedRectangle
{
	public EnemyBullet(int x, int y)
	{
		super(x,y,5,5);
	
	}
	public void moveBullet()
	{
		moveVert(13);
	}
}