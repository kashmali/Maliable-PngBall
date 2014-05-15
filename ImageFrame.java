import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ImageFrame extends JFrame
{
  public ImageFrame ()
  {         
    super ("JFrame Test");
    
    ImagePanel picture = new ImagePanel ();
    picture.setSize (400,450);
    picture.setVisible (true);
    this.add (picture);
    this.repaint ();
    setSize (400, 450);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
  }
  
  public static void main (String [] args)
  {
    new ImageFrame (); 
  }
  
}

class ImagePanel extends Canvas//JPanel
{
  BufferedImage img = null;
  
  public ImagePanel ()
  {
    loadImage ("PinballBackground.png");
    if (img != null)
    {
    /*JLabel imageLabel = new JLabel(); imageLabel.setIcon (new ImageIcon (img));
    this.add (imageLabel);
    this.repaint ();*/
    }
    
  }
  
  public void loadImage (String image)
  {
    try 
    {
      img = ImageIO.read (new File (image)); 
    }
    catch (IOException e)
    {
      System.out.println ("Error Loading Image");
    }
  }
  
  public void paint (Graphics g)
  {
   g.drawImage (img,0,0,400,450,null); 
  }
}