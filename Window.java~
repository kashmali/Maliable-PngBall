import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class Window extends JFrame implements ActionListener
{
  
  
  public Window ()
  {
    super ("Maliable P'ngball");
        
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
    
    BorderLayout border = new BorderLayout ();
    GamePanel gp = new GamePanel ();
    InformationPanel ip  = new InformationPanel ();
    
    add (gp, border.LINE_START);
    add (ip, border.LINE_END);
    
  }
  
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
    
  }
  
  public static void main (String[] args)
  {
    new Window ();       // Create a FrameTest frame
  } // main method
}




