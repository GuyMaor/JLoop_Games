import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class GameLoop  extends Applet  implements Runnable, KeyListener, MouseListener, MouseMotionListener
{

	private static final long serialVersionUID = 1;
	public Image offscreen;
	public Graphics g2;
	public int stageCode;
	public int colorKeyPressed;
	public GravitySystem blue;
	public GravitySystem red;
	public GravitySystem green;	
	public Satellite player;
	public Point finishLocation;
	Stage currentStage;	
	Stages stages;	
	//MouseLocation when clicked
	public int x;
	public int y;
	Button startButton;
	Button stage1Button;
	Button stage2Button;
	Button stage3Button;
	Button stage4Button;
	Button stage5Button;
	Button tryAgain;
	Button backToMenu;
	Button nextLevel;
	Button tryAgain2;
	Button backToMenu2;	
	//ArrayList of Unplayable charged objects.
	ArrayList<StationaryObject> redObjects;
	ArrayList<StationaryObject> greenObjects;
	ArrayList<StationaryObject> blueObjects;	
	//to see if the levels are complete
	public boolean[] levelComplete;
	
	public void run()
	{
		stageCode=0;
		colorKeyPressed=-1;	
		x = -1;
		y = -1;
		levelComplete = new boolean[4];
		startButton = new Button(325,200,200,200);
		stage1Button = new Button(50,50,50,50);
		stage2Button = new Button(150,50,50,50);
		stage3Button = new Button(250,50,50,50);
		stage4Button = new Button(350,50,50,50);
		stage5Button = new Button(450,50,50,50);
		tryAgain = new Button(250,300,100,100);
		backToMenu = new Button(500,300,100,100);
		nextLevel = new Button(375,300,100,100);
		tryAgain2 = new Button(312,300,100,100);
		backToMenu2 = new Button(438,300,100,100);
		stages = new Stages();
		currentStage=null;
		gameLoop();
	}
	private void gameLoop()
	{
		while(true)
		{
			while(stageCode==0)
			{
				Point p = new Point(x,y);
				if(startButton.isOnButton(p))
				{
					x=-1;
					y=-1;
					stageCode=1;
				}
				pause();
				repaint();
			}
			while(stageCode==1)
			{
				Point p = new Point(x,y);
				if(stage1Button.isOnButton(p))
				{
					x=-1;
					y=-1;
					stageCode=2;
					currentStage=stages.level1;
				}
				if(stage2Button.isOnButton(p)&&levelComplete[0])
				{
					x=-1;
					y=-1;
					stageCode=2;
					currentStage=stages.level2;
				}
				if(stage3Button.isOnButton(p)&&levelComplete[1])
				{
					x=-1;
					y=-1;
					stageCode=2;
					currentStage=stages.level3;
				}
				if(stage4Button.isOnButton(p)&&levelComplete[2])
				{
					x=-1;
					y=-1;
					stageCode=2;
					currentStage=stages.level4;
				}
				if(stage5Button.isOnButton(p)&&levelComplete[3])
				{
					x=-1;
					y=-1;
					stageCode=2;
					currentStage=stages.level5;
				}								
			}
			if(stageCode==2)
			{
				fillGravitySystems();				
			}
			while(stageCode==2)
			{
				if(colorKeyPressed==65)
					red.moveSatellites();
				if (colorKeyPressed==83)
					green.moveSatellites();
				if(colorKeyPressed==68)
					blue.moveSatellites();
				if(didCrash())
					stageCode=3;
				if(didCollide(finishLocation))
				{
					stageCode=4;
					if(currentStage==stages.level1)
						levelComplete[0]=true;
					if(currentStage==stages.level2)
						levelComplete[1]=true;						
					if(currentStage==stages.level3)
						levelComplete[2]=true;						
					if(currentStage==stages.level4)							
						levelComplete[3]=true;					
				}
				pause();
				repaint();
			}
			while(stageCode==3)
			{
				Point p = new Point(x,y);
				if(backToMenu2.isOnButton(p))
				{
					x=-1;
					y=-1;
					stageCode=1;
				}
				if(tryAgain2.isOnButton(p))
				{
					x=-1;
					y=-1;
					stageCode=2;
				}
				pause();
				repaint();				
			}
			while(stageCode==4)
			{
				Point p = new Point(x,y);
				if(currentStage!=stages.level5)
				{
					if(backToMenu.isOnButton(p))
					{
						x=-1;
						y=-1;
						stageCode=1;
					}
					if(tryAgain.isOnButton(p))
					{
						x=-1;
						y=-1;
						stageCode=2;
					}
					if(nextLevel.isOnButton(p))
					{
						x=-1;
						y=-1;
						nextLevel();
						stageCode=2;
					}
				}
				else
				{
					if(backToMenu2.isOnButton(p))
					{						
						x=-1;
						y=-1;
						stageCode=1;
					}
					if(tryAgain2.isOnButton(p))
					{
						x=-1;
						y=-1;
						stageCode=2;
					}					
				}
				pause();
				repaint();					
			}
		}
	}
	private void nextLevel()
	{
		if(currentStage==stages.level1)
			currentStage=stages.level2;
		else if(currentStage==stages.level2)
			currentStage=stages.level3;			
		else if(currentStage==stages.level3)
			currentStage=stages.level4;			
		else if(currentStage==stages.level4)
			currentStage=stages.level5;			
	}
	public void fillGravitySystems()
	{
		colorKeyPressed=-1;
		blue=new GravitySystem();
		red=new GravitySystem();
		green=new GravitySystem();
		Point playerPoint = currentStage.movingParticle;
		finishLocation = currentStage.finishLocation;
		player = new Satellite(playerPoint.getX(),playerPoint.getY(),0,0,4);
		blue.addSatellite(player);
		red.addSatellite(player);
		green.addSatellite(player);	
		redObjects = new ArrayList<StationaryObject>();
		greenObjects = new ArrayList<StationaryObject>();
		blueObjects = new ArrayList<StationaryObject>();
		ArrayList<Point> blueP = currentStage.blueOrbs;
		ArrayList<Point> greenP = currentStage.greenOrbs;
		ArrayList<Point> redP = currentStage.redOrbs;
		for(Point p : blueP)
		{
			StationaryObject statObj = new StationaryObject(p.getX(),p.getY(),700);
			blueObjects.add(statObj);
			blue.addSatellite(statObj);
		}
		for(Point p : greenP)
		{
			StationaryObject statObj = new StationaryObject(p.getX(),p.getY(),700);
			greenObjects.add(statObj);
			green.addSatellite(statObj);
		}
		for(Point p : redP)
		{
			StationaryObject statObj = new StationaryObject(p.getX(),p.getY(),700);
			redObjects.add(statObj);
			red.addSatellite(statObj);
		}	
	}	
	private boolean didCollide(Point p)
	{
		double dX = player.getXPos()-p.getX();
		double dY = player.getYPos()-p.getY();
		double r = Math.sqrt(dX*dX+dY*dY);
		if(r<40)
			return true;
		return false;
	}
	private boolean didCrash()
	{
		double playerX = player.getXPos();
		double playerY = player.getYPos();
		if(playerX<0||playerX>850||playerY<0||playerY>450)
			return true;
		for(StationaryObject obj : blueObjects)
		{
			Point p = new Point((int)obj.getXPos(),(int)obj.getYPos());
			if(didCollide(p))
				return true;
		}
		for(StationaryObject obj : redObjects)
		{
			Point p = new Point((int)obj.getXPos(),(int)obj.getYPos());
			if(didCollide(p))
				return true;
		}
		for(StationaryObject obj : greenObjects)
		{
			Point p = new Point((int)obj.getXPos(),(int)obj.getYPos());
			if(didCollide(p))
				return true;
		}
		return false;
		
	}
	private void pause()
	{
		try
		{
		Thread.sleep(40);
		}
		catch(InterruptedException exc)
		{
			System.out.println("Cannot sleep");
		}			
	}		
	public void keyPressed(KeyEvent arg0)
	{
		int keyCode = arg0.getKeyCode();
		if(keyCode==65&&redObjects.size()>0||keyCode==83&&greenObjects.size()>0||keyCode==68&&blueObjects.size()>0)
			colorKeyPressed=keyCode;		
	}
	public void keyReleased(KeyEvent arg0){}
	public void keyTyped(KeyEvent arg0) {}
	public void mouseDragged(MouseEvent arg0){}
	public void mouseMoved(MouseEvent arg0){}
	public void mouseClicked(MouseEvent arg0)
	{
		if(stageCode!=2)
		{
			x = arg0.getX();
			y = arg0.getY();
		}
	}
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0){}
}
