import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Files.Current.physicsEngine.*;
import Files.Current.physicsEngine.Paddle;

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
    
    static JPanel mainPanel;
    //Add a picture panel
    static GameMenu menuBar;
  public Window ()
  {
    super ("Maliable P'ngball");
    menuBar = new GameMenu (this);
    setJMenuBar (menuBar);
    //GamePanel gameP = new GamePanel ();
    infoP  = new InformationPanel ();
    //this.add (gameP);
    
    //Game panel
    JPanel gamePanel = new JPanel ();
    gamePanel.setSize (800,600);
    e = new GameEngine();
    gq = new QuestionManager ();
    e.setVisible (true);
    gamePanel.add (e); //getContentPane()    
    gamePanel.add (infoP);
    GridLayout layout = new GridLayout (1,2);
    //layout.addLayoutComponent ("gameP",gameP);
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
       
    //The main Panel
    mainPanel = new JPanel (new CardLayout()); 
    mainPanel.add ("Menu", menuPanel);
    mainPanel.add ("Game", gamePanel);
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
    //setAutoRequestFocus (true);
    repaint();
  }
    
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals("Exit"))
    {
      HighScoreManager.writeFile ();
      System.exit (0);
    }
    else if (ae.getActionCommand().equals ("Display"))
    {
      GameEngine.paused = true;
      InfoFrame frame = new InfoFrame ("High Scores");
    }
    else if (ae.getActionCommand().equals ("Instructions"))
    {
      GameEngine.paused = true;
      InfoFrame frame = new InfoFrame ("Instructions");
    }
    else if (ae.getActionCommand().equals ("Pause"))
    {
      GameEngine.paused = !GameEngine.paused;
    }
    else if (ae.getActionCommand().equals ("Formulas"))
    {
      GameEngine.paused = true;
      InfoFrame frame = new InfoFrame ("Formulas");
    }
    else if (ae.getActionCommand().equals ("Easy"))
    {
      //change question set.
      //Alert the user of the change.
    }
    else if (ae.getActionCommand().equals ("Medium"))
    {
      
    }
    else if (ae.getActionCommand().equals ("Hard"))
    {
      
    }
    else if (ae.getActionCommand().equals ("Print"))
    {
      //print function
    }
    else if (ae.getActionCommand().equals ("Start"))
    {
      StasisPanel.removeStasis ();
      CardLayout cl = (CardLayout)(mainPanel.getLayout());
      cl.show (mainPanel, "Game");
      //get out of stasis
     //remove old panels
      //put new panels
      
    }
    repaint ();
  }
  
  public  void runProgram()
  {
    //Window w = new Window ();   // Create a FrameTest frame
    while (true){
      StasisPanel.stasis ();
      menuBar.setVisible (false);
      //the game paused screen right now.
      //method in a consistent while loop until a button is pressed
      //The loop that runs a full 
      for (int x = 0; x < 3; x++)
      {
        e.setGameLayout (x);
    e.gamerun();
    if (e.terminated){
      break;}
    //add Ability to terminate
    //update score
    try {
      Thread.sleep (300);
      
    }
    catch (InterruptedException e){}
    
    showQuestion ();
    e.pause();
    }
   e.highscoreManager.checkScores (e.getScore());
     e.resetGame ();
    StasisPanel.addStasis ();
    menuBar.setVisible (true);
      CardLayout cl = (CardLayout)(mainPanel.getLayout());
      cl.show (mainPanel, "Menu");
    }
  } // main method
  
  public void showQuestion ()
  {
    InfoFrame frame = new InfoFrame ("QuestionPanel");
  }
  
  public void terminate ()
  {
  }
  
  //organize this code
  private class TAdapter extends KeyAdapter 
  {   
    public void keyReleased(KeyEvent e) 
    {
      if (!Window.e.living.isEmpty())
      Window.e.living.get (0).keyReleased(e);
      for (Paddle p : Window.e.paddles)
        p.keyReleased (e);
       
    }
    
    public void keyPressed(KeyEvent e)
    {
      if (!Window.e.living.isEmpty())
      {
      for (Spawn x : Window.e.living)
      x.keyPressed(e);
      Window.e.keyPressed (e);
      }
      for (Paddle p :Window.e.paddles)
        p.keyPressed (e);
      for (PseudoPaddle p : Window.e.pseudoPaddles)
        p.keyPressed (e);
      //if space is pressed and the game hasn't started yet
    }
  }
}




