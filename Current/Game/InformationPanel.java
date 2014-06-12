import gameFiles.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Panel that informs the user of information about the game <br>
 * This includes the difficulty, the leve, and basic controls.
 * @author Aakash Mali
 * @version v4.0 June 12th, 2014
 */
public class InformationPanel extends JPanel
{
  /**
   * The image that is used as a header for the top of the panel.
   */
  BufferedImage header;
  
  /**
   * The constructor of the panel. It loads the header image and sets the size to be that of the game panel.
   */
  public InformationPanel ( )
  {
    header = ImagePlacer.loadImage ("MaliablePngballHeader.png",this.getClass());
    repaint();
    setSize (400,500);
  }
  
  /**
   * Overriden paintCompoent method to do custom painting. <br>
   * This paints a header and a few lines of information.
   * 
   * @param Graphics g the graphics used to paint the panel.
   */
  @Override
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.drawImage (header,0,0,400,200,null);
    g.drawString (GameEngine.getLayoutAsString(),20,220);
     g.drawString ("Difficulty: " + GameEngine.getDifficultyAsString(),20,240);
    g.drawString ("Press \'p\' to pause the game",20,260);
    g.drawString ("Press space to release the ball",20,280);
  }
}