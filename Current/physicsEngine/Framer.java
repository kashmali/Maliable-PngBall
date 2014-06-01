package Files.Current.physicsEngine;

import javax.swing.JFrame;
import javax.swing.JPanel;
  import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/*
 * This is a test class. It will not be used in the actual program
 */
public class Framer extends JFrame
{
  static GameEngine e;
  
  public Framer ()
  {
    e = new GameEngine();
    e.setVisible (true);
    getContentPane().add (e);
    setSize (500,500);
    addKeyListener(new TAdapter());
    setVisible (true);
    e.run ();
  }
  public static void main (String [] args)
  {
    Framer f = new Framer ();
    
  }
  
  private class TAdapter extends KeyAdapter 
  {   
    public void keyReleased(KeyEvent e) 
    {
      Framer.e.living.get (0).keyReleased(e);
    }
    
    public void keyPressed(KeyEvent e)
    {
      for (Spawn x : Framer.e.living)
      x.keyPressed(e);
    }
  }
}


