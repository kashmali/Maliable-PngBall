package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public abstract class Spawn
{
    CollisionResponse earliestCollisionResponse = new CollisionResponse();
  protected float vx, vy;
  protected Shape shape;
  protected float x,y;
  private ArrayList<Accel> accelerations = new ArrayList<Accel>();

  public Spawn(int x, int y, float vx, float vy, int m)
  {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  public Spawn(int x, int y)
  {
    this(x, y, 0.0f, 0.0f,100);
  }
  
  public abstract void updatePos(float newX, float newY); 
  public abstract int getLength();
  public abstract int getHeight();
  
  public Vector2D velVector()
  {
    return new Vector2D(this.getvx(), this.getvy());
  }

  public void applyDrag(float drag)
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

  public void updateVelocity(float vx, float vy)
  {
    this.vx = vx;
    this.vy = vy;
  }

  public float getvx()
  {
    return this.vx;
  }

  public float getvy()
  {
    return this.vy;
  }

  public Point2D getCenter()
  {
    return new Point2D.Float(this.x + (this.getLength() / 2), this.y + (this.getHeight() / 2));
  }

  public float getX()
  {
    return this.x;
  }

  public float getY()
  {
    return this.y;
  }

  public float getX2()
  {
    return (this.x + this.getLength());
  }

  public float getY2()
  {
    return (this.y + this.getHeight());
  }
  
  public Shape getShape ()
  {
   return shape; 
  }

  public void setX (int newX)
  {
    this.x = newX;
  }

  public void setY(int newY)
  {
    this.y = newY;
  }
  
  
  public void keyPressed (KeyEvent e)
  {
   int key = e.getKeyCode ();
   switch (key){
     case KeyEvent.VK_W : updateVelocity (vx,-2); //addAccel (new Accel (100,100)); System.out.println ("Space1");//do Nothing
     break;
     case KeyEvent.VK_D : updateVelocity (2,vy);
     break;
     case KeyEvent.VK_A : updateVelocity (-2,vy);
     break;
     case KeyEvent.VK_S : updateVelocity (vx,2);
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
