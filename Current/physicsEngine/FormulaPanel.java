package Files.Current.physicsEngine;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.SpringLayout;
public class FormulaPanel extends JPanel 
{
  
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
                                            .addComponent (headingTwo)
                                            .addComponent (lineFour)
                                            .addComponent (lineFive)
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