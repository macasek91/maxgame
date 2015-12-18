package sprites;

import java.awt.Color;
import java.awt.Graphics2D;
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


public class RyuSprite
{
  private static BufferedImage spriteSheet;
  private static int hSize;
  private static int vSize;
  int color;
  public static Image image;
  
  public RyuSprite(String file)
  {
    spriteSheet = null;
    try
    {
      spriteSheet = ImageIO.read(new File("resources/textures/" + file + ".gif"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static BufferedImage getPortrait()
  {
    return spriteSheet.getSubimage(13, 0, 115, 75);
  }
  
  public static BufferedImage getSprite(int xGrid, int yGrid)
  {
    int x = 13;
    int y = 0;
    vSize = 115;
    hSize = 75;
    if (yGrid == 0)
    {
      vSize = 115;
      y = 0;
      if (xGrid == 0)
      {
        x = 13;
        hSize = 75;
      }
      else if (xGrid == 1)
      {
        x = 87;
        hSize = 70;
      }
      else if (xGrid == 2)
      {
        x = 162;
        hSize = 75;
      }
      else if (xGrid == 3)
      {
        x = 241;
        hSize = 80;
      }
      else if (xGrid == 4)
      {
        x = 317;
        hSize = 70;
      }
      else if (xGrid == 5)
      {
        x = 390;
        hSize = 70;
      }
      else if (xGrid == 6)
      {
        x = 461;
        hSize = 75;
      }
      else
      {
        x = 531;
        y = 1;
        hSize = 70;
      }
    }
    if (yGrid == 1)
    {
      vSize = 115;
      y = 114;
      if (xGrid == 0)
      {
        x = 33;
        hSize = 75;
      }
      else if (xGrid == 1)
      {
        x = 105;
        hSize = 75;
      }
      else if (xGrid == 2)
      {
        x = 162;
        hSize = 75;
      }
      else if (xGrid == 3)
      {
        x = 241;
        hSize = 80;
      }
      else if (xGrid == 4)
      {
        x = 317;
        hSize = 70;
      }
      else if (xGrid == 5)
      {
        x = 390;
        hSize = 70;
      }
    }
    if (yGrid == 2)
    {
      vSize = 115;
      y = 228;
      if (xGrid == 0)
      {
        x = 60;
        hSize = 75;
      }
      else if (xGrid == 1)
      {
        x = 140;
        hSize = 80;
      }
      else if (xGrid == 2)
      {
        x = 220;
        hSize = 80;
      }
      else if (xGrid == 3)
      {
        x = 300;
        hSize = 70;
      }
      else if (xGrid == 4)
      {
        x = 370;
        hSize = 70;
      }
      else
      {
        x = 450;
        hSize = 70;
      }
    }
    if (yGrid == 3)
    {
      vSize = 115;
      y = 338;
      if (xGrid == 0)
      {
        x = 60;
        hSize = 75;
      }
      else if (xGrid == 1)
      {
        x = 140;
        hSize = 75;
      }
      else if (xGrid == 2)
      {
        x = 215;
        hSize = 70;
      }
      else if (xGrid == 3)
      {
        x = 285;
        hSize = 70;
      }
      else if (xGrid == 4)
      {
        x = 360;
        hSize = 70;
      }
      else
      {
        x = 450;
        hSize = 70;
      }
    }
    if (yGrid == 18)
    {
      vSize = 115;
      y = 2065;
      if (xGrid == 5)
      {
        x = 370;
        y = 2065;
        hSize = 75;
      }
      else if (xGrid == 6)
      {
        x = 455;
        y = 2060;
        hSize = 82;
      }
      else
      {
        x = 555;
        y = 2030;
        hSize = 75;
        vSize = 130;
      }
    }
    if (yGrid == 19)
    {
      vSize = 115;
      y = 338;
      if (xGrid == 0)
      {
        x = 5;
        y = 2180;
        hSize = 70;
        vSize = 130;
      }
      else if (xGrid == 1)
      {
        x = 80;
        y = 2180;
        hSize = 80;
        vSize = 130;
      }
      else if (xGrid == 2)
      {
        x = 165;
        y = 2200;
        hSize = 75;
        vSize = 120;
      }
    }
    return spriteSheet.getSubimage(x, y, hSize, vSize);
  }
  
  public BufferedImage getScaledSprite(int scale, int xGrid, int yGrid)
  {
    BufferedImage beforeScale = getSprite(xGrid, yGrid);
    BufferedImage afterScale = Scalr.resize(beforeScale, scale, new BufferedImageOp[0]);
    
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
  
  public static BufferedImage toBufferedImage(Image img)
  {
    BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), 2);
    Graphics2D g2 = bufferedImage.createGraphics();
    g2.drawImage(img, 0, 0, null);
    g2.dispose();
    
    return bufferedImage;
  }
}
