package Files.Current.physicsEngine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PausePanel extends JPanel
{
  JButton  highScoreButton;
    JButton instructionsButton;
    JButton formulaButton;
    JButton resumeButton;
    JButton exitButton;
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