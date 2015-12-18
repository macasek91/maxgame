package utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CharacterLoader
{
  private String charName;
  final int width = 64;
  final int height = 64;
  final int rows = 4;
  final int cols = 4;
  public int x = 300;
  public int y = 300;
  public BufferedImage[] sprites = new BufferedImage[16];
  
  public CharacterLoader(String charName)
  {
    setCharName(charName);
    BufferedImage charSheet = null;
    try
    {
      charSheet = ImageIO.read(new File("./resources/textures/" + charName + ".png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        this.sprites[(i * 4 + j)] = charSheet.getSubimage(
          j * 64, 
          i * 64, 
          64, 
          64);
      }
    }
  }
  
  public String getCharName()
  {
    return this.charName;
  }
  
  public void setCharName(String charName)
  {
    this.charName = charName;
  }
  
  public void render(int i, Graphics g, double x, double y)
  {
    g.drawImage(this.sprites[i], (int)x, (int)y, null);
  }
  
  public BufferedImage[] getSprites()
  {
    return this.sprites;
  }
}
