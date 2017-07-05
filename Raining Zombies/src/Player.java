public class Player extends OuterRectangle
{
	public double vel;
	public double acc;
	public Player(int x,int y, int w, int h)
	{
		super(x, y, w, h);
		acc=0;
		vel=0;
	}
	public void movePlayer(int e)
	{

		if(e==65&&left>0)
		{
			moveHor(-13);
			if(left<0)
			{
				moveHorTo(0);
			}
		}
		else if(e==68&&right<850)
		{
			moveHor(13);
			if(right>850)
			{
				moveHorTo(850-width);
			}			
		}
		else if(e==87&&acc==0)
		{
			vel=-14;
			moveVert(-20);
		}		
		else
		{
			vel+=acc;
			moveVert((int)(vel-acc/2));			
		}
	}
}
