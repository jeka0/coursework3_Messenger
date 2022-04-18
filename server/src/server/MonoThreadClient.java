package server;

import DataBase.DB;
import business.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadClient implements Runnable{
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;

    public MonoThreadClient(Socket client) {
        this.client = client;
    }
    public void run() {
        try {
            DB db = DB.getInstance();
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());

            System.out.println(client.getInetAddress());
            while (!client.isClosed()) {
                String mess = in.readUTF();
                if(mess==null)break;
                db.addMessage("database\\Chats\\chat.json",new Message("asd",mess));
                System.out.print(mess);
            }
            out.close();
            in.close();
            client.close();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
}
