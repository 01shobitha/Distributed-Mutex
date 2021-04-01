import java.io.*;
import java.net.*;
//client
public class Philosopher extends Thread {
    String name;
	ChopStick leftStick;
	ChopStick rightStick;
	
	PrintStream out=null;
    BufferedReader in=null;
    
    public Philosopher(String name,ChopStick left, ChopStick right,Socket s)throws IOException
    {
		this.name = name;
		leftStick = left;
		rightStick = right;
		this.out=new PrintStream(s.getOutputStream());
		this.in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
    
    public void eat() {
    	while(true) {
    		System.out.print("");
    		if(!leftStick.available) { // left chopstick is free
    			if(!rightStick.available) { // right chopstick is free
    				leftStick.take();
    				rightStick.take();
    				
    				System.out.println(name+" eating");
    				try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
    				leftStick.release();
    				rightStick.release();
    				//starts thinking
    				break;
    			}
    		}
    	}
    }
    public void run() {
    	while(true) {
    		try {
    			in.readLine();
    		}
    		catch(IOException e) {
    			e.printStackTrace();
    		}
    		eat();
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
