import java.util.concurrent.*;
public class Philosopher extends RecursiveAction {
	String name;
	Chopstick leftStick,rightStick;
    public Philosopher(String name,Chopstick left, Chopstick right)
    {
			this.name = name;
			leftStick = left;
			rightStick = right;
	}
	
    public void compute() { 
		eat();    	
    }
    void eat() {// eating process
    	while(true) {
		System.out.println("");
		if(!leftStick.available) {
			if(!rightStick.available) {
				leftStick.fork();
				rightStick.fork();
				System.out.println(name+" wants eat");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("started eating");
				leftStick.join();
				rightStick.join();
				System.out.println(name+ " is back to thinking");
				break;
				//back to thinking
			}
			
		}
	}
    }
}
