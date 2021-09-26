/**
 * This is the basic element on the game board of Minesweeper.
 * The Grid has a fixed size.
 * It may or may not contain mine, as indicated by "hasMine" variable.
 * It may be surrounded by up to 8 mines, indicated by "surroundMine" variable.
 * It has several display modes, depended on its state and the state of game.
 * The display mode is intended to be changed by the component.
 * The position of the Grid on the game board was indicated by "x,y" variables.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.swing;

import java.awt.*;

public final class Grid {
  //Protected Static Variable
  protected static Dimension size = new Dimension(16, 16);     //Grid's size
  protected static Point offset = new Point(2, 2);

  //display mode constants
  public static final int CLOSED = 1;         //Grid in the original closed state
  public static final int OPENED = 2;         //Grid w/o mine clicked open
  public static final int MARKED = 3;         //Grid marked having mine
  public static final int MINE = 4;         //Grid opened showing un-marked mine after game ends
  public static final int CROSS = 5;         //Grid opened showing no mine when empty Grid marked after game ends
  public static final int DEAD = 6;         //Grid w/ mine clicked by user

  // Protected Instance Variable
  protected boolean hasMine;                  //true if Grid contains mine

  // Private Instance Variable
  private int mode;                           //indicate display mode
  private int surroundMine;                   //the number of mines surrounding the Grid, maximum is 8
  private final int x;
  private final int y;                           //position for Grid[y][x] on the game board
  private final int x_;
  private final int y_;
  private final Component component;                //the awt component Grid will draw on

  //Public Constructor
  public Grid(int x, int y, Component component) {
    super();
    hasMine = false;
    mode = CLOSED;
    surroundMine = 0;
    this.x = x;
    this.y = y;
    x_ = x * size.width + offset.x;
    y_ = y * size.height + offset.y;
    this.component = component;
  }

  //Public Methods
  public int getMode() {
    return mode;
  }

  public synchronized void setMode(int m, Graphics g) {
    mode = m;
    paint(g);
  }

  public int getSurroundMine() {
    return surroundMine;
  }

  public synchronized void addSurroundMine() {
    ++surroundMine;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void paint(Graphics g) {
    switch (mode) {
      case OPENED:
        ImageSet.ICONS[ImageSet.ZERO + surroundMine].paintIcon(component, g, x_, y_);
        break;
      case CLOSED:
        ImageSet.ICONS[ImageSet.CLOSED].paintIcon(component, g, x_, y_);
        break;
      case MARKED:
        ImageSet.ICONS[ImageSet.MARKED].paintIcon(component, g, x_, y_);
        break;
      case DEAD:
        ImageSet.ICONS[ImageSet.DEAD].paintIcon(component, g, x_, y_);
        break;
      case MINE:
        ImageSet.ICONS[ImageSet.MINE].paintIcon(component, g, x_, y_);
        break;
      case CROSS:
        ImageSet.ICONS[ImageSet.CROSS].paintIcon(component, g, x_, y_);
        break;
      default:
        throw new RuntimeException("Invalid Grid Mode");
    }
  }

  //Reset Grid to initial state
  public void reset(Graphics g) {
    hasMine = false;
    surroundMine = 0;
    if (mode != CLOSED) {
      mode = CLOSED;
      paint(g);
    }
  }

}