package Files.Current.physicsEngine;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Font;
import java.util.Comparator;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class HighScoreManager
{
  
    /* ArrayList<HighScore> scores This keeps all the high scores.
   * String HIGHSCORES This stores the location of the high score file.
   */
  public static ArrayList<HighScore> scores = new ArrayList<HighScore>(10);
  static final String HIGHSCORES = "highscores.txt";
  
  /* This is the main constructor of the class responsible for importing the high scores.
   */
  public HighScoreManager () 
  {
    importFile (HIGHSCORES);
  }
  
  /* This method imports the file.
   * 
   * @param String fileName This is the name of the file. <br>
   * @param BufferedReader in This is a reference to the BufferedReader class. <br>
   * @param String name This is the name of the high score person. <br>
   * @param int score This is the score of the high score person. <br>
   * @throws NumberFormatException e Catches any mistakes in the file. <br>
   * @throws IOException e Prevents the program from crashing due to a corrupted file. <br>
   */
  public void importFile (String fileName)
  {    
    try
    {   
      BufferedReader in = new BufferedReader (new FileReader (fileName));
      for (int x = 0; x < 10; x++)
      {
        String name = in.readLine();
        String level = in.readLine ();
        int score;
        try
        {
          score = Integer.parseInt (in.readLine());
        }
        catch (NumberFormatException e)
        {
          System.out.println ("Error with highscore file. Score is not an integer");
          score = 0;
        }
        scores.add (new HighScore (name,level,score));
      }
      in.close ();
    }
    catch (IOException e)
    {
      System.out.println ("Error Loading High Scores");
      writeBlankFile();
      importFile (fileName);
    }
    Collections.sort (scores, new Comparator<HighScore>() {
    public int compare (HighScore h1, HighScore h2){
      return new Integer (h2.getScore()).compareTo (h1.getScore());
    }
    }
      );
  }
  
   /* This method writes back to the file to update the scores.
   * 
   * @param PrintWriter out This is a reference to the PrintWriter class. <br>
   * @param HighScore score This is the score of a player. <br>
   * @throws IOException e This catches an error in reading the highscore file. <br>
   */
  public static void writeFile ()
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (HIGHSCORES));
      for (int x = 0; x < 10; x++)
      {
        HighScore score = scores.get (x);
        out.println (score.getName());
        out.println (score.getLevel());
        out.println (score.getScore());
      }
      out.close();
    }
    catch (IOException e)
    {
      System.out.println ("Error writing highscore file"); 
    }
  }
  
  /* This method writes a blank file if one does not exist.
   * 
   * @param PrintWriter out This is a reference to the PrintWriter class. <br>
   * @throws IOException e This catches an error in reading the highscore file. <br>
   */
  public static void writeBlankFile ()
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (HIGHSCORES));
      for (int x = 0; x < 10; x++)
      {
        out.println ("P'ngball Grandmaster");
        out.println ("Level 1");
        out.println (0);
      }
      out.close();
    }
    catch (IOException e)
    {
      System.out.println ("Error writing highscore file"); 
    }
    
  }
  
  /**
   * returns the current scores.
   * 
   * @return the array list of highscores.
   */
  public static ArrayList<HighScore> getScores ()
  {
    return scores;
  }
  
  public void clearScores ()
  {
    scores.clear ();
   writeBlankFile();
   importFile (HIGHSCORES);
  }
  
  /*This method checks to see if a score makes a high score list.
   * 
   * @param int score This sends a score to check.
   */
  public int checkScores (int score)
  {
    for (int x = 0; x < scores.size();x++)
    {
      if (scores.get (x).getScore() < score)
      {
        return x;
      }
    }
    return -1;
    
  }   
}