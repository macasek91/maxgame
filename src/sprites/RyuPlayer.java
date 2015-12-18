package sprites;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import handler.CollisionDetection;
import input.KeyInput;

public class RyuPlayer
  extends CollisionDetection
{
  public int x;
  public int y;
  private int ryuWidth = 50;
  private int ryuHeight = 80;
  public Rectangle hitBox;
  public Animation ani;
  public int player = 0;
  int pX = 0;
  int pY = 0;
  private int startingY;
  private boolean isAnimating = false;
  private boolean isShoryuken = false;
  private boolean isCollision = false;
  private boolean isCollision2 = false;
  @SuppressWarnings("unused")
  private boolean isInvincible = false;
  public int ryuCounter = 0;
  public int shoryuCounter = 0;
  private BufferedImage[] walkingLeft = { RyuSprite.getSprite(0, 3), RyuSprite.getSprite(1, 3), RyuSprite.getSprite(2, 3), RyuSprite.getSprite(3, 3), RyuSprite.getSprite(4, 3), RyuSprite.getSprite(5, 3) };
  private BufferedImage[] walkingRight = { RyuSprite.getSprite(0, 2), RyuSprite.getSprite(1, 2), RyuSprite.getSprite(2, 2), RyuSprite.getSprite(3, 2), RyuSprite.getSprite(4, 2), RyuSprite.getSprite(5, 2) };
  private BufferedImage[] walkingUp = { RyuSprite.getSprite(0, 2), RyuSprite.getSprite(1, 2), RyuSprite.getSprite(2, 2), RyuSprite.getSprite(3, 2), RyuSprite.getSprite(4, 2), RyuSprite.getSprite(5, 2) };
  private BufferedImage[] walkingDown = { RyuSprite.getSprite(0, 2), RyuSprite.getSprite(1, 2), RyuSprite.getSprite(2, 2), RyuSprite.getSprite(3, 2), RyuSprite.getSprite(4, 2), RyuSprite.getSprite(4, 2), RyuSprite.getSprite(5, 2) };
  private BufferedImage[] idle = { RyuSprite.getSprite(0, 0), RyuSprite.getSprite(1, 0), RyuSprite.getSprite(2, 0), RyuSprite.getSprite(2, 0), RyuSprite.getSprite(2, 0), RyuSprite.getSprite(3, 0), RyuSprite.getSprite(3, 0), RyuSprite.getSprite(3, 0), RyuSprite.getSprite(4, 0), RyuSprite.getSprite(5, 0), RyuSprite.getSprite(6, 0), RyuSprite.getSprite(5, 0), RyuSprite.getSprite(6, 0), RyuSprite.getSprite(0, 1), RyuSprite.getSprite(7, 0), RyuSprite.getSprite(7, 0), RyuSprite.getSprite(0, 1) };
  private BufferedImage[] shoryu = { RyuSprite.getSprite(5, 18), RyuSprite.getSprite(6, 18), RyuSprite.getSprite(7, 18), RyuSprite.getSprite(0, 19), RyuSprite.getSprite(1, 19), RyuSprite.getSprite(2, 19), RyuSprite.getSprite(2, 19), RyuSprite.getSprite(2, 19), RyuSprite.getSprite(2, 19) };
  private Animation wLeft = new Animation(this.walkingLeft, 8);
  private Animation wRight = new Animation(this.walkingRight, 8);
  private Animation wUp = new Animation(this.walkingUp, 8);
  private Animation wDown = new Animation(this.walkingDown, 8);
  private Animation standing = new Animation(this.idle, 8);
  private Animation shoryuken = new Animation(this.shoryu, 8);
  private Animation animation = this.standing;
  private Rectangle r;
  
  public RyuPlayer(int player)
  {
    this.hitBox = new Rectangle(this.x, this.y, this.ryuWidth, this.ryuHeight);
    this.player = player;
    this.ani = stand();
    if (player == 1)
    {
      setX(0);
      setY(20);
      System.out.println(getX() + player);
    }
    else if (player == 2)
    {
      setX(0);
      setY(350);
      System.out.println(getX() + player);
    }
    else if (player == 3)
    {
      setX(700);
      setY(20);
      System.out.println(getX() + player);
    }
    else
    {
      setX(700);
      setY(350);
      System.out.println(getX() + player);
    }
  }
  
  public Animation stand()
  {
    return this.standing;
  }
  
  public Animation getAnimation()
  {
    return this.animation;
  }
  
  public void setAnimation(Animation animation)
  {
    this.animation = animation;
  }
  
  public void checkControls()
  {
    this.hitBox.setBounds(this.x + 10, this.y + 5, this.ryuWidth, this.ryuHeight + 10);
    if (!this.isAnimating)
    {
      if (((KeyInput.isDown(37)) && (this.player == 1)) || ((KeyInput.isDown(76)) && (this.player == 2)))
      {
        this.ani = this.wLeft;
        this.ani.start();
        this.x -= 2;
      }
      if (((KeyInput.wasReleased(37)) && (this.player == 1)) || ((KeyInput.wasReleased(76)) && (this.player == 2)))
      {
        this.ani.stop();
        this.ani.reset();
        this.ani = this.standing;
      }
      if (((KeyInput.isDown(39)) && (this.player == 1)) || ((KeyInput.isDown(222)) && (this.player == 2)))
      {
        this.ani = this.wRight;
        this.ani.start();
        this.x += 2;
      }
      if (((KeyInput.wasReleased(39)) && (this.player == 1)) || ((KeyInput.wasReleased(222)) && (this.player == 2)))
      {
        this.ani.stop();
        this.ani.reset();
        this.ani = this.standing;
      }
      if (((KeyInput.isDown(40)) && (this.player == 1)) || ((KeyInput.isDown(59)) && (this.player == 2)))
      {
        this.ani = this.wDown;
        this.ani.start();
        this.y += 2;
      }
      if (((KeyInput.wasReleased(40)) && (this.player == 1)) || ((KeyInput.wasReleased(59)) && (this.player == 2)))
      {
        this.ani.stop();
        this.ani.reset();
        this.ani = this.standing;
      }
      if (((KeyInput.isDown(38)) && (this.player == 1)) || ((KeyInput.isDown(80)) && (this.player == 2)))
      {
        this.ani = this.wUp;
        this.ani.start();
        this.y -= 2;
      }
      if (((KeyInput.wasReleased(38)) && (this.player == 1)) || ((KeyInput.wasReleased(80)) && (this.player == 2)))
      {
        this.ani.stop();
        this.ani.reset();
        this.ani = this.standing;
      }
      if (((KeyInput.wasPressed(17)) && (this.player == 1)) || ((KeyInput.wasPressed(79)) && (this.player == 2)))
      {
        this.isAnimating = true;
        this.isShoryuken = true;
        
        this.ani = this.shoryuken;
        this.ani.oneTime();
        this.startingY = getY();
        this.shoryuCounter = 0;
      }
    }
    else if (this.isShoryuken)
    {
      shoryuken();
    }
    else if (this.isCollision)
    {
      DoCollision();
    }
    else if (this.isCollision2)
    {
      DoCollision2();
    }
  }
  
  private void shoryuken()
  {
    if (this.shoryuCounter == 0)
    {
      this.y -= 25;
      this.isInvincible = true;
      this.isCollision = false;
      this.isCollision2 = false;
    }
    else if (this.shoryuCounter < 20)
    {
      this.y -= 15;
    }
    else if (this.shoryuCounter >= 72)
    {
      this.y = this.startingY;
      this.isShoryuken = false;
      this.isAnimating = false;
      this.ani.stop();
      this.ani.reset();
      this.ani = this.standing;
    }
    else
    {
      this.isInvincible = false;
      this.y += 5;
    }
    this.shoryuCounter += 1;
  }
  
  public void DoCollision()
  {
    if (this.ryuCounter == 0)
    {
      this.y += 25 * this.pY;
      this.x += 20 * this.pX;
    }
    else if (this.ryuCounter >= 20)
    {
      this.isAnimating = false;
      this.isCollision = false;
      this.ani.stop();
      this.ani.reset();
      this.ani = this.standing;
    }
    else
    {
      this.y += 5 * this.pY;
      this.x += 5 * this.pX;
    }
    this.ryuCounter += 1;
  }
  
  private void DoCollision2()
  {
    if (this.ryuCounter == 0)
    {
      System.out.println(this.r.x + " " + this.pX + " " + this.x + " " + this.y + this.isCollision + this.isCollision2 + this.player);
      
      this.x += 10 * this.pX;
      this.y += 1 * this.pY;
    }
    else if (this.ryuCounter >= 20)
    {
      this.isAnimating = false;
      this.isCollision = false;
      this.isCollision2 = false;
      this.ani.stop();
      this.ani.reset();
      this.ani = this.standing;
      System.out.println("doh" + this.player);
    }
    else
    {
      System.out.println(this.player + "  " + this.x + "    " + this.y + " " + this.pX + "  " + this.pY);
      this.x += 5 * this.pX;
      this.y += 1 * this.pY;
    }
    this.ryuCounter += 1;
  }
  
  public int getX()
  {
    return this.x;
  }
  
  public void setX(int x)
  {
    this.x = x;
  }
  
  public int getY()
  {
    return this.y;
  }
  
  public void setY(int y)
  {
    this.y = y;
  }
  
  public int getRyuWidth()
  {
    return this.ryuWidth;
  }
  
  public void setRyuWidth(int ryuWidth)
  {
    this.ryuWidth = ryuWidth;
  }
  
  public int getRyuHeight()
  {
    return this.ryuHeight;
  }
  
  public void setRyuHeight(int ryuHeight)
  {
    this.ryuHeight = ryuHeight;
  }
}
