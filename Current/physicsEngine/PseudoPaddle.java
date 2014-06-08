package Files.Current.physicsEngine;

import java.awt.event.KeyEvent;
import java.awt.Color;

public class PseudoPaddle extends ObstacleLine
{
  private double length;
  public final static int moveSpeed = 7;
  
 public PseudoPaddle (double x1, double y1, double length, Color color) {
      super (x1,y1,x1 + length,y1,color);
      this.length = length;
 }
  
 public double getLength()
 {
   return length;
 }
 
 public void setLength (double newLength)
 {
  this.length = newLength; 
 }
 
  public void keyPressed (KeyEvent e)
  {
   int key = e.getKeyCode ();
   switch (key){
     case KeyEvent.VK_RIGHT : 
       if (x1 + length + moveSpeed < 300)
       set (x1 + moveSpeed,y1,x1 + length + moveSpeed, y1);
       else
         set (300 - length,y1,300, y1);
     break;
     case KeyEvent.VK_LEFT :
       if (x1 - moveSpeed > 100)
       set (x1 - moveSpeed,y1,x1 + length - moveSpeed, y1);
       else
         set (100,y1,100+ length, y1);
     break;
   }
  }
}