package evolve;

import java.awt.image.BufferedImage;

/**
 * Created by Jason on 28/10/2016.
 */
public class Assets {

    public static BufferedImage herbivoreSprite;
    public static BufferedImage foodSprite;
    public static BufferedImage carnivoreSprite;
    public static BufferedImage eggSprite;


    public static void init() {
        carnivoreSprite = imageLoader.loadImage("/resources/carnivore.png");
        herbivoreSprite = imageLoader.loadImage("/resources/Herbivore.png");
        foodSprite = imageLoader.loadImage("/resources/food.png");
        eggSprite = imageLoader.loadImage("/resources/egg.png");
    }
}
