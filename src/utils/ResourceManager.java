package utils;

public abstract class ResourceManager
{
  protected int count = 1;
  
  public void addReference()
  {
    this.count += 1;
  }
  
  public boolean removeReference()
  {
    this.count -= 1;
    return this.count == 0;
  }
}