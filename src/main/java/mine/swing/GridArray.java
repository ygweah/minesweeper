/**
 * This the collection of all the Grid.
 * It provides the methods to handle all Grid when user does something.
 * Note the convention: Grid[row][column]; row = y = height; column = x = width.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public final class GridArray extends JPanel implements MouseListener {

  //Private Instance Variable
  private Grid[][] grid;                              //array holding Grid
  private Dimension size;                             //physucal size of GridArray
  private int max_x, max_y, mines, empty, covered;    //dimension of Grid
  //numbers of Grid w/ mine, Grid w/o mine, Grid w/o mine covered
  //user succeeds when covered==0
  private boolean isAlive;                            //indicate if need to respond to user
  //private GameListener listener;                      //handling all the Timer, Counter
  //private Image offImg;                               //offscreen Image

  private final ArrayList listeners;

  //direction constants
  private final static Dimension NorthWest = new Dimension(-1, -1);
  private final static Dimension North = new Dimension(0, -1);
  private final static Dimension NorthEast = new Dimension(1, -1);
  private final static Dimension East = new Dimension(1, 0);
  private final static Dimension SouthEast = new Dimension(1, 1);
  private final static Dimension South = new Dimension(0, 1);
  private final static Dimension SouthWest = new Dimension(-1, 1);
  private final static Dimension West = new Dimension(-1, 0);
  private final static Dimension[] DIRECTIONS = {NorthWest, North, NorthEast, East, SouthEast, South, SouthWest, West};

  //Public Constructor
  public GridArray(int x, int y, int m) {
    listeners = new ArrayList(4);
    addMouseListener(this);
    setBorder(BorderFactory.createLoweredBevelBorder());
    init(x, y, m);
  }

  public void init(int x, int y, int m) {
    grid = new Grid[y][x];
    size = new Dimension(x * 16 + 4, y * 16 + 4);
    max_x = x;
    max_y = y;
    mines = m;
    empty = max_x * max_y - mines;
    //covered = empty;
    //isAlive = false;
    //create Grid
    for (int i = 0; i < max_y; ++i)
      for (int j = 0; j < max_x; ++j)
        grid[i][j] = new Grid(j, i, this);
    start();
    invalidate();
  }

  //Public Methods
  /*  Called to start the game
   *  Set up mine in the Grid randomly
   *  Change variable "surroundMine" in the neighbor Grid
   */
  public void start() {
    //This is a crude algorithm, in the future, permutation should be used instead
    Random random = new Random();       //randomly put mine into Grid
    int row, col;
    int temp_mine = mines;
    while (temp_mine > 0) {
      row = Math.round(random.nextFloat() * (max_y - 1));     //get valid row
      col = Math.round(random.nextFloat() * (max_x - 1));     //get valid column
      if (grid[row][col].hasMine)                         //already hasMine
        continue;
      setMine(row, col);                                   //add a mine to an empty Grid
      --temp_mine;
    }
    covered = max_x * max_y - mines;
    isAlive = true;
    fireGameEvent(new GameEvent(this, GameEvent.GAME_RESET));
  }

  public void reset() {
    Graphics g = getGraphics();
    for (int i = 0; i < max_y; ++i)         //reset Grid
      for (int j = 0; j < max_x; ++j)
        grid[i][j].reset(g);
    g.dispose();
    start();
  }

  public void addGameListener(GameListener listener) {
    listeners.add(listener);
  }

  public void removeGameListener(GameListener listener) {
    int index = listeners.indexOf(listener);
    if (index != -1)
      listeners.remove(index);
  }

  //Methods inherited from Component
  public Dimension getMinimumSize() {
    return size;
  }

  public Dimension getPreferredSize() {
    return size;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (int i = 0; i < max_y; ++i)
      for (int j = 0; j < max_x; ++j)
        grid[i][j].paint(g);
  }

  //Methods inherited from MouseListener
  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseClicked(MouseEvent e) {
  }

  //Note: mouseClicked can also be used, but response is not as good in game
  public void mouseReleased(MouseEvent e) {
    if (!isAlive) return;                           //need not respond
    Graphics g = getGraphics();
    int x = (e.getX() - 2) / 16;
    if (x < 0 || x >= max_x)
      return;
    int y = (e.getY() - 2) / 16;
    if (y < 0 || y >= max_y)
      return;
    if (e.getClickCount() == 2) {                     //short-cut clicks
      autoOpen(grid[y][x], g);
    } else {
      if (e.getClickCount() == 1) {                 //right click
        if (e.isMetaDown()) {
          mark(grid[y][x], g);
        } else {                                  //left click
          open(grid[y][x], g);
        }
      }
    }
    g.dispose();
  }

  //Protected Methods
  //called to set up a mine in a Grid if still empty
  //add mine to Grid, update "surroundMine" in neighbor Grids
  protected void setMine(int row, int col) {
    grid[row][col].hasMine = true;
    int nx, ny;
    for (int k = 0; k < DIRECTIONS.length; ++k) {
      ny = row + DIRECTIONS[k].height;
      nx = col + DIRECTIONS[k].width;
      if (nx < 0 || ny < 0 || nx >= max_x || ny >= max_y)
        continue;
      grid[ny][nx].addSurroundMine();
    }
  }

  //called to open a Grid
  protected void open(Grid target, Graphics g) {
    if (target.getMode() != Grid.CLOSED)      //need not respond if Grid is not in CLOSED mode
      return;

    if (target.hasMine) {                   //Grid has mine.  Game over.  Call method "lose()".
      target.setMode(Grid.DEAD, g);
      lose(g);
      return;
    }
    target.setMode(Grid.OPENED, g);              //good guess.
    if (covered == empty)                         //user first click, let listener start Timer.
      fireGameEvent(new GameEvent(this, GameEvent.GAME_START));
    --covered;                                  //decrement covered mine
    if (covered == 0) {                           //if all Grid w/o mine discovered, you win.  Call method "win()".
      win(g);
      return;
    }
    if (target.getSurroundMine() != 0)
      return;
    int nx, ny;                                  //situation no mine surrounding
    for (int k = 0; k < DIRECTIONS.length; ++k) {
      nx = target.getX() + DIRECTIONS[k].width;
      ny = target.getY() + DIRECTIONS[k].height;
      if (nx < 0 || ny < 0 || nx >= max_x || ny >= max_y)
        continue;
      open(grid[ny][nx], g);                   //open neighbor Grid until it is not empty
    }
  }

  //called when a Grid is marked/unmarked
  protected void mark(Grid target, Graphics g) {
    if (target.getMode() == Grid.CLOSED) {        //if unmarked Grid, mark it.  Also let listener do sth.
      target.setMode(Grid.MARKED, g);
      fireGameEvent(new GameEvent(this, GameEvent.MINE_MARK));
      return;
    }
    if (target.getMode() == Grid.MARKED) {        //if marked Grid, unmark it.  Also let listener do sth.
      target.setMode(Grid.CLOSED, g);
      fireGameEvent(new GameEvent(this, GameEvent.MINE_UNMARK));
      return;
    }
  }

  //called to make a short-cut open
  protected void autoOpen(Grid target, Graphics g) {
    if (target.getMode() != Grid.OPENED)          //check if Grid already open, required for short-cut click.
      return;
    int nx, ny;
    int n_marked = 0;
    for (int k = 0; k < DIRECTIONS.length; ++k) {
      nx = target.getX() + DIRECTIONS[k].width;
      ny = target.getY() + DIRECTIONS[k].height;
      if (nx < 0 || ny < 0 || nx >= max_x || ny >= max_y)
        continue;
      if (grid[ny][nx].getMode() == Grid.MARKED)
        ++n_marked;                         //get the total marked neighbor Grid
    }
    if (n_marked != target.getSurroundMine())     //should be same as "surroundMine" to proceed
      return;
    for (int k = 0; k < DIRECTIONS.length; ++k) {
      nx = target.getX() + DIRECTIONS[k].width;
      ny = target.getY() + DIRECTIONS[k].height;
      if (nx < 0 || ny < 0 || nx >= max_x || ny >= max_y)
        continue;
      open(grid[ny][nx], g);                   //open all neighbor Grids, may open one w/ mine
    }
  }

  //called when user loses
  protected void lose(Graphics g) {
    isAlive = false;                                    //game over and need not to respond to user anymore
    fireGameEvent(new GameEvent(this, GameEvent.GAME_LOSE));
    for (int i = 0; i < max_y; ++i)                         //clean up
      for (int j = 0; j < max_x; ++j)
        if (grid[i][j].getMode() == Grid.CLOSED) {
          if (grid[i][j].hasMine)
            grid[i][j].setMode(Grid.MINE, g);
          else
            ;//grid[i][j].setMode(Grid.OPENED,g);
        } else if (grid[i][j].getMode() == Grid.MARKED && !grid[i][j].hasMine)
          grid[i][j].setMode(Grid.CROSS, g);
  }

  //called when user wins
  protected void win(Graphics g) {
    isAlive = false;                                    //you win and that's it
    for (int i = 0; i < max_y; ++i)                         //clean up, different from method "lose()"
      for (int j = 0; j < max_x; ++j)
        if (grid[i][j].getMode() == Grid.CLOSED)
          grid[i][j].setMode(Grid.MARKED, g);
    fireGameEvent(new GameEvent(this, GameEvent.GAME_WIN));     //listener should know
  }

  protected void fireGameEvent(GameEvent e) {
    Object[] listener = listeners.toArray();
    int i = 0;
    switch (e.id) {
      case GameEvent.GAME_RESET:
        for (; i < listener.length; ++i)
          ((GameListener) listener[i]).gameReset(e);
        break;
      case GameEvent.GAME_START:
        for (; i < listener.length; ++i)
          ((GameListener) listener[i]).gameStarted(e);
        break;
      case GameEvent.MINE_MARK:
        for (; i < listener.length; ++i)
          ((GameListener) listener[i]).mineMarked(e);
        break;
      case GameEvent.MINE_UNMARK:
        for (; i < listener.length; ++i)
          ((GameListener) listener[i]).mineUnmarked(e);
        break;
      case GameEvent.GAME_WIN:
        for (; i < listener.length; ++i)
          ((GameListener) listener[i]).gameWon(e);
        break;
      case GameEvent.GAME_LOSE:
        for (; i < listener.length; ++i)
          ((GameListener) listener[i]).gameLost(e);
        break;
      default:
    }
  }
}
