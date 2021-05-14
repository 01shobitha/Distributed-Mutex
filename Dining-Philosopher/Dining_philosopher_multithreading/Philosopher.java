package multithreaded_program;

public class Philosopher extends Thread{
	int Num;
    static int Number=0;
    private Fork fork;
    public Philosopher(Fork fork){
    	super();
    	this.fork=fork;
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
    @Override
    public void run(){
        while(true){
            thinking();
            //hungry
            fork.take();
            eating();
            fork.release();
        }
    }
}
