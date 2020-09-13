import java.util.logging.Logger;
import java.util.Queue;

public class Consumer extends Thread {
	
	private static final Logger logger = Logger.getLogger(Consumer.class.getName());
	private final Queue<Integer> sharedQ;
	
	public Consumer(Queue<Integer> sharedQ) {
		super("Consumer");
		this.sharedQ = sharedQ;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(sharedQ) {
				while(sharedQ.size() == 0) {
					try {
						logger.info("Queue is empty.");
						sharedQ.wait();
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				int number = sharedQ.poll();
				logger.info("consuming: " + number);
				sharedQ.notify();
				
				if(number == 3) {
					break;
				}
			}
		}
	}
	
}
