import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

public class WordDefender extends GameLoop
{
	private static final long serialVersionUID = 2;
	URL url;
	private static Image redTank;
	private static Image blueTank;
	private static Image tankBoom;
	private static Image background;
	private static Image gunBlast;
	private static Image tutorial;
	private static Image wordDefender;
	private static boolean firstTime;
	private double digSize = 850.0/61.0;
	Font defaultFont;
	Font newFont = new Font(Font.MONOSPACED,Font.BOLD,24);
	public void init()
	{	
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}
		redTank = getImage(url,"Images/Red Tank.png");
		blueTank = getImage(url,"Images/Blue Tank.png");
		tankBoom = getImage(url,"Images/Boom.png");
		background = getImage(url,"Images/Background.png");
		gunBlast = getImage(url,"Images/Gun Blast.png");
		tutorial = getImage(url,"Images/Tutorial.png");
		wordDefender = getImage(url,"Images/Word Defender.png");
		firstTime=true;
		setSize(1050,650);
		Thread th = new Thread(this);
		th.start();
		
		offscreen = createImage(1050,650);
		g2 = offscreen.getGraphics();
		defaultFont=g2.getFont();
		
		addKeyListener(this);
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}


	}
	public void paint(Graphics g)
	{
		if(firstTime)
		{
			g2.drawImage(tutorial,0,0,1050,650,this);
			firstTime=false;
		}
		g2.drawImage(background,0,0,this);
		if(stageCode==0)
		{
			g2.setFont(newFont);
			g2.drawImage(wordDefender,76,100,900,105,this);
			String str = "Press Enter To Continue";
			drawWords(str,getCenteredX(str),600);
		}
		if(stageCode==1)
		{
			g2.drawImage(blueTank,5,5,110,88,this);
			if(boomTimer>0)
				g2.drawImage(gunBlast,90,6,20,20,this);
			g2.setFont(defaultFont);
			g2.setColor(Color.black);
			ArrayList<Enemy> enemyList = enemiesInOrder();
			boolean didBoom = false;
			for(int x = 0; x<enemyList.size(); x++)
			{
				Enemy e = enemyList.get(x);
				if(e.yPosition>boomY&&boomTimer>0&&!didBoom)
				{
					g2.drawImage(tankBoom,boomX-15,boomY-40,110,66,this);
					didBoom=true;
				}
				g2.drawImage(redTank,e.xPosition-15,e.yPosition-40,110,88,this);
				if(currentEnemyIndex!=-1&&e==enemies.get(currentEnemyIndex))
					drawSpecialWord(x,enemyList);
				else
				{
					String str = e.word;
					typeWord(str,e.xPosition,e.yPosition);
				}
			}
			if(!didBoom&&boomTimer>0)
				g2.drawImage(tankBoom,boomX-15,boomY-40,110,66,this);
			g2.setFont(newFont);
			String str = "Level:"+level+"  Kills:"+levelKills+"/"+(level*2+8)+"  Total Kills:"+totalKills+"  Lives:"+health;
			drawWords(str,getCenteredX(str),25);
			g2.setFont(defaultFont);
			g2.setColor(Color.black);
			g2.drawString("Press Ctrl to pause.", 940, 15);
		}
		if(stageCode==2)
		{
			g2.setFont(newFont);
			String str = "Level "+level;
			drawWords(str,getCenteredX(str),38);
		}
		if(stageCode==3)
		{
			g2.setFont(newFont);
			String info = "Final Level:"+level+"  Total Kills:"+totalKills;
			drawWords("Game Over",getCenteredX("Game Over"),27);
			drawWords(info, getCenteredX(info), 51);		
		}
		if(stageCode==4)
		{
			g2.drawImage(tutorial,0,0,1050,650,this);
			g2.drawString("Press Ctrl to return to game.", 895, 15);
		}
		if(stageCode==5)
		{
			g2.drawImage(tutorial,0,0,1050,650,this);
			g2.setFont(newFont);
			String str = "Press Enter To Start The Game";
			drawWords(str, getCenteredX(str), 600);
		}
		
		g.drawImage(offscreen,0,0,this);		
	}
	private void drawSpecialWord(int index,ArrayList<Enemy> orderedEnemies)
	{
		Enemy e = orderedEnemies.get(index);
		String enemyWord = e.word;
		int wordLength = currentWord.length();
		String blueWord = enemyWord.substring(0, enemyWord.length()-wordLength);
		String blackWord = enemyWord.substring(enemyWord.length()-wordLength);
		g2.setColor(Color.blue);
		typeWord(blueWord,e.xPosition,e.yPosition);
		g2.setColor(Color.black);
		typeWord(blackWord,e.xPosition+10*blueWord.length(),e.yPosition);
		g2.setColor(Color.red);
		typeWord(blueWord,20,45);
		g2.setColor(Color.black);
		typeWord(blackWord,20+10*blueWord.length(),45);
		
	}
	private void typeWord(String str,int x, int y)
	{
		for(int k = 0 ; k<str.length(); k++)
		{
			g2.drawString(str.charAt(k)+"", x+10*k, y);
		}
	}
	public void update(Graphics g)
	{
		paint(g);
	}
	public void drawWords(String str,int x,int y)
	{
		g2.setColor(Color.black);
		g2.drawString(str, x-2, y-2);
		((Graphics2D) g2).setPaint(new GradientPaint(0,y-8,Color.red,0,y-9,Color.blue));
		g2.drawString(str, x, y);
	}	
	public int getCenteredX(String str)
	{
		return 525-(int)(str.length()*digSize)/2;
	}
	
}
