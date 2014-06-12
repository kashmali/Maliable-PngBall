package Files.Current.physicsEngine;

import java.awt.event.KeyEvent;
import java.awt.Color;

///add private variable bounce
public class PseudoPaddle extends ObstacleLine
{
  private double length;
  public float moveSpeed = 0;
  
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
 
 public void setMoveSpeed (float newSpeed)
 {
   moveSpeed = newSpeed;
 }
 
 public float getMoveSpeed ()
 {
   return moveSpeed;
 }
 
 public void updatePos (double x)
 {
   if (x < 100)
   {
     x = 100;
   }
   else if (x + length > 300)
   {
     x = 300 - length;
   }
   set (x,y1,x + length,y1);
 }
 
  public void keyPressed (KeyEvent e)
  {
   int key = e.getKeyCode ();
   switch (key){
     case KeyEvent.VK_RIGHT : 
       setMoveSpeed (7);
     break;
     case KeyEvent.VK_LEFT :
       setMoveSpeed (-7);
     break;
   }
  }
}