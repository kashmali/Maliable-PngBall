package Files.Current.physicsEngine;

public class Accel
{
 private double ax, ay;

 public Accel(double ax, double ay)
 {
  this.ax = ax;
  this.ay = ay;
 }

 public double getAx()
 {
  return this.ax;
 }

 public double getAy()
 {
  return this.ay;
 }
 
 public String toString ()
 {
  return (ax + ", " + ay); 
 }
}