import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import gameFiles.*;

/**
 * Panel for the main menu when waiting for the user to start the game.
 * This also contains static methods for pausing the program.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class StasisPanel extends JPanel
{
  /**
   * The state of stasis; whether or not the program is to be paused.
   */
  public static boolean stasis = true;
  
  /**
   * The constructor for the panel.
   * 
   * @param JFrame parent the JFrame for this panel to be on. It uses the actionPerformed method of the parent.
   * @param JButton startButton the button used to begin the game.
   * @param BorderLayout layout the layout used to center the button.
   */
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
  
  /**
   * This method runs a timewasting loop waiting for user input. <br>
   * while structure is the loop to constantly delay <br>
   * if (GameEngine.isTerminated()) - Checks if the game is over so the stasis exits to exit the game. <br>
   * 
   * @throws InterruptedException if the Thread.sleep() method interrupts another thread.
   * 
   */
  public static void stasis ()
  {
    while (stasis == true)
    {
      if (GameEngine.isTerminated())
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