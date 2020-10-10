import java.io.*;
import java.net.*;

public class Consumer
{
    public static void main(String args[])throws IOException, InterruptedException
    {
        Socket s=new Socket("localhost",7000);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        
        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        while(true){
        	System.out.println("Want to consume?");
        	String console_inp=sc.readLine();
        	
        	if(console_inp.equalsIgnoreCase("Yes")){
        		out.println("CONSUME");
        		
        		String item=in.readLine();
            	
        		System.out.println("Consumer consumed - "  + item);
        	}
        	 
        	
        }
        
    }
}