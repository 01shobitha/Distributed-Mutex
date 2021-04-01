import java.util.concurrent.*;

public abstract class Driver extends RecursiveAction{
 
	public static void main(String[] args){
	    ForkJoinPool pool = ForkJoinPool.commonPool();
	    Chopstick[] sticks = new Chopstick[5];
	    for(int i=0; i< sticks.length; i++){
			sticks[i] = new Chopstick("C: "+i);
//			System.out.println(sticks[i].name);
		}
	    
		Philosopher[] philosophers = new Philosopher[5];
		for(int i=0;i<philosophers.length;i++){
			String name = "P: " +i+ "  ";
			philosophers[i] = new Philosopher(name, sticks[i], sticks[(i+1)%5]);
			System.out.println("Philosopher "+ i);
			pool.invoke(philosophers[i]);
		}
	  }
	
}
