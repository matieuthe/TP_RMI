

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.*;

public class ChatClient {

    public static void main(String[] argv) throws Exception {
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = s.nextLine().trim();
            ChatInterface client = new ChatImplement(name);

            ChatInterface stub = (ChatInterface) Naming.lookup("rmi://192.168.11.1:5000/SimpleChatServer");
            String msg = "[" + client.getName() + "] got connected.";
            stub.send(msg);
            System.out.println("[System] Chat Remote Object is ready!");
            stub.setClient(client);

            while (true) {
                msg = s.nextLine().trim();
                msg = "[" + client.getName() + "] " + msg;
                stub.send(msg);
            }

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
}
