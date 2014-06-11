import javax.swing.*;
import java.awt.event.*;

public class GameMenu extends JMenuBar
{
  JFrame parent;
  public GameMenu (JFrame parent)
  {
    this.parent = parent;  
    JMenu options = new JMenu ("Options");
    JMenu game = new JMenu ("Game");
    JMenu difficulty = new JMenu ("Difficulty");
    JMenu highScore = new JMenu ("High Scores");
    JMenu help = new JMenu ("Help");

    addMenuItem (difficulty, "Easy");
    addMenuItem (difficulty, "Medium");
    addMenuItem (difficulty, "Hard");
    addMenuItem (highScore, "Display");
    addMenuItem (highScore, "Print");
    addMenuItem (highScore, "Clear");

    game.add (highScore);
    game.addSeparator();
    addMenuItem (game, "Exit");
    
    options.add(difficulty);
    addMenuItem (help, "Instructions");
    addMenuItem (help, "Formulas");
    addMenuItem (help, "Help");
    
    add (game);
    add (options);
    add (help);

  }
  
  public void addMenuItem (JMenu menu, String name)
  {
   JMenuItem item = new JMenuItem (name);
   menu.add (item);
   item.addActionListener ((ActionListener)parent);
  }
}