package gameFiles;

/**
 * interface for all obstacles in the game to follow.
 * @author Jason P'ng
 * @version v4.0 June 12th, 2014
 */
interface Obstacle
{
  /**
   * The score that should be rewarded when hit.
   */
  int getScore ();
  /**
   * The bounce factor a ball should experience when colliding.
   */
  float getBounce ();
}