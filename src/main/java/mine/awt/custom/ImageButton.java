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

package mine.awt.custom;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import mine.awt.ImageSet;

public class ImageButton extends Canvas {

  //Private Instance Variable
  private final Dimension size;                 //button's size

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
    //load all images at once
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Class thisclass = getClass();
    size = new Dimension(26, 26);
    mode = UP;
    prev = UP;
  }

  //Public Methods
  //Methods inherited from class Canvas
  public Dimension getPreferredSize() {
    return this.size;
  }

  public Dimension getMinimumSize() {
    return this.size;
  }

  public void paint(Graphics g) {
    g.drawImage(ImageSet.IMAGES[ImageSet.UP + mode], 0, 0, this);
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
}
