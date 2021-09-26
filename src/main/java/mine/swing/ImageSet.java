package mine.swing;

import javax.swing.*;


public class ImageSet {
  public static final int ZERO = 0;
  public static final int ONE = 1;
  public static final int TWO = 2;
  public static final int THREE = 3;
  public static final int FOUR = 4;
  public static final int FIVE = 5;
  public static final int SIX = 6;
  public static final int SEVEN = 7;
  public static final int EIGHT = 8;
  public static final int CLOSED = 9;
  public static final int MARKED = 10;
  public static final int DEAD = 11;
  public static final int MINE = 12;
  public static final int CROSS = 13;
  public static final int DIGIT_ZERO = 14;
  public static final int DIGIT_ONE = 15;
  public static final int DIGIT_TWO = 16;
  public static final int DIGIT_THREE = 17;
  public static final int DIGIT_FOUR = 18;
  public static final int DIGIT_FIVE = 19;
  public static final int DIGIT_SIX = 20;
  public static final int DIGIT_SEVEN = 21;
  public static final int DIGIT_EIGHT = 22;
  public static final int DIGIT_NINE = 23;
  public static final int UP = 24;
  public static final int DOWN = 25;
  public static final int HAPPY = 26;
  public static final int SAD = 27;
  public static final int CONFUSED = 28;
  public static final int DUKE = 29;

  public static final Icon[] ICONS = new Icon[30];

  public ImageSet(java.util.Properties properties) {
    Class thisclass = getClass();
    java.net.URL[] url = new java.net.URL[ICONS.length];
    try {
      url[ZERO] = thisclass.getResource(properties.getProperty("ZERO"));
      url[ONE] = thisclass.getResource(properties.getProperty("ONE"));
      url[TWO] = thisclass.getResource(properties.getProperty("TWO"));
      url[THREE] = thisclass.getResource(properties.getProperty("THREE"));
      url[FOUR] = thisclass.getResource(properties.getProperty("FOUR"));
      url[FIVE] = thisclass.getResource(properties.getProperty("FIVE"));
      url[SIX] = thisclass.getResource(properties.getProperty("SIX"));
      url[SEVEN] = thisclass.getResource(properties.getProperty("SEVEN"));
      url[EIGHT] = thisclass.getResource(properties.getProperty("EIGHT"));
      url[CLOSED] = thisclass.getResource(properties.getProperty("CLOSED"));
      url[MARKED] = thisclass.getResource(properties.getProperty("MARKED"));
      url[DEAD] = thisclass.getResource(properties.getProperty("DEAD"));
      url[MINE] = thisclass.getResource(properties.getProperty("MINE"));
      url[CROSS] = thisclass.getResource(properties.getProperty("CROSS"));
      url[DIGIT_ZERO] = thisclass.getResource(properties.getProperty("DIGIT_ZERO"));
      url[DIGIT_ONE] = thisclass.getResource(properties.getProperty("DIGIT_ONE"));
      url[DIGIT_TWO] = thisclass.getResource(properties.getProperty("DIGIT_TWO"));
      url[DIGIT_THREE] = thisclass.getResource(properties.getProperty("DIGIT_THREE"));
      url[DIGIT_FOUR] = thisclass.getResource(properties.getProperty("DIGIT_FOUR"));
      url[DIGIT_FIVE] = thisclass.getResource(properties.getProperty("DIGIT_FIVE"));
      url[DIGIT_SIX] = thisclass.getResource(properties.getProperty("DIGIT_SIX"));
      url[DIGIT_SEVEN] = thisclass.getResource(properties.getProperty("DIGIT_SEVEN"));
      url[DIGIT_EIGHT] = thisclass.getResource(properties.getProperty("DIGIT_EIGHT"));
      url[DIGIT_NINE] = thisclass.getResource(properties.getProperty("DIGIT_NINE"));
      url[UP] = thisclass.getResource(properties.getProperty("UP"));
      url[DOWN] = thisclass.getResource(properties.getProperty("DOWN"));
      url[HAPPY] = thisclass.getResource(properties.getProperty("HAPPY"));
      url[SAD] = thisclass.getResource(properties.getProperty("SAD"));
      url[CONFUSED] = thisclass.getResource(properties.getProperty("CONFUSED"));
      url[DUKE] = thisclass.getResource(properties.getProperty("DUKE"));
    } catch (NullPointerException e) {
      System.out.println("Property not defined.");
      e.printStackTrace();
      System.exit(1);
    }
    for (int i = 0; i < ICONS.length; ++i)
      ICONS[i] = new ImageIcon(url[i]);
  }
}

