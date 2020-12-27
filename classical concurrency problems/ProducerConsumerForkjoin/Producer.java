
import java.util.concurrent.*;
import java.util.*;


public class Producer extends RecursiveAction{
  Queue<Integer> sharedQ = new LinkedList<Integer>();
  ForkJoinPool pool = ForkJoinPool.commonPool();
  // int count;
  Producer(Queue<Integer> sharedQ){
    this.sharedQ = sharedQ;
    // count = 0;
  }

  @Override

  protected void compute(){
    int counter = 0;
      // System.out.println(sharedQ.size());
      if(sharedQ.size() < 3 && counter < 3)
      
      { 
        sharedQ.add(1);//adding to queue
        System.out.println("Producer addedd 1 item");
        Consumer consumes = new Consumer(sharedQ);
        consumes.fork();
        compute();
        consumes.join();
        ++ counter;
      }
      else{
        System.exit(0);
      }

  }
}
