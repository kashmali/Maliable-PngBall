import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.*;
import Files.Current.physicsEngine.*;

public class TestPanel extends JPanel
{
  public Paddle paddle;
  public TestPanel ()
  {
    setSize(800, 600);
    setBackground (Color.WHITE);
    // Add the canvas, and display.
    setVisible(true);
    // Set up the BufferStrategy for double buffering.
    setDoubleBuffered(true);
  }
  
  public void paint (Graphics g)
  {
    super.paint (g);
   Graphics2D g2d = (Graphics2D) g;
   g2d.setColor(Color.BLACK);
          //g2d.fill(paddle.getLine());
          Toolkit.getDefaultToolkit().sync();
        g.dispose ();
  }
  
  public Paddle getPaddle()
  {
    return paddle;
  }
}