import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;
public class Philosophers extends Thread {


 private static int timeThink = 3000;
 private static int timeEat = 1500;
 private static int timeRun = 60000;

 protected static class PhilTimer extends TimerTask {
	 public void run() {

		 Philosophers.runThread = false; 
		 System.out.println("The philosophers are done");
		 for (int i=0; i<=4; i++) {
			 System.out.println("Philosopher " + i + " has eaten " +Philosophers.numMeals[i] + " times");
		 }
		 System.exit(0);
	 }
 }
 // 5 philosophers
 private static Philosophers[] phils = new Philosophers[5];

 // ID of this philosopher
 private int philNum;

 // Server stub
 private static Forks frk;

 //This is set to false by the timer causing the client threads to stop
 private static boolean runThread = true;
 //Number of meals each philosopher has had
 private static int[] numMeals = { 0, 0, 0, 0, 0 };


 public Philosophers(int num, Registry registry) {

 philNum = num;

 this.start();

 }

 public void run() {

 while(runThread) {

 try {
 this.sleep((int)(Math.random()*timeThink)); //Think

 frk.takeFork(philNum);

 numMeals[philNum]++; //Record the meal

 this.sleep((int)(Math.random()*timeEat)); //Eat

 frk.putbackFork(philNum);

 } catch (Exception e) { 
 System.err.println("Philosophers exception");
 e.printStackTrace();
 }
 }
 }

 public static void main(String[] args) {

 if (java.lang.reflect.Array.getLength(args) != 1) {
 System.out.println("Please supply the host to connect to as a command line argument.");
 System.out.println("for example: java Philosophers localhost");
 return;
 }
 try {
 Registry registry = LocateRegistry.getRegistry(args[0]);

 // Get the server object from the registry
 frk = (Forks) registry.lookup("Forks");

 for (int i = 0; i <= 4; i++) {
 new Philosophers(i, registry);
 }
 Timer philTimer = new Timer();

 //Prints the results and exits
 philTimer.schedule(new PhilTimer(), timeRun);
 } catch (Exception e) {
 System.err.println("Philosophers exception");
 e.printStackTrace();
 }
 }
} 