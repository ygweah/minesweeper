/** A BorderLayout-like LayoutManager.
 *  It is used by ThreeD_Panel to create 3D effect around the components.
 *  The components are layed out in such way:
 *      In a relative position like that by BorderLayout
 *      Components are separated by "SPACER" pixels
 *      Components are surrounded by 3D line "SHADOW" pixels wide which drawn in ThreeD_Panel
 *
 *  @author Yuhong Guo
 *  @version 3.5
 */

package mine.awt.custom;

import java.awt.*;

public class ThreeD_BorderLayout implements LayoutManager {

    //Constants
    protected static final int SPACER = 6;
    protected static final int SHADOW = 3;
    protected static final int BORDER = SPACER + 2*SHADOW;

    //Protected Instance Variables
    protected Component north;
    protected Component south;
    protected Component center;
    protected Component west;
    protected Component east;

    //Public Constructor
    public ThreeD_BorderLayout () {
        super();
    }

    //Public Methods
    //Implement methods from LayoutManager interface
    public void addLayoutComponent (String name, Component comp) {
        if ("North".equals(name))
            north = comp;
        else if ("South".equals(name))
            south = comp;
        else if ("Center".equals(name))
            center = comp;
        else if ("West".equals(name))
            west = comp;
        else if ("East".equals(name))
            east = comp;
    }

    public void removeLayoutComponent (Component comp) {
        if (comp==north)
            north = null;
        else if (comp==south)
            south = null;
        else if (comp==center)
            center = null;
        else if (comp==west)
            west = null;
        else if (comp==east)
            east = null;
    }

    public Dimension minimumLayoutSize (Container target) {
        Dimension north_d = new Dimension();
        Dimension south_d = new Dimension();
        Dimension center_d = new Dimension();
        Dimension west_d = new Dimension();
        Dimension east_d = new Dimension();

        if (north!=null && north.isVisible())
            north_d = border(north.getMinimumSize());
        if (south!=null && south.isVisible())
            south_d = border(south.getMinimumSize());
        if (center!=null && center.isVisible())
            center_d = border(center.getMinimumSize());
        if (west!=null && west.isVisible())
            west_d = border(west.getMinimumSize());
        if (east!=null && east.isVisible())
            east_d = border(east.getMinimumSize());

        Dimension d = new Dimension();
        d.width = Math.max( west_d.width + center_d.width + east_d.width,
                            Math.max(north_d.width, south_d.width) );
        d.height = Math.max( center_d.height, Math.max(west_d.height, east_d.height) )
                    + north_d.height + south_d.height;

        Insets insets = target.getInsets();
        d.width += insets.left + insets.right + BORDER;
        d.height += insets.top + insets.bottom + BORDER;
        return d;
    }

    protected Dimension border (Dimension d) {
        return new Dimension(d.width+BORDER, d.height+BORDER);
    }

    public Dimension preferredLayoutSize (Container target) {
        Dimension north_d = new Dimension();
        Dimension south_d = new Dimension();
        Dimension center_d = new Dimension();
        Dimension west_d = new Dimension();
        Dimension east_d = new Dimension();

        if (north!=null && north.isVisible())
            north_d = border(north.getPreferredSize());
        if (south!=null && south.isVisible())
            south_d = border(south.getPreferredSize());
        if (center!=null && center.isVisible())
            center_d = border(center.getPreferredSize());
        if (west!=null && west.isVisible())
            west_d = border(west.getPreferredSize());
        if (east!=null && east.isVisible())
            east_d = border(east.getPreferredSize());

        Dimension d = new Dimension();
        d.width = Math.max( west_d.width + center_d.width + east_d.width,
                            Math.max(north_d.width, south_d.width) );
        d.height = Math.max( center_d.height, Math.max(west_d.height, east_d.height) )
                    + north_d.height + south_d.height;

        Insets insets = target.getInsets();
        d.width += insets.left + insets.right + BORDER;
        d.height += insets.top + insets.bottom + BORDER;
        return d;
    }

    public void layoutContainer (Container target) {
        Dimension d = target.getSize();
        Insets insets = target.getInsets();
        d.width -= (insets.left + insets.right + BORDER);
        d.height -= (insets.top + insets.bottom + BORDER);
        Point p = new Point(insets.left+BORDER/2, insets.top+BORDER/2);

        Dimension north_d = new Dimension();
        Dimension south_d = new Dimension();
        Dimension center_d = new Dimension();
        Dimension west_d = new Dimension();
        Dimension east_d = new Dimension();

        if (north!=null && north.isVisible())
            north_d = border(north.getMinimumSize());
        if (south!=null && south.isVisible())
            south_d = border(south.getMinimumSize());
        if (center!=null && center.isVisible())
            center_d = border(center.getMinimumSize());
        if (west!=null && west.isVisible())
            west_d = border(west.getMinimumSize());
        if (east!=null && east.isVisible())
            east_d = border(north.getMinimumSize());

        int x = 0;
        int y = 0;
        int mid_height = Math.max( center_d.height, Math.max(west_d.height, east_d.height) );

        if (north!=null && north.isVisible()) {
            x = p.x + BORDER/2;
            y = p.y + BORDER/2;
            north_d.width = d.width;
            north.setBounds(x,y,north_d.width-BORDER,north_d.height-BORDER);
        }
        if (south!=null && south.isVisible()) {
            x = p.x + BORDER/2;
            y = p.y + d.height - south_d.height + BORDER/2;
            south_d.width = d.width;
            south.setBounds(x,y,south_d.width-BORDER,south_d.height-BORDER);
        }

        mid_height = d.height - north_d.height - south_d.height;

        if (west!=null && west.isVisible()) {
            x = p.x + BORDER/2;
            y = p.y + north_d.height + BORDER/2;
            west_d.height = mid_height;
            west.setBounds(x,y,west_d.width-BORDER,west_d.height-BORDER);
        }
        if (east!=null && east.isVisible()) {
            x = p.x + d.width - east_d.width + BORDER/2;
            y = p.y + north_d.height + BORDER/2;
            east_d.height = mid_height;
            east.setBounds(x,y,east_d.width-BORDER,east_d.height-BORDER);
        }
        if (center!=null && center.isVisible()) {
            x = p.x + west_d.width + BORDER/2;
            y = p.y + north_d.height + BORDER/2;
            center_d.width = d.width - west_d.width - east_d.width;
            center_d.height = mid_height;
            center.setBounds(x,y,center_d.width-BORDER,center_d.height-BORDER);
        }
    }

}
