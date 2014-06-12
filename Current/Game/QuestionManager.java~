import java.awt.*;
import java.util.*;
import java.io.*;

public class QuestionManager
{
 /* ArrayList <String> questions This is being used to store the questions.<br>
   * BufferedReader in This is a reference to the BufferedReader class.<br>
   */
  public static ArrayList <Question> questions = new ArrayList<Question>();  
  static BufferedReader in;
  
  /* This is the constructor of the class which reads in the questions from a textfile and places them into an <br>
   * ArrayList.
   */
  public QuestionManager ()
  {
    importFile ("QuestionDocument.txt");
  }
  
  /* This method will return one randomized question depending on difficulty.
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
    
    //Question format
    //6 lines are question
    //4 lines are answers
    //last line is the correct answer
//    questionNumber = (int) Math.random () * 12 * 6;
//    optionNumber = (int) Math.random () * 12 * 4;
//    answerNumber = (int) Math.random () * 12 * 1;
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
  
   /* This method imports the text file into the ArrayList.
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
//        Question q = new Question ();
//        for (int y = 0; y < 6; y++)
//        {
//         q.setQuestion (y, in.readLine()); 
//        }
//        for (int z = 0; z < 4; z++)
//        {
//         q.setOption (z, in.readLine()); 
//        }
//        q.setAnswer (in.readLine ());
        String [] ques = new String [6];
        String [] opt = new String [4];
        for (int x = 0; x < 6;x++)
          ques [x] = in.readLine ();
        for (int x = 0; x < 4;x++)
          opt [x] = in.readLine ();
        String answer = in.readLine ();
        questions.add (new Question (ques,opt,answer));
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