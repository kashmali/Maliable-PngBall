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
  public static final double GRAVITY = 1500; //1500
  public static final double DRAG = 0.3; //0.2
  public static final double BOUNCE = 0.9; //0.5
  public static final String TITLE = "Aakash's 2D Physics Engine";
  private static JFrame f;
  private static Canvas c;
  public static BufferStrategy b;
  private static GraphicsEnvironment ge;
  private static GraphicsDevice gd;
  private static GraphicsConfiguration gc;
  private static BufferedImage buffer;
  private static Graphics graphics;
  private static Graphics2D g2d;
  private static AffineTransform at;
  public static ArrayList<Spawn> living = new ArrayList<Spawn>();
  public static boolean isRunning = true;
  private int fps;
  public static boolean paused = false;
  
  public GameEngine ()
  {
    // Create canvas for painting...

    setIgnoreRepaint(true);
    setSize(X, Y);
    setBackground (Color.BLUE);
    // Add the canvas, and display.
    setVisible(true);
    // Set up the BufferStrategy for double buffering.
    setDoubleBuffered(true);
    
    //b = getBufferStrategy();
    // Get graphics configuration...
    ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    gd = ge.getDefaultScreenDevice();
    gc = gd.getDefaultConfiguration();
    // Create off-screen drawing surface
    buffer = gc.createCompatibleImage(X, Y);
    // Objects needed for rendering...
    graphics = null;
    g2d = null;
  }
  
  public void run ()
  {
    Thread moveEngine = new MoveEngine(this);
    moveEngine.start();
    Thread makeBall = new MakeBall (this);
    makeBall.start();
    
    fps = 0;
    int frames = 0;
    long totalTime = 0;
    long curTime = System.currentTimeMillis();
    long lastTime = curTime;
    giveBirth (50,50,500,500,100);

    // Start the loop.
    while (isRunning) {
      try {
        // Calculations for FPS.
        lastTime = curTime;
        curTime = System.currentTimeMillis();
        totalTime += curTime - lastTime;
        if (totalTime > 1000) {
          totalTime -= 1000;
          fps = frames;
          frames = 0;
        }
        ++frames;
        if (paused)
          pauseThread();
        repaint ();
        // clear back buffer...
       /* g2d = buffer.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, X, Y);
        // Draw entities/////////////////////////////////////////////////////////////////////////////////////////////////////////////ADD OBSTACLES HERE///////
        g2d.setColor(Color.BLACK);
     g2d.fill(new Rectangle2D.Double (0,100,480,30));
     g2d.fill(new Rectangle2D.Double (160,200,480,30));
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < living.size(); i++) {
          at = new AffineTransform();
          at.translate(living.get(i).getX(), living.get(i).getY());
          g2d.setColor(Color.BLACK);
          Spawn s = living.get(i);
          g2d.fill(new Ellipse2D.Double(s.getX(), s.getY(), s
                                        .getRadius() * 2, s.getRadius() * 2));
        }
        // display frames per second...
        g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
        g2d.setColor(Color.GREEN);
        g2d.drawString(String.format("FPS: %s", fps), 20, 20);*/
        // Blit image and flip...
        /*graphics = getGraphics();
        if (graphics == null)
          System.out.println ("Null image");
        graphics.drawImage(buffer, 0, 0, null);
        if (!b.contentsLost()) 
          b.show();*/
        // Let the OS have a little time...
        Thread.sleep(15);
      } catch (InterruptedException e) {
      } finally {
        // release resources
        if (graphics != null) graphics.dispose();
        if (g2d != null) g2d.dispose();
      }
    }
  }

  public void paint (Graphics g)
  {
   super.paint (g);
   g2d = (Graphics2D) g;
   try
   {
   for (int i = 0; i < living.size(); i++) {
          /*at = new AffineTransform();
          at.translate(living.get(i).getX(), living.get(i).getY());*/
          g2d.setColor(Color.BLACK);
          Spawn s = living.get(i);
          g2d.fill(s.getShape());
        }
        // display frames per second...
        g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
        g2d.setColor(Color.GREEN);
        /*if (fps == 0)
          System.out.println ("0 fps");
        if (g2d == null)
          System.out.println ("Null Graphics");*/
        //g2d.drawString(String.format("FPS: %s", fps), 20, 20);
        
        
          //Errors once and then stops. Don't know why it throws a NullPointerException
          g2d.drawString ("FPS: " + fps,20,20);
        }
        catch (NullPointerException e)
        {
          
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose ();
  }
  public static boolean allDead()
  {
    if (living.size() < 1) return true;
    return false;
  }

  public static synchronized int giveBirth(int x, int y, double vx,
                                           double vy, int m)
  {
    if (living.size() >= MAX_SPAWN) return 1;
    living.add(new Spawn(x, y, vx, vy, m));
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
    }  */  
  }
  
  public boolean isPaused ()
  {
   return paused; 
  }
  
  public void keyPressed (KeyEvent e)
  {
   int key = e.getKeyCode ();
   switch (key){
     case KeyEvent.VK_SPACE : paused = !paused;
     break;
   }
  }
}

