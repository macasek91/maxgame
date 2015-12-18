package layouts;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import gui.Portrait;
import input.KeyInput;
import sprites.RyuSprite;
import sprites.Sprite;

public class CharSelect
  implements Layout
{
  private static Portrait[] options;
  private static int currentSelection;
  
  public CharSelect()
  {
    options = new Portrait[1];
    options[0] = new Portrait(RyuSprite.getPortrait(), 50, 50);
    options[1] = new Portrait(Sprite.getPortrait(), 100, 50);
  }
  
  public void render(Graphics g)
  {
    g.setColor(Color.GREEN);
    g.fillRect(0, 0, 800, 500);
    for (int i = 0; i < options.length; i++)
    {
      if (i == currentSelection) {
        options[i].setSelected(true);
      } else {
        options[i].setSelected(false);
      }
      options[i].render(g);
    }
  }
  
  public void tick()
  {
    if ((KeyInput.wasPressed(38)) || (KeyInput.wasPressed(87)))
    {
      currentSelection -= 1;
      if (currentSelection < 0) {
        currentSelection = options.length - 1;
      }
    }
    if ((KeyInput.wasPressed(40)) || (KeyInput.wasPressed(83)))
    {
      currentSelection += 1;
      if (currentSelection >= options.length) {
        currentSelection = 0;
      }
    }
    if (KeyInput.wasPressed(10)) {
      select();
    }
  }
  
  public void select()
  {
    switch (currentSelection)
    {
    case 0: 
      Game.gsm.ChangeToMainGameState();
      break;
    case 1: 
      Game.gsm.ChangeToMainGameState();
    }
  }
}
