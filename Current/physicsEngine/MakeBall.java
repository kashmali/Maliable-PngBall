package Files.Current.physicsEngine;

public class MakeBall extends Thread implements Pausable
{
  private GameEngine main;
  
  public MakeBall (GameEngine main)
  {
    this.main = main;
  }
  
  public void run()
  {
    while (main.isRunning) 
    {
      if (main.isPaused())
        pauseThread();
      main.giveBirth(1, 1,(float) (Math.random() * 1000.0),(float)(Math.random() * 1000.0), 100);
      
      try 
      {
        sleep(500);
      } 
      catch (InterruptedException e) 
      {
      }
    }
  }
  
  public void pauseThread ()
  {
    while (main.isPaused())
    {
      try
      {
     Thread.sleep (1);
      }
      catch (InterruptedException e)
      {
        
      }
    }    
  }
}
