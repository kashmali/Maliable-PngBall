import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class ImagePlacer extends Canvas//JPanel
{
  BufferedImage img = null;
  private int x = 0, y = 0, width = 0, height = 0;
  
  public ImagePlacer (String nameOfImage, int x, int y, int width, int height)
  {
    loadImage (nameOfImage);   //Need to type in image file extension
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
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
   g.drawImage (img,x,y,width,height,null); 
  }
}