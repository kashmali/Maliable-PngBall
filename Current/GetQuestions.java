import java.awt.*;
import java.util.*;
import java.io.*;

public class GetQuestions
{
  public static ArrayList <String> questions = new ArrayList<String> ();
  public static ArrayList <String> options = new ArrayList<String> ();
  public static ArrayList <String> correctAnswers = new ArrayList<String> ();
  
  static BufferedReader in;
  public GetQuestions ()
  {
    importFile ("QuestionDocument.txt");
  }
  public static ArrayList <String> getQuestion (String questionSet)
  {
    ArrayList <String> questionPortion = new ArrayList<String> (); //stores all the data nessasary for the problem
    int questionNumber =0, optionNumber = 0, answerNumber =0;
    int amount =0;
    
    
    questionNumber = (int) Math.random () * 12 * 6;
    optionNumber = (int) Math.random () * 12 * 4;
    answerNumber = (int) Math.random () * 12 * 1;
    if (questionSet.equals("Easy"))
    {
      questionNumber = questionNumber;
      optionNumber = optionNumber;
      answerNumber = answerNumber;
    }
    else if (questionSet.equals("Medium"))
    {
      questionNumber = questionNumber + 72;
      optionNumber = optionNumber + 48;
      answerNumber = answerNumber + 12;
    }
    else //(questionSet.equals("Hard"))
    {
      questionNumber = questionNumber + 144;
      optionNumber = optionNumber + 96;
      answerNumber = answerNumber + 24;
    }
    
    for (int x = 0; x < 6 ; x++)
    {
      questionPortion.add(questions.get(questionNumber + x));
    } 
    for (int x = 0; x < 4 ; x++)
    {
      questionPortion.add(options.get(optionNumber + x));
    }
    for (int x = 0; x < 1 ; x++)
    {
      questionPortion.add(correctAnswers.get(answerNumber + x));
    }
    return questionPortion;
  }
  
  
  
  
  public static void importFile (String fileName)
  {  
    try
    {   
      in = new BufferedReader (new FileReader (fileName));
      String lineOne = " ";
      int counter = 0;
      while (counter < 36)
      {
        lineOne = in.readLine();
        String lineTwo = in.readLine();
        String lineThree = in.readLine();
        String lineFour = in.readLine();
        String lineFive = in.readLine();
        String lineSix = in.readLine();
        String answerOne = in.readLine();
        String answerTwo = in.readLine();
        String answerThree = in.readLine();
        String answerFour = in.readLine();
        String correctAnswer = in.readLine();
        
        questions.add (lineOne);
        questions.add (lineTwo);
        questions.add (lineThree);
        questions.add (lineFour);
        questions.add (lineFive);
        questions.add (lineSix);
        
        options.add (answerOne);
        options.add (answerTwo);
        options.add (answerThree);
        options.add (answerFour);
        
        correctAnswers.add (correctAnswer);
        counter ++;
//        System.out.print ("Question Number" + counter);
//        System.out.println ("answer : " + correctAnswer);
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