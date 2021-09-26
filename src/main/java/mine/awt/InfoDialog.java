/*  A modal Dialog displaying info about the program and running system;
 *  Also display an image "purejava.gif".
 *
 *  @author Yuhong Guo
 *  @version 3.5
 */

package mine.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import mine.awt.custom.ImageArea;
import mine.awt.custom.LabelArea;

public class InfoDialog extends Dialog {

  //Private Instance Variables
  private final Frame frame;
  private final LabelArea text;
  private final Button ok;
  private static final String AUTHOR = "Yuhong Guo";
  private static final String VERSION = "3.5";

  //Public Constructor
  public InfoDialog(Frame parent, String title) {
    super(parent, title, true);
    frame = parent;

    setResizable(false);
    setBackground(SystemColor.window);
    setLayout(new BorderLayout(20, 20));
    addWindowListener(new WindowListener() {
      public void windowActivated(WindowEvent e) {
      }

      public void windowClosed(WindowEvent e) {
      }

      public void windowClosing(WindowEvent e) {
        exit();
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

    ok = new Button("OK");
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        exit();
      }
    });

    Panel panel = new Panel();
    panel.add(ok);
    add(panel, "South");

    text = new LabelArea(toString(), 20, 5);
    text.setFont(new Font("Serif", Font.BOLD, 12));
    add(text, "Center");

    Panel panelImage = new Panel();
    panelImage.add(new ImageArea("/mine/icons/purejava.gif"));
    add(panelImage, "West");

    setLocation(parent.getLocation());
    pack();
    setVisible(true);
  }

  //Protected Methods
  //get version information
  protected final String getVersionInfo() {
    String versionInfo = " \nJava Minesweeper\nVersion "
        + VERSION
        + "\nby "
        + AUTHOR
        + "\n";
    return versionInfo;
  }

  //get system information
  protected String getSystemInfo() {
    String systemInfo = " \n \n \nThis product is used by:\n" +
        System.getProperty("user.name") +
        "\n \nOperating System:  " +
        System.getProperty("os.name") +
        "\nJVM Version:  " +
        System.getProperty("java.version");
    return systemInfo;
  }

  //Public Methods
  public String toString() {
    return getVersionInfo() + getSystemInfo();
  }

  //called when Dialog is closed
  public void exit() {
    setVisible(false);
    dispose();
    frame.setVisible(true);
    frame.requestFocus();
  }

}
