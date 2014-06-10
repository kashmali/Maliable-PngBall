import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.SpringLayout;
import java.util.*;




public class QuestionPanel extends JPanel implements ActionListener
{
  final JTextField inputField;
  ArrayList <String> question;
  
  public QuestionPanel (JFrame parent)
  {
    question = new ArrayList <String> ();
    question = GetQuestions.getQuestion ("Easy");
    Font headingFont = new Font (Font.SERIF,Font.BOLD, 25);
    JLabel headingOne = new JLabel ("QUESTION");
    JLabel headingTwo = new JLabel ("OPTIONS");
    JLabel headingThree = new JLabel ("ANSWER");
     
    Font lineFont = new Font (Font.SERIF,Font.PLAIN, 17);
    JLabel lineOne = new JLabel (question.get(0)); 
    JLabel lineTwo = new JLabel (question.get(1)); 
    JLabel lineThree = new JLabel (question.get(2));
    JLabel line34 = new JLabel (question.get(3)); 
    JLabel lineFour = new JLabel (question.get(4)); 
    JLabel lineFive = new JLabel (question.get(5)); 
    JLabel lineSix = new JLabel (question.get(6));
    JLabel lineSeven = new JLabel (question.get(7)); 
    JLabel lineEight = new JLabel (question.get(8)); 
    JLabel lineNine = new JLabel (question.get(9)); 
   // JLabel lineEleven = new JLabel (question.get(10)); // this is the answer!!
    JLabel lineTen = new JLabel ("When answering M/C, do not include spaces or punctuation. Remember significant digits!"); 
    inputField = new JTextField (20);
    JButton answerButton = new JButton ("Check");
    answerButton.addActionListener ((ActionListener)parent);
    
    
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
                                              .addComponent (lineEight)
                                              .addComponent (lineNine)
                                              .addComponent (lineTen)
                                              .addComponent (headingThree)
                                              .addComponent (inputField)
                                              .addComponent (answerButton))
                              );
    layout.setVerticalGroup (layout.createSequentialGroup()
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (headingOne))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineOne))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineTwo))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineThree))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (line34))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineFour))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineFive))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (headingTwo))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineSix))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineSeven))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineEight))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineNine))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (lineTen))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (headingThree))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (inputField))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (answerButton))                               
                            );
    
    
    
    headingOne.setFont (headingFont);
      headingTwo.setFont (headingFont);
     headingThree.setFont (headingFont);
    lineOne.setFont(lineFont);
    lineTwo.setFont(lineFont);
    lineThree.setFont(lineFont);
    line34.setFont(lineFont);
    lineFour.setFont(lineFont);
    lineFive.setFont(lineFont);
    lineSix.setFont(lineFont);
    lineSeven.setFont(lineFont);
    lineEight.setFont(lineFont);
    lineNine.setFont (lineFont);
    lineTen.setFont (lineFont);
    answerButton.setFont(lineFont);
    setSize (layout.maximumLayoutSize(this));
    setVisible (true);
  }
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals("Check"))
    {
      String answer = inputField.getText();
      if (answer.toUpperCase().equals(question.get(10)))
      {
        //reset game keep score
      }
      else 
      {
        //end game bring highscores
        
      }
    }
  }
}