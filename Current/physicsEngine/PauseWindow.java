package Files.Current.physicsEngine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PauseWindow extends JFrame implements ActionListener
{
  JPanel mainPausePanel;
  
  public PauseWindow ()
  {
    super ("Game Paused");
    setUndecorated(true);
    //Instruction
    InstructionsPanel instructions = new InstructionsPanel (this);
    
    //HighScores
    HighScorePanel highscores = new HighScorePanel (this);
    
    //Formulas
    FormulaPanel formulas = new FormulaPanel (this);
    
    //Menu
    PausePanel menu = new PausePanel (this);
    //The main Panel
    mainPausePanel = new JPanel (new CardLayout()); 
    mainPausePanel.add ("Menu",menu);
    mainPausePanel.add ("Instructions",instructions);
    mainPausePanel.add ("Highscores",highscores);
    mainPausePanel.add ("Formulas", formulas);    
    mainPausePanel.setFocusable(true);
    
    add (mainPausePanel,BorderLayout.CENTER);

    validate (); 
    pack();
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    addWindowListener(new WindowAdapter()
                        {
      public void windowClosing(WindowEvent event)
      {
        
      }
    });
    //setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    setSize (800,600);
    setLocationRelativeTo(null);
    setResizable (false);
    setVisible (true);
    
    
    //setAutoRequestFocus (true);
  }
  
  public void actionPerformed (ActionEvent ae)
  {
   String command = ae.getActionCommand();
   if (command.equals ("Close"))
   {
    show ("Menu");
   }
    else if (command.equals ("Instructions"))
    {
     show ("Instructions"); 
    }
    else if (command.equals ("Formulas"))
    {
      show ("Formulas");
    }
    else if (command.equals ("Highscores"))
    {
      show ("Highscores");
    }
    else if (command.equals ("Resume"))
    {
      GameEngine.paused = false;
      GameEngine.open = false;
      this.dispose();
    }
    else if (command.equals ("Exit"))
    {
      GameEngine.terminated = true;
      GameEngine.paused = false;
      GameEngine.open = false;
      this.dispose();
    }
  }
  
  public void show (String panel)
  {
    CardLayout cl = (CardLayout)(mainPausePanel.getLayout());
    cl.show (mainPausePanel, panel);
  }
}