import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import gameFiles.*;

/**
 * This is the main class to run the program. It contains the main window and methods to run the game
 * @author Jason P'ng
 * @author Aakash Mali
 * @version v4.0 June 12th, 2014
 */
public class Window extends JFrame implements ActionListener
{
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
 /**
  * The highscore panel to display the highscores.
  */
  public HighScorePanel highscores;
  /**
  * Panel to display questions.
  */
  public QuestionPanel questions;
  /**
  * The main panel to hold all other panels.
  */
  static JPanel mainPanel;
  /**
  * The menubar that contains all the menu options.
  */
  static GameMenu menuBar;
  
  /**
  * Panel used when the user has a new high score.
  */
  public HighScorePromptPanel prompt;
  
  /**
  * Determines if the user got a question right or wrong.
  */
  public static boolean answer = false;
  
  /**
  * Constructor for the window.
  * 
  * @param JPanel gamePanel the panel that contains the information panel and gameEngine used to play the game.
  * @param JPanel menuPanel the panel used to contain panels for the main menu.
  * @param GridLayout layout the layout used for the game panel and menu panel.
  * @param JPanel StasisPanel the panel for delaying the user and prompting to start the game.
  * @param JPanel LogoPanel the panel the contains the Down to Earth Games logo for the main menu.
  * @param JPanel InstructionsPanel the panel that displays the game instructions.
  * @param JPanel FormulaPanel the panel that displays physics formulas.
  * @param JPanel CorrectPanel the panel displayed for correct answers.
  * @param JPanel WrongPanel the panel displayed for wrong answers.
  */
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
    setAutoRequestFocus (true);
    repaint();
  }
  
  /**
   * Implemented method of the ActionListener interface.
   * Adds function to the menuitems and buttons across several panels. <br>
   * if statement distinguishes between different action commands <br>
   * if (answer == true) - Check if the use answered correctly. <br>
   * if (option == JOptionPane.YES_OPTION) - Checks if the user confirms to clearing the highscores <br>
   * 
   * @param ActionEvent ae the action that triggers this method to be called.
   * @param String command the actionCommand of the action event.
   * 
   * @throws IOException if the printing method is unsuccessful.
   */
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
        e.setTerminated (true);
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
  
  /**
   * Prints out the highscores. <br>
   * for loop traverse through highscores to queue them and print them
   * 
   * @param Printer p the object that accesses the printer and prints.
   */
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
  
  /**
   * helper method to switch between panels in card layout
   * 
   * @param String panel the panel to be shown
   */
  public void show (String panel)
  {
    CardLayout cl = (CardLayout)(mainPanel.getLayout());
    cl.show (mainPanel, panel);
  }
  
  /**
   * Method to prompt the user to enter a name when they achieve a new high score.
   * 
   * @param int location The ranking of the user's score.
   */
  public void namePrompt (int location)
  {
    prompt = new HighScorePromptPanel (location,e.getScore(),this);
    mainPanel.add ("Prompt", prompt);
      show ("Prompt");
  }
  
  /**
   * The main method for running the program <br>
   * while (true) - the loop for running the program until exited. <br>
   * for (int x = 0; x < 3; x++) - loop for three levels of the game. <br>
   * if (e.isTerminated ()) - Checks if there is a call to exit the game. <br>
   * if (loc > -1) - Checks if the user has a new highscore. <br>
   * 
   * @throws InterruptedException if Thread.sleep() interrupts another thread.
   */
  public  void runProgram()
  {
    while (true){
      StasisPanel.stasis ();
      menuBar.setVisible (false);
      for (int x = 0; x < 3; x++)
      {
        //for quick exits
        if (e.isTerminated ())
        {
          e.isRunning = false;
          break;
        }
        //set the level
        e.setGameLayout (x);
        //start the game
        e.gamerun();
        //short delay before asking a question
        try 
        {
          Thread.sleep (300);    
        }
        catch (InterruptedException e){}
        
        //Ask a question and pause the loop
        showQuestion ();
        StasisPanel.addStasis ();
        StasisPanel.stasis ();
        e.setStarted (false);
      }
      e.setTerminated (false);
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
  
  /**
   * Method to display a new question.
   */
  public void showQuestion ()
  {
    mainPanel.remove (questions);
      questions = new QuestionPanel (this);
      mainPanel.add ("Question", questions);
      show ("Question");
  }
  
  /**
   * Mutator method to set the state of the answer variable
   * 
   * @param boolean newAnswer the value that answer is set to.
   */
  public static void setAnswer (boolean newAnswer)
  {
    answer = newAnswer;
  }
  
  /**
   * Key adapter for key listening in the program.
   */
  private class TAdapter extends KeyAdapter 
  {   
    /**
     * implementation to listne of pressed keys and calling the gameEngine method.
     * 
     * @param KeyEvent K the Keyevent that triggers the method.
     */
    public void keyPressed(KeyEvent k)
    {
    e.keyPressed (k);
    }
  }
}