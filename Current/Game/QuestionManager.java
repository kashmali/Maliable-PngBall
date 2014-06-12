import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * This manages the questions for the game. It imports the questions and selects them to display in game.
 * @author Jason P'ng
 * @author Aakash Mali
 * @version v4.0 June 12th, 2014
 */
public class QuestionManager
{
  /**
   * This is being used to store the questions
   */
  public static ArrayList <Question> questions = new ArrayList<Question>();
  /**
   * This is a reference to the BufferedReader class
   */
  static BufferedReader in;
  
  /**
   * This is the constructor of the class which reads in the questions from a textfile and places them into an <br>
   * ArrayList.
   */
  public QuestionManager ()
  {
    importFile ("QuestionDocument.txt");
  }
  
  /** This method will return one randomized question depending on difficulty.
   * 
   * @param String questionSet This specifies the difficulty of the game.<br>
   * @param ArrayList <String> questionPortion This stores all the data nessasary for the problem. <br>
   * @param int questionNumber This stores the question number. <br>
   * @param int optionNumber This stores the option number. <br>
   * @param int answerNumber This stores the answer number. <br>
   * @return ArrayList <String> questionPortion This returns the data necessary for the problem.<br>
   */
  public static Question getQuestion (String questionSet)
  {
    int questionNumber = (int)(Math.random () * 12);
    if (questionSet.equals("Easy"))
    {
      questionNumber = questionNumber;
    }
    else if (questionSet.equals("Medium"))
    {
      questionNumber = questionNumber + 12;
    }
    else //(questionSet.equals("Hard"))
    {
      questionNumber = questionNumber + 24;
    }
    return questions.get (questionNumber);
  }
  
   /** This method imports the text file into the ArrayList.
   * 
   * @param String fileName This is the name of the file being imported. <br>

   * @param String correctAnswer This is the correct answer. <br>
   * @throws IOException ioe This catches any errors that might occur in importing the file.<br>
   * @throws NullPointerException ne This stops the reading of the file.<br>
   * @throws IOException ine This catches any errors that might occur in closing the file.<br>
   */
  public static void importFile (String fileName)
  {  
    try
    {   
      in = new BufferedReader (new FileReader (fileName));
      String lineOne = " ";
      for (int t = 0; t < 36; t ++)
      {
        Question q = new Question ();
        for (int y = 0; y < 6; y++)
        {
         q.setQuestion (y, in.readLine()); 
        }
        for (int z = 0; z < 4; z++)
        {
         q.setOption (z, in.readLine()); 
        }
        q.setAnswer (in.readLine ());
        questions.add (q);
      }
      }
      
    catch (IOException ioe)
    {
      importFile (fileName);
    }
    catch (NullPointerException ne)
    {
      try
      {
        in.close ();
      }
      catch (IOException ine)
      {
      }
    }  
  }
}