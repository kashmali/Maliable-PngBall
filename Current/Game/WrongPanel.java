import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;

/**
 * Panel to show in game after a question is answered incorrectly
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class WrongPanel extends ImagePlacer
{
    /** JFrame to add this panel. This allows the panel to be removed from it.*/
  JFrame parent;
  
  /**
   * Constructor of the panel. Sets up a button and picture.
   * 
   * @param JFrame parent the JFrame this panel is attached to. It must be an actionListener.
   * @param JButton continueButton Button to close this panel.
   */
  public WrongPanel (JFrame parent)
  {
    super ("wrong.png",0,0,800,600);
    JButton continueButton = new JButton ("Continue");
    //JLabel message = new JLabel ("You answered correctly!");
    continueButton.addActionListener ((ActionListener)parent);
    //add (message);
    add (continueButton);
    setVisible (true);
  }
}