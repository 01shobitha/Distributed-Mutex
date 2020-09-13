public class Writer extends Thread
{
  private static int writers = 0;

  private int number;
  private Driver d;

  public Writer(Driver d)
  {
    this.d = d;
    this.number = Writer.writers++;
  }
  public void run()
  {
    while (true)
    {
      final int delay = 100;
      try
      {
        Thread.sleep(delay);
      }
      catch (InterruptedException e) {}
      this.d.write(this.number);
      break;
    }
  }
}
