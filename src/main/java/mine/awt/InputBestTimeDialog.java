/**
 * A dialog prompts user to enter name, then shows the updated best score.
 * Used when user breaks current GameRecord
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

public class InputBestTimeDialog extends Dialog {

  //Private Instance Variables
  private final Board board;
  private final TextField text;
  private final Button ok;

  //Public Constructor
  public InputBestTimeDialog(Board board) {
    super(board, "Congratulations", true);
    this.board = board;

    setResizable(false);
    setLayout(new BorderLayout(10, 40));

    String msg = "The fastest " + this.board.getCurrentLevel().toLowerCase() + " level player : ";
    Label label = new Label(msg);

    text = new TextField("Anonymous", 20);
    text.selectAll();
    ok = new Button("OK");

    text.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        inputName();
      }
    });

    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        inputName();
      }
    });

    add(label, "North");
    add(text, "Center");
    add(ok, "South");

    addWindowListener(new WindowListener() {
      public void windowActivated(WindowEvent e) {
      }

      public void windowClosed(WindowEvent e) {
      }

      public void windowClosing(WindowEvent e) {
        inputName();
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

    Point point = board.getLocation();
    setLocation(point.x, point.y + 40);
    pack();
    setVisible(true);
  }

  //Public Method
  /*  Called to get user input of name
   *  Change it to current GameRecord
   *  Show the BestRecord in Dislog
   */
  public void inputName() {
    setVisible(false);
    board.getCurrent().name = text.getText().toUpperCase();             //put name into current setting
    new BestTimeDialog(board, board.getBestRecord());                   //show best time
    dispose();
  }

}
