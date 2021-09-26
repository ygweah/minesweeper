/**
 * A collection of records can be read from/write to file.
 * Also store current setting.
 *
 * @author Yuhong Guo
 * @version 3.5
 */

package mine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public class BestRecord {

  //Public Instance Methods
  public GameRecord current;

  //Protected Instance Methods
  protected Hashtable records;
  protected Properties properties;

  //Public Constant
  public static final String DEFAULT_GAME = "Beginner";

  //Public Constructor
  public BestRecord() {
    records = new Hashtable(4);
    properties = new Properties();
    current = null;
  }

  //Public Methods
  public void resetRecord() {
    Enumeration enumeration = records.elements();
    while (enumeration.hasMoreElements())
      ((GameRecord) enumeration.nextElement()).reset();
  }

  public void setCurrent(String level) {
    Object newone = records.get(level);
    if (newone != null)
      current = (GameRecord) newone;
  }

  public int getTime(String level) {
    Object record = records.get(level);
    if (record == null)
      return GameRecord.MAX_TIME;
    else
      return ((GameRecord) record).time;
  }

  public String getName(String level) {
    Object record = records.get(level);
    if (record == null)
      return GameRecord.DEFAULT_USER;
    else
      return ((GameRecord) record).name;
  }

  public void load(String filename) {
    try {
      properties.load(new FileInputStream(filename));
    } catch (IOException e) {
    }

    inputRecord("Beginner");
    inputRecord("Intermediate");
    inputRecord("Expert");

    String currentlevel = properties.getProperty("Current");
    if (currentlevel == null)
      currentlevel = DEFAULT_GAME;

    current = (GameRecord) records.get(currentlevel);
    if (current == null)
      current = (GameRecord) records.get(DEFAULT_GAME);
  }

  public void store(String filename) {
    outputRecord((GameRecord) records.get("Beginner"));
    outputRecord((GameRecord) records.get("Intermediate"));
    outputRecord((GameRecord) records.get("Expert"));

    properties.setProperty("Current", current.level);
    try {
      properties.store(new FileOutputStream(filename), "Minesweeper Record");
    } catch (IOException e) {
    }
  }

  private void inputRecord(String level) {
    GameDimension dimension = null;
    String name = null;
    int time = -1;

    dimension = GameDimension.getByName(level);
    if (dimension == null)
      dimension = GameDimension.BEGINNER;

    name = properties.getProperty(level);
    if (name == null)
      records.put(level, new GameRecord(dimension));
    else {
      try {
        time = Integer.parseInt(properties.getProperty(level + "Time"));
      } catch (Exception e) {
      }
      if (time == -1)
        records.put(level, new GameRecord(dimension));
      else
        records.put(level, new GameRecord(name, time, dimension));
    }
  }

  private void outputRecord(GameRecord record) {
    if (record == null)
      return;
    properties.setProperty(record.level, record.name);
    properties.setProperty(record.level + "Time", record.time + "");
  }
}
