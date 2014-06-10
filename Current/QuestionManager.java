import java.awt.*;
import java.util.*;
import java.io.*;

public class QuestionManager
{
  //public static ArrayList <String> questions = new ArrayList<String> ();
  public static ArrayList <String> options = new ArrayList<String> ();
  public static ArrayList <String> correctAnswers = new ArrayList<String> ();
  public static ArrayList <Question> questions = new ArrayList<Question>();
  
  static BufferedReader in;
  public QuestionManager ()
  {
    importFile ("QuestionDocument.txt");
  }
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
System.out.println (questionNumber);
    return questions.get (questionNumber);
  }
  
  
  
  
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
      
//        lineOne = in.readLine();
//        String lineTwo = in.readLine();
//        String lineThree = in.readLine();
//        String lineFour = in.readLine();
//        String lineFive = in.readLine();
//        String lineSix = in.readLine();
//        String answerOne = in.readLine();
//        String answerTwo = in.readLine();
//        String answerThree = in.readLine();
//        String answerFour = in.readLine();
//        String correctAnswer = in.readLine();
//        
//        questions.add (lineOne);
//        questions.add (lineTwo);
//        questions.add (lineThree);
//        questions.add (lineFour);
//        questions.add (lineFive);
//        questions.add (lineSix);
//        
//        options.add (answerOne);
//        options.add (answerTwo);
//        options.add (answerThree);
//        options.add (answerFour);
//        
//        correctAnswers.add (correctAnswer);
//        counter ++;
//        System.out.print ("Question Number" + counter);
//        System.out.println ("answer : " + correctAnswer);
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