//interface

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Forks extends Remote {
	int takeFork(int philosopher) throws RemoteException;
	int putbackFork(int philosopher) throws RemoteException;
}
