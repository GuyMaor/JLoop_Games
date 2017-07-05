
public class Enemy extends OuterRectangle
{

	public double velX;
	public double velY;
	public double acc;
	public Enemy(int x, int y, int w, int h)
	{
		super(x,y,w,h);
		velX=0;
		acc=0;
	}
	public void moveEnemy()
	{
		velY+=acc;
		moveVert((int)(velY-acc/2));
		moveHor((int)velX);
	}

}
