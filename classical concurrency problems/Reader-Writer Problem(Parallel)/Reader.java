

public class Reader extends Thread{

    private static int readers = 0;

    private int number;
    private Driver d;

    public Reader(Driver d){
      this.d = d;
      this.number = Reader.readers++;
    }

    public void run(){
      while(true){
        final int delay = 100;
        try{
          Thread.sleep(delay);
        }
        catch(InterruptedException e){}
        this.d.read(this.number);
        break;
      }
    }

}
