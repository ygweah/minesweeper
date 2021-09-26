/**
 * A dialog to display the BestRecord.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class BestTimeDialog extends Dialog {

  //Private Instance Variables
  private final Frame frame;
  private final GameRecord[] records;
  private final Label[][] labels;
  private final Button ok;
  private final Button reset;

  //Public Constructor
  public BestTimeDialog(Frame parent, BestRecord best) {
    super(parent, "Best Scores", true);
    frame = parent;

    setResizable(false);
    setLayout(new BorderLayout(20, 20));

    records = new GameRecord[best.records.size()];
    records[0] = best.get("Beginner");
    records[1] = best.get("Intermediate");
    records[2] = best.get("Expert");

    Panel pA = new Panel();
    pA.setLayout(new GridLayout(records.length, 3, 10, 20));
    labels = new Label[records.length][3];
    for (int i = 0; i < records.length; ++i) {
      labels[i][0] = new Label(records[i].level, Label.LEFT);
      labels[i][1] = new Label(records[i].name, Label.LEFT);
      labels[i][2] = new Label(String.valueOf(records[i].time), Label.RIGHT);
      pA.add(labels[i][0]);
      pA.add(labels[i][1]);
      pA.add(labels[i][2]);
    }

    Panel pB = new Panel();
    pB.setLayout(new GridLayout(1, 2, 40, 10));
    ok = new Button(" OK ");
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        exit();
      }
    });

    reset = new Button(" Reset ");
    reset.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        reset();
      }
    });

    pB.add(ok);
    pB.add(reset);
    add(new Label("Fastest Mine Sweepers", Label.CENTER), "North");
    add(pA, "Center");
    add(pB, "South");

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

    Point point = parent.getLocation();
    setLocation(point.x, point.y + 40);
    pack();
    setVisible(true);
  }

  //Public Methods
  //called to reset all records
  public void reset() {
    for (int i = 0; i < records.length; ++i) {
      records[i].reset();
      labels[i][1].setText(records[i].name);
      labels[i][2].setText(String.valueOf(records[i].time));
    }
  }

  //called when Dialog is closed
  public void exit() {
    setVisible(false);
    dispose();
    frame.setVisible(true);
    frame.requestFocus();
  }

}
