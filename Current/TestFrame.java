import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Files.Current.physicsEngine.*;

public class TestFrame extends JFrame
{
  JPanel panel;
  public TestFrame ()
  {
    super ("Test frame");
    panel = new TestPanel();
    add (panel);
    validate (); 
    pack();
    setSize (800,600);
    setLocationRelativeTo(null);
    addKeyListener(new TAdapter());
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent event)
            {
              //May have to count windows if more are added
                    System.exit(0);
            }
        });
    setResizable (false);
    //setAutoRequestFocus (true);
    repaint();
  }
  public void actionPerformed (ActionEvent ae)
  {
    
  }
  
  public static void main (String[] args)
  {
          // Create a FrameTest frame
    new TestFrame  ();
  } // main method
  
  private class TAdapter extends KeyAdapter 
  {   
    public void keyReleased(KeyEvent e) 
    {
      //((TestPanel)panel).getPaddle().keyReleased(e);
    }
    
    public void keyPressed(KeyEvent e)
    {
      //((TestPanel)panel).getPaddle().keyPressed(e);
    }
  }
}