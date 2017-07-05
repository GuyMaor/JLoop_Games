import java.awt.Point;


public class Button
{		
		//X and Y locations of the button on the screen.
		public int xLocation;
		public int yLocation;
		
		//Dimentions of the button.
		public int width;
		public int height;
		
		public Button(int x, int y, int w, int h)
		{
			xLocation = x;
			yLocation = y;
			width = w;
			height = h;
		}
		
		//Determains if a point is on top of the button.
		//@param the point to be checked.
		public boolean isOnButton(Point p)
		{
			int x = (int)p.getX();
			int y = (int)p.getY();
			if(x>xLocation&&x<xLocation+width&&y>yLocation&&y<yLocation+height)
				return true;
			return false;
		}
}
