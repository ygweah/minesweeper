/**
 * An Button displaying images:
 * UP:         always
 * DOWN:       when pressed
 * HAPPY:      when win
 * SAD:        when lose
 * CONFUSED:   when user clicked elsewhere
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ImageButton extends JPanel implements MouseListener {

  //Private Instance Variable
  private final Dimension size;                 //button's size
  private final ArrayList listeners;

  //Protected Instance Variable
  protected int mode, prev;               //mode and previous mode,
  //useful when reset

  //Public Static Variable
  public static final int UP = 0;
  public static final int DOWN = 1;
  public static final int HAPPY = 2;
  public static final int SAD = 3;
  public static final int CONFUSED = 4;

  //Public Constructor
  public ImageButton() {
    size = new Dimension(26, 26);
    listeners = new ArrayList(2);
    mode = UP;
    prev = UP;
    addMouseListener(this);
  }

  //Public Methods
  //Methods inherited from class Canvas
  public Dimension getPreferredSize() {
    return this.size;
  }

  public Dimension getMinimumSize() {
    return this.size;
  }

  public void paintComponent(Graphics g) {
    ImageSet.ICONS[ImageSet.UP + mode].paintIcon(this, g, 0, 0);
  }

  public void addActionListener(ActionListener listener) {
    listeners.add(listener);
  }

  public void removeActionListener(ActionListener listener) {
    int index = listeners.indexOf(listener);
    if (index != -1)
      listeners.remove(index);
  }

  //Helper methods
  public int getMode() {
    return mode;
  }

  public void setMode(int m) {
    prev = mode;
    mode = m;
    repaint();
  }

  public void reset() {
    mode = prev;
    repaint();
  }

  protected void fireActionPerformed(ActionEvent e) {
    Object[] listener = listeners.toArray();
    for (int i = 0; i < listener.length; ++i)
      ((ActionListener) listener[i]).actionPerformed(e);
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
    setMode(DOWN);
  }

  public void mouseReleased(MouseEvent e) {
    setMode(UP);
  }

  public void mouseClicked(MouseEvent e) {
    fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
  }

}
