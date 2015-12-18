package utils;

public class SpriteSheet
{
  private Texture texture;
  private int width;
  private int height;
  
  public SpriteSheet(Texture texture, int size)
  {
    this(texture, size, size);
  }
  
  public SpriteSheet(Texture texture, int width, int height)
  {
    this.texture = texture;
    this.width = width;
    this.height = height;
  }
  
  public Texture getTexture()
  {
    return this.texture;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getHeight()
  {
    return this.height;
  }
}
