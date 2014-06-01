import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class HighScoreFrame extends JFrame implements ActionListener
{
 public HighScoreFrame ()
 {
   HighScorePanel panel = new HighScorePanel (this);
   getContentPane().add (panel);
   setSize ((int)(panel.getSize().getWidth()*1.1),(int)(panel.getSize().getHeight()*1.1));
   setVisible (true);
   //setAutoRequestFocus (true);
   setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
 }
  
 public void actionPerformed (ActionEvent ae)
 {
  if (ae.getActionCommand().equals ("Close"))
        {
    //http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
    //this.dispatchEvent (new WindowEvent (this, WindowEvent.WINDOW_CLOSING));
    this.dispose ();
  }
 }
  
}