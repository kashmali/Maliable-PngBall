import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class WrongPanel extends ImagePlacer
{
  JFrame parent;
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