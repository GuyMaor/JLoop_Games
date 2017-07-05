/** JumpGameCollections stores values on the player, bullets, platforms, enemy, and shield
 * @author Michael Hwang, Guy Maor, Enoch Yue
 * Period: 3
 * Date: 05-29-13
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;



public class JumpGameCollections
{
	Player player;
	ArrayList<BoundingRectangle> platforms;
	ArrayList<Bullet> bullets;
	Enemy enemy;
	Shield shield;
	private int score;
	
	/**
	 * Creates the JumpGameCollections object
	 * @param The player that is going to be played.
	 * @param An initial amount of platforms when the game is started
	 */
	public JumpGameCollections(Player p, ArrayList<BoundingRectangle> bR)
	{
		player = p;
		platforms = bR;
		score=0;
		bullets = new ArrayList<Bullet>();
		enemy=null;
		shield = null;
	}
	/**
	 * Gets all the platforms
	 * @return an ArrayList of the platforms
	 */
	public ArrayList<BoundingRectangle> getPlatforms()
	{
		return platforms;
	}
	
	/**
	 * Gets the player
	 * @return the player
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Creates a platform at a random location
	 * @return the platform created.
	 */
	public BoundingRectangle createPlatform()
	{
		int x = (int) (Math.random()* 240);
		int rand = (int)(Math.random()*7);
		int rand2 = (int)(Math.random()*6);
		BoundingRectangle bR = new BoundingRectangle(x, -15, 60, 15);
		if(rand==0&&enemy==null)
			enemy= new Enemy(x);
		if(rand==1)
			bR.setSpecial(rand);
		if(rand==3&&shield==null&&!player.isProtected()&&rand2==0)
			shield= new Shield(x);
		return bR;		
	}
	
	/**
	 * Creates a platform with a 1/50 probability
	 */
	public void addPlatform()
	{
		int rand = (int)(Math.random()*50);
		if((rand==0||platforms.get(platforms.size()-1).getY()>150)&&platforms.get(platforms.size()-1).getY()>5)
			platforms.add(createPlatform());		
	}
	
	/**
	 * Detes platform when the platform goes off screen
	 */
	public void deletePlatform()
	{
		for (int x = 0; x < platforms.size(); x++)
		{
			BoundingRectangle bR = platforms.get(x);
			if (bR.getY() > 600)
			{
				platforms.remove(x);
				x--;
			}
		}
	}
	
	/**
	 * Gets the bullets that the player made.
	 * @return An ArrayList of bullets.
	 */
	public ArrayList<Bullet> getBullets()
	{
		return bullets;
	}
	
	/**
	 * Controls the movement of objects in the game
	 * @return true if the player died.
	 */
	public boolean movement()
	{
		deletePlatform();
		addPlatform();
		player.moveY();
		if(player.getX()>300)
			player.moveHorTo((int)player.getX()-300);
		else if(player.getX()<0)
			player.moveHorTo((int)player.getX()+300);
		int shift = 280-(int)player.getY();
		testJump();
		if(player.getY()<280)
			shiftEverything(shift);
		if(shield!=null&&shield.getY()>600)
			shield=null;
		if(enemy!=null&&enemy.getY()>600)
			enemy=null;
		if(!bullets.isEmpty()&&bullets.get(0).isOffScreen())
			bullets.remove(0);
		if(enemy!=null)
		{
			for(int x = 0; x<bullets.size();x++)
			{
				if(bullets.get(x).didCollide(enemy))
				{
					enemy=null;
					bullets.remove(x);
					score+=500;
					break;	
				}				
			}
		}
		for(int x = 0; x<bullets.size();x++)
			bullets.get(x).move();
		if(shield!=null&&shield.didCollide(player))
		{
			shield=null;
			player.startTimer();
		}
		if(player.getY()>600)
			return true;
		if(enemy!=null&&enemy.didCollide(player))
		{
			if(player.isProtected())
				enemy=null;
			else
				return true;
		}		
		return false;	
	}
	
	/**
	 * Gets the enemy
	 * @return the enemy
	 */
	public Enemy getEnemy()
	{
		return enemy;
	}
	
	/**
	 * Gets the shield
	 * @return the shield
	 */
	public Shield getShield()
	{
		return shield;
	}
	
	/**
	 * Creates a bullet that shoots toward a target
	 * @param the point the bullet will head too.
	 */
	public void shoot(Point p)
	{
		double yDiff = p.getY()-player.getY();
		boolean yN = yDiff<0;
		double xDiff = p.getX()-(player.getX()+17.5)%300;
		boolean xN = xDiff<0;
		double dir = Math.abs(Math.atan(yDiff/xDiff));
		bullets.add(new Bullet((player.getX()+17.5)%300,xN,player.getY(),yN,dir));		
	}
	
	/**
	 * gets the score
	 * @return the score
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * Shifts game platform downward
	 * @param the amount everything should shift.
	 */
	public void shiftEverything(int shift)
	{
		player.moveVert(shift);
		score+=shift;
		for(int x = 0; x<platforms.size(); x++)
		{
			platforms.get(x).moveVert(shift);
		}
		for(int x = 0; x<bullets.size();x++)
		{
			bullets.get(x).moveVert(shift);
		}
		if(enemy!=null)
			enemy.moveVert(shift);
		if(shield!=null)
			shield.moveVert(shift);
	}
	
	/**
	 * Determines if the player should jump
	 */
	public void testJump()
	{
		for(BoundingRectangle bR : platforms)
		{
			if(bR.didCollide(player)&&bR.getY()-player.getY()>45&&player.isMovingDown())
			{
				if(bR.getSpecial()==1)
					player.bounce(20);
				else
					player.bounce(10);					
				break;
			}
		}
		if(player.getX()>240)
		{
			for(BoundingRectangle bR : platforms)
			{
				BoundingRectangle player2 = new BoundingRectangle(player.getX()-300,(int)player.getY(),45,60);
				if(bR.didCollide(player2)&&bR.getY()-player2.getY()>45&&player.isMovingDown())
				{
					if(bR.getSpecial()==1)
						player.bounce(20);
					else
						player.bounce(10);
					break;
				}
			}
		}
	}
	
	/**
	 * Moves the player horizontally
	 * @param the x Distance the player
	 * should move.
	 */
	public void movePlayer(int x)
	{
		player.moveHor(x);
	}
}
