import java.util.concurrent.*;
public class Chopstick extends RecursiveAction {
	boolean available ; // to check if the stick is in use
	String name ;
	public Chopstick(String name) {
		this.name = name;
	}
	
	public  void take() {
		available = false; // not available now
		System.out.println("Using "+name); 
	}
	
	public  void release() {
		available = true;// available
		System.out.println("Released "+name);
	}

	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		take();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		release();
		
	}
}
