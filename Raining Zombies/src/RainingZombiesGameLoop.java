import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
public class RainingZombiesGameLoop extends Applet  implements Runnable, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	public Image offscreen;
	public Graphics g2;
	public OuterRectangle r1,r2,r3,r4,r5,r6,r7,r8;
	public Player p1;
	public Enemy e1;
	public ArrayList<Enemy> enemies;
	public boolean uPressed;
	public boolean lPressed;
	public boolean rPressed;
	public Laser laser;
	public int laserTime;
	public boolean spacePressed;
	public int chance;
	public int maxEnemies;
	public boolean restartDied;
	public boolean restartLevelUp;
	public int kills;
	public int life;
	public int invincible;
	public int maxKills;
	public int level;
	public int totalKills;
	public boolean enterPressed;
	public boolean startGame;
	public int maxInv;
	public int finalLevel;
	public int finalKills;
	public boolean facingRight;
	
	public void run()
	{
		r1 = new OuterRectangle(0,440,850,10);
		r2 = new OuterRectangle(750,220,100,10);
		r3 = new OuterRectangle(0,220,100,10);
		r4 = new OuterRectangle(375,220,100,10);
		r5 = new OuterRectangle(188,330,100,10);
		r6 = new OuterRectangle(562,330,100,10);
		r7 = new OuterRectangle(188,110,100,10);
		r8 = new OuterRectangle(562,110,100,10);				
		p1 = new Player(10,380,20,30);
		enemies = new ArrayList<Enemy>();
		laser = null;
		laserTime = 0;
		spacePressed=false;
		chance = 7;
		maxEnemies = 3;
		restartDied=false;
		restartLevelUp=false;
		kills = 0;
		life = 3;
		invincible=0;
		maxKills = 10;
		level=1;
		maxInv = 30;
		enterPressed=false;
		startGame=true;
		finalLevel=0;
		finalKills=0;
		facingRight = true;
		gameLoop();
	}
	
	public void gameLoop()
	{
		startGame();
		while(true)
		{
			while(true)
			{	
				manageDir();
				movePlayer();
				manageLaser();				
				moveEnemies();
				repaint();
				pause();
				testIfHit();
				testIfLevelUp();
				if(restartDied)
					break;
				if(restartLevelUp)
					break;
			}
			if(restartDied)
				restartDied();
			if(restartLevelUp)
				restartLevelUp();
		}
	}
	
	public void manageDir()
	{
		if(rPressed&&!lPressed)
			facingRight = true;
		else if(lPressed&&!rPressed)
			facingRight = false;
	}

	public void testIfLevelUp()
	{
		if(kills>=maxKills)
			restartLevelUp=true;
	}
	
	public void testIfHit()
	{
		if(invincible>0)
			invincible--;
		for(Enemy e : enemies)
		{
			if(e.didCollide(p1)&&invincible==0)
			{
				life--;
				invincible = maxInv;
			}
			if(life<1)
				restartDied=true;
		}
	}

	public void restartDied()
	{

		finalKills=totalKills;
		finalLevel=level;
		repaint();
		life=3;
		kills=0;
		totalKills=0;
		enemies.clear();
		laser = null;
		laserTime = 0;
		spacePressed=false;
		maxEnemies = 3;
		invincible = 0;
		maxKills = 10;
		level = 1;
		p1.moveHorTo(10);
		p1.moveVertTo(380);
		while(true)
		{
			if(enterPressed)
			{
				enterPressed=false;
				break;
			}
				pause();			
		}
		restartDied=false;		
		
	}

	public void startGame()
	{
		repaint();
		while(true)
		{
			if(enterPressed)
			{
				enterPressed=false;
				break;
			}
				pause();			
		}
		startGame=false;
	}

	public void restartLevelUp()
	{	
		if(level%2==0)
			maxKills+=2;
		else if(level%2==1)
			maxEnemies++;
		maxKills++;
		level++;
		repaint();
		life++;
		kills=0;
		enemies.clear();
		laser=null;
		laserTime = 0;
		spacePressed=false;
		p1.moveHorTo(10);
		p1.moveVertTo(380);		
		while(true)
		{
			if(enterPressed)
			{
				enterPressed=false;
				break;
			}
			pause();
		}
		restartLevelUp=false;		
	}

	public void movePlayer()
	{ 
		if(p1.didCollide(r1))
			playerPlatform(r1);
		else if(p1.didCollide(r2)&&p1.top<=r2.top)
			playerPlatform(r2);
		else if(p1.didCollide(r3)&&p1.top<=r3.top)
			playerPlatform(r3);
		else if(p1.didCollide(r4)&&p1.top<=r4.top)
			playerPlatform(r4);
		else if(p1.didCollide(r5)&&p1.top<=r5.top)
			playerPlatform(r5);
		else if(p1.didCollide(r6)&&p1.top<=r6.top)
			playerPlatform(r6);		
		else if(p1.didCollide(r7)&&p1.top<=r7.top)
			playerPlatform(r7);
		else if(p1.didCollide(r8)&&p1.top<=r8.top)
			playerPlatform(r8);
		else
			p1.acc=1.2;
		if(uPressed)
			p1.movePlayer(87);
		if(lPressed)
			p1.movePlayer(65);
		if(rPressed)
			p1.movePlayer(68);
		p1.movePlayer(-1);
	}

	public void moveEnemies()
	{
		manageEnemies();
		for(int x = 0; x<enemies.size(); x++)
		{
			moveEnemiesHelp(enemies.get(x));
		}
	}

	public void moveEnemiesHelp(Enemy e)
	{
		if(e.didCollide(r1))
			enemyPlatform(e,r1);
		else if(e.didCollide(r2)&&e.top<=r2.top)
			enemyPlatform(e,r2);
		else if(e.didCollide(r3)&&e.top<=r3.top)
			enemyPlatform(e,r3);
		else if(e.didCollide(r4)&&e.top<=r4.top)
			enemyPlatform(e,r4);
		else if(e.didCollide(r5)&&e.top<=r5.top)
			enemyPlatform(e,r5);	
		else if(e.didCollide(r6)&&e.top<=r6.top)
			enemyPlatform(e,r6);		
		else if(e.didCollide(r7)&&e.top<=r7.top)
			enemyPlatform(e,r7);	
		else if(e.didCollide(r8)&&e.top<=r8.top)
			enemyPlatform(e,r8);
		if(e.left<0&&e.top<350)
			e.velX=8;
		if(e.right>850&&e.top<350)
			e.velX=-8;
		else
			e.acc=1.2;
		e.moveEnemy();		
	}

	public void enemyPlatform(Enemy e, OuterRectangle r)
	{
		if(e.velX==0)
		{
			int rand = (int)(Math.random()*2);
			if(rand==0)
				e.velX=8;
			else
				e.velX=-8;
		}			
		e.acc=0;
		e.velY=0;
		e.moveVertTo(r.top-e.height);		
	}

	public void playerPlatform(OuterRectangle r)
	{
		p1.acc=0;
		p1.vel=0;
		p1.moveVertTo(r.top-p1.height);		
	}

	public void manageEnemies()
	{
		for(int x = 0; x<enemies.size(); x++)
		{
			Enemy e = enemies.get(x);
			if(e.right<0||e.left>850)
			{
				enemies.remove(x);
				x--;
			}

		}
		for(int x = 0; x<enemies.size();x++)
		{
			if(!(laser==null)&&laserTime<2)
			{
				Enemy e = enemies.get(x);
				double lX = laser.fX;
				double lY = laser.fY;
				if(lX>e.left&&lX<e.right&&lY>e.top&&lY<e.bottom)
				{
					enemies.remove(x);
					x--;
					kills++;
					totalKills++;
				}
			}			
		}
		if(enemies.size()<maxEnemies)
		{
			int rand = (int)(Math.random()*chance);
			if(rand==0)
			{
				Enemy e = new Enemy((int)(Math.random()*820),-30,20,30);
				enemies.add(e);
			}
		}
	}

	public void manageLaser()
	{
		if(!(laser==null)&&laserTime<10)
		{
			laserTime++;
		}
		else
		{
			laserTime=0;
			laser=null;
		}				

	}

	public Enemy getClosestEnemy()
	{
		Enemy bestE = null;
		double bestD=0;
		for(Enemy e : enemies)
		{
			double x = e.left-p1.left;
			double y = e.top-p1.top;
			double dist = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
			if(bestE==null||bestD>dist)
			{
				bestE = e;
				bestD = dist;
			}
		}
		return bestE;
		
	}

	public void pause()
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

	public void keyPressed(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		if(keyCode==38)
			uPressed=true;
		else if(keyCode==37)
			lPressed=true;
		else if(keyCode==39)
			rPressed=true;
		else if(keyCode==10&&(restartDied||restartLevelUp||startGame))
		{
			enterPressed=true;
		}		
		else if(keyCode==32&&laser==null&&!spacePressed&&enemies.size()>0)
		{	
			spacePressed=true;
			Enemy en = getClosestEnemy();
			int x = en.left+10;
			int y = en.top+15;
			int iX = p1.left+10;
			int iY = p1.top+15;
			laser = new Laser(iX,iY,x,y);			
		}

	}

	public void keyReleased(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		if(keyCode==38)
			uPressed=false;
		else if(keyCode==37)
			lPressed=false;
		else if(keyCode==39)
			rPressed=false;	
		else if(keyCode==32)
			spacePressed=false;
	}
	
	public void keyTyped(KeyEvent e){}
}
