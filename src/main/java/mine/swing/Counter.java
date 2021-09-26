/**
 * A simple Counter
 * Start from between min and max, it can count up or down
 * When reach the min or max limits, it stops
 * The digits showing can be specificed.
 * It can be reseted to start number.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Counter extends JPanel {

  //Private Instance Variable
  private int min_count;
  private int max_count;
  private int start_count;
  private int count;
  private int no_digit;
  private Dimension size;
  private Digit[] digits;

  //Public Constructor
  public Counter(int min, int max, int start, int no_digit) {
    super();
    min_count = min;
    max_count = max;
    start_count = start;
    count = start_count;
    this.no_digit = no_digit;
    size = new Dimension(no_digit * 13, 23);
    digits = new Digit[no_digit];

    for (int i = no_digit - 1, c = count, d; i >= 0; --i) {
      d = c % 10;
      digits[i] = new Digit(d, i, this);
      c /= 10;
    }
  }

  //Public Instance Method
  public Dimension getMinimumSize() {
    return size;
  }

  public Dimension getPreferredSize() {
    return size;
  }

  public void paintComponent(Graphics g) {
    for (int i = 0; i < no_digit; ++i)
      digits[i].paint(g);
  }

  //return current count
  public synchronized int getCount() {
    return count;
  }

  //increment count by 1
  public synchronized void increment() {
    if (count < max_count) {
      ++count;
      Graphics g = getGraphics();
      for (int i = no_digit - 1; i >= 0; --i) {
        if (!digits[i].increment(g))
          break;
      }
      g.dispose();
    }
  }

  //decrement count by 1
  public synchronized void decrement() {
    if (count > min_count) {
      --count;
      Graphics g = getGraphics();
      for (int i = no_digit - 1; i >= 0; --i) {
        if (!digits[i].decrement(g))
          break;
      }
      g.dispose();
    }
  }

  //reset count to start_count
  public synchronized void reset() {
    if (count != start_count) {
      count = start_count;
      Graphics g = getGraphics();
      for (int i = no_digit - 1; i >= 0; --i)
        digits[i].reset(g);
      g.dispose();
    }
  }

  public synchronized void reset(int min, int max, int start, int no_digit) {
    min_count = min;
    max_count = max;
    start_count = start;
    count = start_count;
    this.no_digit = no_digit;
    size = new Dimension(no_digit * 13, 23);
    digits = new Digit[no_digit];

    for (int i = no_digit - 1, c = count, d; i >= 0; --i) {
      d = c % 10;
      digits[i] = new Digit(d, i, this);
      c /= 10;
    }
    invalidate();
  }

}
