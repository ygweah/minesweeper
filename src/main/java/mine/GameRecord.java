/**
 * Game recod extend GameDimansion
 * Including time (best score) and name of player
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine;

public class GameRecord extends GameDimension {

  //Public Instance Variables
  public String name;
  public int time;

  //Constants
  public static final int MAX_TIME = 999;
  public static final String DEFAULT_USER = "Anonymous";

  //Public Constructors
  public GameRecord(String name, int time, GameDimension gameDimension) {
    super(gameDimension);
    this.name = name;
    this.time = time;
  }

  public GameRecord(GameRecord gameRecord) {
    this(gameRecord.name, gameRecord.time, gameRecord);
  }

  public GameRecord(GameDimension gameDimension) {
    this(DEFAULT_USER, MAX_TIME, gameDimension);
  }

  //Public Method
  //reset time and name
  public void reset() {
    time = MAX_TIME;
    name = DEFAULT_USER;
  }

}
