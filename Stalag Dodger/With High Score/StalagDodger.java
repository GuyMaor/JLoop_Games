import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

public class StalagDodger
{

    public static void main(String[] args)throws IOException//MOD
    {
    	JFrame frame = new JFrame();//JFRAME
		frame.setSize(1280, 720);
		frame.setLocation(0, 0);
		frame.setTitle("Stalag Dodger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFRAME
		frame.setVisible(true);
        final int DELAY = 5;  
		final Components c = new Components(40,.04,30,9);
		frame.add(c);
		FileReader fR = new FileReader("HighScore.dat");//MOD
		Scanner scan = new Scanner(fR);//MOD
		final long hS = scan.nextInt();//MOD



      class TimerListener implements ActionListener//ACTIONLISTENER
      {
      	 private long highScore = hS;
      	 private boolean saveTime = true;
         public void actionPerformed(ActionEvent event)//ACTIONEVENT && ACTIONPERFORMED
         {
         	
         	if(!c.didCrash()&&c.getStage()==1)
         	{
         		saveTime = true;
         		c.manageSticks();
         		c.move();
         		c.repaint();
         		if(highScore<c.getScore())
         		{
         			highScore = c.getScore();
         		}
         	}
         	else if(c.didCrash()&&saveTime)
         	{
         		saveTime=false;
         		try
         		{
         		PrintWriter pW = new PrintWriter("HighScore.dat");
         		pW.print(highScore);
         		pW.close();
         		}
         		catch(IOException exc)
         		{
         		}
         		c.resetHighScore(highScore);
         		c.repaint();
         		c.changeStage(2);
         		
         	}
         }
      }

      ActionListener listener = new TimerListener();//ACTIONLISTENER


      Timer t = new Timer(DELAY, listener);
      t.start();

      class MouseListen implements MouseListener//MOUSELISTENER
      {
      	public void mouseClicked(MouseEvent e)//MOUSEEVENT && MOUSECLICKED
      	{
      		c.changeStage(1);
      		if(!c.didCrash())
      			c.changeBallDir();
      		else
      			c.restart();
      	}
       	public void mousePressed(MouseEvent e){}
      	public void mouseReleased(MouseEvent e){}
      	public void mouseEntered(MouseEvent e){}
      	public void mouseExited(MouseEvent e){}

      }

      MouseListen mL = new MouseListen();

      c.addMouseListener(mL);

    }


}