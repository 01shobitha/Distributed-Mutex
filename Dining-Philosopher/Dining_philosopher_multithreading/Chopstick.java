package multithreaded_program;

public class Chopstick {
	boolean taking[] = {false,false,false,false,false};
	synchronized void release(){
		Philosopher phi=(Philosopher) Thread.currentThread();
		int Num=phi.Num;
		System.out.format("Philosopher\t%d\treleases Chopstick\n", Num);
		taking[Num]=false;
		taking[((Num+1)%5)]=false;
		notifyAll();
	}
	synchronized void take() {
		Philosopher phi=(Philosopher) Thread.currentThread();
		int Num=phi.Num;
		while(taking[((Num+1)%5)] || taking[Num]){
	            try {wait();} catch (InterruptedException e){}
	        }
	        System.out.format("Philosopher\t%d\ttakes Chopstick\n", Num);
	        taking[Num]=true;
	        taking[((Num+1)%5)]=true;
	}
}
