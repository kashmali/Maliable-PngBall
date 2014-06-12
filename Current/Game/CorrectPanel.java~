import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class CorrectPanel extends ImagePlacer
{
  JFrame parent;
  public CorrectPanel (JFrame parent)
  {
    super ("correct.png",0,0,800,600);
    JButton continueButton = new JButton ("Continue");
    //JLabel message = new JLabel ("You answered correctly!");
    continueButton.addActionListener ((ActionListener)parent);
    //add (message);
    add (continueButton);
    setVisible (true);
  }
}