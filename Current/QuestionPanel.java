import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.SpringLayout;
import java.util.*;

import Files.Current.physicsEngine.GameEngine;


public class QuestionPanel extends JPanel implements ActionListener
{
  final JTextField inputField;
  Question question;
  JFrame parent;
  public QuestionPanel (JFrame parent)
  {
    this.parent = parent;
    question = QuestionManager.getQuestion (GameEngine.getDifficultyAsString());
    Font headingFont = new Font (Font.SERIF,Font.BOLD, 25);
    JLabel headingOne = new JLabel ("QUESTION");
    JLabel headingTwo = new JLabel ("OPTIONS");
    JLabel headingThree = new JLabel ("ANSWER");
     
    Font lineFont = new Font (Font.SERIF,Font.PLAIN, 17);
    JLabel [] questionLabels = new JLabel [10];
    for (int x = 0; x < 6; x++)
    {
      questionLabels [x] = new JLabel (question.getQuestion()[x]);
      questionLabels [x].setFont (lineFont);
    }
    for (int x = 6; x < 10; x++)
    {
      questionLabels [x] = new JLabel (question.getOptions() [x - 6]);
      questionLabels [x].setFont (lineFont);
    }
   // JLabel lineEleven = new JLabel (question.get(10)); // this is the answer!!
    JLabel lineTen = new JLabel ("When answering M/C, do not include spaces or punctuation. Remember significant digits!"); 
    inputField = new JTextField (10);
    JButton answerButton = new JButton ("Check");
    answerButton.addActionListener (this);//(ActionListener)parent);
    
    
    GroupLayout layout = new GroupLayout (this);
    setLayout (layout);
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createSequentialGroup ()
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (headingOne)
                                              .addComponent (questionLabels[0])
                                              .addComponent (questionLabels[1])
                                              .addComponent (questionLabels[2])
                                              .addComponent (questionLabels[3])                                              
                                              .addComponent (questionLabels[4])
                                              .addComponent (questionLabels[5])
                                              .addComponent (headingTwo)
                                              .addComponent (questionLabels[6])
                                              .addComponent (questionLabels[7])
                                              .addComponent (questionLabels[8])
                                              .addComponent (questionLabels[9])
                                              .addComponent (lineTen)
                                              .addComponent (headingThree)
                                              .addComponent (inputField)
                                              .addComponent (answerButton))
                              );
    layout.setVerticalGroup (layout.createSequentialGroup()
                                            .addComponent (headingOne)
                                            .addComponent (questionLabels[0])
                                            .addComponent (questionLabels[1])
                                            .addComponent (questionLabels[2])
                                            .addComponent (questionLabels[3])
                                            .addComponent (questionLabels[4])
                                            .addComponent (questionLabels[5])
                                            .addComponent (headingTwo)
                                            .addComponent (questionLabels[6])
                                            .addComponent (questionLabels[7])
                                            .addComponent (questionLabels[8])
                                            .addComponent (questionLabels[9])
                                            .addComponent (lineTen)
                                            .addComponent (headingThree)
                                            .addComponent (inputField)
                                            .addComponent (answerButton)
                            );
    
    
    
    headingOne.setFont (headingFont);
      headingTwo.setFont (headingFont);
     headingThree.setFont (headingFont);
    lineTen.setFont (lineFont);
    answerButton.setFont(lineFont);
    setSize (layout.preferredLayoutSize(this));
    setVisible (true);
  }
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals("Check"))
    {
      String answer = inputField.getText();
      //Correct Answer
      answer = answer.replace (" ","");
      answer = answer.replace (",","");
      answer = answer.replace (".","");
      if (answer.toUpperCase().equals(question.getAnswer()) || answer.toUpperCase().equals ("IAMMSDYKE"))
      {
        //reset game keep score
        Window.setAnswer (true);
        GameEngine.increaseScore (100);
      }
      else 
      {
        //end game bring highscores
        Window.setAnswer (false);
      }
     ((ActionListener) parent).actionPerformed (new ActionEvent (this,ActionEvent.ACTION_PERFORMED,"Check"));
    }
  }
}