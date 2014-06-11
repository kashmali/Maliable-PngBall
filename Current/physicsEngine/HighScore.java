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
 
 public HighScore (String name)
 {
   this.name = name;
   this.score = 0;
 }
 
 public HighScore (int score)
 {
   this.name = "P'ngball Grandmaster";
   this.score = score;
 }
 
 public String getName ()
 {
   return name;
 }
 
 public int getScore()
 {
  return score; 
 }
 
 public String getLevel ()
 {
   return level;
 }
 
 public void setName (String newName)
 {
  this.name = newName; 
 }
  
 public void setScore (int newScore)
 {
   this.score = newScore;
 }
 
 public void setLevel (String newLevel)
 {
  this.level = newLevel; 
 }
}