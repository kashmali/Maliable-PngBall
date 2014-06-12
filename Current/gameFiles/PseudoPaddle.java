package gameFiles;

import java.awt.event.KeyEvent;
import java.awt.Color;

/**
 * The paddle controlled by the user to prevent the ball from falling into the gutter
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class PseudoPaddle extends ObstacleLine
{
  /**
   * The length of the paddle.
   */
  private double length;
  /**
   * The speed of the paddle
   */
  public float moveSpeed = 0;
  
  /**
   * The constructor of the paddle setting the x and y coordinates and the length and colour
   * 
   * @param double x1 the x coordinate of the left point of the line.
   * @param double y1 the y coordinate of the left point of the line.
   * @param double length the length of the paddles
   * @param Color color the colour of the line.
   */
 public PseudoPaddle (double x1, double y1, double length, Color color) 
 {
      super (x1,y1,x1 + length,y1,color);
      this.length = length;
 }
  
 /**
  * Returns the length of the line
  * 
  * @return the length of the paddle
  */
 public double getLength()
 {
   return length;
 }
 
 /**
  * Mutator method to set the length of the line
  * 
  * @param double newLength what the length is to set to be.
  */
 public void setLength (double newLength)
 {
  this.length = newLength; 
 }
 
  /**
  * Mutator method to set the speed of the line
  * 
  * @param float newSpeed what the speed is to set to be.
  */
 public void setMoveSpeed (float newSpeed)
 {
   moveSpeed = newSpeed;
 }
 
 /**
  * returns the speed of the paddle
  * 
  * @return the speed of the paddle
  */
 public float getMoveSpeed ()
 {
   return moveSpeed;
 }
 
 /**
  * Update the position of the paddle. It is restricted to not go past the slopes of the game. <br>
  * if (x < 100) - restricts the paddle on the left side. <br>
  * else if (x + length > 300) - restricts the paddle on the right side. <br>
  * 
  * @param double x the new x location
  */
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
 
 /**
  * Implemented method for adding control to the paddle. This allows the speed to be changed using the right and left arrow keys. <br>
  * Swith statement is used to distinguish between right and left.
  * 
  * @param KeyEvent e the keyevent that triggers this method to be called
  * @param int key the key code of the keyEvent to recognize which key was pressed.
  */
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