import javax.swing.*;
import java.awt.event.*;

/**
 * The menu bar to be put on the main window to access options.
 * @author Aakash Mali
 * @version v4.0 June 12th, 2014
 */
public class GameMenu extends JMenuBar
{
  /**
   * This variable is being used to define the parent to access the actionPerformed method
   */
  JFrame parent;
  /**This is the constructor of the GameMenu class, responsible for creating the JMenuBar.
   * 
   * @param JFrame parent This is where the JMenuBar is going to be added.<br>
   * @param JMenu options This is the options menu.<br>
   * @param JMenu game This is the game menu.<br>
   * @param JMenu difficulty This is the difficulty menu.<br>
   * @param JMenu highScore This is the high score menu.<br>
   * @param JMenu help This is the help menu.<br>
   */
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
  
  /**This is a helper method responsible for adding ActionListeners and constructing the JMenuItems.
   * 
   * @param JMenu menu This is the menu where the JMenuItem will be placed. <br>
   * @param String name This is the name of the item.<br>
   * @param JMenuItem item This is the JMenuItem.<br>
   */
  public void addMenuItem (JMenu menu, String name)
  {
   JMenuItem item = new JMenuItem (name);
   menu.add (item);
   item.addActionListener ((ActionListener)parent);
  }
}