
public class ChopStick {
	boolean available ; // to check if the stick is in use
	String name ;
	public ChopStick(String name) {
		this.name = name;
	}
	
	public synchronized void take() {
		available = true;
		System.out.println("Using :"+name); 
	}
	
	public synchronized void release() {
		available = false;
		System.out.println("Released :"+name);
	}
	
	 public static void Delay(int time) {
	    	try{
				Thread.sleep(time);
			}
			catch(InterruptedException ex){ }
	    }
}
