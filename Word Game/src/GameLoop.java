import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class GameLoop  extends Applet  implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1;
	public Image offscreen;
	public Graphics g2;
	public WordList words;
	public int stageCode;
	public boolean keyPressed;
	private int currentKeyPressed;
	public boolean isEnterPressed;
	public int level;	
	public int health;
	public ArrayList<Enemy> enemies;
	public int levelKills;
	public int totalKills;
	public int currentEnemyIndex;
	public String currentWord;
	public String stringQueue;
	private boolean aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx,yy,zz;
	public boolean[] alphabet;
	public int boomTimer;
	public int boomX;
	public int boomY;
	public boolean isCtrlPressed;
	public void run()
	{
		stageCode=0;
		words = new WordList();
		keyPressed=false;
		currentKeyPressed=-1;
		isEnterPressed=false;
		level=1;
		health=3;
		enemies = new ArrayList<Enemy>();
		currentEnemyIndex=-1;
		currentWord="";
		stringQueue="";
		boomTimer=0;
		boomX=-1;
		boomY=-1;
		isCtrlPressed=false;
		gameLoop();
	}
	private void gameLoop()
	{
		while(true)
		{
			while(stageCode==0)
			{
				if(isEnterPressed)
				{
					isEnterPressed=false;
					restartGame();
					stageCode=5;
				}
				pause();
				repaint();
			}
			while(stageCode==5)
			{
				if(isEnterPressed)
				{
					isEnterPressed=false;
					stageCode=1;
				}
				pause();
				repaint();
			}
			while(stageCode==1)
			{
				if(isCtrlPressed)
					stageCode4Loop();
				if(stringQueue.length()>0)
				{
					currentKeyPressed=(int)stringQueue.charAt(0);
					stringQueue=stringQueue.substring(1);
				}
				if(currentKeyPressed!=-1)								
					typeLetter();
				addEnemy();
				moveEnemies();			
				pause();
				repaint();
				if(boomTimer>0)
					boomTimer--;
				if(levelKills==level*2+8)
					stageCode=2;
				if(health==0)
					stageCode=3;
				didGetHit();
			}
			if(stageCode==2)
				levelUp();
			while(stageCode==2)
			{
				if(isEnterPressed)
					stageCode=1;
				pause();
				repaint();
			}				
			while(stageCode==3)
			{
				if(isEnterPressed)
				{
					stageCode=1;
					restartGame();
				}
				pause();
				repaint();
			}
		}
	}
	private void restartGame()
	{
		level=1;
		health=3;
		enemies = new ArrayList<Enemy>();
		levelKills=0;
		totalKills=0;
		currentEnemyIndex=-1;
		currentWord="";
		boomTimer=0;
	}
	private void stageCode4Loop()
	{
		isCtrlPressed=false;
		stageCode=4;
		while(!isCtrlPressed)
		{
			pause();
			repaint();
		}
		isCtrlPressed=false;
		stageCode=1;
		
	}
	private void levelUp()
	{
		level++;
		health++;
		levelKills=0;
		enemies = new ArrayList<Enemy>();
		boomTimer=0;
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
	private void typeLetter()
	{
		int key = currentKeyPressed;
		currentKeyPressed=-1;
		if(currentEnemyIndex==-1)
		{
			int eIndex = -1;
			for(int y = 0; y<enemies.size(); y++)
			{
				Enemy x = enemies.get(y);
				if((int)x.word.charAt(0)==key)
				{
					eIndex=y;
					break;
				}
			}
			if(eIndex!=-1)
			{
				currentEnemyIndex=eIndex;
				currentWord=enemies.get(eIndex).word.substring(1);
			}
		}
		else
		{
			if((int)currentWord.charAt(0)==key)
			{
				currentWord=currentWord.substring(1);
			}
			if(currentWord.equals(""))
			{
				Enemy d = enemies.remove(currentEnemyIndex);
				totalKills++;
				levelKills++;
				currentEnemyIndex=-1;
				boomTimer=3;
				boomX= d.xPosition;
				boomY=d.yPosition;
				
			}
		}		
	}	
	private void moveEnemies()
	{
		for(int x = 0; x<enemies.size(); x++)
		{
			Enemy e = enemies.get(x);
			e.xPosition-=2;
		}
	}
	private void addEnemy()
	{		
		int maxEnemy = level+2;
		if(maxEnemy>26)
			maxEnemy=26;
		if(enemies.size()<maxEnemy)
			enemies.add(new Enemy(1050,(int)(Math.random()*500)+100,getWord()));
	}
	private void didGetHit()
	{
		if(enemies.get(0).xPosition<=0)
		{
			health--;
			enemies.remove(0);
			if(currentEnemyIndex==0)
			{
				currentEnemyIndex=-1;
				currentWord="";
			}
			else if(currentEnemyIndex!=-1)
			{
				currentEnemyIndex--;
			}
		}
	}
	private String getWord()
	{
		if(enemies.size()<=0)
			return words.getWord();
		{
			String str = "";
			for(Enemy e: enemies)
			{
				str += e.word.substring(0,1);			
			}
			while(true)
			{
				String word = words.getWord();
				String charWord = word.substring(0,1);
				if(str.indexOf(charWord)==-1)
					return word;
			}
		}
	}
	public ArrayList<Enemy> enemiesInOrder()
	{
		ArrayList<Enemy> orderedEnemies = new ArrayList<Enemy>();
		for(int k = 0; k<enemies.size();k++)
		{
			Enemy e = enemies.get(k);
			boolean didAdd=false;
			for(int x = 0; x<orderedEnemies.size(); x++)
			{
				Enemy e2 = orderedEnemies.get(x);
				if(e.compareTo(e2)<0)
				{
					didAdd=true;
					orderedEnemies.add(x,e);
					break;
				}
			}
			if(!didAdd)
			{
				orderedEnemies.add(e);
			}
		}
		return orderedEnemies;
	}
	//Ctrl = 17
	public void keyPressed(KeyEvent arg)
	{
		int keyCode=arg.getKeyCode();
		if(keyCode==17)
			isCtrlPressed=true;
		if(stageCode!=4)
		{
		if(keyCode==10)
			isEnterPressed=true;
		if(keyCode==65&&stageCode==1&&!aa)
		{
			stringQueue+="a";
			aa=true;
		}
		if(keyCode==66&&stageCode==1&&!bb)
		{
			stringQueue+="b";
			bb=true;
		}
		if(keyCode==67&&stageCode==1&&!cc)
		{
			stringQueue+="c";
			cc=true;
		}
		if(keyCode==68&&stageCode==1&&!dd)
		{
			stringQueue+="d";
			dd=true;
		}
		if(keyCode==69&&stageCode==1&&!ee)
		{
			stringQueue+="e";
			ee=true;
		}	
		if(keyCode==70&&stageCode==1&&!ff)
		{
			stringQueue+="f";
			ff=true;
		}
		if(keyCode==71&&stageCode==1&&!gg)
		{
			stringQueue+="g";
			gg=true;
		}
		if(keyCode==72&&stageCode==1&&!hh)
		{
			stringQueue+="h";
			hh=true;
		}
		if(keyCode==73&&stageCode==1&&!ii)
		{
			stringQueue+="i";
			ii=true;
		}
		if(keyCode==74&&stageCode==1&&!jj)
		{
			stringQueue+="j";
			jj=true;
		}
		if(keyCode==75&&stageCode==1&&!kk)
		{
			stringQueue+="k";
			kk=true;
		}
		if(keyCode==76&&stageCode==1&&!ll)
		{
			stringQueue+="l";
			ll=true;
		}
		if(keyCode==77&&stageCode==1&&!mm)
		{
			stringQueue+="m";
			mm=true;
		}
		if(keyCode==78&&stageCode==1&&!nn)
		{
			stringQueue+="n";
			nn=true;
		}
		if(keyCode==79&&stageCode==1&&!oo)
		{
			stringQueue+="o";
			oo=true;
		}
		if(keyCode==80&&stageCode==1&&!pp)
		{
			stringQueue+="p";
			pp=true;
		}
		if(keyCode==81&&stageCode==1&&!qq)
		{
			stringQueue+="q";
			qq=true;
		}
		if(keyCode==82&&stageCode==1&&!rr)
		{
			stringQueue+="r";
			rr=true;
		}
		if(keyCode==83&&stageCode==1&&!ss)
		{
			stringQueue+="s";
			ss=true;
		}
		if(keyCode==84&&stageCode==1&&!tt)
		{
			stringQueue+="t";
			tt=true;
		}
		if(keyCode==85&&stageCode==1&&!uu)
		{
			stringQueue+="u";
			uu=true;
		}
		if(keyCode==86&&stageCode==1&&!vv)
		{
			stringQueue+="v";
			vv=true;
		}
		if(keyCode==87&&stageCode==1&&!ww)
		{
			stringQueue+="w";
			ww=true;
		}
		if(keyCode==88&&stageCode==1&&!xx)
		{
			stringQueue+="x";
			xx=true;
		}
		if(keyCode==89&&stageCode==1&&!yy)
		{
			stringQueue+="y";
			yy=true;
		}
		if(keyCode==90&&stageCode==1&&!zz)
		{
			stringQueue+="z";
			zz=true;
		}			
		keyPressed=true;
		}
	}
	public void keyReleased(KeyEvent arg)
	{
		int keyCode=arg.getKeyCode();
		if(keyCode==17)
			isCtrlPressed=false;
		if(keyCode==10)
			isEnterPressed=false;
		if(keyCode==65)
			aa=false;
		if(keyCode==66)
			bb=false;
		if(keyCode==67)
			cc=false;
		if(keyCode==68)
			dd=false;
		if(keyCode==69)
			ee=false;
		if(keyCode==70)
			ff=false;
		if(keyCode==71)
			gg=false;
		if(keyCode==72)
			hh=false;
		if(keyCode==73)
			ii=false;
		if(keyCode==74)
			jj=false;
		if(keyCode==75)
			kk=false;
		if(keyCode==76)
			ll=false;
		if(keyCode==77)
			mm=false;
		if(keyCode==78)
			nn=false;
		if(keyCode==79)
			oo=false;
		if(keyCode==80)
			pp=false;
		if(keyCode==81)
			qq=false;
		if(keyCode==82)
			rr=false;
		if(keyCode==83)
			ss=false;
		if(keyCode==84)
			tt=false;
		if(keyCode==85)
			uu=false;
		if(keyCode==86)
			vv=false;
		if(keyCode==87)
			ww=false;
		if(keyCode==88)
			xx=false;
		if(keyCode==89)
			yy=false;
		if(keyCode==90)
			zz=false;
	}
	public void keyTyped(KeyEvent arg0) {}
}
