package gameFiles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The main panel for the paused screen. It contains the pause menu to acces the other panels.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
public class PausePanel extends JPanel
{
  /**
   * The button that leads to the highscore panel
   */
  JButton  highScoreButton;
  /**
   * The button that leads to the instructions panel
   */
    JButton instructionsButton;
    /**
   * The button that leads to the formla panel
   */
    JButton formulaButton;
    /**
   * The button that exits the pause windown and resumes the current game.
   */
    JButton resumeButton;
    /**
   * The button that exits the game.
   */
    JButton exitButton;
    
    /**
   * Constructor for the panel
   * 
   * @param JFrame parent the window this panel it attached to to acces the actionPerformed method
   * @param Font headingFont font used for all titles
   * @param Font lineFont font used for buttons
   * @param JLabel title Lable that contains the title of the panel.
   * @param GroupLayout layout the layout manager that the panel uses.
   */
  public PausePanel (JFrame parent)
  {
    Font headingFont = new Font (Font.SERIF,Font.BOLD, 25);
    Font lineFont = new Font (Font.SERIF,Font.PLAIN, 17);
    JLabel title = new JLabel ("Paused");
      highScoreButton = new JButton ("Highscores");
     instructionsButton = new JButton ("Instructions");
     formulaButton = new JButton ("Formulas");
     resumeButton = new JButton ("Resume");
     exitButton = new JButton ("Exit");
    
    
    highScoreButton.addActionListener ((ActionListener)parent);
    instructionsButton.addActionListener ((ActionListener)parent);
    formulaButton.addActionListener ((ActionListener)parent);
    resumeButton.addActionListener ((ActionListener)parent);
    exitButton.addActionListener ((ActionListener)parent);
    
    GroupLayout layout = new GroupLayout (this);
    setLayout (layout);
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createSequentialGroup ()
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (title)
                                              .addComponent (resumeButton)
                                              .addComponent (highScoreButton)
                                              .addComponent (instructionsButton)
                                              .addComponent (formulaButton)
                                              .addComponent (exitButton)
                              ));
    layout.setVerticalGroup (layout.createSequentialGroup()
                                            .addComponent (title)
                                            .addComponent (resumeButton)
                                            .addComponent (highScoreButton)
                                            .addComponent (instructionsButton)
                                            .addComponent (formulaButton)
                                            .addComponent (exitButton)                      
                            );
    
    
    
    title.setFont (headingFont);
    resumeButton.setFont(lineFont);
    highScoreButton.setFont(lineFont);
    instructionsButton.setFont(lineFont);
    formulaButton.setFont(lineFont);
    exitButton.setFont(lineFont);
    setSize (layout.maximumLayoutSize(this));
    setVisible (true);
  }
  
  /**
   * Implementation of keyListener methods so the menu items can be chose using the keyboard. <br>
   * This give this menu control key access. <br>
   * switch statement distinguished between different keys.
   * 
   * @param KeyEvent e The action that caused this method to be called.
   * @param int key represents the key pressed to recognize which key was pressed.
   */
  public void keyPressed (KeyEvent e)
  {
    int key = e.getKeyCode ();
    switch (key)
    {
      case KeyEvent.VK_1 : resumeButton.doClick (); System.out.println ("This works");
      break;
      case KeyEvent.VK_2 : highScoreButton.doClick ();
      break;
      case KeyEvent.VK_3 : instructionsButton.doClick ();
      break;
      case KeyEvent.VK_4 : formulaButton.doClick ();
      break;
      case KeyEvent.VK_5 : exitButton.doClick ();
      break;
    }
  }
  
  
}