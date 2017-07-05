import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;

public class Components extends JComponent
{
	private BallComponent ball;
	private ArrayList<StickComponent> sticks;
	private double vel;
	private double height;
	private int score;
	private int delay;
	private int stage;
	private int r;

    public Components(int ra, double gra,int x, double ve, int del)
    {
    	ball = new BallComponent(ra,gra,x,300);
    	r=ra;
    	sticks = new ArrayList<StickComponent>();
    	height =ra;
    	vel = ve;
    	delay = del;
    	stage = 0;
    }
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.black);
    	g2.fill(new Rectangle(0,0,getWidth(),getHeight()));
 		Font f = new Font(Font.SANS_SERIF,Font.BOLD,24);
		g2.setFont(f);   	
		if(stage==0)
		{
			String str = "Click to start";
			g2.setColor(Color.green);
			g2.drawString(str,218,150);
			g2.setColor(Color.blue);
			g2.fill(new Rectangle(getWidth()/2-r/2,getHeight()/2-r-5,r,r));
		}

		else if(stage==1)
		{
    		
    		
    		ball.drawBall(g2);
    		for(StickComponent s : sticks)
    		{
    			s.drawStick(g2);
    		}
    		g2.setColor(Color.green);
    		String str = score+"";
    		g2.drawString(str,3,20);
    		score += delay;
		}
		else
		{
			String yourScore = "Your score";
			String clickToContinue = "Click to continue";
			g2.setColor(Color.blue);
			g2.drawString(yourScore,230,90);
			g2.drawString(clickToContinue,195,190);
			g2.setColor(Color.green);
			g2.drawString(""+score,273,140);
			
			
		}
    }
    public void move()
    {
    	ball.move();
    	for(StickComponent s: sticks)
    	{
    		s.move();
    	}
    }
    public void changeBallDir()
    {
    	ball.changeDir();

    }
    public void manageSticks()
    {
    	if(sticks.size()>0&&sticks.get(0).getBack()<=0)
    		sticks.remove(0);
    	if(sticks.size()<5)
    	{
    		int rand = (int)(Math.random()*40);
    		if(rand==1)
    		{
    			int randLen = (int)(Math.random()*11)+5;
    			int range = getHeight()/2-((int)height);
    			int randHeight = (int)(Math.random()*range);
    			int randBool = (int)(Math.random()*2);
    			boolean b = false;
    			if(randBool==1)
    				b = true;
    			if(!b)
    				randHeight = getHeight()-randHeight;
    			sticks.add(new StickComponent(vel,randLen,randHeight,getHeight(),b,getWidth()));
    		}
    	}
    }
    public boolean didCrash()
    {
    	if(ball.getBot()>=getHeight()||ball.getTop()<=0)
    		return true;
    	for(StickComponent s : sticks)
    	{
    		if(s.isTop())
    		{
    			if(s.getFront()<=ball.getFront()&&s.getTip()>=ball.getTop()&&s.getBack()>=ball.getBack())
    				return true;
    		}
    		else
    		{
    			if(s.getFront()<=ball.getFront()&&s.getTip()<=ball.getBot()&&s.getBack()>=ball.getBack())
    				return true;
    		}
    	}
    	return false;

    }
    public void restart()
    {
    	sticks = new ArrayList<StickComponent>();
    	ball.restart();
    	score = 0;
    }
    public void changeStage(int s)
    {
    	stage = s;
    }
    public int getStage()
    {
    	return stage;
    }


}