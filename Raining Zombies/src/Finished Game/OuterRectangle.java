public class OuterRectangle
{
	public int left;
	public int right;
	public int top;
	public int bottom;
	public int width;
	public int height;
	public OuterRectangle(int x,int y, int w,int h)
	{
		left=x;
		top=y;
		right=x+w;
		bottom=y+h;
		width=w;
		height=h;
	}
	public boolean didCollide(OuterRectangle rect)
	{
		if(right>=rect.left&&rect.right>=left&&bottom>=rect.top&&rect.bottom>=top)
			return true;
		return false;
	}
	public void moveVert(int y)
	{
		top+=y;
		bottom+=y;
	}
	public void moveHor(int x)
	{
		left+=x;
		right+=x;
	}
	public void moveHorTo(int x)
	{
		left=x;
		right=x+width;
	}
	public void moveVertTo(int y)
	{
		top=y;
		bottom=y+height;
	}

}
