package mine.swing;

public interface GameListener extends java.util.EventListener {
  void gameStarted(GameEvent e);

  void gameWon(GameEvent e);

  void gameLost(GameEvent e);

  void gameReset(GameEvent e);

  void mineMarked(GameEvent e);

  void mineUnmarked(GameEvent e);
}
