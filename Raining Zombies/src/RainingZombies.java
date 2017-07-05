import java.awt.*;

import javax.swing.ImageIcon;
public class RainingZombies extends RainingZombiesGameLoop
{

	private static final long serialVersionUID = 2;
	private Image uG = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\UpGround.png").getImage();
	private Image gr = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\Ground.png").getImage();
	private Image rightI = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\Zombie Hunter Right Invincible.png").getImage();
	private Image leftI = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\Zombie Hunter Left Invincible.png").getImage();
	private Image right = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\Zombie Hunter Right.png").getImage();
	private Image left = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\Zombie Hunter Left.png").getImage();
	private Image zombieRight = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\Zombie Right.png").getImage();
	private Image zombieLeft = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\Zombie Left.png").getImage();
	private Image pF = new ImageIcon("C:\\Users\\Guy\\Desktop\\eclipsejava\\Packages\\Raining Zombies\\bin\\Ap.png").getImage();
	private Color lightYellow = new Color(255,255,100);
	private Color darkYellow = new Color(150,150,150);
	private Color darkGreen = new Color(0,150,0);
	private double digSize = 850.0/61.0;
	public void init()
	{
		setSize(850,450);
		Thread th = new Thread(this);
		th.start();
		
		offscreen = createImage(850,450);
		g2 = offscreen.getGraphics();
 		Font f = new Font(Font.MONOSPACED,Font.BOLD,24);
		g2.setFont(f);		
		addKeyListener(this);

	}
	public void paint(Graphics g)
	{

		g2.drawImage(pF,0,0,this);	
		if(startGame)
		{
			g2.setColor(Color.white);
			drawWords("Press Enter To Start", getCenteredX("Press Enter To Start"), 46);		
		}		
		else if(restartLevelUp)
		{
			g2.setColor(Color.white);			
			drawWords("Level "+level,getCenteredX("Level "+level), 46);			
		}
		else if(restartDied)
		{
			g2.setColor(Color.white);
			String info = "Final Level:"+finalLevel+"  Total Kills:"+finalKills;
			drawWords("Game Over",getCenteredX("Game Over"),30);
			drawWords(info, getCenteredX(info), 54);			
		}
		else
		{
			paintGround();
			paintPlatform(r2);
			paintPlatform(r3);
			paintPlatform(r4);
			paintPlatform(r5);
			paintPlatform(r6);
			paintPlatform(r7);
			paintPlatform(r8);
			for(Enemy e : enemies)
			{
				paintZombie(e);
			}		
			if(!(laser==null)&&laserTime<2)
			{
				g2.setColor(lightYellow);
				g2.drawLine(p1.left+10, p1.top+15,(int) laser.fX,(int) laser.fY);			
			}
			int x = p1.left;
			int y = p1.top-5;
			if(invincible>0&&facingRight)
				g2.drawImage(rightI,x,y,this);
			else if(invincible>0&&!facingRight)
				g2.drawImage(leftI,x,y,this);
			else if(invincible<1&&facingRight)
				g2.drawImage(right,x,y,this);
			else if(invincible<1&&!facingRight)
				g2.drawImage(left,x,y,this);
			//good
			String info = "Level:"+level+"  Kills:"+kills+"/"+maxKills+"  Total Kills:"+totalKills+"  Lives:"+life;
			drawWords(info,getCenteredX(info),24);
		}
		g.drawImage(offscreen,0,0,this);
	}
	public void paintPlatform(OuterRectangle r)
	{
		int x = r.left-10;
		int y = r.top;
		g2.drawImage(uG,x,y,this);
		
	}
	public void paintZombie(Enemy e)
	{
		int x = e.left;
		int y = e.top-5;
		if(e.velX<0)
			g2.drawImage(zombieLeft,x,y,this);
		else
			g2.drawImage(zombieRight,x,y,this);
		
	}
	public void paintGround()
	{

		g2.drawImage(gr,0,440,this);		
	}
	public void update(Graphics g)
	{
		paint(g);
	}
	public void drawWords(String str,int x,int y)
	{
		g2.setColor(Color.black);
		g2.drawString(str, x-2, y-2);
		((Graphics2D) g2).setPaint(new GradientPaint(0,y,darkYellow,0,y-48,darkGreen));
		g2.drawString(str, x, y);
	}
	public int getCenteredX(String str)
	{
		return 425-(int)(str.length()*digSize)/2;
	}
}