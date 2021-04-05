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
	
	private void doAction(String action){
		System.out.println(name+" : "+action);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void compute() {
		//thinking
		doAction("Thinking");
		while(true) {
			doAction("Hungry");
			synchronized (leftFork) {
				doAction("want "+l_no+"as left fork");
			}
			synchronized (rightFork) {
				doAction("want "+r_no+"as right fork");
				//eating
				System.out.println(name+ " got both forks. Eating");
				doAction("puts back right fork");
			}
			//back to thinking
			doAction("puts back left fork. Back to thinking. ");
			
		}
	}
}
