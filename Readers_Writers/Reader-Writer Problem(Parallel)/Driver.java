

class Driver{
  private int readers;

  public Driver(){
    this.readers = 0;
  }
  final int delay = 100;
  public void read(int num){
    synchronized(this){
      this.readers++;
      System.out.println("Reader "+num+" starts reading");
    }

    try{
      Thread.sleep(delay);
    }
    catch (InterruptedException e){}
    synchronized(this){
      System.out.println("Reader "+num+" stops reading");
      this.readers--;
      if(this.readers == 0) this.notifyAll();
    }
  }

  public synchronized void write(int num){
    while(this.readers != 0){
      try{
        this.wait();
      }
      catch (InterruptedException e) {}
    }
    System.out.println("Writer "+num+" starts writing");
    try{
      Thread.sleep(delay);
    }
    catch (InterruptedException e){}
    System.out.println("Writer "+num+" stops writing");
    this.notifyAll(); //wakes up all threads that are waiting on this object's monitor
  }

  public static void main(String[] args)
  {
      final int READERS = 3;
      final int WRITERS = 3;
      Driver d = new Driver();
      for (int i = 1; i <= READERS; i++)
      {
        new Reader(d).start();
      }
      for (int i = 1; i <= WRITERS; i++)
      {
        new Writer(d).start();
      }
  }
}
