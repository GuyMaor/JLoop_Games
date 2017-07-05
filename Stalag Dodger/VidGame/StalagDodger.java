import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class StalagDodger
{

    public static void main(String[] args)
    {
    	JFrame frame = new JFrame();
		frame.setSize(600, 300);
		frame.setLocation(0, 0);
		frame.setTitle("Stalag Dodger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
        final int DELAY = 5; // Milliseconds between timer ticks
		final Components c = new Components(20,.01,15,3,DELAY);
		frame.add(c);



      class TimerListener implements ActionListener
      {
      	 private int score;
         public void actionPerformed(ActionEvent event)
         {
         	if(!c.didCrash()&&c.getStage()==1)
         	{
         		
         		c.manageSticks();
         		c.move();
         		c.repaint();
         		score+=DELAY;
         	}
         	else if(c.didCrash())
         	{
         		c.repaint();
         		c.changeStage(2);
         	}
         }
      }

      ActionListener listener = new TimerListener();


      Timer t = new Timer(DELAY, listener);
      t.start();

      class MouseListen implements MouseListener
      {
      	public void mouseClicked(MouseEvent e)
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