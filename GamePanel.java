import java.awt.*;
import javax.swing.*;
import java.awt.event.*;




public class GamePanel extends JPanel 
{
  
  public GamePanel ( )
  {
   ImagePlacer background = new ImagePlacer ("PinballBackground.png",0,0,500,500);
    background.setSize (500,500);
    background.setVisible (true);
    this.add (background);
    this.repaint();
    setSize (500,500);
  }
  
}
