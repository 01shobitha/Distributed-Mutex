import java.io.*;
import java.net.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.*;

public class Monitor
{
	public static Queue<String> item_q = new LinkedList<String>();
	static int capacity = 2;
	 
	public static void print_q(){
		System.out.println("Queue elements\n---------------");
		
		for(String s : item_q) { 
			  System.out.print(s.toString()+" | "); 
			}
		System.out.println("\n-------------");
	}
	
    public static void produce(String value) throws InterruptedException
    {
        
                while (item_q.size()==capacity)
                    Thread.sleep(500);;

                System.out.println("Producer produced-" + value + "\n");

                	 item_q.add(value);
                	 print_q();
                	 System.out.println("Lock with producer\n");
                	 Thread.sleep(500);                
    }
    
    
    public static String consume() throws InterruptedException
    {
           
                while (item_q.size()==0)
                	Thread.sleep(500);;

                String val=null;
                	val = item_q.poll();
                	System.out.println("Lock with consumer\n");
                	Thread.sleep(500);

                System.out.println("Consumer consumed-" + val);
                print_q();

                Thread.sleep(500);
                
                return val;
          
    }
    
    
    public static void main(String args[])throws IOException, InterruptedException
    {
    	ServerSocket s=new ServerSocket(7000);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        
        Socket ss1=s.accept();
       
        // Create producer thread
        Thread producer = new Thread(new Runnable()
        {
        	PrintStream out = new PrintStream(ss1.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(ss1.getInputStream()));
            
            @Override
            public void run()
            {
            	while(true){
            		String item=null;
					try {
						item = in.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
            		try {
						produce(item);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            		
            		out.println("PRODUCE");
            	}
            }
        });
 
        producer.start();
        
        Socket ss2=s.accept();
        
        Thread consumer = new Thread(new Runnable()
        {
        	PrintStream out = new PrintStream(ss2.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(ss2.getInputStream()));
            
            @Override
            public void run()
            {
            	while(true){
            		try {
						in.readLine();
					} catch (IOException e) {

						e.printStackTrace();
					}
            		String item=null;
					try {
						item = consume();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
            		out.println(item);
            	}
            }
        });
        
        consumer.start();
        
        
    }
}