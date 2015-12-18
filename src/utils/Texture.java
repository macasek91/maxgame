package utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Texture
{
  @SuppressWarnings({ "rawtypes", "unchecked" })
private static final Map<String, TextureManager> texMap = new HashMap();
  private TextureManager manager;
  private String fileName;
  
  public Texture(String fileName)
  {
    this.fileName = fileName;
    TextureManager oldTexture = (TextureManager)texMap.get(fileName);
    if (oldTexture != null)
    {
      this.manager = oldTexture;
      
      this.manager.addReference();
    }
    else
    {
      try
      {
        System.out.println("Loading texture: " + fileName);
        this.manager = new TextureManager(ImageIO.read(new File("./resources/textures/" + fileName + ".png")));
        texMap.put(fileName, this.manager);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  protected void finalize()
    throws Throwable
  {
    if ((this.manager.removeReference()) && (!this.fileName.isEmpty())) {
      texMap.remove(this.fileName);
    }
    super.finalize();
  }
  
  public void render(Graphics g, double x, double y)
  {
    g.drawImage(this.manager.getImage(), (int)x, (int)y, null);
  }
  
  public BufferedImage getImage()
  {
    return this.manager.getImage();
  }
}
