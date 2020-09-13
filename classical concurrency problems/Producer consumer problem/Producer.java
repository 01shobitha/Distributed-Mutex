
import java.util.logging.Logger;
import java.util.Queue;

public class Producer extends Thread {
	
	private static final Logger logger = Logger.getLogger(Producer.class.getName());
	private final Queue<Integer> sharedQ;
	
	public Producer(Queue<Integer> sharedQ) {
		super("Producer");
		this.sharedQ = sharedQ;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 4; ++i) {
			synchronized (sharedQ) {
				while(sharedQ.size() >= 1) {
					try {
						logger.info("Queue is full.");
						sharedQ.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				logger.info("producing: " + i);
				sharedQ.add(i);
				sharedQ.notify();
			}
		}
	}
}
