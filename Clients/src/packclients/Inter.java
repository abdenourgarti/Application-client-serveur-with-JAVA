package packclients;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Inter extends Remote{
	public String sendRequest(String service, int idClient)throws RemoteException;
}
