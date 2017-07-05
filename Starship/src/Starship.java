import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Starship extends JLoop
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int level;
	Player player;
	ArrayList<Enemy> enemies;
	int stageCode;
	boolean isEnterPressed;
	ArrayList<EnemyBullet> eBullets;
	ArrayList<PlayerBullet> pBullets;
	ArrayList<PowerUp> powerUps;
	boolean isLeftPressed;
	boolean isRightPressed;
	boolean isUpPressed;
	boolean isDownPressed;
	boolean isSpacePressed;
	boolean isSpaceReallyPressed;
	int levelKills;
	int totalKills;
	public Image pShip;
	public Image eShip;
	public Image eBullet;
	public Image pBullet;
	public Image powBullet;
	public Image health;
	public Image powerShot;
	public Image rapidFire;
	public Image shield;
	public Image wideShot;
	public Image shieldPlayer;
	public Image background;
	public Image playerLives;
	public Image frozenEnemy;
	public Image freezeEnemy;
	public Image explosion;
	private double digSize = 850.0/61.0;
	public void settings()
	{
		level=1;
		player = new Player();
		enemies = new ArrayList<Enemy>();
		powerUps = new ArrayList<PowerUp>();
		stageCode=0;
		isEnterPressed=false;
		setScreenSize(800,500);
		isLeftPressed=false;
		isRightPressed=false;
		isUpPressed=false;
		isDownPressed=false;
		isSpacePressed=false;
		isSpaceReallyPressed=false;
		levelKills=0;
		totalKills=0;
		pShip = retrieveImage("Images/Player Ship.png");
		eShip = retrieveImage("Images/Enemy Ship.png");
		eBullet = retrieveImage("Images/Enemy Bullet.png");
		pBullet = retrieveImage("Images/Player Bullet.png");
		powBullet = retrieveImage("Images/Power Bullet.png");
		health= retrieveImage("Images/Health.png");
		powerShot= retrieveImage("Images/Power Shot.png");
		rapidFire= retrieveImage("Images/Rapid Fire.png");
		shield= retrieveImage("Images/Shield.png");
		wideShot= retrieveImage("Images/Wide Shot.png");
		shieldPlayer = retrieveImage("Images/Shield Player.png");
		background = retrieveImage("Images/Background.png");
		playerLives = retrieveImage("Images/Player Lives.png");
		frozenEnemy = retrieveImage("Images/Frozen Enemy.png");
		freezeEnemy = retrieveImage("Images/Freeze.png");
		explosion = retrieveImage("Images/Explosion.png");
	}


	public void loop()
	{	
		//Start Menu
		if(stageCode==0)
		{
			if(isEnterPressed)
			{
				isEnterPressed=false;
				stageCode=1;
				resetGame();
			}
		}
		//Play Game
		if(stageCode==1)  
		{
			addEnemy();
			moveEverything();
			removeStuff();
			if(didLoseALife()&&player.lives<=1)
				stageCode=4;
			else if(didLoseALife())
			{			
				stageCode=3;
			}
			if(levelKills>=level*3+7)
				stageCode=2;
		}
		//Level Up
		if(stageCode==2)
		{
			if(isEnterPressed)
			{				
				isEnterPressed=false;
				stageCode=1;
				levelUp();
			}
		}
		//Lost A life
		if(stageCode==3)
		{
			if(isEnterPressed)
			{
				player.looseLife();					
				isEnterPressed=false;
				stageCode=1;
				resetLevel();
			}
		}
		//Game Over
		if(stageCode==4)
		{
			if(isEnterPressed)
			{
				isEnterPressed=false;
				stageCode=1;
				resetGame();
			}
		}
		
		
	}
	public void addEnemy()
	{
		if(enemies.size()<(level)/2+3)
		{
			enemies.add(new Enemy(level));
		}
	}
	public void removeStuff()
	{
		for(int e = 0; e<enemies.size();e++)
		{
			Enemy enemy = enemies.get(e);
			if(enemy.yPos>=500)
			{
				enemies.remove(e);
				e--;
			}
			else if(enemy.health<=0)
			{
				addPowerUp((int)enemy.xPos,(int)enemy.yPos);
				enemies.remove(e);
				levelKills++;
				totalKills++;
				e--;
			}
			else
			{
				for(int b = 0; b<pBullets.size();b++)
				{
					PlayerBullet pB = pBullets.get(b);
					if(pB.yPos<=pB.height*-1||pB.xPos>=800||pB.xPos-pB.width<=0)
					{
						pBullets.remove(b);
						b--;
					}
					else if(enemy.didCollide(pB))
					{
						if(pB.isPower)
							enemy.dealDamage(3);
						enemy.dealDamage(1);
						pBullets.remove(b);
						b--;

					}
				}
			}
		}
		for(int b = 0; b<eBullets.size(); b++)
		{
			EnemyBullet eBullet = eBullets.get(b);
			if(eBullet.yPos+eBullet.height>=500)
			{
				eBullets.remove(b);
				b--;
			}
			else if(eBullet.didCollide(player))
			{
				eBullets.remove(b);
				b--;
				player.dealDamage();
			}

		}
		for(int x = 0; x<powerUps.size();x++)
		{
			PowerUp p = powerUps.get(x);
			if(p.isDone())
			{
				powerUps.remove(x);
				x--;
			}
			else if(p.didCollide(player))
			{
				powerUps.remove(x);
				x--;
				usePowerUp(p.specialValue);
			}
		}
	}
	public void moveEverything()
	{
		player.workTimers();
		for(int x = 0 ; x<enemies.size();x++)
		{
			Enemy e = enemies.get(x);
			if(e.moveEnemy())
				eBullets.add(new EnemyBullet((int)e.xPos+18,(int)e.yPos+60));
		}
		for(int x = 0; x<eBullets.size(); x++)
		{
			eBullets.get(x).moveBullet();
		}
		for(int x = 0; x<pBullets.size(); x++)
		{
			pBullets.get(x).moveBullet();
		}
		if(isUpPressed)
			player.moveVert(-10);
		if(isDownPressed)
			player.moveVert(10);
		if(isLeftPressed)
			player.moveHor(-10);
		if(isRightPressed)
			player.moveHor(10);
		if(isSpacePressed||player.rapidFireTimer>0)
		{
			isSpacePressed=false;		
			boolean isPower = player.fire();
			pBullets.add(new PlayerBullet(1,(int)player.xPos+18,(int)player.yPos,isPower));
			if(player.wideShotTimer>0)
			{
				pBullets.add(new PlayerBullet(0,(int)player.xPos+18,(int)player.yPos,isPower));
				pBullets.add(new PlayerBullet(2,(int)player.xPos+18,(int)player.yPos,isPower));
			}
		}
		if(player.xPos<0)
			player.moveHorTo(0);
		if(player.xPos>800-player.width)
			player.moveHorTo(800-player.width);
		if(player.yPos<0)
			player.moveVertTo(0);
		if(player.yPos>500-player.height)
			player.moveVertTo(500-player.height);
		
	}
	public void addPowerUp(int x, int y)
	{
		int rand = (int)(Math.random()*22);
		if(rand>=0&&rand<=6)
			powerUps.add(new PowerUp(x,y,rand));
	}
	public void usePowerUp(int p)
	{
		
		if(p==0)
			player.heal();
		if(p==1)
			player.setShieldTimer();
		if(p==2)
			player.setPowerShot();
		if(p==3)
			player.setRapidFire();
		if(p==4)
			player.setWideShotTimer();
		if(p == 5)
			freezeEnemies();
		if(p == 6)
			createExplosion();
			

	}
	public void levelUp()
	{
		level++;
		levelKills=0;
		player.levelUp();
		enemies = new ArrayList<Enemy>();
		eBullets = new ArrayList<EnemyBullet>();
		pBullets = new ArrayList<PlayerBullet>();
		powerUps = new ArrayList<PowerUp>();
	}
	public void resetGame()
	{
		level=1;
		levelKills=0;
		totalKills=0;
		player.resetGame();
		enemies = new ArrayList<Enemy>();
		eBullets = new ArrayList<EnemyBullet>();
		pBullets = new ArrayList<PlayerBullet>();
		powerUps = new ArrayList<PowerUp>();
	}
	public void resetLevel()
	{
		totalKills-=levelKills;
		player.levelUp();
		levelKills=0;
		enemies = new ArrayList<Enemy>();
		eBullets = new ArrayList<EnemyBullet>();
		pBullets = new ArrayList<PlayerBullet>();
		powerUps = new ArrayList<PowerUp>();
	}
	public boolean didLoseALife()
	{
		if(player.shieldTimer<=0)
		{
			for(Enemy e : enemies)
			{
				if(e.didCollide(player))
					return true;
			}
		}
		return player.health<=0;
	}


	public void art(Graphics g)
	{
		Font f = new Font(Font.MONOSPACED,Font.BOLD,24);
		g.setFont(f);
		g.setColor(Color.blue);
		paintPicture(g,background,0,0,800,500);
		if(stageCode==0)
		{
			String str = "Press Enter To Start";
			g.drawString(str, getCenteredX(str), 30);
		}
		if(stageCode==1)
		{
			if(player.shieldTimer>0)
				paintPicture(g,shieldPlayer,(int)player.xPos-10,(int)player.yPos,60,player.height);
			else
				paintPicture(g,pShip,(int)player.xPos,(int)player.yPos,player.width,player.height);//Real picture
			for(Enemy e : enemies)
			{
				if(e.isFrozen())
					paintPicture(g,frozenEnemy,(int)e.xPos,(int)e.yPos,e.width,e.height);
				else
					paintPicture(g,eShip,(int)e.xPos,(int)e.yPos,e.width,e.height);
			}
			for(PlayerBullet e : pBullets)
			{
				if(e.isPower)
					paintPicture(g,powBullet,(int)e.xPos,(int)e.yPos,e.width,e.height);
				else
					paintPicture(g,pBullet,(int)e.xPos,(int)e.yPos,e.width,e.height);
			}
			for(EnemyBullet e : eBullets)
			{
				paintPicture(g,eBullet,(int)e.xPos,(int)e.yPos,e.width,e.height);
			}
			for(PowerUp p : powerUps)
			{
				paintPowerUps(g,p);
			}
			String info = "Level:"+level+"  Kills:"+levelKills+"/"+(level*3+7)+"  Total Kills:"+totalKills+"  Health:"+player.health+"  Lives:"+player.lives;
			g.drawString(info, getCenteredX(info), 30);
		}
		if(stageCode==2)
		{
			String str = "Level "+(level+1);
			g.drawString(str, getCenteredX(str), 30);			
		}
		if(stageCode==3)
		{
			f = new Font(Font.MONOSPACED,Font.BOLD,72);
			g.setFont(f);
			paintPicture(g, playerLives,340,220,80,60);
			g.drawString(""+(player.lives-1),424,275);
		}
		if(stageCode==4)
		{
			String info = "Game Over";
			g.drawString(info, getCenteredX(info),30);
			info = "Final Level:"+level+"  Total Kills:"+totalKills;
			g.drawString(info, getCenteredX(info),60);
		}
	}
	private void paintPowerUps(Graphics g, PowerUp p)
	{
		int val = p.specialValue;
		if(val==0)
			paintPicture(g,health,(int)p.xPos,(int)p.yPos,p.width,p.height);
		else if(val==1)
			paintPicture(g,shield,(int)p.xPos,(int)p.yPos,p.width,p.height);
		else if(val==2)
			paintPicture(g,powerShot,(int)p.xPos,(int)p.yPos,p.width,p.height);
		else if(val==3)
			paintPicture(g,rapidFire,(int)p.xPos,(int)p.yPos,p.width,p.height);
		else if(val==4)
			paintPicture(g,wideShot,(int)p.xPos,(int)p.yPos,p.width,p.height);
		else if(val==5)
			paintPicture(g,freezeEnemy,(int)p.xPos,(int)p.yPos,p.width,p.height);
		else
			paintPicture(g,explosion,(int)p.xPos,(int)p.yPos,p.width,p.height);
	}
	public int getCenteredX(String str)
	{
		return 400-(int)(str.length()*digSize)/2;
	}
	/*
	 * left==37
	 * right==39
	 * up==38
	 * down==40
	 * spacebar==32
	 */
	public void keyPressed(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		System.out.println(keyCode);
		if(keyCode==10)
			isEnterPressed=true;
		if(keyCode==37)
			isLeftPressed=true;
		if(keyCode==39)
			isRightPressed=true;
		if(keyCode==38)
			isUpPressed=true;
		if(keyCode==40)
			isDownPressed=true;
		if(keyCode==32&&!isSpaceReallyPressed)
		{
			isSpaceReallyPressed=true;
			isSpacePressed=true;
		}
			
	}
	public void keyReleased(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		if(keyCode==10)
			isEnterPressed=false;
		if(keyCode==37)
			isLeftPressed=false;
		if(keyCode==39)
			isRightPressed=false;
		if(keyCode==38)
			isUpPressed=false;
		if(keyCode==40)
			isDownPressed=false;
		if(keyCode==32)
		{
			isSpaceReallyPressed=false;
			isSpacePressed=false;
		}
			
	}
	public void freezeEnemies()
	{
		for(Enemy e : enemies)
		{
			e.freeze();
		}
	}
	public void createExplosion()
	{
		for(int x = 0; x<20; x++)
		{
			PlayerBullet b = new PlayerBullet(1,(int)player.xPos+18,(int)player.yPos,true);
			b.setDirection(Math.PI/10*x);
			pBullets.add(b);
		}
	}
	
}
