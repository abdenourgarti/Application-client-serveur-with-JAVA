package packclients;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client1 {
    private static boolean hasToken = true;
    private static String TokenRecu = "T";

    public static void main(String[] args) throws Exception {
        String[] serviceChain = {"S7", "S10", "S1", "S2", "S4", "S3", "S0", "S6", "S12", "S11", "S8", "S5", "S2", "S1", "S0", "S5", "S1", "S7", "S9", "S2","FIN"};
        String reponse;
        try {
            DatagramSocket udpSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9877;           
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            Inter i = (Inter) reg.lookup("inter");
            int index = 0;
            String service;
            while (index < serviceChain.length && !(service = serviceChain[index]).equals("FIN")) {
            	
            	if (hasToken) {                	
                    reponse = i.sendRequest(service, 1);
                    System.out.println("service " + service + " ==> réponse : " + reponse);
                    // Send token to the next client using UDP
                    sendToken(udpSocket, serverAddress, serverPort);
                    hasToken = false;
                    String TokenRecu = "";
                } else {
                    // Wait for the token
                    System.out.println("Client 1 waiting for the token.");
                    waitForToken();
                    if (hasToken) {                    	
                        reponse = i.sendRequest(service, 1);
                        System.out.println("service " + service + " ==> réponse : " + reponse);
                        sendToken(udpSocket, serverAddress, serverPort);
                        hasToken = false;
                        String TokenRecu = "";
                    }
                }
                index++;
            }
            System.out.println("Fin client 1");
            udpSocket.close();
        } catch (RemoteException e) {
            System.err.println("Impossible de localiser ou de se connecter au registre RMI.");
            e.printStackTrace();
        }
    }

    private static String recivedToken(DatagramSocket socket) throws IOException {    	
    	byte[] recivedData = new byte[10];
    	DatagramPacket Packet = new DatagramPacket(recivedData, recivedData.length);
    	socket.receive(Packet);
    	return new String(Packet.getData(), 0, Packet.getLength());
    }
    private static void sendToken(DatagramSocket socket, InetAddress address, int port) throws Exception {
        String tokenMessage = "T";
        byte[] sendData = tokenMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
        socket.send(sendPacket);
    }
    private static void waitForToken() throws IOException, InterruptedException {
        do {
        	DatagramSocket udpSocketS = new DatagramSocket(9876);
        	TokenRecu = recivedToken(udpSocketS);
        	udpSocketS.close();
        }while(!TokenRecu.equals("T"));
        if(TokenRecu.equals("T")) {
        	hasToken = true;
        	TokenRecu = "";
        }
        Thread.sleep(1000);
    }
}
