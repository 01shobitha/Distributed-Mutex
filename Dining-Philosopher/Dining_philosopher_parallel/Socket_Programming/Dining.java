import java.io.*;
import java.net.*;

public class Dining {
		public static void main(String[] args) throws IOException{
			ChopStick[] sticks = new ChopStick[5];
			
			
			for(int i=0; i< sticks.length; i++){
				sticks[i] = new ChopStick("C: "+i);
			}
			
			ServerSocket ss=new ServerSocket(6001);
			
			Philosopher[] philosophers = new Philosopher[5];
			
			for(int i=0;i<philosophers.length;i++){
				Socket s=ss.accept();
				//Streams
				String name = "P: " +i+ "  ";
				philosophers[i] = new Philosopher(name, sticks[i], sticks[(i+1)%5],s);
				System.out.println("Thread "+ i);
				
				Thread t= new Thread( philosophers[i]);
				t.start();
			}
			ss.close();
		}
}
