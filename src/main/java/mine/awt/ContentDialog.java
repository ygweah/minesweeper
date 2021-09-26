/**
 * A custom dialog used to display large amount of infomation from a file
 * The content is shown in a TextArea
 * The default file name is readme.txt
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ContentDialog extends Dialog {

  //Instance Variables
  private final TextArea text;
  private final Button ok;
  private static final String DEFAULT_FILE = "/mine/awt.txt";

  //Public Constructors
  public ContentDialog(Frame parent, String title, String filename) {
    super(parent, title, true);

    setResizable(false);
    setLayout(new BorderLayout(5, 5));

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

    text = new TextArea("", 15, 60, TextArea.SCROLLBARS_VERTICAL_ONLY);
    text.setEditable(false);
    getContent(filename);
    add(text, "Center");

    setLocation(parent.getLocation());
    pack();
    setVisible(true);
  }

  public ContentDialog(Frame parent, String title) {
    this(parent, title, DEFAULT_FILE);
  }

  //Public Methods
  //read from a file, and show the results on the TextArea
  public synchronized void getContent(String filename) {
    Class thisclass = getClass();
    InputStream in = thisclass.getResourceAsStream(filename);
    InputStreamReader iReader;
    BufferedReader bReader;
    String content;
    try {
      iReader = new InputStreamReader(in);
      bReader = new BufferedReader(iReader);
      while ((content = bReader.readLine()) != null)
        text.append(content + "\n");
      bReader.close();
      iReader.close();
    } catch (IOException e) {
      text.setText(e.getMessage());
    }
  }

  //called when Dialog is closed
  public void exit() {
    setVisible(false);
    dispose();
  }

}
