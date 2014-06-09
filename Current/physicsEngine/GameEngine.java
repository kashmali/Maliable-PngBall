package Files.Current.physicsEngine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class GameEngine extends JPanel implements Pausable
{
  public static final int MAX_SPAWN = 20; //30
  public static final int X = 500;
  public static final int Y = 500;
  public static final float GRAVITY = .0000981f; //1500
  public static final float DRAG = 0.1f; //0.2
  public static final float BOUNCE = 1f; //0.5
  public final int UPDATE_RATE = 60;
  public static final String TITLE = "Mike's 2d Physics Engine greatly modified by Jason using code written by Hock-Chuan Chua";
  private static Graphics graphics;
  private static Graphics2D g2d;
  private static AffineTransform at;
  public static ArrayList<Spawn> living = new ArrayList<Spawn>();
  public static ArrayList<ButtonObstacle> buttons = new ArrayList<ButtonObstacle>();
  public static ArrayList<Paddle> paddles = new ArrayList<Paddle>();
  public static ArrayList<ObstacleLine> lines = new ArrayList<ObstacleLine>();
  public static ArrayList<PseudoPaddle> pseudoPaddles = new ArrayList<PseudoPaddle>();
  public static boolean isRunning = true;
  private int fps;
  public static boolean paused = false;
  MoveEngine moveEngine;
  public GameEngine ()
  {
    // Create canvas for painting...

    //setIgnoreRepaint(true);
    setSize(X, Y);
    setBackground (Color.WHITE);
    // Add the canvas, and display.
    setVisible(true);
    // Set up the BufferStrategy for double buffering.
    setDoubleBuffered(true);
 moveEngine = new MoveEngine(this);
    // Objects needed for rendering...
    graphics = null;
    g2d = null;
    initialize();
  }
  
  public void initialize ()
  {
   living.add (new CircleSpawn (16,16,0,0,100));
    //obstacles.add (new RectangleSpawn (100,300,0,0,200,100));
//    paddles.add (new Paddle (215,450,135,Color.BLACK,Paddle.RIGHT));
//    paddles.add (new Paddle (85,450,45,Color.BLACK,Paddle.LEFT));

    lines.add (new ObstacleLine (100,200,300,200,Color.BLACK));
    lines.add (new ObstacleLine (100,200,200,300,Color.BLACK));
    lines.add (new ObstacleLine (200,300,300,200,Color.BLACK));
    
    //walls
    lines.add (new ObstacleLine (0,0,0,550,Color.BLACK));
    lines.add (new ObstacleLine (400,0,400,550,Color.BLACK));
    lines.add (new ObstacleLine (0,0,400,0,Color.BLACK));
    
    //slopes to the paddle
    lines.add (new ObstacleLine (0,400,100,500,Color.BLACK));
    lines.add (new ObstacleLine (400,400,300,500,Color.BLACK));
    pseudoPaddles.add (new PseudoPaddle (150,500,100,Color.BLACK)); 
  }
  
  public void reset ()
  {
    living.clear ();
    buttons.clear();
    lines.clear();
    paddles.clear();
    pseudoPaddles.clear();
  }
  
  public void gamerun ()
  {
   
    //moveEngine.start();
//    Thread makeBall = new MakeBall (this);
//    makeBall.start();
    fps = 0;
    int frames = 0;
    long totalTime = 0;
    long curTime = System.currentTimeMillis();
    long lastTime = curTime;
    
    // Start the loop.
    while (isRunning) {
        // Calculations for FPS.
      long beginTimeMillis, timeTakenMillis, timeLeftMillis;
               beginTimeMillis = System.currentTimeMillis();
               
        lastTime = curTime;
        curTime = System.currentTimeMillis();
        totalTime += curTime - lastTime;
        
        if (totalTime > 1000) {
          totalTime -= 1000;
          fps = frames;
          frames = 0;
        }
        ++frames;
        
        if (!paused)
        {
          moveEngine.run();
        }
        repaint ();
      
        
        //If the player dies
        if (living.get(0).getY() > 590){
          isRunning = false;
        }
        
        timeTakenMillis = System.currentTimeMillis() - beginTimeMillis;
               timeLeftMillis = 1000L / UPDATE_RATE - timeTakenMillis;
               if (timeLeftMillis < 5) timeLeftMillis = 5; // Set a minimum
               
               // Delay and give other thread a chance
               try {
                  Thread.sleep(timeLeftMillis);

               } catch (InterruptedException e) {}
//      } finally {
//        // release resources
//        if (graphics != null) graphics.dispose();
//        if (g2d != null) g2d.dispose();
//      }
        
    }
    System.out.println ("Game Over");
    living.get (0).updatePos (16,16);
    isRunning = true;
   // run();
  }

  public void paintComponent (Graphics g)
  {
   super.paintComponent (g);
   
    //System.out.println ("Print");
   //g2d = (Graphics2D) g;
//   //try{
   for (int i = 0; i < living.size(); i++) {
          /*at = new AffineTransform();
          at.translate(living.get(i).getX(), living.get(i).getY());*/
          //g2d.setColor(Color.BLACK);
          Spawn s = living.get(i);
          g.fillOval ((int)s.getX() - 15,(int)s.getY() - 15,(int)(((CircleSpawn)s).getRadius() * 2),(int)(((CircleSpawn)s).getRadius() * 2));
          //g2d.fill(s.getShape());
        }
   //g2d.setColor (Color.GREEN);
//   for (int x = 0; x < obstacles.size();x++)
//   {
//     Spawn t = obstacles.get(x);
//     g2d.fill (t.getShape());
//   }
   //g.setColor (Color.BLACK);
     for (Paddle p: paddles)
     {     
       g.setColor (p.getColour());
       g.drawLine ((int)p.getX(),(int)p.getY(),(int)p.getX2(),(int)p.getY2());
       //System.out.println (p.toString());
     }
    for (PseudoPaddle p : pseudoPaddles){
      g.drawLine ((int)p.getX(),(int)p.getY(),(int)p.getX2(),(int)p.getY2());}
    for (ObstacleLine l :lines){
      g.drawLine ((int)l.getX(),(int)l.getY(),(int)l.getX2(),(int)l.getY2());}
//     //g2d.draw (new Line2D.Double (100,100,200,100));
//         //display frames per second...
//        g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
//        g2d.setColor(Color.GREEN);
//        /*if (fps == 0)
//          System.out.println ("0 fps");
//        if (g2d == null)
//          System.out.println ("Null Graphics");*/
        g.drawString(String.format("FPS: %s", fps), 20, 20);
//        
//        
//          //Errors once and then stops. Don't know why it throws a NullPointerException
//          g.drawString ("FPS: " + fps,20,20);
          if (isPaused())
          {
           g.drawString ("Paused game", 20,40);
          }
//        //}
//        //catch (NullPointerException e)
//        //{
//          
//        //}
        
        if (isRunning == false)
        {
          g.setColor (Color.RED);
          g.drawLine (0,0,400,550);
          g.drawLine (0,550,400,0);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose ();
  }
  public static boolean allDead()
  {
    if (living.size() < 1) return true;
    return false;
  }

  public static synchronized int giveBirth(int x, int y, float vx, float vy,int m)
  {
    if (living.size() >= MAX_SPAWN) return 1;
    living.add(new CircleSpawn(x, y, vx, vy,m));
    return 0;
  }
  
  public void pauseThread ()
  {
    /*while (paused)
    {
      try
      {
     Thread.sleep (1);
      }
      catch (InterruptedException e)
      {
        
      }
    }    */
  }
  
  public boolean isPaused ()
  {
   return paused; 
  }
  
  public void pause ()
  {
   paused = true; 
  }
  
  public boolean acknowledge = false;
  public void keyPressed (KeyEvent e)
  {
   int key = e.getKeyCode ();
   switch (key){
     case KeyEvent.VK_SPACE : paused = !paused;
     break;
     case KeyEvent.VK_R : 
//For some strange reason the repaint method stops refrshing once this is pressed
       System.out.println ("Pressed");
//       reset ();
//       initialize();
//       living.get (0).updatePos (16,16);
//       isRunning = true;
       gamerun();
       break;

   }
  }
}


