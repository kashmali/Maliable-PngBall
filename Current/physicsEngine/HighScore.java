package Files.Current.physicsEngine;

public class HighScore
{
 private String name;
 private int score;
 private String level;
 
 public HighScore ()
 {
   this.name = "P'ngball Grandmaster";
   this.score = 0;
 }
 
 public HighScore (String name,String level, int score)
 {
   this.name = name;
   this.score = score;
   this.level = level;
 }
 

 /* This is a constructor that sets the name and score of a current player.
  * 
  * @param String name The name of the player. <br>
  */
 public HighScore (String name)
 {
   this.name = name;
   this.score = 0;
   this.level = "level 1";
 }
 
 /* This is a constructor that sets the name and score of a current player.
  * 
  * @param int score The score of the player. <br>
  */
 public HighScore (int score)
 {
   this.name = "P'ngball Grandmaster";
   this.score = score;
   this.level = "level 1";
 }
 
 /* This method gets the name of the player.
  */
 public String getName ()
 {
   return name;
 }
 
 /* This methods gets the score of the player.
  */
 public int getScore()
 {
  return score; 
 } 
 
 /* This method sets the name of the player.
  * 
  * @param String newName This sets the new name of the player.
  */
 public void setName (String newName)
 {
  this.name = newName; 
 }
  
 
 /* This method sets the score of the player.
  * 
  * @param int newScore This sets the new score of the player.
  */
 public void setScore (int newScore)
 {
   this.score = newScore;
 }
 
 public String getLevel ()
 {
   return level;
 }
 
 public void setLevel (String newLevel)
 {
  this.level = newLevel; 
 }
}