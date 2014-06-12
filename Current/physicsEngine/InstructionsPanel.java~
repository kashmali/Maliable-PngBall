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
    JLabel lineTwo = new JLabel ("While keeping the ball in play aim for the bouncers (yellow coloured) which will score you points."); 
    JLabel lineThree = new JLabel ("If the ball falls down the gutter the game will end and give you a chance to redeem yourself");
    JLabel line34 = new JLabel ("by answering a physics question correctly."); 
    JLabel lineFour = new JLabel ("The difficulty of the game will increase after you have had to answer 3 questions."); 
    JLabel lineFive = new JLabel ("If you fail to answer correctly, the game is over, and your score will be displayed. "); 
    JLabel lineSix = new JLabel ("The difficulty will decide the radius of the ball (the more difficult the smaller),"); 
    JLabel lineSeven = new JLabel (" as well as the difficulty of the physics questions."); 
    JLabel lineEight = new JLabel ("Press ‘z’ to move the left flipper and ‘m’ to move the flipper. To release the ball press spacebar."); 
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
                                              .addComponent (lineFive)
                                              .addComponent (headingTwo)
                                              .addComponent (lineSix)
                                              .addComponent (lineSeven)
                                              .addComponent (headingThree)
                                              .addComponent (lineEight)
                                              .addComponent (closeButton))
                              );
    layout.setVerticalGroup (layout.createSequentialGroup()
                                            .addComponent (headingOne)
                                            .addComponent (lineOne)
                                            .addComponent (lineTwo)
                                            .addComponent (lineThree)
                                            .addComponent (line34)
                                            .addComponent (lineFour)
                                            .addComponent (lineFive)
                                            .addComponent (headingTwo)
                                            .addComponent (lineSix)
                                            .addComponent (lineSeven)
                                            .addComponent (headingThree)
                                            .addComponent (lineEight)
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
    closeButton.setFont(lineFont);
    setSize (layout.maximumLayoutSize(this));
    setVisible (true);
  }
  
}