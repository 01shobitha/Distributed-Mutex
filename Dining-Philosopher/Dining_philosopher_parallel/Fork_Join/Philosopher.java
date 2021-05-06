package parallel;

import java.util.concurrent.*;
public class Philosopher extends RecursiveAction{
	
	String name;
	Object leftFork,rightFork;
	int l_no,r_no;
	public Philosopher(String name,Object l,Object r,int l_no,int r_no) {
		this.name = name;
		this.leftFork = l;
		this.rightFork = r;
		this.l_no = l_no;
		this.r_no = r_no;
	}
	
	private  void getChopstick(String action){
		System.out.println(name+" : "+action);
		try {
			TimeUnit.MILLISECONDS.sleep((int)(Math.random()*500));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private  void thinking(String action){
		System.out.println(name+" : "+action);
		try {
			TimeUnit.MILLISECONDS.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private  void hungry(String action){
		System.out.println(name+" : "+action);
		try {
			TimeUnit.MILLISECONDS.sleep((int)(Math.random()*100));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private  void returnChopstick(String action){
		System.out.println(name+" : "+action);
		try {
			TimeUnit.MILLISECONDS.sleep((int)(Math.random()*500));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void compute() {
		//thinking
		thinking("Thinking");
		while(true) {
			hungry("Hungry");
			synchronized (leftFork) {
				getChopstick("want "+l_no+" as left fork");
			}
			synchronized (rightFork) {
				getChopstick("want "+r_no+" as right fork");
				//eating
				System.out.println(name+ " got both forks. Eating");
				returnChopstick("puts back right fork");
			}
			//back to thinking
			thinking("puts back left fork. Back to thinking. ");
			
		}
	}
}
