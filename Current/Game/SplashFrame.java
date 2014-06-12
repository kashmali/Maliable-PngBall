 import javax.swing.JFrame;
 import java.awt.Frame;
 import java.awt.BorderLayout;
 
 /**
  * Temporary frame used to display a splash screen.
  * @author Jason P'ng
 * @version v4.0 June 12th, 2014
  */
public class SplashFrame extends JFrame
{
  /**
   * Constructor for the splash screen frame. It is full screen and undecorated.
   */
  public SplashFrame ()
  {
    super ();
    setUndecorated (true);
    setExtendedState(Frame.MAXIMIZED_BOTH);
    getContentPane().add (new SplashPanel (),BorderLayout.CENTER);
    setAutoRequestFocus (true);
    setVisible (true); 
  }
  
  /**
   * This sets the lifespan of the splash screen so that it will be temporary, lasting two seconds.
   */
  public void showSplash ()
  {
    requestFocus ();
    try
    {
      Thread.sleep (2000);
    }
    catch (InterruptedException e)
    {
      
    }
    this.dispose ();
  }
}