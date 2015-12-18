package states;

import input.KeyInput;
import java.awt.Color;
import java.awt.Graphics;
import layouts.CharSelect;
import layouts.Menu;
import sprites.Animation;
import sprites.RyuPlayer;
import sprites.RyuSprite;
import sprites.Sprite;

public class GSManager
{
  public static GameStated gamestate;
  public static Menu menu;
  public static CharSelect charSelect;
  public Sprite p1Sprite;
  public static RyuSprite p2Sprite;
  public static RyuSprite p3Sprite;
  public static RyuSprite p4Sprite;
  public static Animation p1Animation;
  public static RyuPlayer player1;
  public static RyuPlayer player2;
  public static RyuPlayer player3;
  public static RyuPlayer player4;
  
  public static enum GameStated
  {
    TITLE_STATE,  MAINGAME_STATE,  PAUSE_STATE,  CHARSELECT_STATE;
  }
  
  public GSManager()
  {
    gamestate = GameStated.MAINGAME_STATE;
    menu = new Menu();
    
    p2Sprite = new RyuSprite("RyuSFA3");
    
    player1 = new RyuPlayer(1);
    player2 = new RyuPlayer(2);
    player3 = new RyuPlayer(3);
    player4 = new RyuPlayer(4);
  }
  
  public void GameStateUpdate()
  {
    switch (gamestate)
    {
    case CHARSELECT_STATE: 
      UpdateTitleScreen();
      
      break;
    case MAINGAME_STATE: 
      UpdateMainGame();
      
      break;
    case PAUSE_STATE: 
      UpdatePause();
      
      break;
    case TITLE_STATE: 
//      UpdateCharSelect();
      break;
    }
  }
  
  public void render(Graphics g)
  {
    switch (gamestate)
    {
    case CHARSELECT_STATE: 
      menu.render(g);
      
      break;
    case MAINGAME_STATE: 
      g.setColor(Color.BLUE);
      g.fillRect(0, 0, 800, 500);
      
      g.drawImage(player1.ani.getSprite(), player1.getX(), player1.getY(), null);
      g.drawImage(player2.ani.getSprite(), player2.getX(), player2.getY(), null);
      g.drawImage(player3.ani.getSprite(), player3.getX(), player3.getY(), null);
      g.drawImage(player4.ani.getSprite(), player4.getX(), player4.getY(), null);
      
      player1.ani.start();
      player2.ani.start();
      player3.ani.start();
      player4.ani.start();
      
      break;
    case PAUSE_STATE: 
      break;
    case TITLE_STATE: 
      //charSelect.render(g);
      break;
    }
  }
  
  private void UpdatePause() {}
  
  private void UpdateMainGame()
  {
    player1.ani.update();
    player2.ani.update();
    player3.ani.update();
    player4.ani.update();
    
    player1.checkControls();
    player2.checkControls();
    player3.checkControls();
    player4.checkControls();
    if (KeyInput.wasPressed(27)) {
      ChangeToTitleState();
    }
  }
  
  private void UpdateTitleScreen()
  {
    menu.tick();
  }
  
//  private void UpdateCharSelect()
//  {
//    charSelect.tick();
//  }
  
  public void ChangeToTitleState()
  {
    gamestate = GameStated.TITLE_STATE;
  }
  
  public void ChangeToMainGameState()
  {
    gamestate = GameStated.MAINGAME_STATE;
  }
  
  public void ChangeToCharSelectState()
  {
    gamestate = GameStated.CHARSELECT_STATE;
  }
}
