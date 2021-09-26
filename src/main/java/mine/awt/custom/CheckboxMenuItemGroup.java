/**
 * CheckboxGroup-like Controller
 * Used to create mutually exclusive CheckboxMenuItem.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt.custom;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

public class CheckboxMenuItemGroup {

  //Instance Variables
  private CheckboxMenuItem current;   //current CheckboxMenuItem, selected or deselected
  private final Vector cbMenuItems;         //list holding registered CheckboxMenuItem
  private final ItemAdapter itemListener;

  //Public Constructor
  public CheckboxMenuItemGroup() {
    cbMenuItems = new Vector();
    itemListener = new ItemAdapter();
  }

  //return current selected CheckboxMenuItem
  public CheckboxMenuItem getSelectedCheckboxMenuItem() {
    return current;
  }

  public synchronized void setSelectedCheckboxMenuItem(CheckboxMenuItem cbMenuItem) {
    if (current == cbMenuItem) {              //same as current
      cbMenuItem.setState(true);          // set it to be selected
      return;
    }
    if (!cbMenuItems.contains(cbMenuItem)) {//new item
      cbMenuItem.setState(true);          //set it to be selected
      add(cbMenuItem);                    //and add it to the group
      return;
    }
    if (current != null)                      //there is a valid current
      current.setState(false);            //set it to be deselected
    current = cbMenuItem;                   //set new item as current
    current.setState(true);                 //and set it to be selected
  }

  //add a CheckboxMenuItem to the group
  public synchronized void add(CheckboxMenuItem cbMenuItem) {
    if (cbMenuItem.getState() == true) {      //new item is selected
      if (current != null)                  //there is a valid current
        current.setState(false);        //set it to be deselected
      current = cbMenuItem;               //set new item as current
    }
    cbMenuItems.addElement(cbMenuItem);     //also add it to the list
    cbMenuItem.addItemListener(itemListener);
  }

  //remove a CheckboxMenuItem from the group
  public synchronized void remove(CheckboxMenuItem cbMenuItem) {
    if (cbMenuItems.removeElement(cbMenuItem)) {
      cbMenuItem.removeItemListener(itemListener);
      if (current == cbMenuItem)
        current = null;
    }
  }

  //member class to handle the works
  class ItemAdapter implements ItemListener {
    public void itemStateChanged(ItemEvent e) {
      switch (e.getStateChange()) {
        case ItemEvent.SELECTED:                //an item selected
          if (current != null)                  //there is a valid current
            current.setState(false);        //set it to be deselected
          current = (CheckboxMenuItem) e.getItemSelectable();  //set current to selected item
          break;
        case ItemEvent.DESELECTED:              //an selected item deselected
          if (current == e.getItemSelectable()) //it must be current
            current.setState(true);         //set it back to be selected
          break;
        default:
      }
    }
  }

}

