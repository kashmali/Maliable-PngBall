import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class Window extends JFrame implements ActionListener
{
  
  
  public Window ()
  {
    super ("Maliable P'ngball");
<<<<<<< HEAD
        
    JMenu options = new JMenu ("Options");
    JMenu game = new JMenu ("Game");
    JMenu difficulty = new JMenu ("Difficulty");
    JMenu highScore = new JMenu ("High Scores");
    JMenu help = new JMenu ("Help");
    
    JMenuBar gameMenus = new JMenuBar ();

    addMenuItem (difficulty, "Easy");
    addMenuItem (difficulty, "Medium");
    addMenuItem (difficulty, "Hard");
    addMenuItem (highScore, "Display");
    addMenuItem (highScore, "Print");

    game.add (highScore);
    game.addSeparator();
    addMenuItem (game, "Pause");
    addMenuItem (game, "Exit");
    
    options.add(difficulty);
    addMenuItem (help, "Instructions");
    addMenuItem (help, "Formulas");
    
    gameMenus.add (game);
    gameMenus.add (options);
    gameMenus.add (help);

    setJMenuBar (gameMenus);
    
    setSize (700,800);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    setResizable (false);
=======
    
    JMenuItem exitButton = new JMenuItem ("Exit");
    JMenuItem hardGameButton = new JMenuItem ("Hard");
    JMenuItem mediumGameButton = new JMenuItem ("Medium");
    JMenuItem easyGameButton = new JMenuItem ("Easy");
    JMenuItem instructionsButton = new JMenuItem ("Instructions");
    JMenuItem formulaButton = new JMenuItem ("Formulas");
    JMenuItem pauseButton = new JMenuItem ("Pause");
    JMenuItem highScoreButton = new JMenuItem ("Display High Scores");
    JMenuItem printHighScoreButton = new JMenuItem ("Print High Scores");
    
    JMenu options = new JMenu ("Options");
    JMenu game = new JMenu ("Game");
    JMenu difficulty = new JMenu ("Difficulty");
    JMenu help = new JMenu ("Help");
    
    JMenuBar gameMenus = new JMenuBar ();
    
    options.add (exitButton);
    difficulty.add (easyGameButton);
    difficulty.add (mediumGameButton);
    difficulty.add (hardGameButton);
    game.add (highScoreButton);
    game.add (printHighScoreButton);
    game.add (pauseButton);
    help.add (instructionsButton);
    help.add (formulaButton);
    
    gameMenus.add (options);
    gameMenus.add (game);
    gameMenus.add (difficulty);
    gameMenus.add (help);
    
    setJMenuBar (gameMenus);
    
    setSize (500,600);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
>>>>>>> 59e3283d395c44c179e218e041e8e412e9cf5832
    
    BorderLayout border = new BorderLayout ();
    GamePanel gp = new GamePanel ();
    InformationPanel ip  = new InformationPanel ();
    
    add (gp, border.LINE_START);
    add (ip, border.LINE_END);
    
  }
  
<<<<<<< HEAD
  public void addMenuItem (JMenu menu, String name)
  {
   JMenuItem item = new JMenuItem (name);
   menu.add (item);
   item.addActionListener (this);
  }
  
  
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals("Exit"))
    {
      System.exit (0);
    }
=======
  public void actionPerformed (ActionEvent ae)
  {
>>>>>>> 59e3283d395c44c179e218e041e8e412e9cf5832
    
  }
  
  public static void main (String[] args)
  {
    new Window ();       // Create a FrameTest frame
  } // main method
}




