package Files.Current.physicsEngine;

import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.event.*;

// The Logic
//If collides then
//if moving, add velocity of the ball of a fixed amount
//direction is the normal of the paddle
public class Paddle
{
  
  private int orientation;
  private double length;
  private double angle;
  private double x,y;
  private double angleVelocity;//degress per update
  private Line2D.Double line;
  private Color colour;
  public double x2,y2;
  //Add angle caps
  
  public static final int RIGHT = 0;//degrees 225 - 135
  public static final int LEFT = 1;//degrees 315 - 45
  public Paddle()
  {
    x = 0.0;
    y = 0.0;
    angle = 90.0;
    length = 20;
    angleVelocity = 0.0;
    orientation = RIGHT;
    colour = Color.BLACK;
    line = new Line2D.Double (x,y,getX2(),getY2());
  }
  
  public Paddle(double x, double y)
  {
    this.x = x;
    this.y = y;
    angle = 90.0;
    length = 20;
    angleVelocity = 0.0;
    orientation = RIGHT;
    colour = Color.BLACK;
    line = new Line2D.Double (x,y,getX2(),getY2());
  }
  
  public Paddle(double x, double y,double angle)
  {
    this.x = x;
    this.y = y;
    this.angle = angle;
    length = 100;
    angleVelocity = 0.0;
    orientation = RIGHT;
    colour = Color.BLACK;
    line = new Line2D.Double (x,y,getX2(),getY2());
  }
  public Paddle(double x, double y,double angle,Color colour)
  {
    this.x = x;
    this.y = y;
    this.angle = angle;
    length = 100;
    angleVelocity = 0.0;
    orientation = RIGHT;
    this.colour = colour;
    line = new Line2D.Double (x,y,getX2(),getY2());
  }
  
  public Paddle(double x, double y,double angle,Color colour,int orientation)
  {
    this.x = x;
    this.y = y;
    this.angle = angle;
    length = 90;
    angleVelocity = 0.0;
    this.orientation = orientation;
    this.colour = colour;
    line = new Line2D.Double (x,y,getX2(),getY2());
  }
  public Paddle(double x, double y,double angle,double length,Color colour,int orientation)
  {
    this.x = x;
    this.y = y;
    this.angle = angle;
    this.length = length;
    angleVelocity = 0.0;
    this.orientation = orientation;
    this.colour = colour;
    line = new Line2D.Double (x,y,getX2(),getY2());
  }
  public void setAngleVelocity (double newVelocity)
  {
    angleVelocity = newVelocity;
  }
  
  public double getAngleVelocity ()
  {
    return angleVelocity;
  }
  
  public void createLine ()
  {
    //calculates the new coordinates to new angles
    line = new Line2D.Double (x,y,getX2(),getY2());
  }
  
  public void setAngle (double newAngle)
  {
    angle = newAngle;
  }
  
  public void setX (double newX)
  {
    x = newX;
  }
  
  public void setY (double newY)
  {
    y = newY;
  }
  
  public void setLength (double newLength)
  {
    length = newLength;
  }
  
  public double getAngle ()
  {
    return angle;
  }
  
  public double getX()
  {
    return x;
  }
  
  public double getY()
  {
    return y;
  }
  
  //Note: Math methods use radians
  public double getX2()
  {
    return x + (Math.cos(Math.toRadians (angle))*length);
  }
  
  public double getY2()
  {
    return y + (Math.sin(Math.toRadians (angle))*length);
  }
  
  public int getOrientation ()
  {
    return orientation;
  }
  
  public Line2D getLine()
  {
    createLine ();
    return line;
  }
  
  public Color getColour ()
  {
    return colour;
  }
  
  public void keyPressed (KeyEvent e)
  {
    int key = e.getKeyCode ();
    if (key == KeyEvent.VK_RIGHT && orientation == RIGHT)
    {
      setAngleVelocity (10); 
    }
    else if (key == KeyEvent.VK_LEFT && orientation == LEFT)
    {
      setAngleVelocity (10); 
    }
  }
  
  public void keyReleased (KeyEvent e)
  {
    int key = e.getKeyCode ();
    if (key == KeyEvent.VK_RIGHT && orientation == RIGHT)
    {
      setAngleVelocity (-10); 
    }
    else if (key == KeyEvent.VK_LEFT && orientation == LEFT)
    {
      setAngleVelocity (-10);
    }
  }
  
  public String toString ()
  {
    return getAngle () + " " + getX() + " " + getY() + "  " + getX2() + " " + getY2();
  }
}