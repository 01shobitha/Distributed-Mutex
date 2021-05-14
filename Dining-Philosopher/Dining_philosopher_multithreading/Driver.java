package multithreaded_program;

public class Driver {
	public static void main(String[] args){
		Fork f = new Fork();
		new Philosopher(f).start();
		new Philosopher(f).start();
		new Philosopher(f).start();
		new Philosopher(f).start();
		new Philosopher(f).start();
	}
}
