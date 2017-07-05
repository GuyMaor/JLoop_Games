
public class Enemy
{
	public int xPosition;
	public int yPosition;
	public String word;
	public Enemy(int x,int y,String str)
	{
		xPosition = x;
		yPosition = y;
		word = str;
	}
	public int compareTo(Enemy e)
	{
		return this.yPosition-e.yPosition;
	}
}
