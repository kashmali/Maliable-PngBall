import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Files.Current.physicsEngine.*;
import Files.Current.physicsEngine.Paddle;

//Every once in a while this program just errors with a null pointer exception
public class Window extends JFrame implements ActionListener
{
  //Change layout
  //Add Window Listener
  
  static GameEngine e;
    static InformationPanel infoP;
    static GetQuestions gq;
  public Window ()
  {
    super ("Maliable P'ngball");
    setJMenuBar (new GameMenu (this));
    //GamePanel gameP = new GamePanel ();
    infoP  = new InformationPanel ();
    //this.add (gameP);
    e = new GameEngine();
    gq = new GetQuestions ();
    e.setVisible (true);
    this.add (e); //getContentPane()
    addKeyListener(new TAdapter());
    this.add (infoP);
    GridLayout layout = new GridLayout (1,2);
    //layout.addLayoutComponent ("gameP",gameP);
    layout.addLayoutComponent ("gameP",e);
    layout.addLayoutComponent ("infoP",infoP);
    this.setLayout (layout);
    validate (); 
    pack();
    setSize (800,600);
    setLocationRelativeTo(null);
    setVisible (true);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent event)
            {
              //May have to count windows if more are added
              HighScoreManager.writeFile ();
                    System.exit(0);
            }
        });
    setResizable (false);
    //setAutoRequestFocus (true);
    repaint();
  }
    
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals("Exit"))
    {
      HighScoreManager.writeFile ();
      System.exit (0);
    }
    else if (ae.getActionCommand().equals ("Display"))
    {
      GameEngine.paused = true;
      InfoFrame frame = new InfoFrame ("High Scores");
    }
    else if (ae.getActionCommand().equals ("Instructions"))
    {
      GameEngine.paused = true;
      InfoFrame frame = new InfoFrame ("Instructions");
    }
    else if (ae.getActionCommand().equals ("Pause"))
    {
      GameEngine.paused = !GameEngine.paused;
    }
    else if (ae.getActionCommand().equals ("Formulas"))
    {
      GameEngine.paused = true;
      InfoFrame frame = new InfoFrame ("Formulas");
    }
    repaint ();
  }
  
  public  void runProgram()
  {
    //Window w = new Window ();   // Create a FrameTest frame
    while (true){
    e.gamerun();
    try {Thread.sleep (300);}
    catch (InterruptedException e){}
    }
    
  } // main method
  
  private class TAdapter extends KeyAdapter 
  {   
    public void keyReleased(KeyEvent e) 
    {
      if (!Window.e.living.isEmpty())
      Window.e.living.get (0).keyReleased(e);
      for (Paddle p : Window.e.paddles)
        p.keyReleased (e);
       
    }
    
    public void keyPressed(KeyEvent e)
    {
      if (!Window.e.living.isEmpty())
      {
      for (Spawn x : Window.e.living)
      x.keyPressed(e);
      Window.e.keyPressed (e);
      }
      for (Paddle p :Window.e.paddles)
        p.keyPressed (e);
      for (PseudoPaddle p : Window.e.pseudoPaddles)
        p.keyPressed (e);
    }
  }
}




