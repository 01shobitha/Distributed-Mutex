import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String args[]) {
		
		final Queue<Integer> sharedQ = new LinkedList<Integer>();
		Thread producer = new Producer(sharedQ);
		Thread consumer = new Consumer(sharedQ);
		
		producer.start();
		consumer.start();
	}
}
