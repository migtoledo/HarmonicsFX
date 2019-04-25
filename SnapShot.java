
/**
 *
 * @author mtg
 * 
 */

package packHarmonicsFXd5;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SnapShot 
{
    
    public static BufferedImage getScreenShot(Component component) 
    {
        int w=component.getWidth();
        int h=component.getHeight();

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // paints into image's Graphics
        component.paint(image.getGraphics());
        return image;
    }
    
    public static void getSaveSnapShot(Component component, String fileName) throws Exception 
    {
        BufferedImage img = getScreenShot(component);
        // write the captured image as a PNG
        ImageIO.write(img, "png", new File(fileName));
    }
    
}
