import java.awt.Point;
import java.util.ArrayList;

public class Stage
{
	public Point movingParticle;
	public Point finishLocation;
	public ArrayList<Point> redOrbs;
	public ArrayList<Point> blueOrbs;
	public ArrayList<Point> greenOrbs;
	
	public Stage(Point mP, Point fL, ArrayList<Point> r, ArrayList<Point> g, ArrayList<Point> b)
	{
		movingParticle = mP;
		finishLocation = fL;
		redOrbs = r;
		blueOrbs = b;
		greenOrbs = g;
	}



	
}
