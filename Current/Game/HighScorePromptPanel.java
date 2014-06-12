import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
  import javax.swing.JTextField;
  import javax.swing.JLabel;
  import java.awt.event.ActionListener;
  import java.awt.event.ActionEvent;
  import javax.swing.JOptionPane;
  import gameFiles.*;

  /**
   * Panel used when a new highscore is achieved and the user must enter a name.
   * @author Jason P'ng
 * @version v4.0 June 12th, 2014
   */
public class HighScorePromptPanel extends JPanel implements ActionListener
{
  /**
   * The textfield for users to input their name.
   */
  JTextField nameField = new JTextField (10);
  /**
   * JFrame parent to use the actionPerformed method
   */
      JFrame parent;
      /**
   * The location in which the highscore will be placed in in the top 10 scores.
   */
      private int location;
      /**
   * The score of the current highscore
   */
      private int score;
      /**
   * The constructor of the panel. FlowLayout is used.
   * 
   * @param int location where the new highscore will be placed in.
   * @param int score the score of the recent game.
   * @param JFrame parent the frame this panel is in to use the actionPerformed method.
   * @param JButton submitButton button to submit the new highscore and proceed to the main menu.
   */
      public HighScorePromptPanel (int location,int score, JFrame parent)
      {
        this.parent = parent;
        this.location = location;
        this.score = score;
        JButton submitButton = new JButton ("Submit");
        submitButton.addActionListener (this);
        add (new JLabel ("NEW HIGHSCORE"));
        add (new JLabel ("Score: " + score));
        add (new JLabel ("Enter your name: "));
        add (nameField);
        add (submitButton);
        setSize (300,200);
        setFocusable (true);
        setVisible (true);
      }
      
      /**
       * Implemented method of the ActionListener interface. Only the submit button is listened to. <br>
       * if (ae.getActionCommand().equals ("Submit")) - confirms the submit button was pressed and not something else. <br>
       * if (name.length() > 0) - makes sure the user entered a name. Otherwise a message dialog appears.
       * 
       * @param ActionEvent ae the action that triggers this method.
       * @param String name the text in the textfield for the user to enter their name.
       */
      public void actionPerformed (ActionEvent ae)
      {
        if (ae.getActionCommand().equals ("Submit"))
        {
          String name = nameField.getText();
          if (name.length() > 0)
          {
            HighScoreManager.scores.add (location,new HighScore (name,GameEngine.getLayoutAsString(),score));
            HighScoreManager.scores.remove (10);
            ((ActionListener)parent).actionPerformed (new ActionEvent (this, ActionEvent.ACTION_PERFORMED,"Submit"));
          }
          else
          {
            JOptionPane.showMessageDialog (this, "You must input a name", "Name Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
}