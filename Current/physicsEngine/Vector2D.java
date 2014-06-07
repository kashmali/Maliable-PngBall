package Files.Current.physicsEngine;

public class Vector2D
{
 public float x;
 public float y;
 public double restoreAngle;

 public Vector2D(float x, float y)
 {
  this.x = x;
  this.y = y;
  this.restoreAngle = 0.0;
 }

 public double angle()
 {
  return Math.atan2(y, x);
 }

 public double mag()
 {
  return Math.sqrt((x * x) + (y * y));
 }

 public void rotateCoordinates(double tiltAngle)
 {
  this.restoreAngle += tiltAngle;
  double angle = angle();
  double mag = mag();
  angle -= tiltAngle;
  x = (float)(mag * Math.cos(angle));
  y = (float)(mag * Math.sin(angle));
 }

 public void restoreCoordinates()
 {
  double angle = angle();
  double mag = mag();
  angle += restoreAngle;
  x = (float)(mag * Math.cos(angle));
  y = (float)(mag * Math.sin(angle));
  restoreAngle = 0.0;
 }
}
