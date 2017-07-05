/** SmashGame paints and updates the SuperSmash Coders Game applet
 * @author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-03-13
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;


public class SmashGame extends SmashGameLoop
{
	private static final long serialVersionUID = 2;
	private URL url;
	private Image background;
	private Font newFont; //instance variable for the font
	private Color wordColor; //Instance variable for the color of the word
	
	//Instance variables for Images
	private Image lHL;
	private Image lHR;
	private Image lPL;
	private Image lPR;
	private Image lRL;
	private Image lRR;
	private Image lSL;
	private Image lSR;
	private Image lST;
	private Image mHL;
	private Image mHR;
	private Image mPL;
	private Image mPR;
	private Image mRL;
	private Image mRR;
	private Image mSL;
	private Image mSR;
	private Image mST;
	
	/*Instantiates all objects and instance variables in the game
	 */
	public void init()
	{
		try
		{
			url = getDocumentBase();
		}
		catch(Exception exc){}
		background = getImage(url,"Images/mario background.png");
		lHL = getImage(url,"Images/Luigi Hit Left.png");
		lHR = getImage(url,"Images/Luigi Hit Right.png");
		lPL = getImage(url,"Images/Luigi Punch Left.png");
		lPR = getImage(url,"Images/Luigi Punch Right.png");
		lRL = getImage(url,"Images/Luigi Running Left.png");
		lRR = getImage(url,"Images/Luigi Running Right.png");
		lSL = getImage(url,"Images/Luigi Shielding Left.png");
		lSR = getImage(url,"Images/Luigi Shielding Right.png");
		lST = getImage(url,"Images/Luigi Standing.png");
		mHL = getImage(url,"Images/Mario Hit Left.png");
		mHR = getImage(url,"Images/Mario Hit Right.png");
		mPL = getImage(url,"Images/Mario Punching Left.png");
		mPR = getImage(url,"Images/Mario Punching Right.png");
		mRL = getImage(url,"Images/Mario Running Left.png");
		mRR = getImage(url,"Images/Mario Running Right.png");
		mSL = getImage(url,"Images/Mario Shielding Left.png");
		mSR = getImage(url,"Images/Mario Shielding Right.png");
		mST = getImage(url,"Images/Mario Standing.png");
		setSize(850,200);
		Thread th = new Thread(this);
		th.start();		
		changeOffScreen(createImage(850,200));
		changeG2(this.getOffScreen().getGraphics()); 	
		addKeyListener(this);
		newFont = new Font(Font.MONOSPACED,Font.BOLD,24);
		wordColor=new Color(50,185,50);
		try
		{
			url = getDocumentBase();
		}
		catch(Exception exc){}
		


	}
	
	/* Paints the images on the applet
	 *@param g the Graphics image to be painted on
	 */
	public void paint(Graphics g)
	{
			
		getG2().drawImage(background,0,0,850,200,this);
		getG2().setColor(Color.black);
		if(stageCode()==0)
		{
			String str = "Press Enter To Start";
			drawWords(str,getCenteredX(str),40);
		}

		if(stageCode()==1)
		{	
			getG2().setColor(Color.black);
			getG2().drawString("Red Player", 10, 19);
			getG2().drawString("Blue Player",689,19);
			getG2().setColor(Color.red);
			((Graphics2D) getG2()).setPaint(new GradientPaint(0,25,Color.red,0,50,new Color(180,0,0)));
			getG2().fillRect(10, 25, (int)(player1().health()*2.5), 25);
			int barWidth = (int)(player2().health()*2.5);
			getG2().fillRect(840-barWidth, 25, barWidth, 25);
			paintMario();
			paintLuigi();
		}
		if(stageCode()==2)
		{
			if(winner()==1)
			{
				String str = "Red Player Wins";
				drawWords(str,getCenteredX(str),27);
			}
			else
			{
				String str = "Blue Player Wins";
				drawWords(str,getCenteredX(str),27);
				
			}
			String str = "Press Enter To Start";
			drawWords(str,getCenteredX(str),50);			
		}
		g.drawImage(getOffScreen(),0,0,this);
		
	}
	
	/* Paints the image of Luigi according to Luigi's position in the game 
	 */
	private void paintLuigi()
	{
		if(player2().isMovingLeft())
		{
			if(player2().hitTimer()>0)
				getG2().drawImage(lHL,player2().getX(),player2().getY()-156,20,40,this);
			else if(player2().isShielding())
				getG2().drawImage(lSL,player2().getX(),player2().getY()-156,20,40,this);
			else if(player2().punchTimer()>0)
				getG2().drawImage(lPL,player2().getX(),player2().getY()-156,20,40,this);
			else if(luigiRunPic()==-1&&(stepCounter()<3||player2().isFalling()))
				getG2().drawImage(lRL,player2().getX(),player2().getY()-156,20,40,this);
			else
				getG2().drawImage(lST,player2().getX(),player2().getY()-156,20,40,this);
		}
		else
		{
			if(player2().hitTimer()>0)
				getG2().drawImage(lHR,player2().getX(),player2().getY()-156,20,40,this);
			else if(player2().isShielding())
				getG2().drawImage(lSR,player2().getX(),player2().getY()-156,20,40,this);
			else if(player2().punchTimer()>0)
				getG2().drawImage(lPR,player2().getX(),player2().getY()-156,20,40,this);
			else if(luigiRunPic()==1&&(stepCounter()<3||player2().isFalling()))
				getG2().drawImage(lRR,player2().getX(),player2().getY()-156,20,40,this);
			else
				getG2().drawImage(lST,player2().getX(),player2().getY()-156,20,40,this);
		}
	}
	
	/* Paints the image of Mario according to Mario's position in the game 
	 */
	private void paintMario()
	{
		if(player1().isMovingLeft())
		{
			if(player1().hitTimer()>0)
				getG2().drawImage(mHL,player1().getX(),player1().getY()-156,20,40,this);
			else if(player1().isShielding())
				getG2().drawImage(mSL,player1().getX(),player1().getY()-156,20,40,this);
			else if(player1().punchTimer()>0)
				getG2().drawImage(mPL,player1().getX(),player1().getY()-156,20,40,this);
			else if(marioRunPic()==-1&&(stepCounter()<3||player1().isFalling()))
				getG2().drawImage(mRL,player1().getX(),player1().getY()-156,20,40,this);
			else
				getG2().drawImage(mST,player1().getX(),player1().getY()-156,20,40,this);
		}
		else
		{
			if(player1().hitTimer()>0)
				getG2().drawImage(mHR,player1().getX(),player1().getY()-156,20,40,this);
			else if(player1().isShielding())
				getG2().drawImage(mSR,player1().getX(),player1().getY()-156,20,40,this);
			else if(player1().punchTimer()>0)
				getG2().drawImage(mPR,player1().getX(),player1().getY()-156,20,40,this);
			else if(marioRunPic()==1&&(stepCounter()<3||player1().isFalling()))
				getG2().drawImage(mRR,player1().getX(),player1().getY()-156,20,40,this);
			else
				getG2().drawImage(mST,player1().getX(),player1().getY()-156,20,40,this);
		}		
	}
	
	/* Updates the graphics object in the applet
	 */
	public void update(Graphics g)
	{
		paint(g);
	}
	

	/* Draws and formats the words on the applet
	 */
	private void drawWords(String str,int x,int y)
	{
		getG2().setFont(newFont);
		getG2().setColor(Color.black);
		getG2().drawString(str, x-2, y-2);
		getG2().setColor(wordColor);
		getG2().drawString(str, x, y);
	}
	
	
	/* Returns the centered x position for the word
	 *@return the centered x position for the word
	 */
	public int getCenteredX(String str)
	{
		return 425-(int)(str.length()*(850.0/61.0))/2;
	}
	
}