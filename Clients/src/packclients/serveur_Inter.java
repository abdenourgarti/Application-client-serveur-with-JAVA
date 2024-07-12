package packclients;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class serveur_Inter extends UnicastRemoteObject implements Inter {
	protected serveur_Inter() throws RemoteException {

		super();

		// TODO Auto-generated constructor stub

		}
    public String sendRequest(String service, int idClient){
        System.out.println(service + " client "+ idClient);
        String Repservice = null;
        try {
            Socket clientSocket;
            ObjectOutputStream out;
            ObjectInputStream in;
            switch (service) {
                case "S0":
                case "S1":
                case "S2":
                case "S3":
                    clientSocket = new Socket("localhost", 2001);
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(service);
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    Repservice = (String) in.readObject();
                    System.out.println("Message reçu : " + Repservice);
                    in.close();
                    out.close();
                    clientSocket.close();
                    break;
                    
                case "S4":
                case "S5":
                case "S6":
                	clientSocket = new Socket("localhost", 2002);
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(service);
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    Repservice = (String) in.readObject();
                    System.out.println("Message reçu : " + Repservice);
                    in.close();
                    out.close();
                    clientSocket.close();
                    break;
                case "S7":
                case "S8":
                case "S9":
                	clientSocket = new Socket("localhost", 2003);
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(service);
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    Repservice = (String) in.readObject();
                    System.out.println("Message reçu : " + Repservice);
                    in.close();
                    out.close();
                    clientSocket.close();
                    break;
                case "S10":
                case "S11":
                case "S12":
                	clientSocket = new Socket("localhost", 2004);
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(service);
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    Repservice = (String) in.readObject();
                    System.out.println("Message reçu : " + Repservice);
                    in.close();
                    out.close();
                    clientSocket.close();
                    break;
                case "S13":
                case "S14":
                case "S15":
                	clientSocket = new Socket("localhost", 2005);
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    out.writeObject(service);
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    Repservice = (String) in.readObject();
                    System.out.println("Message reçu : " + Repservice);
                    in.close();
                    out.close();
                    clientSocket.close();
                    break;
                default:
                    System.out.println("Service non pris en charge: " + service);
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Repservice;
    }

    public static void main(String[] args) throws IOException {
        serveur_Inter SInter = new serveur_Inter();
        Registry reg = LocateRegistry.createRegistry(1099);
        reg.rebind("inter", SInter);
        System.out.println("Serveur intermédiaire (RMI) prêt.");
    }
}
