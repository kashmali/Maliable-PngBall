import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GamePanel extends ImagePlacer
{
  
  public GamePanel ()
  {
    //super ("DownToEarthGames.png",0,0,500,500);
    super ("PinballBackground.png",0,0,400,500);
    //background.setSize (500,500);
    //background.setVisible (true);
    //this.add (background);
    setSize (400,500);
    repaint();
    
  }
  
}
