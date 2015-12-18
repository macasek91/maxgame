package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button
  extends Rectangle
{
  private static final long serialVersionUID = 1L;
  private Font font;
  private Font selectedFont;
  private Color color;
  private Color selectedColor;
  private boolean selected;
  private String text;
  private int textY;
  
  public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor)
  {
    this.text = text;
    this.textY = textY;
    this.font = font;
    this.selectedFont = selectedFont;
    this.color = color;
    this.selectedColor = selectedColor;
  }
  
  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }
  
  public void render(Graphics g)
  {
    if (this.selected) {
      Fonts.drawString(g, this.selectedFont, this.selectedColor, this.text, this.textY);
    } else {
      Fonts.drawString(g, this.font, this.color, this.text, this.textY);
    }
    FontMetrics fm = g.getFontMetrics();
    this.x = ((800 - fm.stringWidth(this.text)) / 2);
    this.y = (this.textY - fm.getHeight());
    this.width = fm.stringWidth(this.text);
    this.height = fm.getHeight();
    g.drawRect(this.x, this.y, this.width, this.height);
  }
}
