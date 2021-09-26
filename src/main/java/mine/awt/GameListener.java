/**
 * Interface for the game Board
 * Providing functions called by GridArray to paly the game.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt;

public interface GameListener {
  //start a new game with new level
  void game(GameRecord gameRecord);        //eventually call method game()

  //start a new game with current settings
  void game();                             //called everytime to replay

  //activate the timer when first time user left clicks a closed Grid w/o mine
  void startTimer();                       //Timer start to count up

  //user right clicks a closed Grid (w/ or w/o mine)
  void mark();

  //user right clicks a marked Grid to cancel the mark
  void unmark();

  /*game is over when
      1. user left clicks a closed Grid w/ mine
      2. user makes short-cut clicks, and hits the mine */
  void lose();

  //all Grid w/o mine are open
  void win();

}
