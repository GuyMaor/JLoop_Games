/** SmashGameLoop opperates the objects in the game and the interactions between the Objects
 * @author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-03-13
 */

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SmashGameLoop extends Applet  implements Runnable, KeyListener
{
	//Instance Variables
	private static final long serialVersionUID = 1;
	private Image offscreen;
	private Graphics g2;
	private int stageCode; //a number that determines if the user is on the start page, fighting page, or the restart page
	private static final int START_MENU = 0;
	private static final int FIGHTING = 1;
	private static final int RESTART = 2;
	
	private BoundedRectangle ground;
	private Player player1;
	private Player player2;
	private static final int MARIO = 1;
	private static final int LUIGI = 2;
	
	private boolean isEnterPressed;
	private boolean isAPressed;
	private boolean isWPressed;
	private boolean isDPressed;
	private boolean isFPressed;
	private boolean isLeftPressed;
	private boolean isUpPressed;
	private boolean isRightPressed;
	private boolean is1Pressed;
	private int winner;
	private static final int INIT_X = 0;
	private static final int INIT_Y = 340;
	private static final int INIT_W = 850;
	private static final int INIT_H = 10;
	
	private int luigiRunPic;
	private int marioRunPic;
	private int stepCounter;
	
	/**
	 * Instantiates all the variables and then runs the loop for the game.
	 */
	public void run()
	{
		stageCode=0;
		ground = new BoundedRectangle(INIT_X, INIT_Y, INIT_W, INIT_H);
		isEnterPressed=false;
		isAPressed=false;
		isWPressed=false;
		isDPressed=false;
		isFPressed=false;
		isLeftPressed=false;
		isUpPressed=false;
		isRightPressed=false;
		is1Pressed=false;
		winner=0;
		luigiRunPic=0;
		marioRunPic=0;
		stepCounter=0;
		gameLoop();
	}
	/**
	 * Represents the loop for the game and determines the Objects to be painted
	 */
	private void gameLoop()
	{
		while(true)
		{
			while(stageCode==0)
			{
				pause();
				repaint();					
				if(isEnterPressed)
					stageCode= FIGHTING;			
			}
			if(stageCode== FIGHTING)
			{				
				resetPlayers();
			}
			combatLoop();
			while(stageCode== RESTART)
			{
				pause();
				repaint();					
				if(isEnterPressed)
					stageCode= FIGHTING;				
			}		
		}
	}
	/**
	 * Represents the loop of the game when players are fighting
	 */
	private void combatLoop()
	{
		while(stageCode== FIGHTING)
		{
			playerMovement();			
			if(player1.punchTimer()>0)
				player1.punchTimer(-1);
			if(player2.punchTimer()>0)
				player2.punchTimer(-1);
			if(player2.hitTimer()>0)
				player2.hitTimer(-1);
			if(player1.hitTimer()>0)
				player1.hitTimer(-1);
			if(player2.health()<=0)
			{
				winner= MARIO;
				stageCode= RESTART;
			}
			if(player1.health()<=0)
			{
				winner= LUIGI;
				stageCode= RESTART;
			}
			stepCounter = (stepCounter+1)%6;
			pause();
			repaint();
		}		
	}
	
	/**
	 * Determines what happens when player 1 punches
	 */
	private void player1Punch()
	{		
		BoundedRectangle bR = generatePunchRectangle(player1);
		if(player2.didCollide(bR)&&player1.punchTimer()<=0&&!player1.isShielding()&&player1.hitTimer()<=0)
		{			
			if(!player2.isShielding())
			{
				if(player1.isMovingLeft())
				{
					player2.isMovingLeft(false);
					player2.moveHor(-5);
				}
				else
				{
					player2.isMovingLeft(true);
					player2.moveHor(5);				
				}			
				player2.hitTimerStart();				
			}
			player2.dealDamage();
		}
		if(player1.punchTimer()<=0&&player1.hitTimer()<=0&&!player1.isShielding())		
			player1.punchTimer(10);	
	}
	
	/**
	 * Determines what happens when player 2 punches
	 */
	private void player2Punch()
	{	
		BoundedRectangle bR = generatePunchRectangle(player2);
		if(player1.didCollide(bR)&&player2.punchTimer()<=0&&!player2.isShielding()&&player2.hitTimer()<=0)
		{
			if(!player1.isShielding())
			{
				if(player2.isMovingLeft())
				{
					player1.isMovingLeft(false);
					player1.moveHor(-5);
				}
				else
				{
					player1.isMovingLeft(true);
					player1.moveHor(5);				
				}
				player1.hitTimer(10);
			}
			player1.dealDamage();
		}
		if(player2.punchTimer()<=0&&player2.hitTimer()<=0&&!player2.isShielding())
			player2.punchTimer(10);		
	}
	
	/**
	 * Controls the player's arrow movements and moves the player left, right, or up
	 */
	private void playerMovement()
	{
		marioRunPic=0;
		if(isAPressed&&player1.punchTimer()<=0&&player1.hitTimer()<=0&&!player1.isShielding())
		{
			player1.moveLeft();
			marioRunPic--;
		}
		if(isDPressed&&player1.punchTimer()<=0&&player1.hitTimer()<=0&&!player1.isShielding())
		{
			player1.moveRight();
			marioRunPic++;
		}
		if(marioRunPic==-1)
			player1.isMovingLeft(true);
		if(marioRunPic==1)
			player1.isMovingLeft(false);
		luigiRunPic=0;
		if(isLeftPressed&&player2.punchTimer()<=0&&player2.hitTimer()<=0&&!player2.isShielding())
		{	
			player2.moveLeft();
			luigiRunPic--;
		}
		if(isRightPressed&&player2.punchTimer()<=0&&player2.hitTimer()<=0&&!player2.isShielding())
		{
			player2.moveRight();
			luigiRunPic++;
		}
		if(luigiRunPic==-1)
			player2.isMovingLeft(true);
		if(luigiRunPic==1)
			player2.isMovingLeft(false);
				
		if(isWPressed&&!player1.isFalling()&&player1.hitTimer()<=0&&!player1.isShielding()&&player1.punchTimer()<=0)
			player1.jump();
		if(player1.didCollide(ground)&&player1.isFalling())
			player1.land(300);
		player1.movePlayerVertically();
			
		if(isUpPressed&&!player2.isFalling()&&player2.hitTimer()<=0&&!player2.isShielding()&&player2.punchTimer()<=0)
			player2.jump();
		if(player2.didCollide(ground)&&player2.isFalling())
			player2.land(300);
		player2.movePlayerVertically();	
		
		if(player1.getX()<0)
			player1.moveHorTo(0);
		if(player2.getX()<0)
			player2.moveHorTo(0);
		if(player1.getX()>830)
			player1.moveHorTo(830);
		if(player2.getX()>830)
			player2.moveHorTo(830);
	}

	/**
	 *Resets the players' locations and health.
	 */
	private void resetPlayers()
	{
		player1 = new Player(10,300,20,40,false);
		player2 = new Player(820,300,20,40,true);		
	}

	/**
	 * Pauses the game for .04 seconds. 
	 */
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
	/**
	 * Generates a rectangle to represent a Player punching
	 * @param the player that is punching
	 * @return the rectangle representing the Player
	 */
	private BoundedRectangle generatePunchRectangle(Player p)
	{
		BoundedRectangle bR = new BoundedRectangle(p.getX(),p.getY(),p.getWidth(),p.getHeight());
		if(p.isMovingLeft())
			bR.moveHor(-20);
		else
			bR.moveHor(20);
		return bR;
	}
	
	/**
	 * Returns Player 1
	 * @return Mario player
	 */
	public Player player1()
	{
		return player1;
	}
	/**
	 * Returns Luigi player
	 * @return Luigi player
	 */
	public Player player2()
	{
		return player2;
	}
	/**
	 * Returns the Stage Code value
	 * @return the stage Code
	 */
	public int stageCode()
	{
		return stageCode;
	}
	/**
	 * Returns the image on the screen
	 * @return the image on the screen
	 */
	public Image getOffScreen()
	{
		return offscreen;
	}
	/**
	 * Sets the overall image on the screen
	 * @param the overall image
	 */
	public void changeOffScreen(Image i)
	{
		offscreen = i;
	}
	/**
	 * Returns the graphics for the offscreen
	 * @return the graphics for offscreen
	 */
	public Graphics getG2()
	{
		return g2;
	}
	/**
	 * Chances the graphic to the image on the screen
	 * @param the graphic to the screen
	 */
	public void changeG2(Graphics g)
	{
		g2=g;
	}
	/** 
	 * Returns the winner of the game
	 * @return 1 if winner is Mario,
	 * 2 if winner is Luigi
	 */
	public int winner()
	{
		return winner;
	}
	public int marioRunPic()
	{
		return marioRunPic;
	}
	public int luigiRunPic()
	{
		return luigiRunPic;
	}
	public int stepCounter()
	{
		return stepCounter;
	}

	/**
	 * Determines what happens if when a key is pressed
	 * @param the key that is pressed
	 */
	public void keyPressed(KeyEvent arg)
	{
		int keyCode=arg.getKeyCode();
		if(keyCode==65)
		{
			isAPressed=true;
		}
		if(keyCode==87)
			isWPressed=true;
		if(keyCode==68)
		{
			isDPressed=true;
		}
		if(keyCode==70)
		{	
			if(!isFPressed)
				player1Punch();
			isFPressed=true;
		}
		if(keyCode==71)
		{
			if(player1.punchTimer()<=0&&player1.hitTimer()<=0)
				player1.isShielding(true);
		}
		if(keyCode==37)
		{
			isLeftPressed=true;
		}
		if(keyCode==38)
			isUpPressed=true;
		if(keyCode==39)
		{
			isRightPressed=true;
		}
		if(keyCode==10)		
			isEnterPressed=true;
		if(keyCode==47)
		{
			if(!is1Pressed)
				player2Punch();
			is1Pressed=true;			
		}
		if(keyCode==46)
		{
			if(player2.punchTimer()<=0&&player2.hitTimer()<=0)
				player2.isShielding(true);			
		}
	}
	/**
	 * Determines what happens when a key is released
	 * @param the key that is released.
	 */
	public void keyReleased(KeyEvent arg)
	{
		int keyCode=arg.getKeyCode();
		if(keyCode==65)
			isAPressed=false;
		if(keyCode==87)
			isWPressed=false;
		if(keyCode==68)
			isDPressed=false;
		if(keyCode==70)
			isFPressed=false;
		if(keyCode==71)
			player1.isShielding(false);			
		if(keyCode==37)
			isLeftPressed=false;
		if(keyCode==38)
			isUpPressed=false;
		if(keyCode==39)
			isRightPressed=false;
		if(keyCode==10)		
			isEnterPressed=false;
		if(keyCode==47)
			is1Pressed=false;
		if(keyCode==46)
			player2.isShielding(false);			
	}
	
	/**
	 * Receives the key that is typed
	 * @param the key that is typed
	 */
	public void keyTyped(KeyEvent arg){}
}