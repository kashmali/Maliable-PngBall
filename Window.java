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
    
    setSize (500,600);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    setResizable (false);
    ///////////////////////////////////////////////////////////// CANT GET LAYOUT MANAGER TOWORK!!!////////////////////////////////////////////
   // SpringLayout layout = new SpringLayout ();
    GridBagLayout layout = new GridBagLayout ();
    GridBagConstraints gbc = new GridBagConstraints ();
    GamePanel gp = new GamePanel ();
    InformationPanel ip  = new InformationPanel ();
    this.setLayout (layout);
 
     gbc.gridwidth = 1;
    gbc.gridy=1;
    gbc.gridx =1;
    this.add (gp,gbc);
    gbc.gridwidth = 1;
    gbc.gridy=1;
    gbc.gridx =2;
    this.add (ip);
    
//    layout.putConstraint (SpringLayout.NORTH, gp, 0, SpringLayout.NORTH, this);
//    layout.putConstraint (SpringLayout.WEST, gp, 0, SpringLayout.WEST, this);
//    layout.putConstraint (SpringLayout.EAST, gp, 0, SpringLayout.WEST,ip);
//    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, ip, 0, SpringLayout.HORIZONTAL_CENTER, this);
//    layout.putConstraint (SpringLayout.NORTH, ip, 0, SpringLayout.NORTH, this);
//    layout.putConstraint (SpringLayout.EAST, gp, 0, SpringLayout.EAST, this);
//    layout.putConstraint (SpringLayout.HORIZONTAL_CENTER, gp, 0, SpringLayout.HORIZONTAL_CENTER, this);
    
    
       revalidate ();
    repaint();
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




