import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/**
 * A template for panels that are filled with one single image. Also provides method to read in images.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class ImagePlacer extends JPanel
{
  /**
   * The image to be placed on the panel.
   */
  BufferedImage img = null;
  /**
   * The x coordinate for the top left hand corner of the image.
   */
  private int x = 0;
  /**
   * The y coordinate for the top left hand corner of the image.
   */
    private int y = 0;
    /**
   * The width of the image.
   */
  private int width = 0;
  /**
   * The height of the image.
   */
    private int height = 0;
  
    /**
   * Constructor for an ImagePkacer panel. This loads the image and sets the dimensions.
   * 
   * @param String nameOfImage the name of the image to be loaded.
   * @param int x the x coordinate for the image.
   * @param int y the y coordinate for the image.
   * @param int width the width of the image.
   * @param int height the height of the image.
   */
  public ImagePlacer (String nameOfImage, int x, int y, int width, int height)
  {
    loadImage (nameOfImage);   //Need to type in image file extension
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  /**
   * Loads in an image to be placed on the panel.
   * 
   * @param String image the name of the image.
   * @throws IOException if the image can't be found in the current directory.
   */
  public void loadImage (String image)
  {
    try 
    {
      img = ImageIO.read (getClass().getResourceAsStream(image)); 
    }
    catch (IOException e)
    {
      System.out.println ("Error Loading Image");
    }
  }
  
  /**
   * Loads and returns an image to be used on other panels.
   * 
   * @param String image the name of the image.
   * @param Class origin the class that is trying to load an image.
   * @param BufferedImage img the image that is being loaded and returned
   * @return A BufferedImage of the image that was loaded.
   * @throws IOException if the image could not be found in the current directory.
   */
  public static BufferedImage loadImage (String image,Class origin)
  {
    BufferedImage img = null;
    try 
    {
      img = ImageIO.read (origin.getResourceAsStream(image)); 
    }
    catch (IOException e)
    {
      System.out.println ("Error Loading Image");
    }
    return img;
  }
  
  /**
   * Overriden method for painting to put the image on the panel.
   * 
   * @param Graphics g graphics used the paint the panel.
   */
  public void paintComponent (Graphics g)
  {
   g.drawImage (img,x,y,width,height,null); 
  }
}