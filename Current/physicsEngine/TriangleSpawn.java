package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Polygon;

public class TriangleSpawn extends Spawn
{
  private int adjacent,opposite;
  //Later add the angle option
  public TriangleSpawn (int x, int y, float vx, float vy, int length1, int length2)
  {
    super (x,y,vx,vy,100);
    adjacent = length1;
    opposite = length2;
    shape = new Polygon (new int []{x,x+ length1,x+length1},new int [] {y,y,y + length2},3);
  }
  
  public TriangleSpawn (int x, int y)
  {
   super (x,y,0.0f,0.0f,100);
   adjacent = 50;
   opposite = 50;
   shape = new Polygon (new int [] {x, x + 50, x + 50},new int []{y, y, y - 50},3);
  }
 
  public void updatePos(float newX, float newY)
  {
    this.x = newX;
    this.y = newY;
    shape = new Polygon (new int [] {(int) newX, (int)newX + adjacent, (int)newX + adjacent}, new int [] {(int)newY, (int)newY,(int) newY - opposite},3);
  }
  
  public void setOpposite (int newOpposite)
  {
    this.opposite = newOpposite;
  }
  
  public void setAdjacent (int newAdjacent)
  {
   this.adjacent = newAdjacent; 
  }
  
  public int getOpposite ()
  {
     return opposite;
  }
  
  public int getAdjacent ()
  {
    return adjacent;
  }
  
  public int getLength ()
  {
   return adjacent; 
  }
  
  public int getHeight ()
  {
    return opposite;
  }
}