package layouts;

import java.awt.Graphics;

public abstract interface Layout
{
  public abstract void tick();
  
  public abstract void select();
  
  public abstract void render(Graphics paramGraphics);
}
