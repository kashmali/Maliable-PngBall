package gameFiles;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.SpringLayout;

/**
   * Panel that displays the instructions of the game.
   * @author Aakash Mali
 * @version v4.0 June 12th, 2014
   */
public class InstructionsPanel extends JPanel 
{
  /**This is the constructor of the instruction panel class, responsible for displaying all instructions on the screen<br>
   * with a GroupLayout.
   * 
   * @param JFrame parent This allows for the usage of the ActionListener in the JFrame this panel is in.<br>
   * @param Font headingFont This is the heading font.<br>
   * @param JLabel headingOne This is the first heading.<br>
   * @param JLabel headingTwo This is the second heading.<br>
   * @param JLabel headingThree This is the third heading.<br>
   * @param Font lineFont This is the default font.<br>
   * @param JLabel lineOne This is the first line.<br>
   * @param JLabel lineTwo This is the second line.<br>
   * @param JLabel lineThree This is the third line.<br>
   * @param JLabel line34 This is the line between the third and the fourth.<br>
   * @param JLabel lineFour This is the fourth line.<br>
   * @param JLabel lineFive This is the fifth line.<br>
   * @param JLabel lineSix This is the sixth line.<br>
   * @param JLabel lineSeven This is the seventh line.<br>
   * @param JLabel lineEight This is the eigth line.<br>
   * @param JLabel lineNine This is the ninth line. <br>
   * 
   * @param JButton closeButton This button closes the window.<br>
   * @param GroupLayout layout This defines the panel as a GroupLayout.<br>
   */
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