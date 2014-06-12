/**
 * Basic panel to display the Down to Earth Games company logo in the main menu.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class LogoPanel extends ImagePlacer
{
  /**
   * Constructor for the logo panel.
   */
  public LogoPanel ()
  {
  super ("DownToEarthGames.png",0,0,400,550);
    repaint();
    setSize (400,550);
  }
  
}