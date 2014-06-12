 import javax.swing.JFrame;
 import java.awt.Frame;
 import java.awt.BorderLayout;
public class SplashFrame extends JFrame
{
  public SplashFrame ()
  {
    super ();
    setUndecorated (true);
    setExtendedState(Frame.MAXIMIZED_BOTH);
    getContentPane().add (new SplashPanel (),BorderLayout.CENTER);
    setAutoRequestFocus (true);
    setVisible (true); 
  }
  
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