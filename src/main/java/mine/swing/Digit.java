/**
 * This contains the info about the digit.
 * It provides methods to do the drawing.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.swing;

import java.awt.*;

public class Digit {
  //Public Static Variable
  public static Dimension size = new Dimension(13, 23);

  //Protected Instance Variable
  protected int start, digit, position;

  //Private Instance Variable
  private final Component component;

  //Constructor
  public Digit(int start, int pos, Component component) {
    this.start = start;
    digit = start;
    position = pos;
    this.component = component;
  }

  public Digit(int pos, Component component) {
    this(0, pos, component);
  }

  //Public method
  public void paint(Graphics g) {
    ImageSet.ICONS[ImageSet.DIGIT_ZERO + digit].paintIcon(component, g, 13 * position, 0);
  }

  public boolean increment(Graphics g) {
    digit = (digit + 1) % 10;
    paint(g);
    return digit == 0;
  }

  public boolean decrement(Graphics g) {
    digit = (digit + 9) % 10;
    paint(g);
    return digit == 9;
  }

  public void reset(Graphics g) {
    digit = start;
    paint(g);
  }

}



