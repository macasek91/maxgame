package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import input.KeyInput;
import input.MouseInput;
import states.GSManager;

public class Game
  extends Canvas
  implements Runnable
{
  private static final long serialVersionUID = 1L;
  public static final String TITLE = "Game";
  public static final int WIDTH = 800;
  public static final int HEIGHT = 500;
  public static GSManager gsm;
  public static Game INSTANCE;
  private boolean running;
  
  public Game()
  {
    gsm = new GSManager();
    
    INSTANCE = this;
    
    addKeyListener(new KeyInput());
    MouseInput mi = new MouseInput();
    addMouseListener(mi);
    addMouseMotionListener(mi);
    System.out.println("Button 1: 1");
    System.out.println("Button 2: 2");
    System.out.println("Button 3: 3");
  }
  
  private void tick()
  {
    gsm.GameStateUpdate();
  }
  
  private void render()
  {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null)
    {
      createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    gsm.render(g);
    
    g.dispose();
    bs.show();
  }
  
  private void start()
  {
    if (this.running) {
      return;
    }
    this.running = true;
    new Thread(this, "GameMain-Thread").start();
  }
  
  private void stop()
  {
    if (!this.running) {
      return;
    }
    this.running = false;
  }
  
  //private void pause() throws InterruptedException {}
  
  public void run()
  {
    requestFocus();
    double target = 60.0D;
    double nsPerTick = 1.0E9D / target;
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    double unprocessed = 0.0D;
    int fps = 0;
    int tps = 0;
    boolean canRender = false;
    while (this.running)
    {
      long now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;
      if (unprocessed >= 1.0D)
      {
        tick();
        KeyInput.update();
        MouseInput.update();
        unprocessed -= 1.0D;
        tps++;
        canRender = true;
      }
      else
      {
        canRender = false;
      }
      try
      {
        Thread.sleep(1L);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      if (canRender)
      {
        render();
        fps++;
      }
      if (System.currentTimeMillis() - 1000L > timer)
      {
        timer += 1000L;
        System.out.printf("FPS: %d | TPS: %d\n", new Object[] { Integer.valueOf(fps), Integer.valueOf(tps) });
        fps = 0;
        tps = 0;
      }
    }
    System.exit(0);
  }
  
  public static void instanceStop()
  {
    INSTANCE.stop();
  }
  
  public static void main(String[] args)
  {
    final Game game = new Game();
    JFrame frame = new JFrame("Game");
    frame.add(game);
    frame.setSize(800, 500);
    frame.setResizable(false);
    frame.setFocusable(true);
    frame.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        System.err.println("Exiting Game");
        game.stop();
      }
    });
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    game.start();
  }
}
