package Files.Current.physicsEngine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PausePanel extends JPanel
{
  public PausePanel (JFrame parent)
  {
    Font headingFont = new Font (Font.SERIF,Font.BOLD, 25);
    Font lineFont = new Font (Font.SERIF,Font.PLAIN, 17);
    JLabel title = new JLabel ("Paused");
    JButton  highScoreButton = new JButton ("Highscores");
    JButton instructionsButton = new JButton ("Instructions");
    JButton formulaButton = new JButton ("Formulas");
    JButton resumeButton = new JButton ("Resume");
    JButton exitButton = new JButton ("Exit");
    
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
  
  
  
}