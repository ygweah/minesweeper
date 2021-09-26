package mine.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mine.BestRecord;

public class BestTimeDialog extends JDialog {
  private final BestRecord bestRecord;
  private final JLabel beginnerTime;
  private final JLabel beginnerName;
  private final JLabel intermediateTime;
  private final JLabel intermediateName;
  private final JLabel expertTime;
  private final JLabel expertName;

  public BestTimeDialog(Frame owner, BestRecord record) {
    super(owner, "Best Record", true);

    bestRecord = record;
    beginnerTime = new JLabel();
    beginnerName = new JLabel();
    intermediateTime = new JLabel();
    intermediateName = new JLabel();
    expertTime = new JLabel();
    expertName = new JLabel();

    setLabel();

    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    JPanel top = new JPanel();
    top.setLayout(gb);
    gbc.gridx = 0;
    gbc.gridy = 0;
    top.add(new JLabel("Beginner:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    top.add(beginnerTime, gbc);
    gbc.gridx = 2;
    gbc.gridy = 0;
    top.add(beginnerName, gbc);
    gbc.gridx = 0;
    gbc.gridy = 1;
    top.add(new JLabel("Intermediate:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 1;
    top.add(intermediateTime, gbc);
    gbc.gridx = 2;
    gbc.gridy = 1;
    top.add(intermediateName, gbc);
    gbc.gridx = 0;
    gbc.gridy = 2;
    top.add(new JLabel("Expert:"), gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    top.add(expertTime, gbc);
    gbc.gridx = 2;
    gbc.gridy = 2;
    top.add(expertName, gbc);

    JButton ok = new JButton("OK");
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        exit();
      }
    });

    JButton reset = new JButton("Reset");
    reset.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        bestRecord.resetRecord();
        setLabel();
      }
    });

    JPanel bottom = new JPanel();
    bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
    bottom.add(reset);
    bottom.add(ok);

    JPanel title = new JPanel();
    title.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    title.add(new JLabel("Fastest Mine Sweeper"));

    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout(5, 5));
    contentPane.add("North", title);
    contentPane.add("Center", top);
    contentPane.add("South", bottom);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exit();
      }
    });

    pack();
  }

  private void exit() {
    hide();
    dispose();
  }

  private void setLabel() {
    beginnerTime.setText(bestRecord.getTime("Beginner") + " seconds");
    beginnerName.setText(bestRecord.getName("Beginner"));
    intermediateTime.setText(bestRecord.getTime("Intermediate") + " seconds");
    intermediateName.setText(bestRecord.getName("Intermediate"));
    expertTime.setText(bestRecord.getTime("Expert") + " seconds");
    expertName.setText(bestRecord.getName("Expert"));
  }

}

