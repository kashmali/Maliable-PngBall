package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class RectangleSpawn extends Spawn
{
  private int length,height;
  
  public RectangleSpawn (int x, int y, float vx, float vy,int length1, int length2)
  {
    super (x,y,vx,vy,100);
    this.length =length1;
    this.height = length2;
    shape = new Rectangle (x,y,length,height);
 //Later change so not everything is a circle
             }
  
  public RectangleSpawn (int x, int y)
  {
    super (x,y,0.0f,0.0f,100);
    this.length = 50;
    this.height = 50;
    shape = new Rectangle (x,y,length,height); 
  }
  
  public void updatePos(float newX, float newY)
  {
    /*this.x = newX;
    this.y = newY;
    shape = new Rectangle ((int)x,(int)y,length,height);*/
  }
  
  public void setLength (int newLength)
  {
    this.length = newLength;
  }
  
  public void setHeight (int newHeight)
  {
   this.height = newHeight; 
  }
  
  public int getLength ()
  {
     return length;
  }
  
  public int getHeight ()
  {
    return height;
  }  
}