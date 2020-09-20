import java.util.concurrent.*;
import java.util.*;



public class Consumer extends RecursiveAction {
    Queue<Integer> sharedQ = new LinkedList<Integer>();
    ForkJoinPool pool = ForkJoinPool.commonPool();
    Consumer(Queue<Integer> sharedQ){
      this.sharedQ = sharedQ;
    }
    @Override
    protected void compute(){
        //consume
        if(!sharedQ.isEmpty()){
          sharedQ.remove();
          System.out.println("Consumer took 1 item");
        }

    }



}
