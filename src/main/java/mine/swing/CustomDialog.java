/**
 * A customize dialog box used to get input from user
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import mine.GameDimension;

public class CustomDialog extends JDialog {

  //Private Instance Variables
  private final Minesweeper minesweeper;
  private final JButton ok;
  private final JLabel label_width;
  private final JLabel label_height;
  private final JLabel label_mine;
  private final JTextField text_width;
  private final JTextField text_height;
  private final JTextField text_mine;

  //Constants to limit the dimension of the game minesweeper
  public static final int MAX_WIDTH = 30;
  public static final int MAX_HEIGHT = 20;

  //Public Constructor
  public CustomDialog(Minesweeper minesweeper) {
    super(minesweeper, "Custom", true);
    this.minesweeper = minesweeper;

    setResizable(false);
    setLayout(new BorderLayout(10, 10));

    label_width = new JLabel("Width:");
    label_height = new JLabel("Height:");
    label_mine = new JLabel("Mines:");
    Panel pA = new Panel();
    pA.setLayout(new GridLayout(3, 1, 10, 10));
    pA.add(label_width);
    pA.add(label_height);
    pA.add(label_mine);

    text_width = new JTextField(String.valueOf(minesweeper.getCurrent().width));
    text_height = new JTextField(String.valueOf(minesweeper.getCurrent().height));
    text_mine = new JTextField(String.valueOf(minesweeper.getCurrent().mines));
    Panel pB = new Panel();
    pB.setLayout(new GridLayout(3, 1, 10, 10));
    pB.add(text_width);
    pB.add(text_height);
    pB.add(text_mine);

    ok = new JButton(" OK ");
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        inputGameRecord();
      }
    });
    Panel pC = new Panel();
    pC.setLayout(new BorderLayout(10, 10));
    pC.add(ok, "Center");

    add(new Label("Custom Field", Label.CENTER), "North");
    add(pA, "West");
    add(pB, "Center");
    add(pC, "East");

    addWindowListener(new WindowListener() {
      public void windowActivated(WindowEvent e) {
      }

      public void windowClosed(WindowEvent e) {
      }

      public void windowClosing(WindowEvent e) {
        exit();
      }

      public void windowDeactivated(WindowEvent e) {
      }

      public void windowDeiconified(WindowEvent e) {
      }

      public void windowIconified(WindowEvent e) {
      }

      public void windowOpened(WindowEvent e) {
      }
    });

    Point point = minesweeper.getLocation();
    setLocation(point.x, point.y + 40);
    pack();
    setVisible(true);
  }

  //Public Methods
  /*  Called to get game setting for custom game
   *  Use new GameRecord to start a new game
   */
  public void inputGameRecord() {
    try {
      int width = Math.min(Integer.parseInt(text_width.getText()), MAX_WIDTH);
      int height = Math.min(Integer.parseInt(text_height.getText()), MAX_HEIGHT);
      int mines = Integer.parseInt(text_mine.getText());
      GameDimension g = new GameDimension("Custom", width, height, mines);
//      minesweeper.getBestRecord().current = new GameRecord("NONE", GameRecord.MAX_TIME, g);
      exit();
//      minesweeper.game(minesweeper.getCurrent());        //start a new game
    } catch (NumberFormatException e) {
      exit();
    }
  }

  /*  Called to select "Custom" CheckboxMenuItem
   *  Close the Dialog
   */
  public void exit() {
    minesweeper.requestFocus();
//    minesweeper.selectCurrent();
    setVisible(false);
    dispose();
  }

}
