package gameFiles;
import java.awt.Color;

/**
 * This class creates a bumber obstacle for the game. When hit it gives the player points.
 * @author Jason P'ng
 * @version 4.0, June 12, 2014
 */
public class BumperObstacleLine extends ObstacleLine
{
    /**
     * Contains the constant bounce force assosiated with each instantiation of the BumperObstacleLine.
     */
  private float bounce;
     /**
      * Contains the constant score value assosiated with each instantiation of the BumperObstacleLine.
      */
  private int scoreValue;
  
    /**
   * This is the constructor of this object, which creates a line defined by two points on the graphical display. <br>
   * The colour of ths line is always red, and will always give the player 10 points. <br>
   * 
   * @param double x1 Specifies the x-coord of the first point.<br>
   * @param double x2 Specifies the x-coord of the second point.<br>
   * @param double y1 Specifies the y-coord of the first point.<br>
   * @param double y2 Specifies the y-coord of the second point.<br>
   */
  public BumperObstacleLine (double x1, double y1, double x2, double y2)
  {
    super (x1,y1,x2,y2,Color.RED);
    scoreValue = 10;
    bounce = 1.2f;
  }
  
    /**
   * This method is responcible for returning the bounce factor of this objecct. <br>
   * 
   * @return float bounce Return the constant bounce force associated with this object.
   */
  public float getBounce ()
  {
    return bounce;
  }
  
  /**
   * This method is responcible for returning the score value of this objecct. <br>
   * 
   * @return int scoreValue Returns the score that the user gets from hittign this object.
   */
  public int getScore()
  {
    return scoreValue;
  }
}