/**
 * The driver class of the program
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class PngBallRunner
{
  /**
   * The driver method of the program
   * 
   * @param String[] args arguments passed in to run the program
   */
  public static void main (String [] args)
  {
    SplashFrame s = new SplashFrame ();    
    s.showSplash (); 
    Window w = new Window ();
    w.runProgram ();
  }
}