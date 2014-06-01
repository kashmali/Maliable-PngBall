public class HighScore
{
 private String name;
 private int score;
 
 public HighScore ()
 {
   this.name = "P'ngball Grandmaster";
   this.score = 0;
 }
 
 public HighScore (String name, int score)
 {
   this.name = name;
   this.score = score;
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
 
 public void setName (String newName)
 {
  this.name = newName; 
 }
  
 public void setScore (int newScore)
 {
   this.score = newScore;
 }
}