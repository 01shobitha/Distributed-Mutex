import java.util.concurrent.*;
import java.util.*;
abstract class Main extends RecursiveAction{
  public static void main(String[] args){
    final Queue<Integer> sharedQ = new LinkedList<Integer>();
    ForkJoinPool pool = ForkJoinPool.commonPool();
    Producer produces = new Producer(sharedQ);
    pool.invoke(produces);
    // System.exit(0);

  }
}
