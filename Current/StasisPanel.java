import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import Files.Current.physicsEngine.*;
public class StasisPanel extends JPanel
{
  public static boolean stasis = true;
  
  public StasisPanel (JFrame parent)
  {
    JButton startButton = new JButton ("<html><font size = \"20\" color = \"#009933\">Start</html>");
    startButton.setActionCommand ("Start");
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
      if (GameEngine.terminated)
      {
        StasisPanel.removeStasis ();
      }
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