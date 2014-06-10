import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;

/**
 * This is a JFrame that will contain various panels for the popup windows to display info to the user. <b>
 * This class is used to contain the instructions, highscores, formulas, and questions.
 * 
 * @author Aakash Mali
 * @author Jason P'ng
 * @version 1.0 June 5th 2014
 */
public class InfoFrame extends JFrame implements ActionListener
{
  /**
   * The general constructor for the Jframe
   * The if structure provides distinction between the different kinds of panels.
   * @param String panelName This determines which type of panel should be in the Jframe.
   */
 public InfoFrame (String panelName)
 {

   super (panelName);
   if (panelName.equals ("Instructions"))
   {
     InstructionsPanel panel = new InstructionsPanel (this);
     getContentPane().add (panel);
     setSize ((int)(panel.getSize().getWidth()*1.1),(int)(panel.getSize().getHeight()*1.1));
     
   }
   else if (panelName.equals ("Formulas"))
   {
     FormulaPanel panel = new FormulaPanel (this);
     getContentPane().add (panel);
     setSize ((int)(panel.getSize().getWidth()*1.1),(int)(panel.getSize().getHeight()*1.1));
   }
   else if (panelName.equals ("QuestionPanel"))
   {
   }
   else if (panelName.equals ("High Scores"))
   {
     HighScorePanel panel = new HighScorePanel (this);
     getContentPane().add (panel);
     setSize ((int)(panel.getSize().getWidth()*1.1),(int)(panel.getSize().getHeight()*1.1));
   }
   
   
   setVisible (true);
   setAutoRequestFocus (true);
   setResizable (false);
   setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
 }
  
 /**
  * The default constructor for the JFrame. It contains no JPanel.
  */
 public InfoFrame ()
 {
 }
 
 /**
  * implemented method for the ActionListener interface.
  * The if structure will determine to dispose of the JFrame once the "Close" action has occured. Each panel contains a close button.
  * @param ActionEvent ae stores the action that occured
  */
 public void actionPerformed (ActionEvent ae)
 {
  if (ae.getActionCommand().equals ("Close"))
        {
    //http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
    //this.dispatchEvent (new WindowEvent (this, WindowEvent.WINDOW_CLOSING));
    this.dispose ();
  }
 }
  
}