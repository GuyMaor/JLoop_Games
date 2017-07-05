import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;


public class HelicopterHill extends JLoop
{

	//world is 15 by 10 blocks
	ObjectWorld world;
	int stageCode;
	final int MAX_LEVELS = 7;
	int highestLevel;
	int level;
	ArrayList<Button> levelButtons;
	ArrayList<Button> crashedButtons;
	ArrayList<Button> wonButtons;
	ArrayList<Image> rocks;
	ArrayList<Image> helis;
	ArrayList<Image> helisL;
	ArrayList<Image> startAndFinish;
	ArrayList<Image> numberedRocks;
	Image lock;
	Image mainMenu;
	Image nextLevel;
	Image retry;
	Image keyImage;
	Image doorImage;
	Image chooseALevel;
	Image levelCleared;
	Image youCrashed;
	Image beginGame;
	int heliImageCounter;
	Image cave;
	int rockCounter;
	public void createButtons()
	{
		levelButtons = new ArrayList<Button>();
		crashedButtons = new ArrayList<Button>();
		wonButtons = new ArrayList<Button>();
		heliImageCounter = 0;
		int buttonCount = 0;
		for(int y = 0; y<4; y++)
		{
			for(int x = 0; x<7; x++)
			{		
				levelButtons.add(new Button(100*x+50,300+y*100));
				buttonCount++;
				if(buttonCount>=MAX_LEVELS)
					break;
			}
			if(buttonCount>=MAX_LEVELS)
				break;			
		}
		levelButtons.get(0).unlock();
		for(int x = 0; x<2; x++)
		{
			Button b = new Button(200*x+225,300,100,100);
			b.unlock();
			crashedButtons.add(b);
		}
		for(int x = 0; x<3; x++)
		{
			Button b = new Button(200*x+125,300,100,100);
			b.unlock();
			wonButtons.add(b);
		}
	}
	public void retrieveNumberedRocks()
	{
		for(int x = 1; x<=MAX_LEVELS; x++)
		{
			numberedRocks.add(this.retrieveImage("Stones/"+x+".png"));
		}
	}
	public void retrieveHelis()
	{
		for(int x = 0; x<3; x++)
		{
			helis.add(this.retrieveImage("Stones/Heli "+x+".png"));
			helisL.add(this.retrieveImage("Stones/Heli "+x+" L.png"));
		}
	}
	public void retrieveStartAndFinish()
	{
		for(int x = 0; x<3; x++)
		{
			startAndFinish.add(this.retrieveImage("Stones/Start And Finish "+x+".png"));
		}
	}
	public Image getHeli()
	{
		heliImageCounter++;
		heliImageCounter%=3;
		Helicopter heli = (Helicopter) world.getObjectOf("Helicopter").get(0);
		if(heli.isMovingLeft())
			return helisL.get(heliImageCounter);
		return helis.get(heliImageCounter);
		
	}
	public Image getHeliImage()
	{
		heliImageCounter++;
		heliImageCounter%=3;
		return helis.get(heliImageCounter);
	}
	public void settings()
	{
		stageCode=0;
		level = 0;
		highestLevel = 1;
		mainMenu = this.retrieveImage("Stones/Main Menu.png");
		nextLevel = this.retrieveImage("Stones/Next Level.png");
		retry = this.retrieveImage("Stones/Retry.png");
		cave = this.retrieveImage("Stones/Cave.png");
		keyImage = this.retrieveImage("Stones/Key.png");
		doorImage = this.retrieveImage("Stones/Door.png");
		chooseALevel = this.retrieveImage("Stones/Choose A Level.png");
		helis = new ArrayList<Image>();
		helisL = new ArrayList<Image>();
		startAndFinish = new ArrayList<Image>();
		numberedRocks = new ArrayList<Image>();
		lock = this.retrieveImage("Stones/Lock.png");
		levelCleared = this.retrieveImage("Stones/Level Cleared.png");
		youCrashed = this.retrieveImage("Stones/You Crashed.png");
		beginGame = this.retrieveImage("Stones/Begin Game.png");
		createButtons();
		getRocks();
		retrieveHelis();
		retrieveStartAndFinish();
		retrieveNumberedRocks();
		setScreenSize(750,500);		
	}
	public void getRocks()
	{
		rockCounter = 0;
		rocks = new ArrayList<Image>();
		for(int x = 0; x<5; x++)
		{
			rocks.add(this.retrieveImage("Stones/Rock.png"));
		}
		for(int x = 1; x<=5; x++)
		{
			rocks.add(this.retrieveImage("Stones/Rock "+x+".png"));
		}
	}
	public Image getRock()
	{
		rockCounter++;
		rockCounter%=10;
		return rocks.get(rockCounter);
		
	}
	//TEMPORARY
	public void reloadWorld()
	{
		if(level==1)
		{
			setWorldI();
		}
		else if(level==2)
		{
			setWorldII();
		}
		else if(level==3)
		{
			setWorldIII();
		}
		else if(level==4)
		{
			setWorldIV();
		}
		else if(level==5)
		{
			setWorldV();
		}
		else if(level==6)
		{
			setWorldVI();
		}
		else if(level==7)
		{
			setWorldVII();
		}
	}
	public void setWorldI()
	{
		world = new ObjectWorld();
		world.addObjectType("Rock");
		world.addObjectType("Start");
		world.addObjectType("Finish");
		world.addObjectType("Helicopter");
		world.addObjectType("Key");
		world.addObject("Start", new Start(1,9,4,1));
		world.addObject("Finish", new Finish(10,9,4,1));
		world.addObject("Helicopter",new Helicopter(2,8));
		for(int x = 5; x<10; x++)
		{
			for(int y = 5; y<10; y++)
			{
				world.addObject("Rock", new Rock(x,y));
			}
		}
		for(int x = 0; x<15; x++)
		{
			world.addObject("Rock", new Rock(x,0));
		}
		for(int y = 1; y<10; y++)
		{
			world.addObject("Rock", new Rock(0,y));
			world.addObject("Rock", new Rock(14,y));
		}
	}
	public void setWorldII()
	{
		world = new ObjectWorld();
		world.addObjectType("Rock");
		world.addObjectType("Start");
		world.addObjectType("Finish");
		world.addObjectType("Helicopter");
		world.addObjectType("Key");
		world.addObject("Start", new Start(1,9,4,1));
		world.addObject("Finish", new Finish(1,4,4,1));
		world.addObject("Helicopter",new Helicopter(2,8));
		for(int x = 0; x<15; x++)
		{
			world.addObject("Rock", new Rock(x,0));
		}
		for(int y = 1; y<10; y++)
		{
			world.addObject("Rock", new Rock(0,y));
			world.addObject("Rock", new Rock(14,y));
		}
		for(int x = 5; x<11; x++)
		{
			world.addObject("Rock", new Rock(x,4));
			world.addObject("Rock", new Rock(x,5));
		}
		for(int x = 1; x<=4; x++)
		{
			world.addObject("Rock", new Rock(x,5));
		}
		for(int x = 5; x<=13; x++)
		{
			world.addObject("Rock", new Rock(x,9));
		}
		
	}
	public void setWorldIII()
	{
		world = new ObjectWorld();
		world.addObjectType("Rock");
		world.addObjectType("Start");
		world.addObjectType("Finish");
		world.addObjectType("Helicopter");
		world.addObjectType("Key");		
		world.addObject("Start", new Start(10,3,4,1));
		world.addObject("Finish", new Finish(10,9,4,1));
		world.addObject("Helicopter",new Helicopter(11,2));
		for(int x = 0; x<15; x++)
		{
			world.addObject("Rock", new Rock(x,0));
		}
		for(int y = 1; y<=9; y++)
		{
			world.addObject("Rock", new Rock(0,y));
			world.addObject("Rock", new Rock(14,y));
		}
		for(int x = 4; x<10; x++)
		{
			world.addObject("Rock", new Rock(x,3));
		}
		
		for(int x = 4; x<14; x++)
		{
			world.addObject("Rock", new Rock(x,4));
			world.addObject("Rock", new Rock(x,5));
			world.addObject("Rock", new Rock(x,6));
		}
		for(int x = 1; x<=9; x++)
		{
			world.addObject("Rock", new Rock(x,9));
		}
	}
	public void setWorldIV()
	{
		world = new ObjectWorld();
		world.addObjectType("Rock");
		world.addObjectType("Start");
		world.addObjectType("Finish");
		world.addObjectType("Helicopter");
		world.addObjectType("Key");		
		world.addObject("Start", new Start(1,3,4,1));
		world.addObject("Finish", new Finish(10,9,4,1));
		world.addObject("Helicopter",new Helicopter(2,2));
		for(int x = 0; x<15; x++)
		{
			world.addObject("Rock", new Rock(x,0));
		}
		for(int y = 1; y<=9; y++)
		{
			world.addObject("Rock", new Rock(0,y));
			world.addObject("Rock", new Rock(14,y));
		}
		for(int x = 1; x<=5; x++)
		{
			for(int y = 4; y<=9; y++)
			{
				world.addObject("Rock", new Rock(x,y));
			}
		}
		for(int x = 6; x<=10; x++)
		{
			world.addObject("Rock", new Rock(x,3));
			world.addObject("Rock", new Rock(x+3,6));
			world.addObject("Rock", new Rock(x,9));
		}
		world.addObject("Rock", new Rock(5,3));
	}
	public void setWorldV()
	{
		world = new ObjectWorld();
		world.addObjectType("Rock");
		world.addObjectType("Start");
		world.addObjectType("Finish");
		world.addObjectType("Helicopter");
		world.addObjectType("Key");
		world.addObjectType("Door");
		world.addObject("Start", new Start(5,9,4,1));
		world.addObject("Finish", new Finish(11,9,3,1));
		world.addObject("Helicopter",new Helicopter(6,8));
		for(int x = 0; x<=14; x++)
		{
			world.addObject("Rock", new Rock(x,0));
		}
		for(int y = 1; y<=9; y++)
		{
			world.addObject("Rock", new Rock(0,y));
			world.addObject("Rock", new Rock(14,y));
		}
		for(int x = 1; x<=4; x++)
		{
			world.addObject("Rock", new Rock(x,9));
		}
		for(int y = 1; y<=6; y++)
		{
			world.addObject("Rock", new Rock(4,y));
			world.addObject("Rock", new Rock(5,y));
			world.addObject("Rock", new Rock(9,y+2));
			world.addObject("Rock", new Rock(10,y+2));
		}
		world.addObject("Rock", new Rock(9,9));
		world.addObject("Rock", new Rock(10,9));
		world.addObject("Key", new Key(2,2));
		for(int x = 11; x<=13; x++)
		{
			world.addObject("Door", new Door(x,3));
		}
	}
	public void setWorldVI()
	{
		world = new ObjectWorld();
		world.addObjectType("Rock");
		world.addObjectType("Start");
		world.addObjectType("Finish");
		world.addObjectType("Helicopter");
		world.addObjectType("Key");
		world.addObjectType("Door");
		world.addObject("Start", new Start(6,9,4,1));
		world.addObject("Finish", new Finish(11,9,3,1));
		world.addObject("Helicopter",new Helicopter(7,8));
		for(int x = 0; x<=14; x++)
		{
			world.addObject("Rock", new Rock(x,0));
		}
		for(int y =0; y<=9; y++)
		{
			world.addObject("Rock", new Rock(0,y));
			world.addObject("Rock", new Rock(14,y));
		}
		for(int x = 4; x<=13; x++)
		{
			world.addObject("Rock", new Rock(x,6));
		}
		for(int x = 1; x<=10; x++)
		{
			world.addObject("Rock", new Rock(x,3));
		}
		for(int x = 1; x<6; x++)
		{
			world.addObject("Rock", new Rock(x,9));
		}
		world.addObject("Rock", new Rock(10,9));
		world.addObject("Key", new Key(1,2));
		world.addObject("Door", new Door(10,7));
		world.addObject("Door", new Door(10,8));
	}
	public void setWorldVII()
	{
		world = new ObjectWorld();
		world.addObjectType("Rock");
		world.addObjectType("Start");
		world.addObjectType("Finish");
		world.addObjectType("Helicopter");
		world.addObjectType("Key");
		world.addObjectType("Door");
		world.addObject("Start", new Start(5,9,4,1));
		world.addObject("Finish", new Finish(1,9,3,1));
		world.addObject("Helicopter",new Helicopter(6,8));
		for(int x = 0; x<=14; x++)
		{
			world.addObject("Rock", new Rock(x,0));
		}
		for(int y =0; y<=9; y++)
		{
			world.addObject("Rock", new Rock(0,y));
			world.addObject("Rock", new Rock(14,y));
		}
		for(int x = 1; x<=10; x++)
		{
			world.addObject("Rock", new Rock(x,6));
		}
		for(int y = 1; y<=3; y++)
		{
			world.addObject("Rock", new Rock(6,y));
			world.addObject("Rock", new Rock(10,y+2));
		}
		for(int x = 4; x<=5; x++)
		{
			world.addObject("Rock", new Rock(x,3));
		}
		world.addObject("Rock", new Rock(4,9));
		for(int x = 9; x<=13; x++)
		{
			world.addObject("Rock", new Rock(x,9));
		}
		//4 2
		world.addObject("Key", new Key(5,2));
		world.addObject("Door", new Door(4,7));
		world.addObject("Door", new Door(4,8));
		
	}
	
	@Override
	public void loop()
	{
		if(stageCode==0)
		{
			if(isKeyPressed(KEY_ENTER))
			stageCode=1;
		}
		if(stageCode==1)
		{
			Point clickPoint = getClickPoint();
			if(clickPoint!=null)
			{
				if(levelButtons.get(0).isOnButton(clickPoint))			
				{
					setWorldI();
					level = 1;
					stageCode=2;
				}
				else if(levelButtons.get(1).isOnButton(clickPoint))
				{
					setWorldII();
					level = 2;
					stageCode=2;
				}
				else if(levelButtons.get(2).isOnButton(clickPoint))
				{
					setWorldIII();
					level = 3;
					stageCode=2;
				}
				else if(levelButtons.get(3).isOnButton(clickPoint))
				{
					setWorldIV();
					level = 4;
					stageCode=2;
				}
				else if(levelButtons.get(4).isOnButton(clickPoint))
				{
					setWorldV();
					level = 5;
					stageCode=2;
				}
				else if(levelButtons.get(5).isOnButton(clickPoint))
				{
					setWorldVI();
					level = 6;
					stageCode=2;
				}
				else if(levelButtons.get(6).isOnButton(clickPoint))
				{
					setWorldVII();
					level = 7;
					stageCode=2;
			}
			}
			
		}
		if(stageCode==2)
		{
			Helicopter heli = (Helicopter) world.getObjectOf("Helicopter").get(0);
			Start start = (Start) world.getObjectOf("Start").get(0);
			Finish finish = (Finish) world.getObjectOf("Finish").get(0);
			if(this.isKeyPressed(KEY_UP))
			{
				if(start.didCollide(heli))
					heli.smallShift();
				heli.decelerateY();
			}
			else
			{
				heli.accelerateY();
			}
			if(start.didCollide(heli))
			{
				if(heli.isTooFast())
					crashed();
				heli.stopMotion();
				heli.moveVertTo(start.getY()-50);
			}
			boolean r = this.isKeyPressed(KEY_RIGHT);
			boolean l = this.isKeyPressed(KEY_LEFT);
			if(r)
			{
				heli.accelerateX();
			}
			if(l)
			{
				heli.decelerateX();
			}
			if(!(r||l))
				heli.slowX();
			if(world.getObjectOf("Key").size()>0)
			{
				Key key = (Key) world.getObjectOf("Key").get(0);
				if (!key.isPickedUp()&&heli.didCollide(key))
				{
					key.pickUp();
					ArrayList<GameObject> doors = world.getObjectOf("Door");
					for(GameObject door : doors)
					{
						((Door)door).open();
					}
				}
				ArrayList<GameObject> doors = world.getObjectOf("Door");
				for(GameObject door : doors)
				{
					if(!((Door)door).isOpen()&&door.didCollide(heli))
					{
						crashed();
						break;
					}
				}
			}
			ArrayList<GameObject> rocks = world.getObjectOf("Rock");
			for(GameObject rock : rocks)
			{
				if(rock.didCollide(heli))
				{
					crashed();
					break;
				}
			}
			if(finish.didCollide(heli))
			{
				if(heli.isTooFast())
					crashed();
				else
					won();
			}
			heli.move();
			
		}
		if(stageCode==3)
		{
			Point clickPoint = getClickPoint();
			if(clickPoint!=null)
			{
				if(crashedButtons.get(0).isOnButton(clickPoint))
				{
					reloadWorld();
					stageCode = 2;
				}
				else if(crashedButtons.get(1).isOnButton(clickPoint))
				{
					stageCode = 1;
				}
			}
		}
		if(stageCode==4)
		{
			Point clickPoint = getClickPoint();
			if(clickPoint!=null)
			{
				if(level>=highestLevel&&level<MAX_LEVELS)
				{
					levelButtons.get(highestLevel).unlock();
					highestLevel++;
				}
				if(wonButtons.get(0).isOnButton(clickPoint))
				{
					reloadWorld();
					stageCode = 2;
				}
				else if(wonButtons.get(1).isOnButton(clickPoint))
				{
					stageCode = 1;
				}
				else if(level<MAX_LEVELS && wonButtons.get(2).isOnButton(clickPoint))
				{
					level++;
					reloadWorld();
					stageCode=2;
				}
			}
		}
		
	}
	
	public void won()
	{
		
		stageCode=4;
	}
	public void crashed()
	{
		stageCode=3;
	}
	@Override
	public void art(Graphics g)
	{
		g.drawImage(cave,0,0,750,500,this);
		if(stageCode == 0)
		{
			this.paintObject(g,beginGame,new GameObject(0,0,750,500));
			this.paintObject(g,getHeliImage(),new GameObject(325,250,100,50));
		}
		if(stageCode == 1)
		{
			this.paintObject(g,chooseALevel,new GameObject(0,0,750,300));
			for(int x = 0; x<MAX_LEVELS; x++)
			{
				Button b = levelButtons.get(x);
				if(b.isLocked())
					this.paintObject(g,lock,b);
				else
					this.paintObject(g, numberedRocks.get(x), b);
			}
		}
		if(stageCode==2)
		{
			ArrayList<GameObject> rocks = world.getObjectOf("Rock");
			for(GameObject rock : rocks)
			{
				this.paintObject(g, getRock(), rock);
			}
			rockCounter=0;
			ArrayList<GameObject> keyList = world.getObjectOf("Key");
			if(keyList.size()>0)
			{
				GameObject key = keyList.get(0);
				if(!((Key)key).isPickedUp())
				{
					this.paintObject(g, keyImage, key);
					ArrayList<GameObject> doors = world.getObjectOf("Door");
					for(GameObject door : doors)
					{
						this.paintObject(g, doorImage, door);
					}
				}
			}
			g.setColor(Color.GREEN);
			GameObject start = world.getObjectOf("Start").get(0);
			this.paintObject(g, startAndFinish.get(0), start);
			GameObject finish = world.getObjectOf("Finish").get(0);
			if(finish.getWidth()<200)
				this.paintObject(g, startAndFinish.get(2), finish);
			else
				this.paintObject(g, startAndFinish.get(1), finish);
			g.setColor(Color.BLACK);
			GameObject helicopter = world.getObjectOf("Helicopter").get(0);
			this.paintObject(g, getHeli(), helicopter);
			
		}
		if(stageCode==3)
		{
			this.paintObject(g,youCrashed,new GameObject(0,0,750,300));
			this.paintObject(g, retry, crashedButtons.get(0));
			this.paintObject(g, mainMenu, crashedButtons.get(1));
		}
		if(stageCode==4)
		{
			this.paintObject(g,levelCleared,new GameObject(0,0,750,300));
			this.paintObject(g, retry, wonButtons.get(0));
			this.paintObject(g, mainMenu, wonButtons.get(1));
			if(level<MAX_LEVELS)
			{
				this.paintObject(g, nextLevel, wonButtons.get(2));
			}
		}
		
	}

}
