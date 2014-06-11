import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Image;

public class SplashPanel extends JPanel //ImagePlacer
{
  
  public SplashPanel ()
  {
    //super ("splash_DownToEarthGames.png",0,0,500,700);
    setBackground (Color.WHITE);
    BorderLayout layout = new BorderLayout ();
    setLayout (layout);
    ImageIcon splashImage = new ImageIcon("splash_DownToEarthGames.png");
    JLabel splashLabel = new JLabel (splashImage);
    this.add(splashLabel,BorderLayout.CENTER);
    
    repaint();
    setSize (700,800);
    setVisible (true);
  }
  
}