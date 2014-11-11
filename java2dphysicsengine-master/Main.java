import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
  
import java.util.ArrayList;
import javax.swing.JFrame;

public class Main extends JFrame
{
  public static final int MAX_SPAWN = 30;
  public static final int X = 640;
  public static final int Y = 480;
  public static final double GRAVITY = 1500;
  public static final double DRAG = 0.2;
  public static final double BOUNCE = 0.9;
  public static final String TITLE = "Mike's 2D Physics Engine";
  //private static JFrame f;
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

  public static void main(String[] args)
  {
    // Initialize some things.
    new Main ();
    
    // Create and start threads.
    Thread moveEngine = new MoveEngine();
    moveEngine.start();
    //Thread makeBall = new MakeBall();
    //makeBall.start();
    // Run the animation loop.
    for (int x = 0; x < 2;x++){
    giveBirth(x * 100, 1, Math.random() * 1000.0,
              Math.random() * 1000.0, 100);}
    living.add (new TriangleSpawn (500,50));
    living.add (new RectangleSpawn(300,50));
    runAnimation();
  }

  public static void runAnimation()
  {
    // Set up some variables.
    int fps = 0;
    int frames = 0;
    long totalTime = 0;
    long curTime = System.currentTimeMillis();
    long lastTime = curTime;
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
        // clear back buffer...
        g2d = buffer.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, X, Y);
        // Draw entities
        for (int i = 0; i < living.size(); i++) {
          at = new AffineTransform();
          at.translate(living.get(i).getX(), living.get(i).getY());
          g2d.setColor(Color.BLACK);
          Spawn s = living.get(i);
          g2d.fill(s.shape);
        }
        // display frames per second...
        g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
        g2d.setColor(Color.GREEN);
        g2d.drawString(String.format("FPS: %s", fps), 20, 20);
        g2d.drawString(living.get(0).vx() + ", " + living.get(0).vy(),20,50);
        // Blit image and flip...
        graphics = b.getDrawGraphics();
        graphics.drawImage(buffer, 0, 0, null);
        if (!b.contentsLost()) b.show();
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

  public Main ()
  {
    // Create the frame...
    super(TITLE);
    setIgnoreRepaint(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Create canvas for painting...
    c = new Canvas();
    c.setIgnoreRepaint(true);
    c.setSize(X, Y);
    
    // Add the canvas, and display.
    add(c);
    pack();
    // The following line centers the window on the screen.
    setLocationRelativeTo(null);
    setVisible(true);
    // Set up the BufferStrategy for double buffering.
    c.createBufferStrategy(2);
    addKeyListener(new TAdapter());
    b = c.getBufferStrategy();
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
  
  private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            living.get (0).keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            living.get (0).keyPressed(e);
        }
    }
}
