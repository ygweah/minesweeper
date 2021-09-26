package mine.swing;

import java.util.EventObject;

public class GameEvent extends EventObject {
  public static final int GAME_START = 0;
  public static final int GAME_WIN = 1;
  public static final int GAME_LOSE = 2;
  public static final int GAME_RESET = 3;
  public static final int MINE_MARK = 4;
  public static final int MINE_UNMARK = 5;

  public final int id;

  public GameEvent(Object source, int id) {
    super(source);
    this.id = id;
  }

}
        