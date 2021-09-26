package mine;

import javax.swing.UIManager;

public class Main {

  //main() method for application
  public static void main(String[] argv) {
    if (argv.length != 1) {
      System.out.println("Usage: java mine.Main --AWT|Swing|Windows|Motif|Native");
      System.exit(1);
    }
    if (argv[0].equalsIgnoreCase("--awt")) {
      mine.awt.Minesweeper minesweeperA = new mine.awt.Minesweeper();
    } else if (argv[0].equalsIgnoreCase("--swing")) {
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception e) {
        System.out.println("Cross Platform LookAndFeel Not Supported");
        System.exit(1);
      }
      mine.swing.Minesweeper minesweeperS = new mine.swing.Minesweeper();
    } else if (argv[0].equalsIgnoreCase("--windows")) {
      try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } catch (Exception e) {
        System.out.println("Windows LookAndFeel Not Supported");
        System.exit(1);
      }
      mine.swing.Minesweeper minesweeperS = new mine.swing.Minesweeper();
    } else if (argv[0].equalsIgnoreCase("--motif")) {
      try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      } catch (Exception e) {
        System.out.println("Motif LookAndFeel Not Supported");
        System.exit(1);
      }
      mine.swing.Minesweeper minesweeperS = new mine.swing.Minesweeper();
    } else if (argv[0].equalsIgnoreCase("--native")) {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
        System.out.println("Native LookAndFeel Not Supported");
        System.exit(1);
      }
      mine.swing.Minesweeper minesweeperS = new mine.swing.Minesweeper();
    } else {
      System.out.println("Usage: java mine.Main --AWT|Swing|Windows|Motif|Native");
      System.exit(1);
    }
  }

}
