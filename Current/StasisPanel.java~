import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StasisPanel extends JPanel
{
  public static boolean stasis = true;
  
  public StasisPanel (JFrame parent)
  {
    JButton startButton = new JButton ("Start");
    startButton.addActionListener ((ActionListener)parent);
    BorderLayout layout = new BorderLayout ();
    setLayout (layout);
    add (startButton,BorderLayout.CENTER);
    setSize (400,550);
    setVisible (true);
  }
  
  public static void stasis ()
  {
    while (stasis == true)
    {
      try {Thread.sleep (1);}
      catch (InterruptedException e){}
    }
  }
  public static void removeStasis ()
  {
    stasis = false; 
  }
  
  public static void addStasis ()
  {
   stasis = true; 
  }
}