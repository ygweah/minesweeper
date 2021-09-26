package mine.swing;

import java.util.EventListener;

public interface GameListener extends EventListener {
  void gameStarted(GameEvent e);

  void gameWon(GameEvent e);

  void gameLost(GameEvent e);

  void gameReset(GameEvent e);

  void mineMarked(GameEvent e);

  void mineUnmarked(GameEvent e);
}
