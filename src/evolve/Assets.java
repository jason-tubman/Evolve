package evolve;

import java.awt.image.BufferedImage;

/**
 * Created by Jason on 28/10/2016.
 */
public class Assets {

    public static BufferedImage creatureSprite;

    public static void init() {

        creatureSprite = imageLoader.loadImage("/resources/Placeholder.png");

    }
}
