import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask; 

@SuppressWarnings("deprecation")
public class Philosophers  extends Thread {
	
	 private static int timeThink = 3000;
	 private static int timeEat = 1500;
	 private static int timeRun = 60000; 

	 protected static class PhilTimer extends TimerTask {
		 // Stop the the threads and print out status info
		 public void run() {

			 Philosophers.runThread = false;
			 System.out.println("The philosophers are done");
			 for (int i=0; i<=4; i++) {
				 System.out.println("Philosopher " + i + " has eaten " +
						 Philosophers.numMeals[i] + " times");
			 }
			 System.exit(0); 
		 }
	 }
	
	// Our 5 philosophers
	 private static Philosophers[] phils = new Philosophers[5];

	 // ID of this philosopher
	 private int philNum;

	 // Server stub
	 private static ChopstickInterface frk;

	 //This is set to false by the timer causing the client threads to stop
	 private static boolean runThread = true;
	 //Number of meals each philosopher has had
	 private static int[] numMeals = { 0, 0, 0, 0, 0 }; 
	 
	public Philosophers(int num, Registry registry) {
			philNum = num;
			this.start();

	} 
	
	private void gotForks(int f1,int f2) {
		return;
	}
	private void returnedForks(int f1,int f2) {
		return;
	}
	private void hungry() {
		return;
	}
	
	public void run() {

		 while(runThread) {

		 try {
			 hungry();
			 this.sleep((int)(Math.random()*timeThink)); //Think
			 frk.getForks(philNum);
			 int fork1 = philNum;
			 int fork2 = (philNum+1)%5;
			 gotForks(fork1,fork2);
			 numMeals[philNum]++; //Record the meal
			 this.sleep((int)(Math.random()*timeEat)); //Eat
			 frk.returnForks(philNum);
			 returnedForks(fork1,fork2);

		 } 
		 catch (Exception e) {
			 System.err.println("Philosophers exception");
			 e.printStackTrace();
		 }
		}
	} 
	public static void main(String[] args) {
		try {
//			System.setSecurityManager(new RMISecurityManager());
			String name = "ForkServer";
			Registry registry = LocateRegistry.getRegistry();
			frk= (ChopstickInterface) Naming.lookup("//localhost:8000/"+name); 
			for (int i = 0; i <= 4; i++) {
				 new Philosophers(i, registry);
				 }
				 Timer philTimer = new Timer();

				 //Prints the results and exits
				 philTimer.schedule(new PhilTimer(), timeRun); 
			
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
}
