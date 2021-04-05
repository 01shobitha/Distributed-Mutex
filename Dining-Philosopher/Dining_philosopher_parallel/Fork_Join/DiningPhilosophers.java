package parallel;

import java.util.*;
import java.util.concurrent.*;
public class DiningPhilosophers {

	  public static void main(String[] args) throws Exception {

		  List<Philosopher> subtasks = new ArrayList<>();
		  
	        Philosopher[] philosophers = new Philosopher[5];
	        Object[] forks = new Object[philosophers.length];

	        for (int i = 0; i < forks.length; i++) {
	            forks[i] = new Object();
	        }

	        for (int i = 0; i < philosophers.length; i++) {
	            Object leftFork = forks[i];
	            Object rightFork = forks[(i + 1) % forks.length];
	            String name = "P "+i;
	            if(i != philosophers.length - 1)
	            	philosophers[i] = new Philosopher(name,leftFork, rightFork,i,(i + 1) % forks.length);
	            else
	            	philosophers[i] = new Philosopher(name, rightFork, leftFork,i,(i + 1) % forks.length);
	            subtasks.add(philosophers[i]);
	        }
	        ForkJoinTask.invokeAll(subtasks);
	    }
	
}
