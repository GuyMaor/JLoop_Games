import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class CSJump extends JLoop
{
	private JumpGameCollections jGC;
	private int stageCode;
	private boolean isEnterPressed;
	private boolean isLeftPressed;
	private boolean isRightPressed;
	private Point p;
	private Image Rocket;
	private Image RocketS;
	private Image Alien;
	private Image Shield;
	private Image Platform1;
	private Image Platform2;
	private Image Background;
	private Image Bullet;
	private double digSize = 850.0/61.0;
	

	public void settings() 
	{
		resetGame();
		stageCode=0;
		isEnterPressed = false;
		isLeftPressed = false;
		isRightPressed = false;
		p = new Point(-1,-1);
		Rocket = retrieveImage("Images/Rocket.png");
		RocketS = retrieveImage("Images/Rocket Shield.png");
		Alien = retrieveImage("Images/Alien.png");
		Shield = retrieveImage("Images/Shield.png");
		Platform1 = retrieveImage("Images/Platform 1.png");
		Platform2 = retrieveImage("Images/Platform 2.png");
		Background = retrieveImage("Images/Background.png");
		Bullet = retrieveImage("Images/Bullet.png");
		
	}
	private void resetGame()
	{
		ArrayList<BoundingRectangle> bR = new ArrayList<BoundingRectangle>();
		bR.add(new BoundingRectangle(0,585,60,15));
		bR.add(new BoundingRectangle(60,585,60,15));
		bR.add(new BoundingRectangle(120,585,60,15));
		bR.add(new BoundingRectangle(180,585,60,15));
		bR.add(new BoundingRectangle(240,585,60,15));	
		bR.add(new BoundingRectangle(200,400,60,15));
		bR.add(new BoundingRectangle(50,200,60,15));
		jGC = new JumpGameCollections(new Player(50,300,45,60),bR );		
	}

	public void loop() 
	{
		if(stageCode==0)
		{
			if(isEnterPressed)
			{
				isEnterPressed=false;
				stageCode=1;
			}
		}
		else if(stageCode==1)
		{
			
			if(isLeftPressed)
				jGC.movePlayer(-5);
			if(isRightPressed)
				jGC.movePlayer(5);
			if(p.getX()!=-1&&p.getY()!=-1)
				jGC.shoot(p);
			if(jGC.movement())
				stageCode=2;
		}
		else if(stageCode==2)
		{		
			if(isEnterPressed)
			{
				isEnterPressed=false;
				stageCode=1;
				resetGame();
			}			
		}
		p = new Point(-1,-1);
	}

	public void art(Graphics g) 
	{
		Font f = new Font(Font.MONOSPACED,Font.BOLD,24);
		g.setColor(Color.blue);
		g.setFont(f);
		g.drawImage(Background,0,0,this);
		if(stageCode==0)
		{
			String str = "Press Enter To Start";
			g.drawString(str,getCenteredX(str),100);
		}
		if(stageCode==1)
		{
			Enemy e = jGC.getEnemy();
			if(e!=null)
			{
				g.drawImage(Alien,(int)e.getX(),(int)e.getY(),e.getWidth(),e.getHeight(),this);
			}
			Shield s = jGC.getShield();
			if(s!=null)
			{
				g.drawImage(Shield,(int)s.getX(),(int)s.getY(),s.getWidth(),s.getHeight(),this);
			}
			ArrayList<BoundingRectangle> platforms = jGC.getPlatforms();
			for(BoundingRectangle b : platforms)
			{
				if(b.getSpecial()==1)
					g.drawImage(Platform2,(int)b.getX(),(int)b.getY(),b.getWidth(),b.getHeight(),this);
				else
					g.drawImage(Platform1,(int)b.getX(),(int)b.getY(),b.getWidth(),b.getHeight(),this);
			}
			ArrayList<Bullet> bullets = jGC.getBullets();
			for(BoundingRectangle b : bullets)
			{
				g.drawImage(Bullet,(int)b.getX(),(int)b.getY(),10,10,this);
			}
			if(jGC.getPlayer().isProtected())
			{
				if(jGC.getPlayer().getX()>240)
					g.drawImage(RocketS,(int)jGC.getPlayer().getX()-300,(int)jGC.getPlayer().getY(),jGC.getPlayer().getWidth(),jGC.getPlayer().getHeight(),this);
				g.drawImage(RocketS,(int)jGC.getPlayer().getX(),(int)jGC.getPlayer().getY(),jGC.getPlayer().getWidth(),jGC.getPlayer().getHeight(),this);
			}
			else
			{
				if(jGC.getPlayer().getX()>240)
					g.drawImage(Rocket,(int)jGC.getPlayer().getX()-300,(int)jGC.getPlayer().getY(),jGC.getPlayer().getWidth(),jGC.getPlayer().getHeight(),this);
				g.drawImage(Rocket,(int)jGC.getPlayer().getX(),(int)jGC.getPlayer().getY(),jGC.getPlayer().getWidth(),jGC.getPlayer().getHeight(),this);
			}

			g.drawString(""+jGC.getScore(), 5, 24);
			
		}
		if(stageCode==2)
		{
			String str1 = "Game Over";
			String str2 = jGC.getScore()+"";
			g.drawString(str1,getCenteredX(str1),100);
			g.drawString(str2,getCenteredX(str2),124);
		}
			
	}
	private int getCenteredX(String str)
	{
		return 150-(int)(str.length()*digSize)/2;
	}
	public void keyPressed(KeyEvent e)
	{
		
		int keyCode = e.getKeyCode();
		if(keyCode==10)
		{
			isEnterPressed = true;
		}
		if(keyCode==90)
			isLeftPressed=true;
		if(keyCode==88)
			isRightPressed=true;
	}
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if(keyCode==10)
		{
			isEnterPressed=false;
		}
		if(keyCode==90)
			isLeftPressed=false;
		if(keyCode==88)
			isRightPressed=false;
	}
	public void mouseReleased(MouseEvent e)
	{
		p = e.getPoint();
	}

}
