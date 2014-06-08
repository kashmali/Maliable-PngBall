package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

//Warning: x and y and the coordinates of the top left corner
public class CircleSpawn extends Spawn
{
  private float radius;
  

  private CollisionResponse tempResponse = new CollisionResponse ();
  
  public CircleSpawn(int x, int y, float vx, float vy, int m)
  {
    super (x,y,vx,vy,m);
    
    this.radius = 15.0f;
    this.shape = new Ellipse2D.Double(x - radius, y - radius,radius * 2, radius * 2);
  }
  
  public void updatePos(float newX, float newY)
  {
    this.x = newX;
    this.y = newY;
    shape = new Ellipse2D.Double(newX - radius, newY - radius,radius * 2, radius * 2);
  }
  
  public int getLength()
  {
    return (int) (this.radius * 2);
  }

  public int getHeight()
  {
    return (int) (this.radius * 2);
  }
  
  public Point2D getCenter ()
  {
    return new Point2D.Double (x,y);
  }
  public float getRadius()
  {
    return this.radius;
  }
  
  public void setRadius (float newRadius)
  {
   this.radius = newRadius; 
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
            earliestCollisionResponse.copy(tempResponse);
      }
   }
   
  /**
    * Check if this ball collides with the given BlockLineSegment in the interval 
    * (0, timeLimit]. A line segment has two definite end-points.
    * 
    * @param lineSegment: the line-shape obstacle.
    * @param timeLimit: upperbound of the time interval.
    */
   public boolean paddleHit = false;
   public double angleVelocity = 0;
   
   public void intersect(Paddle paddle, float timeLimit) {
      // Check the line segment for probable collision.
      MoveEngine.pointIntersectsLineSegment(
            this,
            (float)paddle.getX(), (float)paddle.getY(), (float)paddle.getX2(),(float) paddle.getY2(),
            timeLimit, tempResponse);
      if (tempResponse.t < earliestCollisionResponse.t) {
        paddleHit = true;
        angleVelocity = paddle.getAngleVelocity();
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
     if (paddleHit == true && angleVelocity > 0){
       bounce = 2.5f;
       System.out.println ("PAddle");
       //if (earliestCollisionResponse.newSpeedX < 
     }
     else if (pseudoPaddleHit == true)
     {
       //if this happens twice within the same time frame, then move the ball upwards
       if (earliestCollisionResponse.newSpeedY >= 0)
      earliestCollisionResponse.newSpeedY = 5;
       else if (earliestCollisionResponse.newSpeedY < 0)
      earliestCollisionResponse.newSpeedY = -20;
      System.out.println ("PseudoHit");
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
      paddleHit = false;
      pseudoPaddleHit = false;
      angleVelocity = 0;
   }
  
}