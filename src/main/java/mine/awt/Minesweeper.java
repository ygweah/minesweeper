/*  GUI class, also contains main() methods.
 *
 *  @author Yuhong Guo
 *  @version 3.5
 */

package mine.awt;

import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import mine.awt.custom.CheckboxMenuItemGroup;

public class Minesweeper extends Board {
  //Private Instance Variables
  private MenuItem newMenuItem, exitMenuItem, bestMenuItem, contentMenuItem, aboutMenuItem;
  private CheckboxMenuItemGroup group;
  private CheckboxMenuItem beginner, intermediate, expert, custom;
  private MenuBar menuBar;
  private Menu gameMenu, helpMenu;

  //Public Constructor
  public Minesweeper() {
    super();

    //register WindowListener
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exit();             //quit from the program
      }
    });

    createMenu();
    selectMenu();
    createListener();

    pack();
    show();
  }

  //Public Methods

  private void createMenu() {
    //set up newMenuItem
    newMenuItem = new MenuItem("New", new MenuShortcut(KeyEvent.VK_F2));
    //set up "Exclusive" CheckboxMenuItem
    group = new CheckboxMenuItemGroup();
    beginner = new CheckboxMenuItem("Beginner");
    intermediate = new CheckboxMenuItem("Intermediate");
    expert = new CheckboxMenuItem("Expert");
    custom = new CheckboxMenuItem("Custom...");
    group.add(beginner);
    group.add(intermediate);
    group.add(expert);
    group.add(custom);

    //set up bestMenuItem
    bestMenuItem = new MenuItem("Best Time...", new MenuShortcut(KeyEvent.VK_T));
    //set up exitMenuItem
    exitMenuItem = new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_X));
    //set up gameMenu
    gameMenu = new Menu("Game");
    gameMenu.add(newMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(beginner);
    gameMenu.add(intermediate);
    gameMenu.add(expert);
    gameMenu.add(custom);
    gameMenu.addSeparator();
    gameMenu.add(bestMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(exitMenuItem);
    //set up contentMenuItem
    contentMenuItem = new MenuItem("Contents", new MenuShortcut(KeyEvent.VK_F1));
    //set up aboutMenuItem
    aboutMenuItem = new MenuItem("About", new MenuShortcut(KeyEvent.VK_A));
    //set up helpMenu
    helpMenu = new Menu("Help");
    helpMenu.add(contentMenuItem);
    helpMenu.addSeparator();
    helpMenu.add(aboutMenuItem);
    //set up menuBar
    menuBar = new MenuBar();
    menuBar.add(gameMenu);
    menuBar.add(helpMenu);
    setMenuBar(menuBar);
  }

  //set up current CheckboxMenuItem
  public void selectMenu() {
    String level = getCurrentLevel();
    CheckboxMenuItem cm;
    if (level.equals("Beginner")) {
      cm = beginner;
    } else if (level.equals("Intermediate")) {
      cm = intermediate;
    } else if (level.equals("Expert")) {
      cm = expert;
    } else
      cm = custom;
    group.setSelectedCheckboxMenuItem(cm);
  }

  private void createListener() {
    //set up newMenuItem
    newMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game();         //new game w/o changing board dimension
      }
    });
    //set up "Exclusive" CheckboxMenuItem
    //local ItemListener
    ItemListener groupListener = new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == custom) {        //select "Custom"
          new CustomDialog(Minesweeper.this);
          return;
        }
        if (((CheckboxMenuItem) e.getSource()).getLabel().equals(getCurrentLevel())) {    //select the current one
          game();
          return;
        }
        getBestRecord().current = getBestRecord().get(((CheckboxMenuItem) e.getSource()).getLabel()); //select a new one
        game(getCurrent());
      }
    };
    beginner.addItemListener(groupListener);
    intermediate.addItemListener(groupListener);
    expert.addItemListener(groupListener);
    custom.addItemListener(groupListener);
    //set up bestMenuItem
    bestMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new BestTimeDialog(Minesweeper.this, getBestRecord());      //show best time
      }
    });
    //set up exitMenuItem
    exitMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        exit();             //quit from the program
      }
    });
    //set up contentMenuItem
    contentMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new ContentDialog(Minesweeper.this, "Minesweeper Help");        //show help file
      }
    });
    //set up aboutMenuItem
    aboutMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new InfoDialog(Minesweeper.this, "About Minesweeper");          //show about message
      }
    });
  }

  //exit form the program
  public void exit() {
    super.exit();
    setVisible(false);
    dispose();
    System.exit(0);
  }

}
