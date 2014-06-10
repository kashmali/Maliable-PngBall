package Files.Current.physicsEngine;
import java.awt.Color;

public class BumperObstacleLine extends ObstacleLine
{
  private float bounce;
  private int scoreValue;
  public BumperObstacleLine (double x1, double y1, double x2, double y2)
  {
    super (x1,y1,x2,y2,Color.RED);
    scoreValue = 10;
  }
  
  public float getBounce ()
  {
    return bounce;
  }
  
  public int getScoreValue()
  {
    return scoreValue;
  }
}