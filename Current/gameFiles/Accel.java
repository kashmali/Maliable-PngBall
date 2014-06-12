package gameFiles;

/*
 * This class is responcible for giving the pinball an acceleration and storing the data individually in case 
 * multiple pinballs want to be made.
 * 
 * @author Mike aka Wilkystyles
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class Accel
{
 /**
  * This private double is responsible for keeping the decimal number for acceleration for the x plane.
  */
 private double ax;
    /**
  * This private double is responsible for keeping the decimal number for acceleration for the y plane.
  */
 private double ay;

  /**
  * This is the constructor, responcible for assigning the acceleration to it's variables.
  * 
  * @param double ax This is the horizontal acceleration.
  * @param double ay This is the verticle acceleration.
  */
 public Accel(double ax, double ay)
 {
  this.ax = ax;
  this.ay = ay;
 }

  /** 
   * An accessor method responcible for returing acceleration in the x-axis. <br>
  * 
  * @return double ax contains the horizontal acceleration. 
  */
 public double getAx()
 {
  return this.ax;
 }

  /** 
   * An accessor method responcible for returing acceleration in the y-axis. <br>
  * 
  * @return double ay contains the horizontal acceleration.
  */
 public double getAy()
 {
  return this.ay;
 }
 
  /**
   * A method that returns the string equivalent of the current accerlaeration. <br>
  * 
  * @return String string Returns the text equivalent of the values with a comma seperating them. <br>
  */
 public String toString ()
 {
  return (ax + ", " + ay); 
 }
}