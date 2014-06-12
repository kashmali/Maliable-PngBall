package gameFiles;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.geom.*;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
 * The main class to run the gameplay. This bridges the Window and the moveEngine. <br>
 * This is also where all obstacles are found and gameplay settings can be altered.<br>
 * The core structure of this class was a combination of Mike's code and Chua's code.
 * Most of the code is written by Jason P'ng.
 * @author Jason P'ng
 * @author Mike aka Wilkystyles
 * @author Hock-Chuan Chua
 * @version v4.0 June 12th, 2014
 */
public class GameEngine extends JPanel
{
  /**
   * The maximum amount of balls allowed in the game at once. May be used when more balls are added.
   */
  public static final int MAX_SPAWN = 20;
  /**
   * The amount of frames per second. This alters the speed of the game.
   */
  public final int UPDATE_RATE = 60;
  /**
   * The balls active in the game.
   */
  public static ArrayList<Ball> living = new ArrayList<Ball>();
  /**
   * The button obstacles in the game.
   */
  public static ArrayList<ButtonObstacle> buttons = new ArrayList<ButtonObstacle>();
  /**
   * The line obstacles present in the game.
   */
  public static ArrayList<ObstacleLine> lines = new ArrayList<ObstacleLine>();
  /**
   * The paddles present in the game.
   */
  public static ArrayList<PseudoPaddle> pseudoPaddles = new ArrayList<PseudoPaddle>();
  /**
   * Determines if the ball is in play.
   */
  public static boolean isRunning = true;
  /**
   * Determines what the current amount of frames per second is.
   */
  private int fps;
  /**
   * Determines if the game is paused.
   */
  public static boolean paused = false;
  /**
   * Determines if there has been a call to exit the game.
   */
  public static boolean terminated = false;
  /**
   * Determines if the pause window is open.
   */
  public static boolean open = false;
  /**
   * Determines if the game has started or not. This becomes true when the user presses space
   */
  public boolean started = false;
  /**
   * The score the the current game.
   */
  public static int score = 0;
  /**
   * The current layout in the game. It can be easy, medium, or hard.
   */
  public static int layout;
  /**
   * The difficulty setting in the game. It can be easy, medium, or hard.
   */
  public static int difficulty = 0;
  
  /**
   * The moveEngine used by the game engine to determine where the current objects should be.
   */
  private MoveEngine moveEngine;
  /**
   * Manages the scores in the game to determine if a score belongs in the top 10 scores.
   */
  public  HighScoreManager highscoreManager;
  
  /**
   * Represents an easy layout. Used for ease of reading.
   */
  public static final int EASY_LAYOUT = 0;
  /**
   * Represents an medium layout. Used for ease of reading.
   */
  public static final int MEDIUM_LAYOUT = 1;
  /**
   * Represents an hard layout. Used for ease of reading.
   */
  public static final int HARD_LAYOUT = 2;
  
  /**
   * Represents an easy difficulty setting. Used for ease of reading.
   */
  public static final int EASY = 0;
  /**
   * Represents an medium difficulty setting. Used for ease of reading.
   */
  public static final int MEDIUM= 1;
  /**
   * Represents an hard difficulty setting. Used for ease of reading.
   */
  public static final int HARD = 2;
  
  /**
   * The main constructor for the game engine. This initalizes the panel and adds necessary components.
   */
  public GameEngine ()
  {
    // Create canvas for painting...
    //setIgnoreRepaint(true);
    setSize(500, 500);
    setBackground (Color.WHITE);
    // Add the canvas, and display.
    setVisible(true);
    // Set up the BufferStrategy for double buffering.
    setDoubleBuffered(true);
    moveEngine = new MoveEngine(this);
    // Objects needed for rendering...
    initialize();
    highscoreManager = new HighScoreManager();
  }
  
  /**
   * Initalizes the current layout to have basic objects required in every layout.<br>
   * This includes one ball, 3 walls, 2 slopes, and one paddle.
   */
  public void initialize ()
  {
    //The ball
    living.add (new Ball (16,16,0,0));
    
    //walls
    lines.add (new ObstacleLine (0,0,0,550,Color.BLACK));
    lines.add (new ObstacleLine (396,0,396,550,Color.BLACK));
    lines.add (new ObstacleLine (0,0,400,0,Color.BLACK));
    
    //slopes to the paddle
    lines.add (new ObstacleLine (0,400,100,500,Color.BLACK));
    lines.add (new ObstacleLine (400,400,300,500,Color.BLACK));
    
    //The paddle
    pseudoPaddles.add (new PseudoPaddle (150,500,100,Color.BLACK)); 
  }
  
  /**
   * Clears all arrays of objects. This is used to elminate previous layouts in order to apply a new one.
   */
  public void reset ()
  {
    living.clear ();
    buttons.clear();
    lines.clear();
    pseudoPaddles.clear();
  }
  
  /**
   * Adds the objects for an easy layout setting.
   */
  public void easyLayout ()
  {   
    //Button
    buttons.add (new ButtonObstacle (50,200,15,1.1f,Color.GREEN));
    buttons.add (new ButtonObstacle (150,200,15,1.1f,Color.RED));
    buttons.add (new ButtonObstacle (250,200,15,1.1f,Color.BLUE));
    buttons.add (new ButtonObstacle (350,200,15,1.1f,Color.MAGENTA));
    layout = EASY_LAYOUT;
  }
  
  /**
   * Adds the objects for an medium layout setting.
   */
  public void mediumLayout ()
  {
    //   Center obstacle
    lines.add (new BumperObstacleLine (100,200,300,200));
    lines.add (new ObstacleLine (100,200,200,300,Color.BLACK));
    lines.add (new ObstacleLine (200,300,300,200,Color.BLACK));
    layout = MEDIUM_LAYOUT;
  }
  
  /**
   * Adds the objects for an hard layout setting.
   */
  public void hardLayout ()
  {
    buttons.add (new ButtonObstacle (200,200,30,1.1f,Color.CYAN));
    lines.add (new BumperObstacleLine (200,150,250,200));
    lines.add (new BumperObstacleLine (200,150,150,200));
    layout = HARD_LAYOUT;
  }
  
  /**
   * Sets the game layout. This clears the old one, initalizes the board, and adds objects for a new layout. <br>
   * switch statement distinguishes which layout should be applied.
   * 
   * @paran int layout determines which layout is to be applied to the game engine.
   */
  public void setGameLayout (int layout)
  {
    reset ();
    initialize ();
    switch (layout){
      case EASY_LAYOUT : easyLayout();
      break;
      case MEDIUM_LAYOUT : mediumLayout ();
      break;
      case HARD_LAYOUT : hardLayout ();
      break;
    }      
  }
  
  /**
   * Returns a String manifestation of the current layout. This is used to the information panel. <br>
   * Switch statement distinguishes between layouts to return the appropriate String.
   * 
   * @return a String representing the current layout.
   */
  public static String getLayoutAsString ()
  {
    switch (layout){
      case EASY_LAYOUT : return "Level 1";
      case MEDIUM_LAYOUT : return "Level 2";
      case HARD_LAYOUT : return "Level 3";
      default :return "Can't be found";
    }  
  }
  
  /**
   * Sets the difficulty setting of the game. It can be easy, medium, or hard.<br>
   * This will determine from which set of questions the question panel will show. <br>
   * Switch statement determines how to set the difficulty according the parameter.
   * 
   * @param int difficulty The difficulty setting to be applied.
   */
  public void setGameDifficulty (int difficulty)
  {
    switch (difficulty){
      case EASY : this.difficulty = EASY;
      break;
      case MEDIUM: this.difficulty = MEDIUM;
      break;
      case HARD : this.difficulty = HARD;
      break;
    }      
  }
  
  /**
   * Returns a String manifestation of the current difficulty. This is used to the information panel. <br>
   * Switch statement distinguishes between difficulties to return the appropriate String.
   * 
   * @return a String representing the current difficulty.
   */
  public static String getDifficultyAsString ()
  {
    switch (difficulty){
      case EASY : return "Easy";
      case MEDIUM : return "Medium";
      case HARD : return "Hard";
      default :return "Can't be found";
    }  
  }
  
  /**
   * This is the main methods that controls the game. The flow of the game will be interrupted if the ball falls in <br>
   * the gutter, if the user pauses the game, or the game is terminated. <br>
   * While (isRunning) - the game loop while the ball is moving <br>
   * if (terminated) - call to stop the game <br>
   *  if (!paused && started) - If the game is paused or hasn't started the ball should not move. <br>
   * if (living.get(0).getY() > 590) - the ball is in the gutter at this point. <br>
   * if (timeLeftMillis < 5) - Ensure a minimum time is waited. <br>
   * 
   * @param beginTimeMillis - the time when the game loop starts
   * @param timeTakenMillis - the time for the loop to cycle through
   * @param timeLeftMillis - the time left over from the set amount of time according to the update rate.
   */
  public void gamerun ()
  {
    isRunning = true;
    // Start the loop.
    while (isRunning) {
      long beginTimeMillis, timeTakenMillis, timeLeftMillis;
      beginTimeMillis = System.currentTimeMillis();
      
      //Disrupting the game
      if (terminated){       
        break;
      }
      if (!paused && started){
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
      
    }
    //reset the ball
    living.get (0).updatePos (16,16);
  }
  
  /**
   * Overriden method to paint graphics on the panel. This includes painting of all objects and String on the game panel. <br>
   * for loops are used to traverse through each object array and paint all objects in them. <br>
   * if (isPaused()) - finds if the game is paused so \"game paused\" is painted on the game panel. <br>
   * if (isRunning == false) - determines if the ball is in the gutter so a red x is temporarily painted.
   * 
   * @param Graphics g graphics being used to paint the panel
   */
  @Override
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.setColor (Color.BLACK);
    g.drawString ("Score: " + Integer.toString (score),40,20);
    for (Ball s : living) 
    {
      g.fillOval ((int)(s.getX() - s.getRadius()),(int)(s.getY() - s.getRadius()),(int)(s.getRadius() * 2),(int)(s.getRadius() * 2));
    }
    for (PseudoPaddle p : pseudoPaddles){
      g.drawLine ((int)p.getX(),(int)p.getY(),(int)p.getX2(),(int)p.getY2());}
    for (ObstacleLine l :lines){
      g.setColor (l.getColour ());
      g.drawLine ((int)l.getX(),(int)l.getY(),(int)l.getX2(),(int)l.getY2());}
    for (ButtonObstacle b : buttons){
      g.setColor (b.getColour());
      g.fillOval ((int)(b.getX() - b.getRadius()),(int)(b.getY() - b.getRadius()),(int)(b.getRadius() * 2),(int)(b.getRadius() * 2));
    }
    if (isPaused())
    {
    g.setColor (Color.BLACK);
      g.drawString ("Paused game", 40,40);
    }
    
    if (isRunning == false)
    {
      g.setColor (Color.RED);
      g.drawLine (0,0,400,550);
      g.drawLine (0,550,400,0);
    }
    Toolkit.getDefaultToolkit().sync();
    g.dispose ();
  }
  
  /**
   * Returns if there are no balls active in the game.
   * 
   * @return if there is at least one ball active in the game.
   */
  public static boolean allDead()
  {
    if (living.size() < 1) return true;
    return false;
  }
  
  /**
   * Returns if the game has been paused.
   * 
   * @return if whether or not the game is in a paused state.
   */
  public boolean isPaused ()
  {
    return paused; 
  }
  
  /**
   * Sets the terminated variable to end or revive the game.
   * 
   * @param boolean state the state of termination the game is set to.
   */
  public void setTerminated (boolean state)
  {
    terminated = state;
  }
  
  /** 
   * Returns the state of termination the game is in.
   * 
   * @return whether or not the game has been terminated.
   */
  public static boolean isTerminated ()
  {
    return terminated;
  }
  
  /**
   * Sets if the game has started or not.
   * 
   * @oaram boolean state the state of being started the game is set to.
   */
  public void setStarted (boolean state)
  {
    started = state;
  }
  
  /**
   * Pauses the game if it is running. It will open a new pause window to display a pause menu.<br>
   * if (isRunning) - Checks if the game is running so the game isn't paused before it starts.
   * if (open == false) - Checks if the pause window is already opened.
   */
  public void pause ()
  {
    if (isRunning)
    {
      paused = true;
      if (open == false)
    {
      new PauseWindow ();      
      open = true;
    }
    }
  }
  
  /**
   * Increases the game score by the amount in the parameters.
   * 
   * @param int increment the amounts the score is increased by.
   */
  public static void increaseScore (int increment)
  {
    score += increment;
  }
  
  /**
   * Accessor method for the score of the game.
   * 
   * @return the current score of the game.
   */
  public int getScore ()
  {
    return score;
  }
  
  /**
   * Resets varables of the game to be played again.
   */
  public void resetGame ()
  {
    terminated = false;
    score = 0;
  }
  
/**
 * The super secret cheat to get a high score. This is a cheat line that blocks the gutter. It is activated with 'x' and removed with 'z'.
 */
  private ObstacleLine cheat = new ObstacleLine (0,510,400,510,Color.BLACK);
  
  /**
   * Method for the keylistener in the main window. This allows for pausing with 'p' and starting with space. It also <br>
   * extends to call the keyPressed method in the PseudoPaddle class to move it. <br>
   * Switch statement distinguished between different keys.<br>
   * For loop runs through all paddles to move all of them.
   * 
   * @param KeyEvent e The key pressed to trigger this method to be called.
   * @param int key Represents the value of the key event to recognize which key was presssed.
   */
  public void keyPressed (KeyEvent e)
  {
    int key = e.getKeyCode ();
    switch (key){
      case KeyEvent.VK_P : 
        pause();
        break;
      case KeyEvent.VK_X : 
        if (!lines.contains (cheat))
        lines.add (cheat);
        break;
      case KeyEvent.VK_Z :
        lines.remove (cheat);
        break;
        //Uncomment for an easy way to exit the game
//      case KeyEvent.VK_ENTER :
//        terminated = true;      
//        break;
      case KeyEvent.VK_SPACE :
        started = true;
        break;
    }
    for (PseudoPaddle p : pseudoPaddles)
      p.keyPressed (e);
  }

}