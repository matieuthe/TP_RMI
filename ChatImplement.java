

import java.rmi.*;
import java.rmi.server.*;

public class ChatImplement extends UnicastRemoteObject implements ChatInterface {

    public String name;
    public ChatInterface client = null;

    public ChatImplement(String n) throws RemoteException {
        this.name = n;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setClient(ChatInterface c) {
        client = c;
    }

    @Override
    public ChatInterface getClient() {
        return client;
    }

    @Override
    public void send(String s) {
        System.out.println(s);
    }
}
