/**
 * An animated Button responding to the progress of the game.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import mine.awt.custom.ImageButton;

public class AnimatedButton extends ImageButton {

  //Private Instance Variable
  private final GameListener listener;

  //Public Constructor
  public AnimatedButton(GameListener listener) {
    super();
    this.listener = listener;
    addMouseListener(new MouseListener() {
      public void mouseEntered(MouseEvent e) {
      }

      public void mouseExited(MouseEvent e) {
        if (getMode() == DOWN) {
          setMode(UP);
          game();
        }
      }

      public void mousePressed(MouseEvent e) {
        setMode(DOWN);
      }

      public void mouseReleased(MouseEvent e) {
        setMode(UP);
      }

      public void mouseClicked(MouseEvent e) {
        game();
      }

    });
  }

  //Public Methods
  public void up() {
    setMode(UP);
  }

  public void down() {
    setMode(DOWN);
  }

  public void win() {
    setMode(HAPPY);
  }

  public void lose() {
    setMode(SAD);
  }

  public void ask() {
    setMode(CONFUSED);
  }

  public void game() {
    listener.game();
  }

}
