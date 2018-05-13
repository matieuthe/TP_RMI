

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.*;

public class ChatServer {

    public static void main(String[] argv) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = s.nextLine().trim();

            ChatInterface stub = new ChatImplement(name);

            Naming.rebind("rmi://localhost:5000/SimpleChatServer", stub);

            System.out.println("[System] Chat Remote Object is ready!");

            while (true) {
                String msg = s.nextLine().trim();
                if (stub.getClient() != null) {
                    ChatInterface client = stub.getClient();
                    msg = "[" + stub.getName() + "] " + msg;
                    client.send(msg);
                }
            }
        } catch (RemoteException | MalformedURLException e) {
            System.out.println("[System] Server failed: " + e);
        }
    }
}
