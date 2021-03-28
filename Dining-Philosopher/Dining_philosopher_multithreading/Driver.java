package multithreaded_program;

public class Driver {
	public static void main(String[] args){
		Chopstick ch = new Chopstick();
		new Philosopher(ch).start();
		new Philosopher(ch).start();
		new Philosopher(ch).start();
		new Philosopher(ch).start();
		new Philosopher(ch).start();
	}
}
