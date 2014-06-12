package gameFiles;

/**
 * Data class to contain a highscore. It has the name, level, and score.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class HighScore
{
  /**
   * The name of the player.
   */
 private String name;
 /**
   * The score of the highscore. This determines its placement on the highscore table.
   */
 private int score;
 /**
   * The level that highscore was achieved on.
   */
 private String level;
 
 /**
  * Constructor that automatically sets all variables
  */
 public HighScore ()
 {
   this.name = "P'ngball Grandmaster";
   this.score = 0;
   this.level = "level 1";
 }
 
 /**
  * Constructor that sets all variables in the parameters.
  * 
  * @param String name the name of the player.
  * @param String level the level the highscore was scored on.
  * @param int score the score of the highscore.
  */
 public HighScore (String name,String level, int score)
 {
   this.name = name;
   this.score = score;
   this.level = level;
 }
 

 /** This is a constructor that sets the name and score of a current player.
  * 
  * @param String name The name of the player. <br>
  */
 public HighScore (String name)
 {
   this.name = name;
   this.score = 0;
   this.level = "level 1";
 }
 
 /** This is a constructor that sets the name and score of a current player.
  * 
  * @param int score The score of the player. <br>
  */
 public HighScore (int score)
 {
   this.name = "P'ngball Grandmaster";
   this.score = score;
   this.level = "level 1";
 }
 
 /**
  * This method gets the name of the player.
  * 
  * @return the name of the player.
  */
 public String getName ()
 {
   return name;
 }
 
 /** This methods gets the score of the player.
   * 
   * @return the score
  */
 public int getScore()
 {
  return score; 
 } 
 
 /** This method sets the name of the player.
  * 
  * @param String newName This sets the new name of the player.
  */
 public void setName (String newName)
 {
  this.name = newName; 
 }
  
 
 /** This method sets the score of the player.
  * 
  * @param int newScore This sets the new score of the player.
  */
 public void setScore (int newScore)
 {
   this.score = newScore;
 }
 
 /**
   * Gets the level the highscore was achieved on.
   * 
   * @return the level of the highscore.
   */
 public String getLevel ()
 {
   return level;
 }
 
 /**
   * Sets the level of this highscore.
   * 
   * @param String newLevel the level to set this highscore to.
   */
 public void setLevel (String newLevel)
 {
  this.level = newLevel; 
 }
}