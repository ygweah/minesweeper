/**
 * Setting for the game
 * Including width, height, level and number of mines
 * Savable to file.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt;

import java.awt.Dimension;
import java.io.Serializable;

public class GameDimension extends Dimension implements Serializable {

  //Public Instance Variables
  public String level;
  public int mines;

  //Constants
  public static final GameDimension BEGINNER = new GameDimension("Beginner", 8, 8, 10);
  public static final GameDimension INTERMEDIATE = new GameDimension("Intermediate", 16, 16, 40);
  public static final GameDimension EXPERT = new GameDimension("Expert", 30, 16, 99);

  public static final int MIN_WIDTH = 8;
  public static final int MIN_HEIGHT = 1;
  public static final int MAX_MINES = 999;
  private static final float SPACE_FACTOR = 0.5f;

  //Public Constructors
  public GameDimension(String level, int width, int height, int mines) {
    super(width, height);
    this.level = level;
    if (this.width < MIN_WIDTH)
      this.width = MIN_WIDTH;
    if (this.height < MIN_HEIGHT)
      this.height = MIN_HEIGHT;
    if (mines > width * height * SPACE_FACTOR)
      this.mines = (int) (width * height * SPACE_FACTOR);     //max no. mines is limited by game Board size
    else
      this.mines = mines;
    if (this.mines > MAX_MINES)
      this.mines = MAX_MINES;
  }

  public GameDimension(GameDimension gameDimension) {
    this(gameDimension.level, gameDimension.width, gameDimension.height, gameDimension.mines);
  }

  public GameDimension() {
    this("", MIN_WIDTH, MIN_HEIGHT, 0);
  }

}
