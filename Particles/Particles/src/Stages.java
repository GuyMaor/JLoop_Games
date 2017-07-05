import java.awt.Point;
//To create a new Level, do 2 things. Create an Instance variable and make a method for the level.
import java.util.ArrayList;


public class Stages
{
	public Stage level1;
	public Stage level2;
	public Stage level3;
	public Stage level4;
	public Stage level5;
	public Stages()
	{
		level1();
		level2();
		level3();
		level4();
		level5();
	}
	
	public void level1()
	{
		Point start = new Point(50,225);
		Point end = new Point(425,225);
		Point red = new Point(800,225);
		ArrayList<Point> reds = new ArrayList<Point>();
		reds.add(red);
		level1 = new Stage(start,end,reds,new ArrayList<Point>(),new ArrayList<Point>());
	}
	public void level2()
	{
		Point start = new Point(50,400);
		Point red = new Point(800,400);
		Point blue = new Point(800,50);
		Point end = new Point(800, 225);
		ArrayList<Point> reds = new ArrayList<Point>();
		reds.add(red);
		ArrayList<Point> blues = new ArrayList<Point>();
		blues.add(blue);
		level2 = new Stage(start,end,reds, new ArrayList<Point>(),blues);
	}
	public void level3()
	{
		Point start = new Point(800,225);
		Point end = new Point(50,225);
		Point green = new Point(50,50);
		Point blue = new Point(50,400);
		ArrayList<Point> greens = new ArrayList<Point>();
		greens.add(green);
		ArrayList<Point> blues = new ArrayList<Point>();
		blues.add(blue);
		level3 = new Stage(start,end,new ArrayList<Point>(), greens,blues);
	}
	public void level4()
	{
		Point start = new Point(300,400);
		Point end = new Point(425,50);
		Point green = new Point(425,225);
		Point red = new Point(550,400);
		ArrayList<Point> greens = new ArrayList<Point>();
		greens.add(green);
		ArrayList<Point> reds = new ArrayList<Point>();
		reds.add(red);
		level4 = new Stage(start,end,reds,greens,new ArrayList<Point>());
	}	
	public void level5()
	{
		Point start = new Point(225,400);
		Point end = new Point(625,50);
		Point green = new Point(625,225);
		Point red = new Point(625,400);
		Point blue = new Point(425,225);
		ArrayList<Point> greens = new ArrayList<Point>();
		greens.add(green);
		ArrayList<Point> reds = new ArrayList<Point>();
		reds.add(red);
		ArrayList<Point> blues = new ArrayList<Point>();
		blues.add(blue);
		level5 = new Stage(start,end,reds,greens,blues);
	}
	
}
