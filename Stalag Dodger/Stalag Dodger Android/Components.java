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
	private long score;
	private int stage;
	private int r;

    public Components(int ra, double gra,int x, double ve)
    {
    	ball = new BallComponent(ra,gra,x,720);
    	r=ra;
    	sticks = new ArrayList<StickComponent>();
    	height =ra;
    	vel = ve;
    	stage = 0;
    }
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setColor(Color.black);
    	g2.fill(new Rectangle(0,0,getWidth(),getHeight()));
 		Font f = new Font(Font.SANS_SERIF,Font.BOLD,48);
		g2.setFont(f);   	
		if(stage==0)
		{
			String str = "Tap to start";
			g2.setColor(Color.green);
			g2.drawString(str,498,385);
			g2.setColor(Color.blue);
			g2.fill(new Rectangle(getWidth()/2-r/2,getHeight()/2-r-5-15,r,r));
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
    		g2.drawString(str,6,40);
    		score++;
		}
		else
		{
			String yourScore = "Your score";
			String clickToContinue = "Tap to continue";
			g2.setColor(Color.blue);
			g2.drawString(yourScore,503,275);
			g2.drawString(clickToContinue,448,390);
			g2.setColor(Color.green);
			int xLoc = getWidth()/2-((int)getLength())/2;
			g2.drawString(""+score,xLoc,335);
			
			
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
    	if(sticks.size()<20)
    	{
    		int rand = (int)(Math.random()*2);
    		if(rand==1)
    		{
    			int randLen = (int)(Math.random()*22)+10;
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
    public double getLength()
    {
    	double digSize = 1280.0/47.0;
    	int longSize = (""+score).length();
    	return longSize*digSize;
    }


}