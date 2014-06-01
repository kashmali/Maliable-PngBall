package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class RectangleSpawn extends Spawn
{
  private int width,height;
  
  public RectangleSpawn (int x, int y, double vx, double vy,int length1, int length2)
  {
    super (x,y,vx,vy,100);
    this.width =length1;
    this.height = length2;
    shape = new Rectangle (x,y,width,height);
    setRadius (0); //Later change so not everything is a circle
             }
  
  public RectangleSpawn (int x, int y)
  {
    super (x,y,0.0,0.0,100);
    this.width = 50;
    this.height = 50;
    shape = new Rectangle (x,y,width,height);
    setRadius (0);  
  }
  
  public void updatePos(double newX, double newY)
  {
    this.x = newX;
    this.y = newY;
    shape = new Rectangle ((int)x,(int)y,width,height);
  }
  
  public void setWidth (int newWidth)
  {
    this.width = newWidth;
  }
  
  public void setHeight (int newHeight)
  {
   this.height = newHeight; 
  }
  
  public int getWidth ()
  {
     return width;
  }
  
  public int getHeight ()
  {
    return height;
  }
  
  public int dimX ()
  {
   return width; 
  }
  
  public int dimY()
  {
   return height; 
  }
  
  
}