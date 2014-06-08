package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.awt.Rectangle;
import java.util.ArrayList;

//Chamge time to be relative to the computer
//Change collisions so that only the first one is registered
//Speed is different between the two engines
public class MoveEngine implements Pausable
{
  public final int MAX_SPEED = 500;
  public final int UPDATE_RATE = 30;
  private long timePassed = 0;
  private long curTime = 0;
  private long lastTime = 0;
  private long timeLeft = 0;
  private double timeFraction = 0.0;
  private static GameEngine main;
  private ArrayList<Accel> constForces = new ArrayList<Accel>();
  private static CollisionResponse tempResponse = new CollisionResponse();
  
  public MoveEngine (GameEngine main)
  {
    this.main = main; 
  }
  
  public MoveEngine ()
  {
    
  }

  public void run()
  {
    curTime = System.currentTimeMillis();
    initializeConstForces();
    //while (main.isRunning) {
      if (main.isPaused())
        this.pauseThread();
      
      //applyConstForces();
     //sumForces();
      updateTime();
      moveEnts();
//      try {
//        Thread.sleep(timeLeft);
//      } catch (InterruptedException e) {
//      }
    //}
  }
  
  private void updateTime()
  {
    lastTime = curTime;
    curTime = System.currentTimeMillis();
    timePassed = (curTime - lastTime);
    timeLeft = 1000L/ UPDATE_RATE - timePassed;
    if (timeLeft < 5) timeLeft = 5;
    timeFraction = (timePassed / 1000.0);
  }
  
  private void initializeConstForces()
  {
    constForces.add(new Accel(0.0, main.GRAVITY));
  }
  
  private synchronized void applyConstForces()
  {
    double xAccel = 0, yAccel = 0;
    // Find the total acceleration of all const forces.
    for (int i = 0; i < constForces.size(); i++) {
      xAccel += constForces.get(i).ax();
      yAccel += constForces.get(i).ay();
    }
    // Apply the sum acceleration to each entity.
    for (int i = 0; i < main.living.size(); i++) {
      Spawn s = main.living.get(i);
      s.addAccel(new Accel(xAccel, yAccel));
    }
  }
  
  private synchronized void sumForces()
  {
    for (int i = 0; i < main.living.size(); i++) {
      Spawn s = main.living.get(i);
      // Get the sum of all accelerations acting on object.
      Accel theAccel = s.sumAccel();
      // Apply the resulting change in velocity.
      float vx = (float)Math.min (MAX_SPEED,s.getvx() + (theAccel.ax() * timeFraction));
      float vy = (float)Math.min (MAX_SPEED,s.getvy() + (theAccel.ay() * timeFraction));
      s.updateVelocity(vx, vy);
      // Apply drag coefficient
      s.applyDrag((float)(1.0 - (timeFraction * main.DRAG)));
    }
  }
  
  private synchronized void moveEnts()
  {
    for (int i = 0; i < main.living.size(); i++) {
      Spawn s = main.living.get(i);
      // Get the initial x and y coords.
      double oldX = s.getX(), oldY = s.getY();
      // Calculate the new x and y coords.
      double newX = oldX + (s.getvx() * timeFraction);
      double newY = oldY + (s.getvy() * timeFraction);
      //s.updatePos(newX, newY);
      checkWallCollisions(s);
    }
    //movePaddles();
    checkCollisions();
  }
  
  private synchronized void checkCollisions()
  {
    float time = 1.0f;
    do
    {
      float tMin = time;
      for (int i = 0; i < main.living.size(); i++) 
    {
      Spawn s = main.living.get(i);
      /*Point2D sCenter = s.getCenter();
      for (int j = i + 1; j < main.living.size(); j++) 
      {
        Spawn t = main.living.get(j);
        if (t == null) break;
        try
        {
          Point2D tCenter = t.getCenter();
          double distBetween = sCenter.distance(tCenter);
          double bigR = ((CircleSpawn)s).getRadius() > ((CircleSpawn)t).getRadius() ? ((CircleSpawn)s).getRadius() : ((CircleSpawn) t).getRadius();//(bigR * 2
          if (distBetween < (((CircleSpawn)t).getRadius() + ((CircleSpawn)s).getRadius())) 
            collide(s, t, distBetween);
        }
        catch (ClassCastException e)
        {
          System.out.println ("Some casting issues");
        }
      }
      
      //Put these two together
      for (int x = 0; x < main.obstacles.size();x++)
      {       
        checkObstacleCollision (s,(RectangleSpawn)main.obstacles.get(x));        
      }*/
      //CollisionResponse earliestCollision = new CollisionResponse();
      
      for (int y = 0;y < main.lines.size();y++)
      {
        
        ObstacleLine l = main.lines.get (y);
          //There is a little gap that balls can get into
         // CollisionResponse tempResponse = new CollisionResponse();
          // pointIntersectsLineSegment (s,(float)l.getX(),(float)l.getY(),(float)l.getX2(),(float)l.getY2(),tMin,tempResponse);
        ((CircleSpawn)s).intersect (l,tMin);
           //if (tempResponse.t < earliestCollision.t)
           //  earliestCollision.copy (tempResponse);
           if (s.earliestCollisionResponse.t <= tMin)
           {             
                 System.out.println (y);
                 System.out.println (s.earliestCollisionResponse.t);
           }
           if (s.earliestCollisionResponse.t < tMin){
             tMin = s.earliestCollisionResponse.t;}
           
           
          //pointIntersectsLineHorizontal (s,(float)l.getY(),0.05f);
      }
      ((CircleSpawn)s).update (tMin);
      }
    time -= tMin;
    } while (time > 1e-2f);
  }
  
  public void lineCollide (float time,Spawn s,CollisionResponse earliestCollisionResponse)
  {
    // Check if this ball is responsible for the first collision?
      if (earliestCollisionResponse.t <= time) { // FIXME: threshold?
         // This ball collided, get the new position and speed
         s.setX((int)(earliestCollisionResponse.getNewX(s.getX(),s.getvx())));
         s.setY((int)(earliestCollisionResponse.getNewY(s.getY(), s.getvy())));
         s.updateVelocity (earliestCollisionResponse.newSpeedX,earliestCollisionResponse.newSpeedY);
      } else {
         // This ball does not involve in a collision. Move straight.
         s.updatePos (s.getX() + s.getvx() * time,s.getY() +s.getvy() * time);         
      }
      // Clear for the next collision detection
      earliestCollisionResponse.reset();
  }
  private synchronized void collide(Spawn s, Spawn t, double distBetween)
  {
    // Get the relative x and y dist between them.
    double relX = s.getX() - t.getX();
    double relY = s.getY() - t.getY();
    // Take the arctan to find the collision angle.
    double collisionAngle = Math.atan2(relY, relX);
    // if (collisionAngle < 0) collisionAngle += 2 * Math.PI;
    // Rotate the coordinate systems for each object's velocity to align
    // with the collision angle. We do this by supplying the collision angle
    // to the vector's rotateCoordinates method.
    Vector2D sVel = s.velVector(), tVel = t.velVector();
    sVel.rotateCoordinates(collisionAngle);
    tVel.rotateCoordinates(collisionAngle);
    // In the collision coordinate system, the contact normals lie on the
    // x-axis. Only the velocity values along this axis are affected. We can
    // now apply a simple 1D momentum equation where the new x-velocity of
    // the first object equals a negative times the x-velocity of the
    // second.
    float swap = sVel.x;
    sVel.x = tVel.x;
    tVel.x = swap;
    // Now we need to get the vectors back into normal coordinate space.
    sVel.restoreCoordinates();
    tVel.restoreCoordinates();
    // Give each object its new velocity.
    s.updateVelocity(sVel.x * main.BOUNCE, sVel.y * main.BOUNCE);
    t.updateVelocity(tVel.x * main.BOUNCE, tVel.y * main.BOUNCE);
    // Back them up in the opposite angle so they are not overlapping.
    double minDist = ((CircleSpawn)s).getRadius() + ((CircleSpawn)t).getRadius();
    double overlap = minDist - distBetween;
    double toMove = overlap / 2;
    float newX = s.getX() + (float)(toMove * Math.cos(collisionAngle));
    float newY = s.getY() + (float)(toMove * Math.sin(collisionAngle));
    s.updatePos(newX, newY);
    newX = t.getX() - (float)(toMove * Math.cos(collisionAngle));
    newY = t.getY() - (float)(toMove * Math.sin(collisionAngle));
    t.updatePos(newX, newY);
  }
  
  private synchronized void checkWallCollisions(Spawn s)
  {
    int maxY = (int)main.getSize().getHeight() - 15;//(int)s.getHeight();
    int maxX = (int)main.getSize().getWidth() - 15;//(int)s.getLength();
    //Top boundary
    if (s.getY() > maxY) {
      s.updatePos(s.getX(), maxY);
      s.updateVelocity(s.getvx(), (s.getvy() * -main.BOUNCE));
    }
    //Right boundary
    if (s.getX() > maxX) {
      s.updatePos(maxX, s.getY());
      s.updateVelocity((s.getvx() * -main.BOUNCE), s.getvy());
    }
    //Left boundary
    if (s.getX() < 1 + ((CircleSpawn) s).getRadius()) {
      s.updatePos(((CircleSpawn) s).getRadius(), s.getY());
      s.updateVelocity((s.getvx() * -main.BOUNCE), s.getvy());
    }
    //Bottom boundary
    if (s.getY() < 1 + ((CircleSpawn) s).getRadius()){
      s.updatePos (s.getX(),((CircleSpawn) s).getRadius());
      s.updateVelocity(s.getvx(), (s.getvy() * -main.BOUNCE));
    }
  }
  
  //sometimes, it will randomly go through, should check the corners
  //Also, it shakes when resting on top
  //High speeds will crash the program through the bottom
  public synchronized void checkObstacleCollision (Spawn s, RectangleSpawn t)
  {
    //ends of the box
    float maxX = t.getX() + t.getLength();
    float maxY = t.getY() + t.getHeight();
    //if there is a collision...
    if (t.getShape().intersects (s.getShape().getBounds()))
    {      
      int place = determineArea (s,t);
      //main.pause();
      switch (place){
        case 1 : case 3  : case 7 : case 9:
          //s.updatePos (s.getX() - (s.getvx() * timeFraction),s.getY() - (s.getY() * timeFraction));
          //s.updateVelocity (s.getvx() * -main.BOUNCE, -s.getvy() * -main.BOUNCE);
          break;
        case 5:
          //Something has gone wrong if this happens
          System.out.println ("What happened?");
          //s.updatePos (s.getX() - (s.getvx() * timeFraction),s.getY() - (s.getY() * timeFraction));
          s.updateVelocity (s.getvx() * -main.BOUNCE, -s.getvy() * -main.BOUNCE);
          break;
        case 2:
          s.updateVelocity (s.getvx() * -main.BOUNCE, s.getvy());
          s.updatePos (t.getX() - s.getLength(),s.getY());
          break;
        case 4:
          s.updateVelocity (s.getvx(), s.getvy() * -main.BOUNCE);
          s.updatePos (s.getX(),t.getY() - s.getHeight());
          break;
        case 6:
          s.updateVelocity (s.getvx(), s.getvy() * -main.BOUNCE);
          s.updatePos (s.getX(),maxY);
          break;
        case 8 :
          s.updateVelocity (s.getvx()* -main.BOUNCE, s.getvy());
          s.updatePos (maxX,s.getY());
          break;
        default :
          System.out.println ("Something has gone terribly wrong with rectangle collision");
          s.updatePos (0,0);
          break;
      }
    }
  }
  
  
  public int determineArea (Spawn s, RectangleSpawn t)
  {
    Rectangle area;// = new Rectangle (t.getX(), t.getY(),t.getLength(),t.getHeight());
    
    int counter = 0;
    //change the box parameters
    int length = t.getLength();
    int height = t.getHeight();      
    for (int x = 0; x < 3;x++)
    {
      
      for (int y = 0;y < 3;y++)
      {
        counter++;
        area = new Rectangle ((int)((t.getX() - length) + (x * length)),(int)((t.getY() - height) + (y * height)),length, height);
        
        if (area.contains (s.getCenter()))
        {
          System.out.println (counter);
          return counter;
        }
      }        
    }
    return 5;
  }
  
  public void movePaddles()
  {
    //if angle > 360; angle = angle mod 360 - ake sure that the angle does not exceed 360 degrees
    //if o = right, if angle < 135, if angle > 225
    //if o = left, if angle > 45 && angle < 315
    // then velocity = 0;
    //Note: the angle goes from 0 - 360 in a clockwise manner. The far right is 0 degrees
    for (Paddle p : main.paddles)
    {
      double velocity = p.getAngleVelocity();
      int o = p.getOrientation ();
      if (o == Paddle.RIGHT)
      {
        p.setAngle (formatAngle(p.getAngle() + velocity)); //turning clockwise
        if (p.getAngle() < 135)
        {
          p.setAngle (135);
          p.setAngleVelocity (0);
        }
        else if (p.getAngle () > 225)
        {
          p.setAngle (225);
          p.setAngleVelocity (0);
        }
        
      }
      else if (o == Paddle.LEFT)
      {
        p.setAngle (formatAngle(p.getAngle() - velocity)); //turning counterclockwise
        if (p.getAngle() > 45 && p.getAngle() < 180)
        {
          p.setAngle (45);
          p.setAngleVelocity (0);
        }
        else if (p.getAngle () >= 180 && p.getAngle() < 315 )
        {
          p.setAngle (315);
          p.setAngleVelocity (0);
        }
      }
    }
  }
  
  public double formatAngle (double angle)
  {
    angle = angle % 360;
    if (angle < 0)
      angle = 360 + angle;
    return angle;
  }
  /**
    * Detect collision for a moving point hitting a vertical line,
    * within the given timeLimit.
    * If collision is detected within the timeLimit, compute collision time and 
    * response in the given CollisionResponse object. Otherwise, set collision time
    * to infinity.
    * The result is passed back in the given CollisionResponse object.
    * 
    * @param pointX : x-position of the center of the point.
    * @param pointY : y-position of the center of the point.
    * @param speedX : speed in x-direction.
    * @param speedY : speed in y-direction.
    * @param radius : radius of the point. Zero for a true point.
    * @param lineX : x-value of the vertical line
    * @param timeLimit : max time to detect collision, in (0, 1] range.
    * @param response : If collision is detected, update the collision time and response.
    *                   Otherwise, set collision time to infinity.
    */
   public static void pointIntersectsLineVertical(Spawn s, float lineX, float timeLimit,CollisionResponse response) {
     float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = ((CircleSpawn)s).getRadius();
      // Assumptions:
      assert (radius >= 0) : "Negative radius!";
      assert (timeLimit > 0) : "Non-positive time";

     response.reset();  // Reset detected collision time to infinity

      // No collision possible if speedX is zero
      if (speedX == 0) { // FIXME: Should I use a threshold?
         return;
      }

      // Compute the distance to the line, offset by radius.
      float distance;
      if (lineX > pointX) {
         distance = lineX - pointX - radius; 
      } else {
         distance = lineX - pointX + radius; 
      }
      
      float t = distance / speedX;  // speedX != 0
      // Accept 0 < t <= timeLimit
      if (t > 0 && t <= timeLimit) {
         response.t = t;
         response.newSpeedX = -speedX;  // Reflect horizontally
         response.newSpeedY = speedY;   // No change vertically
      }
      // Error analysis:
      // nextX = lineX, which falls on the line. It never crosses the line. 
      // In next step t = 0 which is excluded from the acceptable t.
   }

   /**
    * @see movingPointIntersectsLineVertical().
    */
   public static void pointIntersectsLineHorizontal(Spawn s,float lineY, float timeLimit,CollisionResponse response) {
     float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = ((CircleSpawn)s).getRadius();

      // Assumptions:
      assert (radius >= 0) : "Negative radius!";
      assert (timeLimit > 0) : "Non-positive time";

     response.reset();  // Reset detected collision time to infinity

      // No collision possible if speedY is zero
      if (speedY == 0) { // Should I use a threshold?
         return;
      }

      // Compute the distance to the line, offset by radius.
      float distance;
      if (lineY > pointY) {
         distance = lineY - pointY - radius; 
      } else {
         distance = lineY - pointY + radius; 
      }
      
      float t = distance / speedY;  // speedY != 0
      // Accept 0 < t <= timeLimit
      if (t > 0 && t <= timeLimit) {
         response.t = t;
           //s.updateVelocity (s.getvx(),-s.getvy() * main.BOUNCE);
         response.newSpeedY = -speedY;  // Reflect vertically
         response.newSpeedX = speedX;   // No change horizontally
      }
   }
   
   // The solution for colliding to a line has tow parts: t and lambda.
   // Re-use this to avoid repeatedly allocating new instances.
   private static float[] pointLineResult = new float[2];
   /**
    * Helper method to compute the collision time (t) and point of impact
    * on the line (lambda), for a moving point and a line.
    * 
    * @param pointX : x-position of the center of the point.
    * @param pointY : y-position of the center of the point.
    * @param speedX : speed in x-direction.
    * @param speedY : speed in y-direction.
    * @param radius : radius of the point. Zero for a true point.
    * @param lineX1 : line's beginning point x value.
    * @param lineY1 : line's beginning point y value.
    * @param lineX2 : line's ending point x value.
    * @param lineY2 : line's ending point y value.
    * @return an float[2], where
    *   First element is t, or infinity if no collision detected. 
    *   Second element is lambda, point of impact on the line. 
    */
   private static float[] pointIntersectsLineDetection(
         Spawn s,
         float lineX1, float lineY1, float lineX2, float lineY2) {
     
     float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = ((CircleSpawn)s).getRadius();
      double lineVectorX = lineX2 - lineX1;
      double lineVectorY = lineY2 - lineY1;

      // Compute the offset caused by radius
      double lineX1Offset = lineX1;
      double lineY1Offset = lineY1;
      
      // FIXME: Inefficient!
      if (radius > 0) {
         // Check which side of the line the point is. Offset reduces the distance
         double lineAngle = Math.atan2(lineVectorY, lineVectorX);
         double rotatedY = rotate(pointX - lineX1, pointY - lineY1, lineAngle)[1];
         if (rotatedY >  0) {
            lineX1Offset -= radius * Math.sin(lineAngle);
            lineY1Offset += radius * Math.cos(lineAngle);
         } else {
            lineX1Offset += radius * Math.sin(lineAngle);
            lineY1Offset -= radius * Math.cos(lineAngle);
         }
      }
      
      // Solve for t (time of collision) and lambda (point of impact on the line)
      double t;
      double lambda;
      double det = -speedX * lineVectorY + speedY * lineVectorX;

      if (det == 0) {             // FIXME: Use a threshold?
         t = Double.MAX_VALUE;    // No collision possible.
         lambda = Double.MAX_VALUE;
      }

      double xDiff = lineX1Offset - pointX;
      double yDiff = lineY1Offset - pointY;
      t = (-lineVectorY * xDiff + lineVectorX * yDiff) / det;
      lambda = (-speedY * xDiff + speedX * yDiff) / det;

      pointLineResult[0] = (float)t;
      pointLineResult[1] = (float)lambda;
      return pointLineResult;
   }
  /**
    * Helper method to compute the collision response given collision time (t)
    * for a moving point and a line.
    * Store and return the result in the given CollisionResponse object.
    * 
    * @param pointX : x-position of the center of the point.
    * @param pointY : y-position of the center of the point.
    * @param speedX : speed in x-direction.
    * @param speedY : speed in y-direction.
    * @param lineX1 : line's beginning point x value.
    * @param lineY1 : line's beginning point y value.
    * @param lineX2 : line's ending point x value.
    * @param lineY2 : line's ending point y value.
    * @param response : update collision time and response.
    * @param t : the given detected collision time
    */
   private static void pointIntersectsLineResponse(Spawn s,
         float lineX1, float lineY1, float lineX2, float lineY2, float t,CollisionResponse response) {
      float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     
       response.t = t;
      
      // Direction along the line of collision is P, normal is N.
      // Project velocity from (x, y) to (p, n)
      double lineAngle = Math.atan2(lineY2 - lineY1, lineX2 - lineX1);
      double[] result = rotate(speedX, speedY, lineAngle);
      double speedP = result[0];
      double speedN = result[1];

      // Reflect along the normal (N), no change along the line of collision (P)
      double speedPAfter = speedP;
      double speedQAfter = -speedN;

      // Project back from (p, n) to (x, y)
      result = rotate(speedPAfter, speedQAfter, -lineAngle);
      response.newSpeedX = (float)result[0];
      response.newSpeedY = (float)result[1];
      System.out.println ("Collision");
   }

   /**
    * Detect collision for a moving point hitting hitting an arbitrary line segment,
    * within the given timeLimit. No need to consider the two end points.
    * If collision is detected within the timeLimit, compute collision time and 
    * response in the given CollisionResponse object. Otherwise, set collision time
    * to infinity.
    * The result is passed back in the given CollisionResponse object.
    * 
    * @param pointX : x-position of the center of the point.
    * @param pointY : y-position of the center of the point.
    * @param speedX : speed in x-direction.
    * @param speedY : speed in y-direction.
    * @param radius : radius of the point. Zero for a true point.
    * @param lineX1 : line's beginning point x value.
    * @param lineY1 : line's beginning point y value.
    * @param lineX2 : line's ending point x value.
    * @param lineY2 : line's ending point y value.
    * @param timeLimit : max time to detect collision, in (0, 1] range.
    * @param response : If collision is detected, update the collision time and response.
    *                   Otherwise, set collision time to infinity.
    */
   public static void pointIntersectsLineSegmentNoEndPoints(Spawn s,
         float lineX1, float lineY1, float lineX2, float lineY2,
         float timeLimit,CollisionResponse response) {
      float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = ((CircleSpawn)s).getRadius();
      // Assumptions:
      assert (radius >= 0) : "Negative radius!";
      assert (timeLimit > 0) : "Non-positive time";
      // lineX1 == lineX2 && lineY1 == lineY2, a point?

      // If line is vertical or horizontal, use simplified solution.
      if (lineX1 == lineX2) {  // Vertical line
         pointIntersectsLineVertical(s,lineX1, timeLimit,response);
         // Need to confirm that the point of impact is within the line-segment
         double impactY = response.getImpactY (pointY,speedY);
         if (!(impactY >= lineY1 && impactY <= lineY2 || impactY >= lineY2 && impactY <= lineY1)) {
               response.reset();// no collision
         }
         if (response.t > 0 && response.t <= timeLimit)
           s.updateVelocity (-s.getvx()* main.BOUNCE,s.getvy());
         return;
      } else if (lineY1 == lineY2) {  // Horizontal line
         pointIntersectsLineHorizontal(s, lineY1, timeLimit,response);
         // Need to confirm that the point of impact is within the line-segment
         double impactX = response.getImpactX (pointX,speedX);
         if (!(impactX >= lineX1 && impactX <= lineX2 || impactX >= lineX2 && impactX <= lineX1)) {
            response.reset();//no collision
         }
         return;
      }

      response.reset();  // Set detected collision time to infinity

      // Call helper method to compute the collision time.
      float[] result = pointIntersectsLineDetection(
            s,
            lineX1, lineY1, lineX2, lineY2);
      float t = result[0];
      float lambda = result[1];
      
      // Accept 0 < t <= timeLimit
      if (t > 0 && t <= timeLimit && lambda >=0 && lambda <= 1) {
         // Call helper method to compute response.
         pointIntersectsLineResponse(s, 
                  lineX1, lineY1, lineX2, lineY2, t,response);
      }
   }
   
   /**
    * Detect collision for a moving point hitting hitting an arbitrary line segment,
    * within the given timeLimit. Consider both the end points.
    * If collision is detected within the timeLimit, compute collision time and 
    * response in the given CollisionResponse object. Otherwise, set collision time
    * to infinity.
    * The result is passed back in the given CollisionResponse object.
    * 
    * @param pointX : x-position of the center of the point.
    * @param pointY : y-position of the center of the point.
    * @param speedX : speed in x-direction.
    * @param speedY : speed in y-direction.
    * @param radius : radius of the point. Zero for a true point.
    * @param lineX1 : line's beginning point x value.
    * @param lineY1 : line's beginning point y value.
    * @param lineX2 : line's ending point x value.
    * @param lineY2 : line's ending point y value.
    * @param timeLimit : max time to detect collision, in (0, 1] range.
    * @param response : If collision is detected, update the collision time and response.
    *                   Otherwise, set collision time to infinity.
    */
   public static void pointIntersectsLineSegment(Spawn s,
         float lineX1, float lineY1, float lineX2, float lineY2,
         float timeLimit,CollisionResponse response) {
      float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = ((CircleSpawn)s).getRadius();
      // Assumptions:
      assert (radius >= 0) : "Negative radius!";
      assert (timeLimit > 0) : "Non-positive time";
      // lineX1 == lineX2 && lineY1 == lineY2, a point?
      
      response.reset();  // Reset the resultant response for earliest collision

      // Check the line segment for probable collision.
      pointIntersectsLineSegmentNoEndPoints(s, lineX1, lineY1, lineX2, lineY2,timeLimit,tempResponse);
      if (tempResponse.t < response.t) {
         response.copy(tempResponse);
      }
      // Check the two end points (with radius = 0) for probable collision
      pointIntersectsPoint(s,
            lineX1, lineY1, 0,
            timeLimit,tempResponse);
      if (tempResponse.t < response.t) {
         response.copy (tempResponse);
      }
      pointIntersectsPoint(s,lineX2, lineY2, 0,timeLimit,tempResponse);
      if (tempResponse.t < response.t) {
         response.copy (tempResponse);
      }
   }
   /**
    * Detect collision for a moving point hitting a stationary point,
    * within the given timeLimit.
    * If collision is detected within the timeLimit, compute collision time and 
    * response in the given CollisionResponse object. Otherwise, set collision time
    * to infinity.
    * The result is passed back in the given CollisionResponse object.
    * 
    * @param p1X : x-position of the center of point p1.
    * @param p1Y : y-position of the center of point p1.
    * @param p1SpeedX : p1's speed in x-direction.
    * @param p1SpeedY : p1's speed in y-direction.
    * @param p1Radius : p1's radius. Zero for a true point.
    * @param p2X : x-position of the center of point p2.
    * @param p2Y : y-position of the center of point p2.
    * @param p2Radius : p2's radius. Zero for a true point.
    * @param timeLimit : max time to detect collision, in (0, 1] range.
    * @param p1Response : If collision is detected, update the collision time and response for p1.
    *                   Otherwise, set collision time to infinity.
    */
   public static void pointIntersectsPoint(
         Spawn s, 
         float p2X, float p2Y, float p2Radius,
         float timeLimit, CollisionResponse response) {
     float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = ((CircleSpawn)s).getRadius();
      // Assumptions:
      assert (radius >= 0) : "Negative radius!";
      assert (timeLimit > 0) : "Non-positive time";

      response.reset();  // Set detected collision time to infinity
      
      // Call helper method to compute and return the collision time t.
      float t = pointIntersectsMovingPointDetection(
            s, 
            p2X, p2Y, 0f, 0f, p2Radius);
      
      // Accept 0 < t <= timeLimit
      if (t > 0 && t <= timeLimit) {
         // Call helper method to compute and return the response given collision time t.
        pointIntersectsPointResponse(s, p2X, p2Y, t,response);
      }
   }
   
   /**
    * Helper method to compute the collision response given the collision time (t),
    * for a moving point hitting a stationary point.
    * Store and return the results in the given CollisionResponse object.
    * 
    * @param p1X : x-position of the center of point p1.
    * @param p1Y : y-position of the center of point p1.
    * @param p1SpeedX : p1's speed in x-direction.
    * @param p1SpeedY : p1's speed in y-direction.
    * @param p2X : x-position of the center of point p2.
    * @param p2Y : y-position of the center of point p2.
    * @param p1Response : To update the collision time and response for p1.
    *                     Reset time to infinity if error is detected.
    * @param t : the given detected collision time.
    */
   private static void pointIntersectsPointResponse(Spawn s,float p2X, float p2Y,float t,CollisionResponse response) {
      float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();

      response.t = t; // Update collision time in response
      
      // Need to get the point of impact to form the line of collision.
      double p1ImpactX = response.getImpactX (pointX,speedX);
      double p1ImpactY = response.getImpactY (pointY, speedY); 
      
      // Direction along the line of collision is P, normal is N.
      // Get the direction along the line of collision
      double lineAngle = Math.atan2(p2Y - p1ImpactY, p2X - p1ImpactX);

      // Project velocities from (x ,y) to (p, n)
      double[] result = rotate(speedX, speedY, lineAngle);
      double p1SpeedP = result[0];
      double p1SpeedN = result[1];

      // Collision possible only if p1SpeedP > 0
      // Needed to resolve overlaps.
      if (p1SpeedP <= 0) {
         //System.out.println("velocities cannot collide! t = " + t);
         response.reset();  // No collision, keep moving.
      }

      double p1SpeedPAfter = -p1SpeedP;
      double p1SpeedNAfter = p1SpeedN;

      // Project the velocities back from (p, n) to (x, y)
      result = rotate(p1SpeedPAfter, p1SpeedNAfter, -lineAngle);
      response.newSpeedX = (float)result[0];
      response.newSpeedY = (float)result[1];
   }
   /**
    * Helper method to detect the collision time (t) for two moving points.
    * 
    * @param p1X : x-position of the center of point p1.
    * @param p1Y : y-position of the center of point p1.
    * @param p1SpeedX : p1's speed in x-direction.
    * @param p1SpeedY : p1's speed in y-direction.
    * @param p1Radius : p1's radius. Zero for a true point.
    * @param p2X : x-position of the center of point p2.
    * @param p2Y : y-position of the center of point p2.
    * @param p2SpeedX : p2's speed in x-direction.
    * @param p2SpeedY : p2's speed in y-direction.
    * @param p2Radius : p2's radius. Zero for a true point.
    * @return smaller positive t, or infinity if collision is not possible.
    */
   private static float pointIntersectsMovingPointDetection(
         Spawn s,float p2X,float p2Y, float p2SpeedX, float p2SpeedY, float p2Radius) {
      
     float p1X = (float)s.getCenter().getX();
     float p1Y = (float)s.getCenter().getY();
     float p1SpeedX = s.getvx();
     float p1SpeedY = s.getvy();
     float p1Radius = ((CircleSpawn)s).getRadius();

      // Rearrange the parameters to set up the quadratic equation.
      double centerX = p1X - p2X;
      double centerY = p1Y - p2Y;
      double speedX = p1SpeedX - p2SpeedX;
      double speedY = p1SpeedY - p2SpeedY;
      double radius = p1Radius + p2Radius;
      double radiusSq = radius * radius;
      double speedXSq = speedX * speedX;
      double speedYSq = speedY * speedY;
      double speedSq = speedXSq + speedYSq;
   
      // Solve quadratic equation for collision time t
      double termB2minus4ac = radiusSq * speedSq - (centerX * speedY - centerY * speedX)
            * (centerX * speedY - centerY * speedX);
      if (termB2minus4ac < 0) {
         // No intersection.
         // Moving spheres may cross at different times, or move in parallel.
         return Float.MAX_VALUE;
      }
      
      double termMinusB = -speedX * centerX - speedY * centerY;
      double term2a = speedSq;
      double rootB2minus4ac = Math.sqrt(termB2minus4ac);
      double sol1 = (termMinusB + rootB2minus4ac) / term2a;
      double sol2 = (termMinusB - rootB2minus4ac) / term2a;
      // Accept the smallest positive t as the solution.
      if (sol1 > 0 && sol2 > 0) {
         return (float)Math.min(sol1, sol2);
      } else if (sol1 > 0) {
         return (float)sol1;
      } else if (sol2 > 0) {
         return (float)sol2;
      } else {
         // No positive t solution. Set detected collision time to infinity.
         return Float.MAX_VALUE;
      }
   }
   /**
    * Note: not made by me. Taken from the Internet
    * 
    * Helper method to rotation vector (x, y) by theta, in Graphics coordinates.
    * y-axis is inverted. 
    * theta measured in counter-clockwise direction.
    * Re-use the double[] rotateResult to avoid repeated new operations.
    * 
    * @param x : x coordinate of the vector to be rotated.
    * @param y : y coordinate of the vector to be rotated, inverted.
    * @param theta : rotational angle in radians, counter-clockwise.
    * @return An double array of 2 elements x and y, in the rotated coordinates.
    */
   private static double[] rotateResult = new double[2];
   private static double[] rotate(double x, double y, double theta) {
      double sinTheta = Math.sin(theta);
      double cosTheta = Math.cos(theta);
      rotateResult[0] = x * cosTheta + y * sinTheta;
      rotateResult[1] = -x * sinTheta + y * cosTheta;
      return rotateResult;
   }
   
  public void pauseThread ()
  {
    while (main.isPaused())
    {
      try
      {
        Thread.sleep (1);
        updateTime();
        //System.out.println ("Paused");
      }
      catch (InterruptedException e)
      {
        
      }
    }    
  }
  
  //not used
  public synchronized void checkTriangleObstacleCollision (Spawn s, Spawn t)
  {
    //(s,t.getX(),t.getY(),t.getX() + ((TriangleSpawn)t).getOpposite(),t.getY() + ((TriangleSpawn)t).getAdjacent(),t.getX() + ((TriangleSpawn)t).getOpposite(),t.getY()); 
  }
}
