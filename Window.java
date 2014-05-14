import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class Window extends JFrame implements ActionListener
{
  
  
  public Window ()
  {
    super ("Maliable P'ngball");
    
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
    
    setSize (900,800);
    setResizable (false);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    
    GamePanel gamePanel = new GamePanel ();
    InformationPanel infoPanel = new InformationPanel ();
    BorderLayout bl = new BorderLayout ();
    add (gamePanel, bl.LINE_START);
    add (infoPanel, bl.LINE_END);
    
    
    
    
    
    
  }
  
  public void actionPerformed (ActionEvent ae)
  {
    
  }
  
  public static void main (String[] args)
  {
    new Window ();       // Create a FrameTest frame
  } // main method
}




