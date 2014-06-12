import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import Files.Current.physicsEngine.*;

//Every once in a while this program just errors with a null pointer exception
/**
 * This is the main class to run the program. It contains the main window and methods to run the game
 */
public class Window extends JFrame implements ActionListener
{
  //Change layout
  //Add Window Listener
  
  /**
   * The current game engine that will manage and run the gameplay.
   */
  static GameEngine e;
  /**
   * Panel that displays information about the current gameplay.
   */
  static InformationPanel infoP;
  /**
   * The Question manager to read in and access the questions from the question file.
   */
  static QuestionManager gq;
  HighScorePanel highscores;
  QuestionPanel questions;
  static JPanel mainPanel;
  //Add a picture panel
  static GameMenu menuBar;
  HighScorePromptPanel prompt;
  
  public static boolean answer = false;
  
  public Window ()
  {
    super ("Maliable P'ngball");    
    menuBar = new GameMenu (this);
    setJMenuBar (menuBar);

    infoP  = new InformationPanel ();
    
    //Game panel
    JPanel gamePanel = new JPanel ();
    gamePanel.setSize (800,600);
    e = new GameEngine();
    gq = new QuestionManager ();
    e.setVisible (true);
    gamePanel.add (e);  
    gamePanel.add (infoP);
    GridLayout layout = new GridLayout (1,2);
    layout.addLayoutComponent ("gameP",e);
    layout.addLayoutComponent ("infoP",infoP);
    gamePanel.setLayout (layout);
    
    //Menu panel
    JPanel menuPanel = new JPanel ();
    menuPanel.setSize (800,600);
    StasisPanel stasis = new StasisPanel (this);
    LogoPanel logo = new LogoPanel ();
    GridLayout layout2 = new GridLayout (1,2);
    menuPanel.add (stasis);
    menuPanel.add (logo);
    //layout.addLayoutComponent ("gameP",gameP);
    layout.addLayoutComponent ("Stasis",stasis);
    layout.addLayoutComponent ("Logo",logo);
    menuPanel.setLayout (layout2);
    
    //Instruction
    InstructionsPanel instructions = new InstructionsPanel (this);
    
    //HighScores
    highscores = new HighScorePanel (this);
    
    //Formulas
    FormulaPanel formulas = new FormulaPanel (this);
    
    //Question
    questions = new QuestionPanel (this);
    
    CorrectPanel correct = new CorrectPanel (this);
    WrongPanel wrong = new WrongPanel (this);
    //The main Panel
    mainPanel = new JPanel (new CardLayout()); 
    mainPanel.add ("Menu", menuPanel);
    mainPanel.add ("Game", gamePanel);
    mainPanel.add ("Instructions",instructions);
    mainPanel.add ("High Scores",highscores);
    mainPanel.add ("Formulas", formulas);
    mainPanel.add ("Question",questions);
    mainPanel.add ("Correct", correct);
    mainPanel.add ("Wrong", wrong);
    mainPanel.setFocusable(true);
    mainPanel.addKeyListener(new TAdapter());
    
    add (mainPanel,BorderLayout.CENTER);
    
    validate (); 
    pack();
    setSize (800,600);
    setLocationRelativeTo(null);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter()
                        {
      public void windowClosing(WindowEvent event)
      {
        //May have to count windows if more are added
        HighScoreManager.writeFile ();
        System.exit(0);
      }
    });
    setResizable (false);
    ///setAutoRequestFocus (true);
    repaint();
  }
  
  public void actionPerformed (ActionEvent ae)
  {
    String command = ae.getActionCommand ();
    if (command.equals("Exit"))
    {
      HighScoreManager.writeFile ();
      System.exit (0);
    }
    else if (command.equals ("Display"))
    {
      mainPanel.remove (highscores);
      highscores = new HighScorePanel (this);
      mainPanel.add ("High Scores", highscores);
      show ("High Scores");
    }
    else if (command.equals ("Instructions"))
    {
      show ("Instructions");
    }
    else if (command.equals ("Formulas"))
    {
      show ("Formulas");
    }
    else if (command.equals ("Easy"))
    {
      //change question set.
      //Alert the user of the change.
      e.setGameDifficulty (GameEngine.EASY);
      JOptionPane.showMessageDialog (this,"Difficuly set to easy","Change in Difficulty",JOptionPane.INFORMATION_MESSAGE);
    }
    else if (command.equals ("Medium"))
    {
      e.setGameDifficulty (GameEngine.MEDIUM);
      JOptionPane.showMessageDialog (this,"Difficuly set to medium","Change in Difficulty",JOptionPane.INFORMATION_MESSAGE);
    }
    else if (command.equals ("Hard"))
    {
      e.setGameDifficulty (GameEngine.HARD);
      JOptionPane.showMessageDialog (this,"Difficuly set to HARD","Change in Difficulty",JOptionPane.INFORMATION_MESSAGE);
    }
    else if (command.equals ("Print"))
    {
      print();
    }
    else if (command.equals ("Clear"))
    {
      int option = JOptionPane.showConfirmDialog (this,"Are you sure you want to erase the highscores?","Clear highscores?",JOptionPane.YES_NO_OPTION);
      if (option == JOptionPane.YES_OPTION)
      {
     e.highscoreManager.clearScores ();
      }
    }
    else if (command.equals ("Start"))
    {
      StasisPanel.removeStasis ();
      show ("Game");      
    }
    else if (command.equals ("Close"))
    {
      show ("Menu");
    }
    else if (command.equals ("Check"))
    {
       if (answer == true)
       {
        show ("Correct"); 
       }
       else 
       {
         show ("Wrong");
       }
    }
    else if (command.equals ("Continue"))
    {
      StasisPanel.removeStasis();
      if (answer == true)
      {
      show ("Game");
      }
      else
      {
        e.terminated = true;
      }
    }
    else if (command.equals ("Help"))
    {
      try 
      {
        Runtime.getRuntime ().exec ("hh.exe Maliable_P'ngBall.chm");
      }
      catch (IOException e)
      {
      }
    }
    else if (command.equals ("Submit"))
    {
      mainPanel.remove (prompt);
      StasisPanel.removeStasis ();
    }
    repaint ();
  }
  
  public void print ()
  {
    System.out.println ("Printing");
    Printer p = new Printer ();
    p.println ("", "High Scores", "");
    p.println ();
    p.println ("Rank", "Score", "Level");
    for (int x = 0; x < HighScoreManager.scores.size (); x ++)
    {
      p.println ((x+1) + ". " + HighScoreManager.scores.get (x).getName (), Integer.toString ( HighScoreManager.scores.get (x).getScore ()) , HighScoreManager.scores.get (x).getLevel ());
    }
    p.printUsingDialog();
  }
  
  public void show (String panel)
  {
    CardLayout cl = (CardLayout)(mainPanel.getLayout());
    cl.show (mainPanel, panel);
  }
  
  public void namePrompt (int location)
  {
    prompt = new HighScorePromptPanel (location,e.getScore(),this);
    mainPanel.add ("Prompt", prompt);
      show ("Prompt");
  }
  public  void runProgram()
  {
    //Window w = new Window ();   // Create a FrameTest frame
    while (true){
      StasisPanel.stasis ();
      menuBar.setVisible (false);
      for (int x = 0; x < 3; x++)
      {
        if (e.terminated)
        {
          e.isRunning = false;
          break;
        }
        e.setGameLayout (x);
        e.gamerun();       
        try 
        {
          Thread.sleep (300);    
        }
        catch (InterruptedException e){}
        
        showQuestion ();
        StasisPanel.addStasis ();
        StasisPanel.stasis ();
        e.started = false;
      }
      e.terminated = false;
      int loc = e.highscoreManager.checkScores (e.getScore());
      if (loc > -1)
      {
        namePrompt (loc);
        StasisPanel.addStasis ();
        StasisPanel.stasis ();
      }
      e.resetGame ();
      StasisPanel.addStasis ();
      menuBar.setVisible (true);
      show ("Menu");
    }
  } // main method
  
  public void showQuestion ()
  {
    mainPanel.remove (questions);
      questions = new QuestionPanel (this);
      mainPanel.add ("Question", questions);
      show ("Question");
  }
  
  public static void setAnswer (boolean newAnswer)
  {
    answer = newAnswer;
  }
  
  public void terminate ()
  {
  }
  
  //organize this code
  private class TAdapter extends KeyAdapter 
  {   
    public void keyReleased(KeyEvent k) 
    {
      e.keyReleased (k);
    }
    
    public void keyPressed(KeyEvent k)
    {
    e.keyPressed (k);
    }
  }
}