package Files.Current.physicsEngine;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.SpringLayout;
public class InstructionsPanel extends JPanel 
{
  
  public InstructionsPanel (JFrame parent)
  {
    
    Font headingFont = new Font (Font.SERIF,Font.BOLD, 25);
    JLabel headingOne = new JLabel ("The Game");
    JLabel headingTwo = new JLabel ("The Difficulty");
    JLabel headingThree = new JLabel ("The Controls");
    
    Font lineFont = new Font (Font.SERIF,Font.PLAIN, 17);
    JLabel lineOne = new JLabel ("The goal of the game is to prevent the ball from falling down the middle gap called the gutter."); 
    JLabel lineTwo = new JLabel ("While keeping the ball in play aim for the bouncers (Coloured objects) which will score you points."); 
    JLabel lineThree = new JLabel ("If the ball falls down the gutter the game will end and give you a chance to redeem yourself");
    JLabel line34 = new JLabel ("by answering a physics question correctly. This extends for a maximum of 3 levels."); 
    JLabel lineFour = new JLabel ("If you fail to answer correctly, the game is over, and your score will be displayed. "); 
    JLabel lineFive = new JLabel ("The difficulty of the game  can be changed in the main menu.");     
    JLabel lineSix = new JLabel ("The difficulty will decide the difficulty of the physics questions."); 
    JLabel lineSeven = new JLabel ("You may pause the game at any time by pressing \'p\'");
    JLabel lineEight = new JLabel ("The options of the pause menu can be pressed using the keyboard numbers 1 - 5.");
    JLabel lineNine = new JLabel ("Press the right and left arrow keys to move the paddle. To release the ball press spacebar."); 
    JButton closeButton = new JButton ("Close");
    closeButton.addActionListener ((ActionListener)parent);
    
    
    GroupLayout layout = new GroupLayout (this);
    setLayout (layout);
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createSequentialGroup ()
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (headingOne)
                                              .addComponent (lineOne)
                                              .addComponent (lineTwo)
                                              .addComponent (lineThree)
                                              .addComponent (line34)
                                              .addComponent (lineFour)
                                              
                                              .addComponent (headingTwo)
                                              .addComponent (lineFive)
                                              .addComponent (lineSix)
                                              
                                              .addComponent (headingThree)
                                              .addComponent (lineSeven)
                                              .addComponent (lineEight)
                                              .addComponent (lineNine)
                                              .addComponent (closeButton))
                              );
    layout.setVerticalGroup (layout.createSequentialGroup()
                               .addComponent (headingOne)
                               .addComponent (lineOne)
                               .addComponent (lineTwo)
                               .addComponent (lineThree)
                               .addComponent (line34)
                               .addComponent (lineFour)
                               .addComponent (headingTwo)
                               .addComponent (lineFive)
                               .addComponent (lineSix)
                               .addComponent (headingThree)
                               .addComponent (lineSeven)
                               .addComponent (lineEight)
                               .addComponent (lineNine)
                               .addComponent (closeButton)
                            );
    
    
    
    headingOne.setFont (headingFont);
    lineOne.setFont(lineFont);
    lineTwo.setFont(lineFont);
    lineThree.setFont(lineFont);
    line34.setFont(lineFont);
    lineFour.setFont(lineFont);
    lineFive.setFont(lineFont);
    headingTwo.setFont (headingFont);
    lineSix.setFont(lineFont);
    lineSeven.setFont(lineFont);
    headingThree.setFont (headingFont);
    lineEight.setFont(lineFont);
    lineNine.setFont (lineFont);
    closeButton.setFont(lineFont);
    setSize (layout.maximumLayoutSize(this));
    setVisible (true);
  }
  
}