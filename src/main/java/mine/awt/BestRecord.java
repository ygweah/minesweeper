/**
 * A collection of records can be read from/write to file.
 * Also store current setting.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine.awt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

public class BestRecord {

  //Protected Instance Methods
  protected Hashtable records;        //read only, use get(), put(GameRecord) to operate
  protected GameRecord current;

  //Private Instance Variable
  private final String filename;

  //Public Constant
  public static final String DEFAULT_GAME = "Beginner";

  //Public Constructor
  public BestRecord(String record_file) {
    filename = record_file;
    inputBestRecord();
  }

  //Public Methods
  /*  Called to read GameRecord from file
   *  Get Hashtable for storage of GameRecord
   *  Get current GameRecord to be used by Board
   */
  public void inputBestRecord() {
    try {        //read GameRecord from file
      FileInputStream fin = new FileInputStream(filename);
      ObjectInputStream oin = new ObjectInputStream(fin);
      records = (Hashtable) oin.readObject();
      current = (GameRecord) oin.readObject();
      oin.close();
      fin.close();
    } catch (Exception e) {       //use default if i/o exception
      records = new Hashtable(3);
      put(new GameRecord(GameDimension.BEGINNER));
      put(new GameRecord(GameDimension.INTERMEDIATE));
      put(new GameRecord(GameDimension.EXPERT));
      current = get(DEFAULT_GAME);
    }
  }

  /*  Called to save GameRecord to file
   *  Save Hashtable and current GameRecord
   */
  public void outputBestRecord() {
    try {       //save records to file
      FileOutputStream fout = new FileOutputStream(filename);
      ObjectOutputStream oout = new ObjectOutputStream(fout);
      oout.writeObject(records);
      oout.writeObject(current);
      oout.flush();
      oout.close();
      fout.close();
    } catch (Exception e) {
      System.out.println("Cann't save record: " + e.getMessage());
    }
  }

  //Helper Methods
  /*  Return GameRecord for a given level
   *  null may be returned if no such key in the Hashtable
   */
  public GameRecord get(String level) {
    return (GameRecord) records.get(level);
  }

  /*  Put a GameRecord to the Hashtable
   *  Use it level as key
   */
  public void put(GameRecord g) {
    records.put(g.level, g);
  }

  /*  Determine if Hashtable contains GameRecord
   *  Instead of search for records directly, search for key-level used
   */
  public boolean contains(GameRecord g) {
    return records.containsKey(g.level);
  }

}
