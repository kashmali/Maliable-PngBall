public class ZPngBallRunner
{
  public static void main (String [] args)
  {
    Window w = new Window ();
    w.runProgram ();
  }
}
//code organization
//  Circle spawn and spawn need to be merged
//  each obstacle should have their own bounce factor
//  getRadius should be used in the paintComponent 
//  redundant code should be eliminated
//  put the KeyListener methods in the right place