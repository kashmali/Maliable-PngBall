package gameFiles;
import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Button obstacles for the game, a cirlce that prevents the ball from passing through it and grants points when hit.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class ButtonObstacle implements Obstacle
{
  /**
   * x coordinate of the midpoint of the button obstacle.
   */
  private float x;
  /**
   * y coordinate of the midpoint of the button obstacle.
   */
  private float y;
    /**
   * The radius of the button obstacle.
   */
  private float radius;
    /**
   * Colour of the button obstacle.
   */
  private Color colour;
    /**
   * The bounce factor of the button obstacle.
   */
  private float bounce;
  
  /**
   * This is the default constructor of this object, and predifine the coordinates, radius, bounce value and colour.
   */
  public ButtonObstacle ()
  {
    this.x = 100;
    this.y = 100;
    this.radius = 15;
    colour = Color.BLACK;
    this.bounce = 1.2f;
  }
  
   /**
   * This is constructor is used to create buttonobstacles in the specified region, with a specified colour and <br>
   * specified bounce value. <br>
   * 
   * @param float x        this variable specifies the x coordinate of the center of the ball. <br>
   * @param float y        this variable specifies the y coordinate of the center of the ball.<br>
   * @param float radius   this variable specifies the radius of the obstacle.<br>
   * @param float bounce   this variable specifies the change in energy when a collision occurs.<br>
   * @param Color colour   this variable specifies the colour of the onject.<br>
   */
  public ButtonObstacle (float x, float y, float radius,float bounce,Color colour)
  {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.bounce = bounce;
    this.colour = colour;
  }
  
  /**
   * This method is used to update the current position of the obstacle in case it needs to be moved.<br>
   * 
   * @param float x        this variable specifies the x coordinate of the center of the ball. <br>
   * @param float y        this variable specifies the y coordinate of the center of the ball. <br>
   */
  public void updatePos (float x, float y)
  {
   this.x = x;
   this.y = y;
  }

  /**
   * This method sends back the center of this object as a Point2D object.
   * 
   * @return A Point2D object of the center coordinates of the button obstacle
   */
  public Point2D getCenter()
  {
    return new Point2D.Double(this.x,this.y);
  }

/**
   * This method sends back the x coordinate of the center of this object.
   * 
   * @return x coordinate of the center
   */
  public float getX()
  {
    return this.x;
  }

  /**
   * This method sends back the y coordinate of the center of this object.
   * 
   * @return y coordinate of the center
   */
  public float getY()
  {
    return this.y;
  }

  /**
   * This method sets the x coordinate of the center of this object. <br>
   * 
   * @param int newX This variable specifies the x-ccordinate fo the new point.
   */
  public void setX (int newX)
  {
    this.x = newX;
  }

  /**
   * This method sets the y coordinate of the center of this object. <br>
   * 
   * @param int newY This variable specifies the y-ccordinate fo the new point.
   */
  public void setY(int newY)
  {
    this.y = newY;
  }
  
  /**
   * This method returns the colour of this object.
   * 
   * @return the colour of the button obstacle.
   */
  public Color getColour ()
  {
    return this.colour;
  }
  
  /**
   * This method sets the colour of this object. <br>
   * 
   * @param Color newColour This variable specifies the new colour for this object.
   */
  public void setColour (Color newColour)
  {
    this.colour = newColour;
  }
  
  /**
   * This method returns the radius of this object.
   * 
   * @return the radius of the button obstacle.
   */
  public float getRadius ()
  {
    return radius;
  }
  
   /**
   * This method sets the radius of this object. <br>
   * 
   * @param float newRadius This variable specifies the new radius for this object.
   */
  public void setRadius(float newRadius)
  {
    this.radius = newRadius;
  }
  
  /**
   * This method returns the bounce value of the button.
   * 
   * @return the bounce variable of the button
   */
    public float getBounce ()
  {
    return bounce;
  }
    
     /**
   * This method returns the score value of the button.
   * 
   * @return the score variable of the button
   */
  public int getScore ()
  {
   return 20; 
  }
  
}