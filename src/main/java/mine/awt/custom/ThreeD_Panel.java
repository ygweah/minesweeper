/**
 * A Panel showing components with 3D effect
 * A default ThreeD_BorderLayout used
 * Lines are drawn around the components to create 3D effect
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt.custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Point;

public class ThreeD_Panel extends Panel {

  //Private Instance Variable
  private final ThreeD_BorderLayout layout = new ThreeD_BorderLayout();

  //Public Constructor
  public ThreeD_Panel() {
    super();
    setLayout(layout);
    setBackground(Color.lightGray);
  }

  //Public Methods
  public Insets getInsets() {
    return new Insets(2, 2, 2, 2);
  }

  public void paint(Graphics g) {
    //draw 3D effect at the border of Panel
    int x1 = getInsets().left;
    int y1 = getInsets().top;
    int x2 = getSize().width - getInsets().right - 1;
    int y2 = getSize().height - getInsets().bottom - 1;

    g.setColor(Color.white);
    g.drawLine(x1, y1, x2, y1);
    g.drawLine(x1 + 1, y1 + 1, x2 - 1, y1 + 1);
    g.drawLine(x1 + 2, y1 + 2, x2 - 2, y1 + 2);
    g.drawLine(x1, y1, x1, y2 - 1);
    g.drawLine(x1 + 1, y1 + 1, x1 + 1, y2 - 2);
    g.drawLine(x1 + 2, y1 + 2, x1 + 2, y2 - 3);

    g.setColor(Color.darkGray);
    g.drawLine(x2, y1 + 1, x2, y2);
    g.drawLine(x2 - 1, y1 + 2, x2 - 1, y2 - 1);
    g.drawLine(x2 - 2, y1 + 3, x2 - 2, y2 - 2);
    g.drawLine(x1, y2, x2, y2);
    g.drawLine(x1 + 1, y2 - 1, x2 - 1, y2 - 1);
    g.drawLine(x1 + 2, y2 - 2, x2 - 2, y2 - 2);

    //draw 3D effect at the border of Component
    draw3DComponents(g);
    invalidate();
  }

  //Protected Methods
  protected void draw3DComponents(Graphics g) {
    Component[] comp = getComponents();
    for (int i = 0; i < comp.length; ++i)
      draw3D(comp[i], g);
  }

  protected void draw3D(Component comp, Graphics g) {
    Dimension d = comp.getSize();
    d.width += 2 * ThreeD_BorderLayout.SHADOW;
    d.height += 2 * ThreeD_BorderLayout.SHADOW;

    Point p = comp.getLocation();
    p.x -= ThreeD_BorderLayout.SHADOW;
    p.y -= ThreeD_BorderLayout.SHADOW;

    g.setColor(Color.darkGray);
    g.drawLine(p.x, p.y, p.x + d.width - 1, p.y);
    g.drawLine(p.x + 1, p.y + 1, p.x + d.width - 2, p.y + 1);
    g.drawLine(p.x + 2, p.y + 2, p.x + d.width - 3, p.y + 2);
    g.drawLine(p.x, p.y, p.x, p.y + d.height - 2);
    g.drawLine(p.x + 1, p.y + 1, p.x + 1, p.y + d.height - 3);
    g.drawLine(p.x + 2, p.y + 2, p.x + 2, p.y + d.height - 4);

    g.setColor(Color.white);
    g.drawLine(p.x, p.y + d.height - 1, p.x + d.width - 1, p.y + d.height - 1);
    g.drawLine(p.x + 1, p.y + d.height - 2, p.x + d.width - 2, p.y + d.height - 2);
    g.drawLine(p.x + 2, p.y + d.height - 3, p.x + d.width - 3, p.y + d.height - 3);
    g.drawLine(p.x + d.width - 1, p.y + 1, p.x + d.width - 1, p.y + d.height - 1);
    g.drawLine(p.x + d.width - 2, p.y + 2, p.x + d.width - 2, p.y + d.height - 2);
    g.drawLine(p.x + d.width - 3, p.y + 3, p.x + d.width - 3, p.y + d.height - 3);
  }

}
