

public class Player extends BoundedRectangle
{
	public int health;
	public int lives;
	public int shieldTimer;
	public int rapidFireTimer;
	public int powerShotAmount;
	public int wideShotTimer;

	public Player()
	{
		super(380,230,40,60);
		health=10;
		lives = 5;
		shieldTimer = 0;
		rapidFireTimer=0;
		powerShotAmount=0;
		wideShotTimer = 0;

	}
	public void levelUp()
	{
		moveHorTo(380);
		moveVertTo(230);
		health=10;
		shieldTimer = 0;
		rapidFireTimer=0;
		powerShotAmount=0;
		wideShotTimer = 0;
	}
	public void resetGame()
	{
		levelUp();
		lives=5;
	}
	public void resetHealth()
	{
		health=10;
	}
	public void dealDamage()
	{
		if(shieldTimer<=0)
			health--;
	}
	public void workTimers()
	{
		if(shieldTimer>0)
			shieldTimer--;
		if(rapidFireTimer>0)
			rapidFireTimer--;	
		if(wideShotTimer>0)
			wideShotTimer--;
	}
	public boolean fire()
	{
		if(powerShotAmount>0)
		{
			powerShotAmount--;
			return true;
		}
		return false;
	}
	public void setRapidFire()
	{
		rapidFireTimer=50;
	}
	public void setShieldTimer()
	{
		shieldTimer=200;
	}
	public void setPowerShot()
	{
		powerShotAmount=30;
	}
	public void setWideShotTimer()
	{
		wideShotTimer=170;
	}
	public void looseLife()
	{
		lives--;
	}
	public void heal()
	{
		health=10;
	}
}
