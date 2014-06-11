package Files.Current.physicsEngine;
import java.awt.Color;
import java.awt.geom.Point2D;
public class ButtonObstacle implements Obstacle
{
  private float x,y;
  private float radius;
  private Color colour;
    private float bounce;
  
  public ButtonObstacle ()
  {
    this.x = 100;
    this.y = 100;
    this.radius = 15;
    colour = Color.BLACK;
    this.bounce = 1.2f;
  }
  public ButtonObstacle (float x, float y, float radius,float bounce,Color colour)
  {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.bounce = bounce;
    this.colour = colour;
  }
  
  public void updatePos (float x, float y)
  {
   this.x = x;
   this.y = y;
  }

  public Point2D getCenter()
  {
    return new Point2D.Double(this.x,this.y);
  }

  public float getX()
  {
    return this.x;
  }

  public float getY()
  {
    return this.y;
  }

  public void setX (int newX)
  {
    this.x = newX;
  }

  public void setY(int newY)
  {
    this.y = newY;
  }
  
  public Color getColour ()
  {
    return this.colour;
  }
  
  public void setColour (Color newColour)
  {
    this.colour = newColour;
  }
  
  public float getRadius ()
  {
    return radius;
  }
  
  public float getBounce ()
  {
    return bounce;
  }
  public int getScore ()
  {
   return 20; 
  }
  
  public void setRadius(float newRadius)
  {
    this.radius = newRadius;
  }
}