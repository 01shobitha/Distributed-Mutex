package multithreaded_program;

public class Fork {
	boolean fork[] = {false,false,false,false,false};
	synchronized void release(){
		Philosopher phi=(Philosopher) Thread.currentThread();
		int Num=phi.Num;
		System.out.format("Philosopher\t%d\treleases Fork\n", Num);
		fork[Num]=false;
		fork[((Num+1)%5)]=false;
		notifyAll();
	}
	synchronized void take() {
		Philosopher phi=(Philosopher) Thread.currentThread();
		int Num=phi.Num;
		while(fork[((Num+1)%5)] || fork[Num]){
	            try {wait();} catch (InterruptedException e){}
	        }
	        System.out.format("Philosopher\t%d\ttakes Fork\n", Num);
	        fork[Num]=true;
	        fork[((Num+1)%5)]=true;
	}
}
