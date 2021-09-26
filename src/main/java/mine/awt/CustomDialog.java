/**
 * A customize dialog box used to get input from user
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomDialog extends Dialog {

  //Private Instance Variables
  private final Minesweeper minesweeper;
  private final Button ok;
  private final Label label_width;
  private final Label label_height;
  private final Label label_mine;
  private final TextField text_width;
  private final TextField text_height;
  private final TextField text_mine;

  //Constants to limit the dimension of the game minesweeper
  public static final int MAX_WIDTH = 30;
  public static final int MAX_HEIGHT = 20;

  //Public Constructor
  public CustomDialog(Minesweeper minesweeper) {
    super(minesweeper, "Custom", true);
    this.minesweeper = minesweeper;

    setResizable(false);
    setLayout(new BorderLayout(20, 20));

    label_width = new Label("Width:");
    label_height = new Label("Height:");
    label_mine = new Label("Mines:");
    Panel pA = new Panel();
    pA.setLayout(new GridLayout(3, 1, 10, 10));
    pA.add(label_width);
    pA.add(label_height);
    pA.add(label_mine);

    text_width = new TextField(String.valueOf(minesweeper.getCurrent().width));
    text_height = new TextField(String.valueOf(minesweeper.getCurrent().height));
    text_mine = new TextField(String.valueOf(minesweeper.getCurrent().mines));
    Panel pB = new Panel();
    pB.setLayout(new GridLayout(3, 1, 10, 10));
    pB.add(text_width);
    pB.add(text_height);
    pB.add(text_mine);

    ok = new Button(" OK ");
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

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exit();
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
      minesweeper.getBestRecord().current = new GameRecord("NONE", GameRecord.MAX_TIME, g);
      minesweeper.game(minesweeper.getCurrent());        //start a new game
    } catch (NumberFormatException e) {
    } finally {
      exit();
    }
  }

  /*  Called to select "Custom" CheckboxMenuItem
   *  Close the Dialog
   */
  public void exit() {
    setVisible(false);
    dispose();
    minesweeper.selectMenu();
  }

}
