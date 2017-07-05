
public class Enemy extends BoundedRectangle
{
	public int health;
	public int shootTimer;
	public int velocity;
	public int speedConstant;
	public int freezeCounter;
	private int FREEZE_CONSTANT = 150;
	public Enemy(int l)
	{
		super((int)(Math.random()*760),-60,40,60);
		int s = 1;
		if(l!=1)
			s=3;
		l = Math.min(l, 8);
		speedConstant = (int)(Math.random()*s)+1;
		health = (int)(Math.random()*((l+1)/2))+1;
		shootTimer = 0;
		velocity = ((int)(Math.random()*3)-1)*speedConstant;
		freezeCounter = 0;
	}
	public boolean moveEnemy()
	{
		if(!isFrozen())
		{
			moveVert(speedConstant);
			moveHor(velocity);
			if(xPos>760)
				velocity=-1*speedConstant;
			if(xPos<0)
				velocity=speedConstant;
		}
		else
			freezeCounter--;
		if(shootTimer>0)
		{
			shootTimer--;
			return false;
		}
		shootTimer=80;
		return true;
	}
	public void dealDamage(int d)
	{
		health-=d;
	}
	public void freeze()
	{
		freezeCounter = FREEZE_CONSTANT;
	}
	public boolean isFrozen()
	{
		return freezeCounter>0;
	}
	

}
