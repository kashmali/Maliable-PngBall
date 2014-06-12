import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Panel which displays the image for the splash screen.
 * @author Jason P'ng
 * @author Aakash Mali
 * @version v4.0 June 12th, 2014
 */
public class SplashPanel extends JPanel
{
  /**
   * Constructor for the panel
   * 
   * @param BorderLayout layout the layout used to center the image
   * @param ImageIcon splashImage the image featured in the splash screen.
   * @param JLabel splashLabel the label that hosts the splash image.
   */
  public SplashPanel ()
  {
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