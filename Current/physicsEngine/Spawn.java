package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Spawn
{
  private double vx, vy, radius;
  protected Shape shape;
  protected double x,y;
  private ArrayList<Accel> accelerations = new ArrayList<Accel>();

  public Spawn(int x, int y, double vx, double vy, int m)
  {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.radius = 15.0;
    this.shape = new Ellipse2D.Double(x, y,radius * 2, radius * 2);
  }

  public Spawn(int x, int y)
  {
    this(x, y, 0.0, 0.0, 100);
  }

  public Vector2D velVector()
  {
    return new Vector2D(this.vx(), this.vy());
  }

  public void applyDrag(double drag)
  {
    this.vx = (drag * this.vx);
    this.vy = (drag * this.vy);
  }

  public Accel sumAccel()
  {
    double xAccel = 0, yAccel = 0;
    for (int i = 0; i < this.accelerations.size(); i++) {
      xAccel += this.accelerations.get(i).ax();
      yAccel += this.accelerations.get(i).ay();
    }
    this.accelerations.clear();
    return new Accel(xAccel, yAccel);
  }

  public void addAccel(Accel a)
  {
    this.accelerations.add(a);
  }

  public void updateVelocity(double vx, double vy)
  {
    this.vx = vx;
    this.vy = vy;
  }

  public void updatePos(double newX, double newY)
  {
    this.x = newX;
    this.y = newY;
    shape = new Ellipse2D.Double(newX, newY,radius * 2, radius * 2);
  }

  public double vx()
  {
    return this.vx;
  }

  public double vy()
  {
    return this.vy;
  }

  public int dimX()
  {
    return (int) (this.radius * 2);
  }

  public int dimY()
  {
    return (int) (this.radius * 2);
  }

  public Point2D getCenter()
  {
    return new Point2D.Double(this.x + (this.dimX() / 2), this.y
                              + (this.dimY() / 2));
  }

  public double getRadius()
  {
    return this.radius;
  }

  public double getX()
  {
    return this.x;
  }

  public double getY()
  {
    return this.y;
  }

  public double getX2()
  {
    return (this.x + this.dimX());
  }

  public double getY2()
  {
    return (this.y + this.dimY());
  }
  
  public Shape getShape ()
  {
   return shape; 
  }

  public void setX(int newX)
  {
    this.x = newX;
  }

  public void setY(int newY)
  {
    this.y = newY;
  }
  public void setRadius (double newRadius)
  {
   this.radius = newRadius; 
  }
  
  public void keyPressed (KeyEvent e)
  {
   int key = e.getKeyCode ();
   switch (key){
     case KeyEvent.VK_UP : updateVelocity (vx,vy -500); //addAccel (new Accel (100,100)); System.out.println ("Space1");//do Nothing
     break;
     case KeyEvent.VK_RIGHT : updateVelocity (vx + 500,vy);
     break;
     case KeyEvent.VK_LEFT : updateVelocity (vx - 500,vy);
     break;
     case KeyEvent.VK_DOWN : updateVelocity (vx, vy + 500);
     break;
   }
  }
  
  public void keyReleased (KeyEvent e)
  {
    int key = e.getKeyCode ();
   switch (key){
     case KeyEvent.VK_SPACE : //addAccel (new Accel (100,100)); System.out.println ("Space2"); System.out.println (sumAccel ().toString());
     break;
   }
  }
}
