import java.awt.Point;


public class Button extends GameObject
{		
		//X and Y locations of the button on the screen.

		private boolean isLocked;
		//Dimentions of the button.
		
		public Button(int x, int y)
		{
			super(x,y,50,50);
			isLocked = true;
		}
		public Button(int x, int y, int w, int h)
		{
			super(x,y,w,h);
			isLocked = true;
		}
		
		//Determains if a point is on top of the button.
		//@param the point to be checked.
		public boolean isOnButton(Point p)
		{
			if(isLocked)
				return false;
			int x = (int)p.getX();
			int y = (int)p.getY();
			if(x>getX()&&x<getX()+getWidth()&&y>getY()&&y<getY()+getWidth())
				return true;
			return false;
		}
		public boolean isLocked()
		{
			return isLocked;
		}
		public void unlock()
		{
			isLocked = false;
		}
}
