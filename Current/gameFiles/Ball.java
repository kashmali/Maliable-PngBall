package gameFiles;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * The bouncing ball. This is the main object in the game. The intersects methods and update method were coded by Hock-Chuan Chua.
 * They have been modified to fit the program. The Constructors, access methods and methods that change the velocity of the ball
 * were coded by Mike. The code is a mixture of two programs modified to fit together.
 * @author Mike aka Wilkystyles
 * @author Hock-Chuan Chua
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class Ball
{
  /**
   * The radius of the ball. Default is 15.
   */
  private float radius;
   /**
   * The earliest response the ball encounters within a millisecond.
   */
  private CollisionResponse earliestCollisionResponse = new CollisionResponse();
   /**
   * The velocity along the x - axis.
   */
  protected float vx;
   /**
   * The velocity along the y - axis.
   */
    protected float vy;
   /**
   * A shape that represents the ball.
   */
  protected Shape shape;
   /**
   * The x coordinate of the center of the ball.
   */
  protected float x;
     /**
   * The y coordinate of the center of the ball.
   */
    protected float y;
   /**
   * The forces of acceleration the ball currently has.
   */
  private ArrayList<Accel> accelerations = new ArrayList<Accel>();
   /**
   * A temporary CollisionRespone to determine which response is the earliest.
   */
  private CollisionResponse tempResponse = new CollisionResponse ();
   /**
   * The bounce value by which velocity is multiplied by.
   */
  public float bounce = 0.9f;
   /**
   * The score value that is added when the ball hits an object.
   */
  public int scoreIncrease = 0;
  /**
   * Confirms if the ball has hit the paddle or not.
   */
   boolean pseudoPaddleHit = false;
   
   /**
   * This is the constructor of the CircleSpawn class and is responcible for creating the object with specified<br>
   * dimensions and values.
   * 
   * @param int x This variable stores the innitial x position of the center.<br>
   * @param int y This variabel stores the innitial y position of the center.<br>
   * @param float vx This is the innitial x velocty of the object.<br>
   * @param float vy This is the innitial y velocity of the object.<br>
   */
  public Ball(int x, int y, float vx, float vy)
  {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.radius = 15f;
  }
  
  /**
   * constructor for the ball class with on the coordinates provided. <br>
   * velocities are set to be 0.
   * 
   * @param int x the x coordinate of the center of the ball.
   * @param int y the y coordinate of the center of the ball.
   */
  public Ball(int x, int y)
  {
    this(x, y, 0.0f, 0.0f);
  }
  
  /**
   * Multiplies the velocities of the ball by a drag multiplier
   * Written by Mike modified by Jason P'ng
   * 
   *@param float drag the decimal to multiply velocities by.
   */
  public void applyDrag(float drag)
  {
    this.vx = (drag * this.vx);
    this.vy = (drag * this.vy);
  }
  
  /**
   * Adds all accelerations acting on the ball into a single Accel object <br>
   * Written by Mike modified by Jason P'ng
   *
   * @param double xAccel The sum of the x accelerations.
   * @param double yAccel The sum of the y accelerations.
   * @return new acceleration that is the sum of current acting forces.
   */
  public Accel sumAccel()
  {
    double xAccel = 0, yAccel = 0;
    for (int i = 0; i < this.accelerations.size(); i++) {
      xAccel += this.accelerations.get(i).getAx();
      yAccel += this.accelerations.get(i).getAy();
    }
    this.accelerations.clear();
    return new Accel(xAccel, yAccel);
  }
  
  /**
   * Adds a new acceleration force to the ball
   * Written by Mike modified by Jason P'ng
   * 
   * @param Accel a the force to be added
   */
  public void addAccel(Accel a)
  {
    this.accelerations.add(a);
  }
  
   /**
   * Updates the velocities according the the parameters.
   * Written by Mike modified by Jason P'ng
   * 
   * @param float vx The new x velocity.
   * @param float vy The new y velocity.
   */
  public void updateVelocity(float vx, float vy)
  {
    this.vx = vx;
    this.vy = vy;
  }
  
   /*This method is responsible for updating the position of the circle spawn.<br>
    * Written by Mike modified by Jason P'ng
   *
   * @param float newX This is the new X position.<br>
   * @param float newY This is the new Y position.<br>
   */
  public void updatePos(float newX, float newY)
  {
    this.x = newX;
    this.y = newY;
    shape = new Ellipse2D.Double(newX - radius, newY - radius,radius * 2, radius * 2);
  }
  
   /**
   * Accessor method for x velocity.
   * 
   * @return the current x velocity
   */
  public float getvx()
  {
    return this.vx;
  }
  
  /**
   * Accessor method for y velocity.
   * 
   * @return the current y velocity
   */
  public float getvy()
  {
    return this.vy;
  }
  
  /**
   * Accessor method for x coordinate.
   * 
   * @return the current x coordinate
   */
  public float getX()
  {
    return this.x;
  }
  
  /**
   * Accessor method for y coordinate.
   * 
   * @return the current y coordinate
   */
  public float getY()
  {
    return this.y;
  }
  
  /**
   * Accessor method for current shape.
   * 
   * @return Shape that represents the ball
   */
  public Shape getShape ()
  {
    return shape; 
  }
  
  /**
   * Accessor method for earliestCollisionResponse
   * 
   * @return the earlistCollisionResponse for the next update
   */
  public CollisionResponse getEarliestCollisionResponse ()
  {
   return earliestCollisionResponse; 
  }
  
  /**
   * sets the x coordinate.
   * 
   * @param int newX The new x coordinate
   */
  public void setX (int newX)
  {
    this.x = newX;
  }
  /**
   * sets the y coordinate.
   * 
   * @param int newY The new y coordinate
   */
  public void setY(int newY)
  {
    this.y = newY;
  }
  
  /**
   * This method returns the centre of the object as a Point2D.
   * 
   * @return a Point2D object of the coordinate of the center of the ball.
   */
  public Point2D getCenter ()
  {
    return new Point2D.Double (x,y);
  }
 /**
  * This method returns the radius of the object.
  * 
  * @return the radius of the ball.
  */
  public float getRadius()
  {
    return this.radius;
  }
  /**This method sets the radius of the object.
   * 
   * @param float newRadius This is the new radius of the object.
   */
  public void setRadius (float newRadius)
  {
   this.radius = newRadius; 
  }
  
  /**
   * Check if the ball collides with the given ButtonObstacle within the time interval. <br>
   * Written by Hock-Chuan Chau modified by Jason P'ng <br>
   * If statement checks if the collision is earlier than the current earliest collision.
   * @param ButtonObstacle button the obstacle being checked
   * @param float timeLimit the amount of time being checked for
   */
  public void intersect (ButtonObstacle button, float timeLimit)
  {
    MoveEngine.pointIntersectsPoint (this, button.getX(),button.getY(),button.getRadius(),timeLimit, tempResponse);
    if (tempResponse.t < earliestCollisionResponse.t) {
      earliestCollisionResponse.copy(tempResponse);
      scoreIncrease = button.getScore ();
    }
  }
  
  
  /**
   * Check if this ball collides with the given BlockLineSegment in the interval 
   * (0, timeLimit]. A line segment has two definite end-points. <br>
   * Written by Hock-Chuan Chau modified by Jason P'ng <br>
   * If statement checks if the collision is earlier than the current earliest collision. <br>
   * nested if statement checks for a type of lineSegment to add an effect. 
   * 
   * @param lineSegment: the line-shape obstacle.
   * @param timeLimit: upperbound of the time interval.
   */
  public void intersect(ObstacleLine lineSegment, float timeLimit) {
    // Check the line segment for probable collision.
    MoveEngine.pointIntersectsLineSegment(this,
                                          (float)lineSegment.x1, (float)lineSegment.y1, (float)lineSegment.x2,(float) lineSegment.y2,
                                          timeLimit, tempResponse);
    if (tempResponse.t < earliestCollisionResponse.t) {
      if (lineSegment instanceof PseudoPaddle)
      {
        pseudoPaddleHit = true;
      }
      else if (lineSegment instanceof BumperObstacleLine)
      {
        scoreIncrease = ((BumperObstacleLine)lineSegment).getScore();
      bounce = ((BumperObstacleLine)lineSegment).getBounce ();
      }
      earliestCollisionResponse.copy(tempResponse);
    }
  }
  
  /** 
   * Update the states of this ball for the given time, where time <= 1. <p>
   * Written by Hock-Chuan Chau modified by Jason P'ng <br>
   * if (pseudoPaddleHit == true) - Checks if the padde was hit to apply a fixed velocity <br>
   * Nested if apply the appropriate velocity up or down according the the balls previous velocity. <br>
   * if (earliestCollisionResponse.t <= time) - Checks if the collision is within the time limit. Otherwise, move on.
   * 
   * @param time: the earliest collision time detected in the system.
   *    If this ball's earliestCollisionResponse.time equals to time, this
   *    ball is the one that collided; otherwise, there is a collision elsewhere.
   */
  public void update(float time) {
    // float lastTime = time;
    
    if (pseudoPaddleHit == true)
    {
      //if this happens twice within the same time frame, then move the ball upwards
      if (earliestCollisionResponse.newSpeedY >= 0){
        earliestCollisionResponse.newSpeedY = 5;}
      else if (earliestCollisionResponse.newSpeedY < 0){
        earliestCollisionResponse.newSpeedY = -20;}
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

      updatePos (x + (vx * time),y + (vy * time));
    }
    GameEngine.increaseScore (scoreIncrease);
    // Clear for the next collision detection
    earliestCollisionResponse.reset();
    scoreIncrease = 0;
    bounce = 0.9f;
    pseudoPaddleHit = false;
  }
}