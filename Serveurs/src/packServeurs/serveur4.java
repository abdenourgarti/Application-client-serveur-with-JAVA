package packServeurs;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import central.IservCent;
import central.Service;
import central.serveur;

public class serveur4 {
	public static void main(String[] args) throws NamingException {
        Properties props = new Properties();
        try {
            ServerSocket server = new ServerSocket(2004);
            System.out.println("Attente de connexion...");
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            InitialContext context = new InitialContext(props);

            IservCent x = (IservCent) context.lookup("ejb:/serveur central/servCent!central.IservCent");
            if(!x.IsServeurExsit("serveur 4")) {
            	x.ajouterServeur(new serveur("serveur 4","127.0.0.1",2004));
            }
            
            
            while (true) {
                Socket connection = server.accept();
                System.out.println("Connexion acceptée");

                try (ObjectInputStream in = new ObjectInputStream(connection.getInputStream())) {
                    String service = (String) in.readObject();
                    System.out.println("Message reçu : " + service);

                    Service ejbResult = x.GetService(service, 1);
                    String Result = ejbResult.getDescription();

                    System.out.println("Résultat EJB : " + Result);                    
                    ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
                    out.writeObject(Result);
                    in.close();
                    out.close();
                    
                } catch (IOException | ClassNotFoundException e) {
                    
                    e.printStackTrace();
                } finally {               	
                    connection.close();
                }
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
