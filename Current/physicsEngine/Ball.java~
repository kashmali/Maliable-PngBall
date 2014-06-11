package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

//Warning: x and y and the coordinates of the top left corner
public class Ball
{
  private float radius;
  CollisionResponse earliestCollisionResponse = new CollisionResponse();
  protected float vx, vy;
  protected Shape shape;
  protected float x,y;
  private ArrayList<Accel> accelerations = new ArrayList<Accel>();
  private CollisionResponse tempResponse = new CollisionResponse ();
  
 
  public Ball(int x, int y, float vx, float vy)
  {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.radius = 15f;
  }

  public Ball(int x, int y)
  {
    this(x, y, 0.0f, 0.0f);
  }
  
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
  public void sumForces (float timeFraction)
  {
Accel theAccel = sumAccel();
      // Apply the resulting change in velocity.
      float vx = (float)Math.min (MoveEngine.MAX_SPEED,getvx() + (theAccel.ax() * timeFraction));
      float vy = (float)Math.min (MoveEngine.MAX_SPEED,getvy() + (theAccel.ay() * timeFraction));
      updateVelocity(vx, vy);
      // Apply drag coefficient
      applyDrag((float)(1.0 - (timeFraction * 0.0003)));
  }
  public void updateVelocity(float vx, float vy)
  {
    this.vx = vx;
    this.vy = vy;
  }

   public void updatePos(float newX, float newY)
  {
    this.x = newX;
    this.y = newY;
    shape = new Ellipse2D.Double(newX - radius, newY - radius,radius * 2, radius * 2);
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
    return new Point2D.Float(this.x, this.y);
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
    return (this.x + radius);
  }

  public float getY2()
  {
    return (this.y + radius);
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
  
  public int getLength()
  {
    return (int) (this.radius * 2);
  }

  public int getHeight()
  {
    return (int) (this.radius * 2);
  }
  
  public float getRadius()
  {
    return this.radius;
  }
  
  public void setRadius (float newRadius)
  {
   this.radius = newRadius; 
  }
  
  public void intersect (ButtonObstacle button, float timeLimit)
  {
    MoveEngine.pointIntersectsPoint (this, button.getX(),button.getY(),button.getRadius(),timeLimit, tempResponse);
    if (tempResponse.t < earliestCollisionResponse.t) {
            earliestCollisionResponse.copy(tempResponse);
            GameEngine.increaseScore (20);
      }
  }
  //The following two methods changed something to make the collision work
  
  /**
    * Check if this ball collides with the given BlockLineSegment in the interval 
    * (0, timeLimit]. A line segment has two definite end-points.
    * 
    * @param lineSegment: the line-shape obstacle.
    * @param timeLimit: upperbound of the time interval.
    */
  
  boolean pseudoPaddleHit = false;
   public void intersect(ObstacleLine lineSegment, float timeLimit) {
      // Check the line segment for probable collision.
      MoveEngine.pointIntersectsLineSegment(
            this,
            (float)lineSegment.x1, (float)lineSegment.y1, (float)lineSegment.x2,(float) lineSegment.y2,
            timeLimit, tempResponse);
      if (tempResponse.t < earliestCollisionResponse.t) {
        if (lineSegment instanceof PseudoPaddle){
          pseudoPaddleHit = true;
        }
        else if (lineSegment instanceof BumperObstacleLine)
          GameEngine.increaseScore (((BumperObstacleLine)lineSegment).getScoreValue());
            earliestCollisionResponse.copy(tempResponse);
      }
   }

   /** 
    * Update the states of this ball for the given time, where time <= 1.
    * 
    * @param time: the earliest collision time detected in the system.
    *    If this ball's earliestCollisionResponse.time equals to time, this
    *    ball is the one that collided; otherwise, there is a collision elsewhere.
    */
   public void update(float time) {
    // float lastTime = time;
     float bounce = 0.9f;
    if (pseudoPaddleHit == true)
     {
       //if this happens twice within the same time frame, then move the ball upwards
       if (earliestCollisionResponse.newSpeedY >= 0)
      earliestCollisionResponse.newSpeedY = 5;
       else if (earliestCollisionResponse.newSpeedY < 0)
      earliestCollisionResponse.newSpeedY = -20;
     }
      // Check if this ball is responsible for the first collision?
      if (earliestCollisionResponse.t <= time) { // FIXME: threshold?
         // This ball collided, get the new position and speed
         this.x = earliestCollisionResponse.getNewX(this.x, this.vx);
         this.y = earliestCollisionResponse.getNewY(this.y, this.vy);
         this.vx = earliestCollisionResponse.newSpeedX * bounce;
         this.vy = earliestCollisionResponse.newSpeedY * bounce;
      } else {
         // This ball does not involve in a collision. Move straight.
        //if (time >= 1.0)
        //sumForces (time);
         this.x += this.vx * time;         
         this.y += this.vy * time;         
      }
      // Clear for the next collision detection
      earliestCollisionResponse.reset();
      pseudoPaddleHit = false;
   }
    
  public void keyPressed (KeyEvent e)
  {
   int key = e.getKeyCode ();
   switch (key){
     case KeyEvent.VK_W : updateVelocity (vx, -2); //addAccel (new Accel (100,100)); System.out.println ("Space1");//do Nothing
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