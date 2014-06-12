package gameFiles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The window used to host the pause menu during the game.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class PauseWindow extends JFrame implements ActionListener
{
  /**
   * The main panel that contains all the other panels. It uses card layout to switch between panels.
   */
  JPanel mainPausePanel;
  
  /**
   * Constructor for the pauseWindow.
   * 
   * @param InstructionsPanel instructions the panel for the instructions.
   * @param HighScorePanel highscores the panel displaying the highscores.
   * @param FormulaPanel formulas the panel that displays the formulas.
   */
  public PauseWindow ()
  {
    super ("Game Paused");
    setUndecorated(true);
    //Instruction
    InstructionsPanel instructions = new InstructionsPanel (this);
    
    //HighScores
    HighScorePanel highscores = new HighScorePanel (this);
    
    //Formulas
    FormulaPanel formulas = new FormulaPanel (this);
    
    //Menu
    final PausePanel menu = new PausePanel (this);
    //The main Panel
    mainPausePanel = new JPanel (new CardLayout()); 
    mainPausePanel.add ("Menu",menu);
    mainPausePanel.add ("Instructions",instructions);
    mainPausePanel.add ("Highscores",highscores);
    mainPausePanel.add ("Formulas", formulas);    
    mainPausePanel.setFocusable(true);
    mainPausePanel.addKeyListener (new KeyAdapter ()
                      {
      public void keyPressed (KeyEvent e)
      {
        System.out.println ("This owrkk");
        menu.keyPressed (e);
      }
    });
    add (mainPausePanel,BorderLayout.CENTER);
    
    
    validate (); 
    pack();
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setSize (800,600);
    setLocationRelativeTo(null);
    setResizable (false);
    setVisible (true);
  }
  
  /**
   * implemented method for the ActionListener interface. This add functionality to the buttons. <br>
   * The if structure distinguishes between different buttons.
   * 
   * @param ActionEvent ae the action that triggers this method to be called
   * @param String commmand action command of the actionEvent.
   */
  public void actionPerformed (ActionEvent ae)
  {
   String command = ae.getActionCommand();
   if (command.equals ("Close"))
   {
    show ("Menu");
   }
    else if (command.equals ("Instructions"))
    {
     show ("Instructions"); 
    }
    else if (command.equals ("Formulas"))
    {
      show ("Formulas");
    }
    else if (command.equals ("Highscores"))
    {
      show ("Highscores");
    }
    else if (command.equals ("Resume"))
    {
      GameEngine.paused = false;
      GameEngine.open = false;
      this.dispose();
    }
    else if (command.equals ("Exit"))
    {
      GameEngine.terminated = true;
      GameEngine.paused = false;
      GameEngine.open = false;
      this.dispose();
    }
  }
  
  /**
   * Helper method to switch between panels.
   * 
   * @param String panel the name of the panel to be shown.
   */
  public void show (String panel)
  {
    CardLayout cl = (CardLayout)(mainPausePanel.getLayout());
    cl.show (mainPausePanel, panel);
  }
}