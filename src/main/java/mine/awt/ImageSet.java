
package mine.awt;

import mine.awt.*;
import java.awt.*;

public class ImageSet extends Component {
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
    public static final int PURE_JAVA = 29;

    public static final Image[] IMAGES = new Image[30];

    public ImageSet (java.util.Properties properties) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Class thisclass = getClass();

        IMAGES[ZERO] = toolkit.getImage(thisclass.getResource(properties.getProperty("ZERO")));
        IMAGES[ONE] = toolkit.getImage(thisclass.getResource(properties.getProperty("ONE")));
        IMAGES[TWO] = toolkit.getImage(thisclass.getResource(properties.getProperty("TWO")));
        IMAGES[THREE] = toolkit.getImage(thisclass.getResource(properties.getProperty("THREE")));
        IMAGES[FOUR] = toolkit.getImage(thisclass.getResource(properties.getProperty("FOUR")));
        IMAGES[FIVE] = toolkit.getImage(thisclass.getResource(properties.getProperty("FIVE")));
        IMAGES[SIX] = toolkit.getImage(thisclass.getResource(properties.getProperty("SIX")));
        IMAGES[SEVEN] = toolkit.getImage(thisclass.getResource(properties.getProperty("SEVEN")));
        IMAGES[EIGHT] = toolkit.getImage(thisclass.getResource(properties.getProperty("EIGHT")));
        IMAGES[CLOSED] = toolkit.getImage(thisclass.getResource(properties.getProperty("CLOSED")));
        IMAGES[MARKED] = toolkit.getImage(thisclass.getResource(properties.getProperty("MARKED")));
        IMAGES[DEAD] = toolkit.getImage(thisclass.getResource(properties.getProperty("DEAD")));
        IMAGES[MINE] = toolkit.getImage(thisclass.getResource(properties.getProperty("MINE")));
        IMAGES[CROSS] = toolkit.getImage(thisclass.getResource(properties.getProperty("CROSS")));
        IMAGES[DIGIT_ZERO] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_ZERO")));
        IMAGES[DIGIT_ONE] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_ONE")));
        IMAGES[DIGIT_TWO] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_TWO")));
        IMAGES[DIGIT_THREE] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_THREE")));
        IMAGES[DIGIT_FOUR] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_FOUR")));
        IMAGES[DIGIT_FIVE] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_FIVE")));
        IMAGES[DIGIT_SIX] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_SIX")));
        IMAGES[DIGIT_SEVEN] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_SEVEN")));
        IMAGES[DIGIT_EIGHT] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_EIGHT")));
        IMAGES[DIGIT_NINE] = toolkit.getImage(thisclass.getResource(properties.getProperty("DIGIT_NINE")));
        IMAGES[UP] = toolkit.getImage(thisclass.getResource(properties.getProperty("UP")));
        IMAGES[DOWN] = toolkit.getImage(thisclass.getResource(properties.getProperty("DOWN")));
        IMAGES[HAPPY] = toolkit.getImage(thisclass.getResource(properties.getProperty("HAPPY")));
        IMAGES[SAD] = toolkit.getImage(thisclass.getResource(properties.getProperty("SAD")));
        IMAGES[CONFUSED] = toolkit.getImage(thisclass.getResource(properties.getProperty("CONFUSED")));
        IMAGES[PURE_JAVA] = toolkit.getImage(thisclass.getResource(properties.getProperty("PURE_JAVA")));

        MediaTracker mt = new MediaTracker(this);
        for (int i=0; i<IMAGES.length; ++i)
            mt.addImage(IMAGES[i],i);
        try {
            mt.waitForAll();
        }
        catch (InterruptedException e) {
            throw new RuntimeException("Cannot Load Images");
        }
    }
}
