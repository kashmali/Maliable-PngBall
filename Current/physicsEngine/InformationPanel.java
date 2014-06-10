package Files.Current.physicsEngine;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;





public class InformationPanel extends JPanel//ImagePlacer
{
  
  public InformationPanel ( )
  {
    //super ("DownToEarthGames.png",0,0,400,500);
    repaint();
    setSize (400,500);
  }
  
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.drawString (Integer.toString (GameEngine.score),20,20);
  }
}