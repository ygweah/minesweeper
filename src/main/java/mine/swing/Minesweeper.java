/*  GUI class, also contains main() methods.
 *
 *  @author Yuhong Guo
 *  @version 3.5
 */

package mine.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Timer;
import mine.BestRecord;
import mine.GameDimension;

public class Minesweeper extends JFrame implements GameListener {
  //Private Instance Variables
  private final JMenuItem newMenuItem = new JMenuItem("New");
  private final JRadioButtonMenuItem beginnerMenuItem = new JRadioButtonMenuItem("Beginner");
  private final JRadioButtonMenuItem intermediateMenuItem = new JRadioButtonMenuItem("Intermediate");
  private final JRadioButtonMenuItem expertMenuItem = new JRadioButtonMenuItem("Expert");
  private final JRadioButtonMenuItem customMenuItem = new JRadioButtonMenuItem("Custom...");
  private final JMenuItem besttimeMenuItem = new JMenuItem("Best Time...");
  private final JMenuItem exitMenuItem = new JMenuItem("Exit");
  private final JMenuItem contentMenuItem = new JMenuItem("Contents");
  private final JMenuItem aboutMenuItem = new JMenuItem("About");
  private final ButtonGroup levelGroup = new ButtonGroup();

  private ActionListener newGameListener;
  private ImageSet imageSet;
  private final BestRecord bestRecord;
  private GameDimension customGame;
  private final GridArray gridArray;
  private final Counter mineCounter;
  private final Counter timeCounter;
  private final Timer timer;
  private final ImageButton imageButton;

  private boolean isFirstTime;
  private boolean isCustomGame;
  private final String recordFile;
  private String prevLevel;
  private final String aboutString;
  private final String contentString;

  //Public Constructor
  public Minesweeper() {
    super("Minesweeper");

    createImage();
    createMenu();

    recordFile = "best.record";
    bestRecord = new BestRecord();
    bestRecord.load(recordFile);
    prevLevel = bestRecord.current.level;
    gridArray = new GridArray
        (bestRecord.current.width, bestRecord.current.height, bestRecord.current.mines);
    gridArray.addGameListener(this);
    mineCounter = new Counter(0, bestRecord.current.mines, bestRecord.current.mines, 3);
    timeCounter = new Counter(0, 999, 0, 3);
    timer = new Timer(1000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        timeCounter.increment();
      }
    });
    imageButton = new ImageButton();
    isFirstTime = true;
    isCustomGame = false;
    aboutString = "<html><table>" +
        "<tr><td>Java Minesweeper Version <em>4.0</em></td></tr>" +
        "<tr><td>Author <em>Yuhong Guo</em></td></tr></table><hr>" +
        "<table><tr><td>This product is used by: <em>" +
        System.getProperty("user.name") +
        "</em></tr></td><tr><td>Operating System:  <em>" +
        System.getProperty("os.name") +
        "</em></tr></td><tr><td>JVM Version:  <em>" +
        System.getProperty("java.version") +
        "</em></tr></td></table></html>";
    contentString = getContent("/mine/swing.html");

    if (prevLevel.equalsIgnoreCase("Beginner"))
      beginnerMenuItem.setSelected(true);
    else if (prevLevel.equalsIgnoreCase("Intermediate"))
      intermediateMenuItem.setSelected(true);
    else if (prevLevel.equalsIgnoreCase("Expert"))
      expertMenuItem.setSelected(true);
    else
      customMenuItem.setSelected(true);

    setResizable(false);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exit();
      }
    });

    createComponent();
    createListener();
    pack();
    show();
  }

  //Public Methods
  public void gameStarted(GameEvent e) {
    if (isFirstTime) {
      timer.start();
      isFirstTime = false;
    } else if (!timer.isRunning())
      timer.restart();
  }

  public void gameWon(GameEvent e) {
    timer.stop();
    mineCounter.reset();
    imageButton.setMode(ImageButton.HAPPY);
    if (bestRecord.current.time > timeCounter.getCount()) {
      String msg = "<html>Congratulations!<p>" +
          "You are the fastest " +
          bestRecord.current.level.toLowerCase() +
          " player.<p>" +
          "Please enter your name:<p></html>";
      String name = JOptionPane.showInputDialog(this, msg, "Player Name", JOptionPane.QUESTION_MESSAGE);
      if (name != null) {
        bestRecord.current.name = name;
        bestRecord.current.time = timeCounter.getCount();
      }
    }
  }

  public void gameLost(GameEvent e) {
    timer.stop();
    mineCounter.reset();
    imageButton.setMode(ImageButton.SAD);
  }

  public void gameReset(GameEvent e) {
    timeCounter.reset();
    mineCounter.reset();
    if (!isFirstTime && timer.isRunning())
      timer.stop();
    imageButton.setMode(ImageButton.UP);
  }

  public void mineMarked(GameEvent e) {
    mineCounter.decrement();
  }

  public void mineUnmarked(GameEvent e) {
    mineCounter.increment();
  }

  //exit form the program
  public void exit() {
    setVisible(false);
    dispose();
    bestRecord.store(recordFile);
    System.exit(0);
  }

  public void reset(String level) {
    if (prevLevel.equals(level))
      return;
    bestRecord.setCurrent(level);
    prevLevel = bestRecord.current.level;
    isCustomGame = false;
    gridArray.init(bestRecord.current.width, bestRecord.current.height, bestRecord.current.mines);
    mineCounter.reset(0, bestRecord.current.mines, bestRecord.current.mines, 3);
    timeCounter.reset();
    imageButton.setMode(ImageButton.UP);
    if (!isFirstTime && timer.isRunning())
      timer.stop();
    pack();
  }

  //Private Method
  private String getContent(String url) {
    BufferedReader bReader = null;
    Class thisclass = getClass();
    StringBuffer content = new StringBuffer();
    String line;
    try {
      bReader = new BufferedReader(new InputStreamReader(thisclass.getResourceAsStream(url)));
      while ((line = bReader.readLine()) != null)
        content.append(line);
    } catch (IOException e) {
      content.append(e.getMessage());
    } finally {
      try {
        if (bReader != null)
          bReader.close();
      } catch (IOException ex) {
      }
    }
    return content.toString();
  }

  private void createMenu() {
    levelGroup.add(beginnerMenuItem);
    levelGroup.add(intermediateMenuItem);
    levelGroup.add(expertMenuItem);
    levelGroup.add(customMenuItem);

    JMenu gameMenu = new JMenu("Game");
    JMenu helpMenu = new JMenu("Help");

    gameMenu.add(newMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(beginnerMenuItem);
    gameMenu.add(intermediateMenuItem);
    gameMenu.add(expertMenuItem);
    gameMenu.add(customMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(besttimeMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(exitMenuItem);

    helpMenu.add(contentMenuItem);
    helpMenu.add(aboutMenuItem);

    JMenuBar bar = new JMenuBar();
    bar.add(gameMenu);
    bar.add(helpMenu);
    getRootPane().setJMenuBar(bar);
  }

  private void createImage() {
    Properties properties = new Properties();
    try {
      properties.load(getClass().getResourceAsStream("/mine/icon.cfg"));
    } catch (IOException e) {
    }
    imageSet = new ImageSet(properties);
  }

  private void createListener() {
    newGameListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        gridArray.reset();
      }
    };
    newMenuItem.addActionListener(newGameListener);
    imageButton.addActionListener(newGameListener);

    exitMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        exit();
      }
    });

    beginnerMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        reset("Beginner");
      }
    });

    intermediateMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        reset("Intermediate");
      }
    });

    expertMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        reset("Expert");
      }
    });

    customMenuItem.setEnabled(false);

    besttimeMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        BestTimeDialog bestDialog = new BestTimeDialog(Minesweeper.this, bestRecord);
        bestDialog.setLocationRelativeTo(Minesweeper.this);
        bestDialog.show();
      }
    });

    contentMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(Minesweeper.this,
            contentString,
            "Information",
            JOptionPane.INFORMATION_MESSAGE);
      }
    });

    aboutMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(Minesweeper.this,
            aboutString,
            "About Java Minesweeper",
            JOptionPane.INFORMATION_MESSAGE,
            ImageSet.ICONS[ImageSet.DUKE]);
      }
    });
  }

  private void createComponent() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 2));
    buttonPanel.add(imageButton);
    buttonPanel.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        imageButton.setMode(ImageButton.CONFUSED);
      }

      public void mouseReleased(MouseEvent e) {
        imageButton.setMode(ImageButton.UP);
      }
    });

    JPanel minePanel = new JPanel();
    minePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 4));
    minePanel.add(mineCounter);

    JPanel timePanel = new JPanel();
    timePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 4));
    timePanel.add(timeCounter);

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new BorderLayout());
    northPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    northPanel.add("West", minePanel);
    northPanel.add("Center", buttonPanel);
    northPanel.add("East", timePanel);

    JPanel boardPanel = new JPanel();
    boardPanel.setLayout(new BorderLayout(0, 2));
    boardPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    boardPanel.add("North", northPanel);
    boardPanel.add("Center", gridArray);

    Container contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
    contentPane.add(boardPanel);
  }

}
