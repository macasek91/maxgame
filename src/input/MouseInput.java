package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput
  extends MouseAdapter
{
  private static final int NUM_BUTTONS = 10;
  private static final boolean[] buttons = new boolean[NUM_BUTTONS];
  private static final boolean[] lastButtons = new boolean[NUM_BUTTONS];
  private static int x = -1;
  private static int y = -1;
  public static int lastX = x;
  public static int lastY = y;
  private static boolean moving;
  
  public void mousePressed(MouseEvent e)
  {
    buttons[e.getButton()] = true;
  }
  
  public void mouseReleased(MouseEvent e)
  {
    buttons[e.getButton()] = false;
  }
  
  public void mouseMoved(MouseEvent e)
  {
    x = e.getX();
    y = e.getY();
    moving = true;
  }
  
  public static void update()
  {
    for (int i = 0; i < 10; i++) {
      lastButtons[i] = buttons[i];
    }
    if ((x == lastX) && (y == lastY)) {
      moving = false;
    }
    lastX = x;
    lastY = y;
  }
  
  public static boolean isDown(int button)
  {
    return buttons[button];
  }
  
  public static boolean wasPressed(int button)
  {
    return (isDown(button)) && (lastButtons[button] == 0);
  }
  
  public static boolean wasReleased(int button)
  {
    return (!isDown(button)) && (lastButtons[button] != 0);
  }
  
  public static int getX()
  {
    return x;
  }
  
  public static int getY()
  {
    return y;
  }
  
  public static boolean isMoving()
  {
    return moving;
  }
}

