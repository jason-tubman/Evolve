package evolve;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Jason on 27/10/16.
 */
public class imageLoader {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(imageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }


}


