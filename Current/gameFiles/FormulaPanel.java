package gameFiles;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.SpringLayout;
/**
 * The panel that contains physics formulas for the user to refer to.
 * @author Jason P'ng
 * @author Aakash Mali
 * @version v4.0 June 12th, 2014
 */
public class FormulaPanel extends JPanel 
{
    /**This is the constructor of the formula panel class, responsible for displaying all formulas on the screen<br>
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
   * @param JLabel line345 Line inbetween the third and fourth. <br>
   * @param JLabel lineFour This is the fourth line.<br>
   * @param JLabel lineFive This is the fifth line.<br>
   * @param JLabel lineSix This is the sixth line.<br>
   * @param JLabel lineSeven This is the seventh line.<br>
   * @param JLabel lineEight This is the eigth line.<br>
   * 
   * @param JButton closeButton This button closes the window.<br>
   * @param GroupLayout layout This defines the panel as a GroupLayout.<br>
   */
  public FormulaPanel (JFrame parent)
  {
    
    Font headingFont = new Font (Font.SERIF,Font.BOLD, 25);
    JLabel headingOne = new JLabel ("Physics Formulas");
    JLabel headingTwo = new JLabel ("Uniform Acceleration Equations");
    JLabel headingThree = new JLabel ("Constants");
     
    Font lineFont = new Font (Font.SERIF,Font.PLAIN, 17);
    JLabel lineOne = new JLabel ("Force = Mass * Acceleration"); 
    JLabel lineTwo = new JLabel ("Distance = Velocity * Time"); 
    JLabel lineThree = new JLabel ("Gravitational Potential Energy  = Mass * Acceleration of Gravity * Height");
    JLabel line34 = new JLabel ("Kinetic Potencial Energy = 1/2 * Mass * (Velocity^2)"); 
    JLabel line345 = new JLabel ("Momentum = mass * velocity");
    JLabel lineFour = new JLabel ("d = difference in distance, vf = final velocity, vi = innitial velocity, t = difference in Time"); 
    JLabel lineFive = new JLabel ("a = acceleration (constant)"); 
    JLabel lineSix = new JLabel ("d = ((vf +vi)/2)*t,       vf = vi + a*t,       d = vi*t + (a*t)/2");
    JLabel lineSeven = new JLabel ("(vf^2) = (vi^2) + (2*a*t),       d = (v*t) - (a*(t^2))/2");     
    JLabel lineEight = new JLabel ("Gravitaional Constan; G = 9.8 m/(s^2)"); 
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
                                              .addComponent (line345)
                                              .addComponent (headingTwo)
                                              .addComponent (lineFour)
                                              .addComponent (lineFive)
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
                               .addComponent (line345)
                                            .addComponent (headingTwo)
                                            .addComponent (lineFour)
                                            .addComponent (lineFive)
                                            .addComponent (lineSix)
                                            .addComponent (lineSeven)
                               .addComponent (lineEight)
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
    line345.setFont(lineFont);
    closeButton.setFont(lineFont);
    setSize (layout.maximumLayoutSize(this));
    setVisible (true);
  }
  
}