/**
 * Setting for the game
 * Including width, height, level and number of mines
 * Savable to file.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine;

import java.io.Serializable;

public class GameDimension implements Serializable {

  //Public Instance Variables
  public final String level;
  public final int width, height;
  public final int mines;

  //Constants
  public static final GameDimension BEGINNER = new GameDimension("Beginner", 8, 8, 10);
  public static final GameDimension INTERMEDIATE = new GameDimension("Intermediate", 16, 16, 40);
  public static final GameDimension EXPERT = new GameDimension("Expert", 30, 16, 99);

  public static final int MIN_WIDTH = 8;
  public static final int MIN_HEIGHT = 1;
  public static final int MAX_MINES = 999;

  //Public Constructors
  public GameDimension(String level, int width, int height, int mines) {
    this.level = level;
    if (width < MIN_WIDTH)
      width = MIN_WIDTH;
    if (height < MIN_HEIGHT)
      height = MIN_HEIGHT;
    if (mines > MAX_MINES)
      mines = MAX_MINES;
    this.width = width;
    this.height = height;
    this.mines = mines;
  }

  public GameDimension(GameDimension gameDimension) {
    this(gameDimension.level, gameDimension.width, gameDimension.height, gameDimension.mines);
  }

  public static GameDimension getByName(String name) {
    if (name.equalsIgnoreCase("Beginner"))
      return GameDimension.BEGINNER;
    else if (name.equalsIgnoreCase("Intermediate"))
      return GameDimension.INTERMEDIATE;
    else if (name.equalsIgnoreCase("Expert"))
      return GameDimension.EXPERT;
    else
      return null;
  }
}
