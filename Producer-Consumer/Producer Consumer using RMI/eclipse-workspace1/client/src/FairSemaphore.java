// package semaphore;

import java.util.Deque;
import java.util.ArrayDeque;

public class FairSemaphore extends Semaphore {
  private Deque<Lock> locks = new ArrayDeque<>();

  //class for storing information about who waits and for how many units
  private class Lock {
    public int units;

    public Lock(int units) {
      this.units = units;
    }
  }

  public FairSemaphore() {
  }

  public FairSemaphore(int units) {
    super(units);
  }

  @Override
  public void acquire(int units) {
    Lock lock = addYourselfToQueue(units);
    waitForAccess(lock);
    removeYourselfFromQueue(lock);

    //if multiple release()'s notified this thread, maybe there's already enough units
    //to wake the next one (who might have missed aforementioned notify()ies)
    notifyNext();
  }

  @Override
  public void release(int units) {
    synchronized (this) {
      this.units += units;
    }

    notifyNext();
  }





  private synchronized Lock addYourselfToQueue(int units) {
    Lock lock = new Lock(units);

    locks.addLast(lock);

    return lock;
  }

  private void waitForAccess(Lock lock) {
    synchronized(lock) {
      while (lock.units > units || (!locks.isEmpty() && locks.getFirst() != lock)) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          //log about error and reinterrupt the thread
          e.printStackTrace();
          Thread.currentThread().interrupt();
        }
      }
    }
  }

  private synchronized void removeYourselfFromQueue(Lock lock) {
    units -= lock.units;
    locks.remove(lock);
  }

  private void notifyNext() {
    Lock nextLock;

    synchronized (this) {
      nextLock = locks.isEmpty() ? null : locks.getFirst();
    }

    if (nextLock != null && nextLock.units <= this.units) {
      synchronized (nextLock) {
        nextLock.notify();
      }
    }
  }
}
