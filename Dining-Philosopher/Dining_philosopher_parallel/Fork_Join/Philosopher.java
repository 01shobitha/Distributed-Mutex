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
	
	
	private  void thinking(){
		return;
	}
	
	private  void hungry(){
		System.out.println(name+" is hungry");
		return;
	}
	
	@Override
	protected void compute() {
		//thinking
		thinking();
		try {
			TimeUnit.MILLISECONDS.sleep((int)(Math.random()*100));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			hungry();
			System.out.println(name + " wants forks "+l_no+" and "+r_no);
			synchronized (leftFork) {
				synchronized (rightFork) {
					//eating
					System.out.println(name+ " got forks "+l_no+" and "+r_no+". Eating");
					gotForks(leftFork,rightFork);
					try {
						TimeUnit.MILLISECONDS.sleep((int)(Math.random()*50));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			returnedForks(leftFork,rightFork);
			//back to thinking
			System.out.println("puts back the forks. Back to thinking. ");
			
		}
	}
	
	private void gotForks(Object f1,Object f2) {
		return;
	}
	private void returnedForks(Object f1,Object f2) {
		return;
	}
}