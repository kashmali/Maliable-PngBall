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
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class HighScoreManager
{
  public static ArrayList<HighScore> scores = new ArrayList<HighScore>(10);
  //static final File HIGHSCORES = new File ("highscores.jp");
  static final String HIGHSCORES = "highscores.txt";
  
  public HighScoreManager () 
  {
    importFile (HIGHSCORES);
    //sort array just in case
  }
  
  public void importFile (String fileName)
  {    
    try
    {   
      BufferedReader in = new BufferedReader (new FileReader (fileName));
      for (int x = 0; x < 10; x++)
      {
        String name = in.readLine();
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
        scores.add (new HighScore (name,score));
      }
      in.close ();
    }
    catch (IOException e)
    {
      System.out.println ("Error Loading High Scores");
      writeBlankFile();
      importFile (fileName);
    }
  }
  
  public static void writeFile ()
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (HIGHSCORES));
      for (int x = 0; x < 10; x++)
      {
        HighScore score = scores.get (x);
        out.println (score.getName());
        out.println (score.getScore());
      }
      out.close();
    }
    catch (IOException e)
    {
      System.out.println ("Error writing highscore file"); 
    }
  }
  
  public static void writeBlankFile ()
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (HIGHSCORES));
      for (int x = 0; x < 10; x++)
      {
        out.println ("P'ngball Grandmaster");
        out.println (0);
      }
      out.close();
    }
    catch (IOException e)
    {
      System.out.println ("Error writing highscore file"); 
    }
    
  }
  
  public static ArrayList<HighScore> getScores ()
  {
    return scores;
  }
  
  public boolean checkScores (int score)
  {
    for (int x = 0; x < scores.size();x++)
    {
      if (scores.get (x).getScore() < score)
      {
        insertScore (x,score);
        return true;
      }
    }
    return false;
    
  }
  
  public void insertScore (final int location, final int score)
  {
    class NamePromptPanel extends JPanel implements ActionListener
    {
      JTextField nameField = new JTextField (10);
      JFrame parent;
      public NamePromptPanel (int score, JFrame parent)
      {
        this.parent = parent;
        JButton submitButton = new JButton ("Submit");
        submitButton.addActionListener (this);
        add (new JLabel ("Score: " + score));
        add (new JLabel ("Enter your name: "));
        add (nameField);
        add (submitButton);
        setSize (300,200);
        setFocusable (true);
        setVisible (true);
      }
      
      public void actionPerformed (ActionEvent ae)
      {
        System.out.println ("Pressed");
        if (ae.getActionCommand().equals ("Submit"))
        {
          String name = nameField.getText();
          if (name.length() > 0)
          {
            scores.add (location,new HighScore (name,score));
            scores.remove (10);
            parent.dispose ();
          }
          else
          {
            JOptionPane.showMessageDialog (this, "You must input a name", "Name Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    }
    class NamePromptFrame extends JFrame
    {
      public NamePromptFrame (int score)
      {
        super ("New Highscore");
        add (new NamePromptPanel (score, this));
        setSize (300,200);
        setVisible (true);
        setAutoRequestFocus (true);
        setResizable (false);
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
      }
      
    }
    JFrame temp = new JFrame ("New HighScore");
    temp.add (new NamePromptPanel (score, temp));
        temp.setSize (300,200);
        temp.setVisible (true);
        temp.setAutoRequestFocus (true);
        temp.setResizable (false);
        temp.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                                
  }
  
    
    
}