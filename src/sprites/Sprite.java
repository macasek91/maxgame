package sprites;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class Sprite
{
  private static BufferedImage spriteSheet;
  //private static final int TILE_SIZE = 32;
  
  public Sprite(String file)
  {
    spriteSheet = null;
    try
    {
      spriteSheet = ImageIO.read(new File("resources/textures/" + file + ".png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static BufferedImage getPortrait()
  {
    return spriteSheet.getSubimage(0, 0, 32, 32);
  }
  
  public static BufferedImage getSprite(int xGrid, int yGrid)
  {
    return spriteSheet.getSubimage(xGrid * 32, yGrid * 32, 32, 32);
  }
  
  public static BufferedImage getScaledSprite(int Scale, int xGrid, int yGrid)
  {
    BufferedImage beforeScale = spriteSheet.getSubimage(xGrid * 32, yGrid * 32, 32, 32);
    BufferedImage afterScale = Scalr.resize(beforeScale, Scalr.Method.QUALITY, 32 * Scale, 32 * Scale, new BufferedImageOp[0]);
    
    return afterScale;
  }
  
  public static Image makeColorTransparent(BufferedImage im, Color color)
  {
    ImageFilter filter = new RGBImageFilter()
    {
      public int markerRGB;
      
      public final int filterRGB(int x, int y, int rgb)
      {
        if ((rgb | 0xFF000000) == this.markerRGB) {
          return 0xFFFFFF & rgb;
        }
        return rgb;
      }
    };
    ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
    return Toolkit.getDefaultToolkit().createImage(ip);
  }
  
  @SuppressWarnings("unused")
private static BufferedImage imageToBufferedImage(Image image)
  {
    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
    return bufferedImage;
  }
}
