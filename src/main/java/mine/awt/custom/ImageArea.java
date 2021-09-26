/**
 * A Canvas displaying an Image;
 * If image is not successfully loaded, nothing showed.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt.custom;

import java.awt.*;

public class ImageArea extends Canvas {

  //Private Instance Variables
  private final Image image;
  private final MediaTracker tracker;
  private Dimension size;

  //Public Constructor
  public ImageArea(String filename) {
    Class thisclass = getClass();
    image = Toolkit.getDefaultToolkit().getImage(thisclass.getResource(filename));
    tracker = new MediaTracker(this);
    tracker.addImage(image, 0);
    try {
      tracker.waitForID(0);       //Force loading image "now"
      size = new Dimension(image.getWidth(this), image.getHeight(this));
    } catch (InterruptedException e) {
      size = new Dimension();     //Loading fails, set size to zero
    }
  }

  //Public Methods
  public Dimension getPreferredSize() {
    return size;
  }

  public Dimension getMinimumSize() {
    return size;
  }

  public void paint(Graphics g) {
    if (tracker.statusID(0, true) == MediaTracker.COMPLETE)
      g.drawImage(image, 0, 0, this);
  }

  //Helper Method: return shown Image
  public Image getImage() {
    return image;
  }

}
