package layouts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;
import gui.Button;
import gui.Fonts;
import input.KeyInput;
import input.MouseInput;

public class Menu
{
  private static Button[] options;
  private static int currentSelection;
  
  public Menu()
  {
    options = new Button[3];
    options[0] = new Button("Play", 200, 
      new Font("Arial", 0, 32), new Font("Arial", 1, 48), 
      Color.WHITE, Color.YELLOW);
    options[1] = new Button("Options", 280, 
      new Font("Arial", 0, 32), new Font("Arial", 1, 48), 
      Color.WHITE, Color.YELLOW);
    options[2] = new Button("Exit", 360, 
      new Font("Arial", 0, 32), new Font("Arial", 1, 48), 
      Color.WHITE, Color.YELLOW);
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
    boolean clicked = false;
    for (int i = 0; i < options.length; i++) {
      if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1)))
      {
        currentSelection = i;
        clicked = MouseInput.wasPressed(1);
      }
    }
    if ((clicked) || (KeyInput.wasPressed(10))) {
      select();
    }
  }
  
  private void select()
  {
    switch (currentSelection)
    {
    case 0: 
      System.out.println("Play");
      Game.gsm.ChangeToMainGameState();
      break;
    case 1: 
      System.out.println("Options");
      break;
    case 2: 
      System.out.println("Exit");
      Game.instanceStop();
    }
  }
  
  public void render(Graphics g)
  {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 800, 500);
    Fonts.drawString(g, new Font("Arial", 1, 72), Color.ORANGE, "Game", 80);
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
}
