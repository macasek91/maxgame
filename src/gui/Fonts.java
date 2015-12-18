package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Fonts
{
  public static void drawString(Graphics g, Font f, Color c, String text, int x, int y)
  {
    g.setColor(c);
    g.setFont(f);
    g.drawString(text, x, y);
  }
  
  public static void drawString(Graphics g, Font f, Color c, String text)
  {
    FontMetrics fm = g.getFontMetrics(f);
    int x = (800 - fm.stringWidth(text)) / 2;
    int y = (500 - fm.getHeight()) / 2 + fm.getAscent();
    drawString(g, f, c, text, x, y);
  }
  
  public static void drawString(Graphics g, Font f, Color c, String text, double x)
  {
    FontMetrics fm = g.getFontMetrics(f);
    int y = (500 - fm.getHeight()) / 2 + fm.getAscent();
    drawString(g, f, c, text, (int)x, y);
  }
  
  public static void drawString(Graphics g, Font f, Color c, String text, int y)
  {
    FontMetrics fm = g.getFontMetrics(f);
    int x = (800 - fm.stringWidth(text)) / 2;
    drawString(g, f, c, text, x, y);
  }
}

