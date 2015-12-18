package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Portrait
{
  private int x;
  private int y;
  private BufferedImage pic;
  @SuppressWarnings("unused")
  private boolean selected;
  
  public Portrait(BufferedImage pic, int x, int y)
  {
    this.pic = pic;
    this.x = x;
    this.y = y;
  }
  
  public void setSelected(boolean selected)
  {
    this.selected = true;
  }
  
  public void render(Graphics g)
  {
    g.drawImage(this.pic, this.x, this.y, null);
  }
}
