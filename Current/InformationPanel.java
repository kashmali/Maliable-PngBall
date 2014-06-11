import Files.Current.physicsEngine.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class InformationPanel extends JPanel//ImagePlacer
{
  BufferedImage header;
  public InformationPanel ( )
  {
    //super ("DownToEarthGames.png",0,0,400,500);
    header = ImagePlacer.loadImage ("MaliablePngballHeader.png",this.getClass());
    repaint();
    setSize (400,500);
  }
  
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.drawImage (header,0,0,400,200,null);
    g.drawString (GameEngine.getLayoutAsString(),20,220);
    g.drawString ("Press \'p\' to pause the game",20,240);
    g.drawString ("<Insert any other important info here>",20,260);
  }
}