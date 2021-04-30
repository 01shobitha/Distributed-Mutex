import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

public class ChopstickServer extends UnicastRemoteObject implements ChopstickInterface{

	protected ChopstickServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private int[] forks = { -1, -1, -1, -1, -1 }; 
	static Semaphore rs = new Semaphore(1);
	static Semaphore[] s = { new Semaphore(1),
			 new Semaphore(1),
			 new Semaphore(1),
			 new Semaphore(1),
			 new Semaphore(1) }; 
	@Override
	public int getForks(int philNum) throws RemoteException {
		if (philNum < 0 || philNum > 4)
			 return -1;
		System.out.println("Philosopher " + philNum + " trying to get forks...");
		while(true) {
			try {
				rs.acquire();
			} catch(InterruptedException e) {}
			
			int rnd = (int)(Math.random()*2);
			rs.release();
			int fork1 = (philNum + rnd)%5; 
			try {
				 s[fork1].acquire();
			} catch (InterruptedException e) {}
			if(forks[fork1] == -1) {
				int fork2 = (philNum + (1 - rnd))%5;
				try {
					 s[fork2].acquire();
				} catch (InterruptedException e) {} 
				if(forks[fork2] == -1) {
					forks[fork1] = philNum;
					forks[fork2] = philNum;
                    System.out.println("Philosopher " + philNum + " can start to eat using forks " +fork1 + " and " +fork2 + "...");
					s[fork1].release();
					s[fork2].release();

					return 0; 
				}
				s[fork2].release();
			}
			s[fork1].release();
		}
	}

	@Override
	public int returnForks(int philNum) throws RemoteException {
		if (philNum < 0 || philNum > 4)
			 return -1;
		int fork1 = philNum;
		int fork2 = (philNum+1)%5;
		try {
			 s[fork1].acquire();
			 s[fork2].acquire();
		} catch (InterruptedException e) {}
		System.out.println("Philosopher " + philNum + " has returned the forks " +fork1 + " and " + fork2 + "...");

		forks[fork1] = -1;
		forks[fork2] = -1;

		s[fork1].release();
		s[fork2].release();
		return 0;
	}
	
	public static void main(String[] args) {

		 try {
//		 String name = "rmi://localhost:8000";
		 String name = "ForkServer";
		 ChopstickInterface server = new ChopstickServer();
//		 ChopstickInterface stub = (ChopstickInterface) UnicastRemoteObject.exportObject(server, 0);
		 Registry registry = LocateRegistry.createRegistry(8000);
		 registry.rebind(name, server);
//		 Naming.bind(name,server);
		 System.out.println(name + " is running");
		 } catch (Exception e) {
		 System.err.println("ForkServer exception");
		 e.printStackTrace();
		 }
		 } 

}
