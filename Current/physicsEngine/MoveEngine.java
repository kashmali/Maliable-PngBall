package Files.Current.physicsEngine;

import java.awt.geom.Point2D;
import java.awt.Rectangle;
import java.util.ArrayList;

//Chamge time to be relative to the computer
//Change collisions so that only the first one is registered
//Speed is different between the two engines
public class MoveEngine
{
    public static final float GRAVITY = 0.45f; //1500
  public static final float DRAG = 0.00001f; //0.2
  public static final int MAX_SPEED = 500;
  private static GameEngine main;
  private ArrayList<Accel> constForces = new ArrayList<Accel>();
  private static CollisionResponse tempResponse = new CollisionResponse();
  
  public MoveEngine (GameEngine main)
  {
    this.main = main;
    initializeConstForces();
  }
  
  public MoveEngine ()
  {
    initializeConstForces();
  }

  public void run()
  {
    applyConstForces ();
      moveEnts();
  }
  
  private void initializeConstForces()
  {
    constForces.add(new Accel(0.0,GRAVITY));
  }
  
  private synchronized void applyConstForces()
  {
    double xAccel = 0, yAccel = 0;
    // Find the total acceleration of all const forces.
    for (Accel a : constForces) 
    {
      xAccel += a.getAx();
      yAccel += a.getAy();
    }
    // Apply the sum acceleration to each entity.
    for (Ball s : main.living) 
    {
      s.addAccel(new Accel(xAccel, yAccel));
    }
//    for (Ball s : main.living)
//      s.updateVelocity (s.getvx(),s.getvy() + 0.45f);
    sumForces ();
  }
  
  private synchronized void sumForces()
  {
    for (int i = 0; i < main.living.size(); i++) {
      Ball s = main.living.get(i);
      // Get the sum of all accelerations acting on object.
      Accel theAccel = s.sumAccel();
      // Apply the resulting change in velocity.
      float vx = (float)Math.min (MAX_SPEED,s.getvx() + (theAccel.getAx()));
      float vy = (float)Math.min (MAX_SPEED,s.getvy() + (theAccel.getAy()));
      s.updateVelocity(vx, vy);
      // Apply drag coefficient
      s.applyDrag((float)(1.0 - (DRAG)));
    }
  }
  
  //rename
  private synchronized void moveEnts()
  {
    float time = 1.0f;
    //apply constant forces   
    do
    {
      float tMin = time;
      for (int i = 0; i < main.living.size(); i++) 
    {
      Ball s = main.living.get(i);
      for (int y = 0;y < main.lines.size();y++)
      {
        
        ObstacleLine l = main.lines.get (y);
          //There is a little gap that balls can get into
        s.intersect (l,tMin);
           if (s.earliestCollisionResponse.t < tMin)
           {
             tMin = s.earliestCollisionResponse.t;
           }
      }
      for (int y = 0;y < main.pseudoPaddles.size();y++)
      {
        PseudoPaddle p = main.pseudoPaddles.get (y);
        s.intersect (p,tMin);
           if (s.earliestCollisionResponse.t < tMin)
           {
             tMin = s.earliestCollisionResponse.t;
           }
      }
      for (int c = 0; c < main.buttons.size();c++)
      {
        ButtonObstacle b = main.buttons.get (c);
        s.intersect (b,tMin);
        if (s.earliestCollisionResponse.t < tMin)
        {
             tMin = s.earliestCollisionResponse.t;
        }
      }
      s.update (tMin);
        for (PseudoPaddle p : main.pseudoPaddles)
        {
          p.updatePos (p.getX() + p.getMoveSpeed() *tMin);
        }
      }
    time -= tMin;
    } while (time > 1e-2f);
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
   public static void pointIntersectsLineVertical(Ball s, float lineX, float timeLimit,CollisionResponse response) {
     float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = s.getRadius();
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
   public static void pointIntersectsLineHorizontal(Ball s,float lineY, float timeLimit,CollisionResponse response) {
     float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = s.getRadius();

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
         Ball s,
         float lineX1, float lineY1, float lineX2, float lineY2) {
     
     float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = s.getRadius();
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
   private static void pointIntersectsLineResponse(Ball s,
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
      //System.out.println ("Collision");
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
   public static void pointIntersectsLineSegmentNoEndPoints(Ball s,
         float lineX1, float lineY1, float lineX2, float lineY2,
         float timeLimit,CollisionResponse response) {
      float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = s.getRadius();
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
           s.updateVelocity (-s.getvx(),s.getvy());
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
   public static void pointIntersectsLineSegment(Ball s,
         float lineX1, float lineY1, float lineX2, float lineY2,
         float timeLimit,CollisionResponse response) {
      float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = s.getRadius();
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
         Ball s, 
         float p2X, float p2Y, float p2Radius,
         float timeLimit, CollisionResponse response) {
     float pointX = s.getX();
     float pointY = s.getY();
     float speedX = s.getvx();
     float speedY = s.getvy();
     float radius = s.getRadius();
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
   private static void pointIntersectsPointResponse(Ball s,float p2X, float p2Y,float t,CollisionResponse response) {
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
         Ball s,float p2X,float p2Y, float p2SpeedX, float p2SpeedY, float p2Radius) {
      
     float p1X = (float)s.getCenter().getX();
     float p1Y = (float)s.getCenter().getY();
     float p1SpeedX = s.getvx();
     float p1SpeedY = s.getvy();
     float p1Radius = s.getRadius();

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
}
