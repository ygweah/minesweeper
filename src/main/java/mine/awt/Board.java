/**
 * A basic window with all the components except menu.
 * It is fully functional.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Properties;
import mine.awt.custom.Counter;
import mine.awt.custom.ThreeD_Panel;
import mine.awt.custom.Timer;


public class Board extends Frame implements GameListener, MouseListener {

  //Private Instance Variables
  private ImageSet imageSet;
  private final BestRecord bestRecord;
  private AnimatedButton button;
  private Timer timer;
  private GridArray gridArray;
  private Counter mineCounter;

  //Private Constant
  private static final String RECORD_FILE = "BestRec";

  //Public Constructor
  public Board() {
    super("Minesweeper");
    setResizable(false);

    createImage();

    //set up the best time
    bestRecord = new BestRecord(RECORD_FILE);
    init(getCurrent());
  }

  //Public Methods
  public void init(GameRecord gameRecord) {
    gridArray = new GridArray(gameRecord.width, gameRecord.height, gameRecord.mines, this);
    mineCounter = new Counter(0, GameDimension.MAX_MINES, gameRecord.mines, 3);
    timer = new Timer(0, GameRecord.MAX_TIME, 0, 3);
    button = new AnimatedButton(this);

    Panel panelA = new Panel();
    panelA.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridwidth = 1;
    c.gridheight = 1;
    c.weightx = 0.5;
    c.weighty = 1.0;
    c.insets = new Insets(2, 2, 2, 2);
    c.fill = GridBagConstraints.NONE;
    c.anchor = GridBagConstraints.WEST;
    c.gridx = 0;
    c.gridy = 0;
    panelA.add(mineCounter, c);
    c.weightx = 0.0;
    c.anchor = GridBagConstraints.CENTER;
    c.gridx = 1;
    c.gridy = 0;
    panelA.add(button, c);
    c.weightx = 0.5;
    c.anchor = GridBagConstraints.EAST;
    c.gridx = 2;
    c.gridy = 0;
    panelA.add(timer, c);

    ThreeD_Panel threeD = new ThreeD_Panel();
    threeD.add("North", panelA);
    threeD.add("Center", gridArray);
    threeD.addMouseListener(this);

    add(threeD);
    addNotify();

    game();
  }

  //Methods implemented from interface GameListener
  public void game(GameRecord gameRecord) {
    gridArray.init(gameRecord.width, gameRecord.height, gameRecord.mines);
    mineCounter.reset(0, GameDimension.MAX_MINES, gameRecord.mines, 3);

    game();
    pack();
    show();
  }

  public void game() {    //start components
    button.up();
    gridArray.reset();
    timer.setRunning(false);
    timer.reset();
    mineCounter.reset();
  }

  public void startTimer() {
    timer.reset();
    timer.setRunning(true);
  }

  public void mark() {
    mineCounter.decrement();
  }

  public void unmark() {
    mineCounter.increment();
  }

  public void lose() {
    timer.setRunning(false);
    button.lose();
  }

  public void win() {
    timer.setRunning(false);
    button.win();
    mineCounter.reset();
    if (!bestRecord.contains(getCurrent()))     //not a standard setting
      return;
    if (timer.getCount() >= getCurrent().time)    //not good enough
      return;
    getCurrent().time = timer.getCount();       //set new record
    new InputBestTimeDialog(this);
  }

  //other help methods
  public void exit() {
    bestRecord.outputBestRecord();              //save reocrds
  }

  public BestRecord getBestRecord() {
    return bestRecord;
  }

  public GameRecord getCurrent() {
    return bestRecord.current;
  }

  public String getCurrentLevel() {
    return bestRecord.current.level;
  }

  //Methods inherited from MouseListener
  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
    button.ask();
  }

  public void mouseReleased(MouseEvent e) {
    button.reset();
  }

  public void mouseClicked(MouseEvent e) {
  }

  private void createImage() {
    Properties properties = new Properties();
    try {
      properties.load(getClass().getResourceAsStream("/mine/icon.cfg"));
    } catch (IOException e) {
    }
    imageSet = new ImageSet(properties);
  }

}
