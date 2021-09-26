/**
 * A Multi-Line Label
 * Mutiple new_line treated as one;
 * A new_line may be created with "\n \n".
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt.custom;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.IllegalComponentStateException;
import java.util.StringTokenizer;

public class LabelArea extends Canvas {

  //Private Instance Variables
  private String[] labels;
  private int width_margin;
  private int height_margin;
  private int height, ascent, max_width;
  private int[] widths;
  private int alignment;

  //Alignment Constants
  public static final int LEFT = 0;
  public static final int CENTER = 1;
  public static final int RIGHT = 2;


  //Public Constructors
  public LabelArea(String label) {
    this(label, 10, 10, LEFT);
  }

  public LabelArea(String label, int width_margin, int height_margin) {
    this(label, width_margin, height_margin, LEFT);
  }

  public LabelArea(String label, int width_margin, int height_margin, int alignment) {
    super();
    parseLabel(label);
    this.width_margin = width_margin;
    this.height_margin = height_margin;
    this.alignment = alignment;
  }

  //Public Methods
  public void addNotify() {
    super.addNotify();
    measure();
  }

  public void paint(Graphics g) {
    Dimension d = this.getSize();
    int x;
    int y = ascent + (d.height - widths.length * height) / 2;
    for (int i = 0; i < widths.length; ++i, y += height) {
      switch (alignment) {
        case LEFT:
        default:
          x = width_margin;
          break;
        case CENTER:
          x = (d.width - widths[i]) / 2;
          break;
        case RIGHT:
          x = d.width - width_margin - widths[i];
          break;
      }
      g.drawString(labels[i], x, y);
    }
  }

  //parse the input string
  public void parseLabel(String label) {
    StringTokenizer st = new StringTokenizer(label, "\n");
    labels = new String[st.countTokens()];
    widths = new int[st.countTokens()];
    for (int i = 0; i < labels.length; ++i)
      labels[i] = st.nextToken();
  }

  public void setLabel(String label) {
    parseLabel(label);
    measure();
    repaint();
  }

  public void setFont(Font font) {
    super.setFont(font);
    measure();
    repaint();
  }

  public Dimension getPreferredSize() {
    return new Dimension(max_width + 2 * width_margin, labels.length * height + 2 * height_margin);
  }

  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  public void setAlignment(int a) {
    alignment = a;
    repaint();
  }

  public int getAlignment() {
    return alignment;
  }

  public void setMargin(int width_margin, int height_margin) {
    this.width_margin = width_margin;
    this.height_margin = height_margin;
  }

  public Dimension getMargin() {
    return new Dimension(width_margin, height_margin);
  }

  //measure the widths of each line, and the height
  public synchronized void measure() {
    Font ft = this.getFont();
    FontMetrics fm = this.getFontMetrics(ft);
    if (fm == null)
      throw new IllegalComponentStateException("No FontMetrics Avaliable");
    height = fm.getHeight();                    //get height
    ascent = fm.getAscent();                    //get ascent
    max_width = 0;
    for (int i = 0; i < labels.length; ++i) {
      widths[i] = fm.stringWidth(labels[i]);  //get widths of each line
      if (widths[i] > max_width)
        max_width = widths[i];              //get the max width amoung them
    }
  }

}