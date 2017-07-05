import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.net.URL;

public class Particles extends GameLoop
{
	URL url;	
	private static final long serialVersionUID = 2;
	private static Image start;
	private static Image button1;
	private static Image button2;
	private static Image button3;
	private static Image button4;
	private static Image button5;
	private static Image tryAgainButton;
	private static Image backToMenuButton;
	private static Image nextStageButton;
	private static Image lock;
	private static Image blueOrb;
	private static Image redOrb;
	private static Image greenOrb;
	private static Image blueCore;
	private static Image redCore;
	private static Image greenCore;
	private static Image startCore;
	private static Image backGround;
	private static Image title;
	private static Image endCore;
	private static Image stageComplete;
	private static Image stageFailed;
	public void init()
	{
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}	
		title = getImage(url,"Images/Particles.png");
		blueOrb = getImage(url,"Images/Blue Orb.png");
		redOrb = getImage(url,"Images/Red Orb.png");
		greenOrb = getImage(url,"Images/Green Orb.png");
		blueCore = getImage(url,"Images/Blue Core.png");
		redCore = getImage(url,"Images/Red Core.png");
		greenCore = getImage(url,"Images/Green Core.png");
		startCore = getImage(url,"Images/Start Core.png");
		start = getImage(url,"Images/Start Button.png");
		button1 = getImage(url,"Images/1 Button.png");
		button2 = getImage(url,"Images/2 Button.png");
		button3 = getImage(url,"Images/3 Button.png");
		button4 = getImage(url,"Images/4 Button.png");
		button5 = getImage(url,"Images/5 Button.png");
		lock = getImage(url,"Images/Lock.png");
		tryAgainButton = getImage(url,"Images/Try Again Button.png");
		backToMenuButton = getImage(url,"Images/Back To Menu Button.png");
		nextStageButton = getImage(url,"Images/Next Stage Button.png");
		backGround = getImage(url,"Images/Grid.png");
		endCore = getImage(url,"Images/End.png");
		stageComplete = getImage(url,"Images/Stage Complete.png");
		stageFailed = getImage(url,"Images/Stage Failed.png");
		
		setSize(850,450);
		Thread th = new Thread(this);
		th.start();		
		offscreen = createImage(850,450);
		g2 = offscreen.getGraphics();		
		addKeyListener(this);
		addMouseListener(this);
		try
		{
			url = getDocumentBase();
		}catch(Exception exc){}


	}
	public void paint(Graphics g)
	{
		g2.drawImage(backGround,0,0,this);
		if(stageCode==0)
		{
			g2.drawImage(title,25,35,this);
			g2.drawImage(start,startButton.xLocation,startButton.yLocation,startButton.width,startButton.height,this);
		}
		else if(stageCode==1)
		{
			g2.drawImage(button1,stage1Button.xLocation,stage1Button.yLocation,stage1Button.width,stage1Button.height,this);
			if(!levelComplete[0])
				g2.drawImage(lock,stage2Button.xLocation,stage2Button.yLocation,stage2Button.width,stage2Button.height,this);				
			g2.drawImage(button2,stage2Button.xLocation,stage2Button.yLocation,stage2Button.width,stage2Button.height,this);
			if(!levelComplete[1])
				g2.drawImage(lock,stage3Button.xLocation,stage3Button.yLocation,stage3Button.width,stage3Button.height,this);				
			g2.drawImage(button3,stage3Button.xLocation,stage3Button.yLocation,stage3Button.width,stage3Button.height,this);
			if(!levelComplete[2])
				g2.drawImage(lock,stage4Button.xLocation,stage4Button.yLocation,stage4Button.width,stage4Button.height,this);				
			g2.drawImage(button4,stage4Button.xLocation,stage4Button.yLocation,stage4Button.width,stage4Button.height,this);
			if(!levelComplete[3])
				g2.drawImage(lock,stage5Button.xLocation,stage5Button.yLocation,stage5Button.width,stage5Button.height,this);			
			g2.drawImage(button5,stage5Button.xLocation,stage5Button.yLocation,stage5Button.width,stage5Button.height,this);			
		}
		else if(stageCode==2)
		{
			
			
			if(colorKeyPressed==65)
				g2.drawImage(redCore,(int)player.getXPos()-20,(int) player.getYPos()-20, 40, 40,this);
			else if(colorKeyPressed==83)
				g2.drawImage(greenCore,(int)player.getXPos()-20,(int) player.getYPos()-20, 40,40,this);
			else if(colorKeyPressed==68)
				g2.drawImage(blueCore,(int)player.getXPos()-20,(int) player.getYPos()-20, 40, 40,this);
			else
				g2.drawImage(startCore,(int)player.getXPos()-20,(int) player.getYPos()-20, 40, 40,this);							
			g2.drawImage(endCore,(int)finishLocation.getX()-30,(int)finishLocation.getY()-30, 60,60,this);
			for(StationaryObject obj : redObjects)
			{
				g2.drawImage(redOrb,(int)obj.getXPos()-30,(int)obj.getYPos()-30, 60, 60,this);

			}
			for(StationaryObject obj : greenObjects)
			{
				g2.drawImage(greenOrb,(int)obj.getXPos()-30,(int)obj.getYPos()-30, 60, 60,this);				
			}
			for(StationaryObject obj : blueObjects)
			{
				g2.drawImage(blueOrb,(int)obj.getXPos()-30,(int)obj.getYPos()-30, 60, 60,this);				
			}			
		}
		else if(stageCode==3)
		{
			g2.drawImage(stageFailed,25,35,this);
			g2.drawImage(tryAgainButton,tryAgain2.xLocation,tryAgain2.yLocation,tryAgain2.width,tryAgain2.height,this);
			g2.drawImage(backToMenuButton,backToMenu2.xLocation,backToMenu2.yLocation,backToMenu2.width,backToMenu2.height,this);			
		}
		else if(stageCode==4)
		{
			g2.drawImage(stageComplete,25,35,this);
			if(currentStage!=stages.level5)
			{
				g2.drawImage(nextStageButton,nextLevel.xLocation,nextLevel.yLocation,nextLevel.width,nextLevel.height,this);
				g2.drawImage(tryAgainButton,tryAgain.xLocation,tryAgain.yLocation,tryAgain.width,tryAgain.height,this);
				g2.drawImage(backToMenuButton,backToMenu.xLocation,backToMenu.yLocation,backToMenu.width,backToMenu.height,this);
			}
			else
			{
				g2.drawImage(tryAgainButton,tryAgain2.xLocation,tryAgain2.yLocation,tryAgain2.width,tryAgain2.height,this);
				g2.drawImage(backToMenuButton,backToMenu2.xLocation,backToMenu2.yLocation,backToMenu2.width,backToMenu2.height,this);					
			}

							
		}
		g.drawImage(offscreen,0,0,this);		
	}
}
