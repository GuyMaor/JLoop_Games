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
        final int DELAY = 1; // Milliseconds between timer ticks
		final Components c = new Components(20,.06,15,6,DELAY);
		frame.add(c);



      class TimerListener implements ActionListener
      {
      	 private boolean checkedScore;
         public void actionPerformed(ActionEvent event)
         {
         	if(!c.didCrash())
         	{
         		if(checkedScore)
         			checkedScore = false;
         		c.manageSticks();
         		c.move();
         		c.repaint();

         	}
         	else
         	{
         		if(!checkedScore)
         		{
         			checkedScore = true;
         		}
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