package multithreaded_program;

public class Philosopher extends Thread{
	int Num;
    static int Number=0;
    private Chopstick Chop;
    public Philosopher(Chopstick Chop){
	super();
	this.Chop=Chop;
        Num=Number;
	Number++;
    }
    
    private void eating(){
        System.out.format("Philosopher\t%d\tis Eating\n", Num);
        try {Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
    private void thinking(){
        System.out.format("Philosopher\t%d\tis Thinking\n", Num);
        try { Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
    public void run(){
        while(true){
            thinking();
            Chop.take();
            eating();
            Chop.release();
        }
    }
}
