import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
  import javax.swing.JTextField;
  import javax.swing.JLabel;
  import java.awt.event.ActionListener;
  import java.awt.event.ActionEvent;
  import javax.swing.JOptionPane;
  import Files.Current.physicsEngine.*;

public class HighScorePromptPanel extends JPanel implements ActionListener
{
  JTextField nameField = new JTextField (10);
      JFrame parent;
      private int location;
      private int score;
      public HighScorePromptPanel (int location,int score, JFrame parent)
      {
        this.parent = parent;
        this.location = location;
        this.score = score;
        JButton submitButton = new JButton ("Submit");
        submitButton.addActionListener (this);
        add (new JLabel ("Score: " + score));
        add (new JLabel ("Enter your name: "));
        add (nameField);
        add (submitButton);
        setSize (300,200);
        setFocusable (true);
        setVisible (true);
      }
      
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