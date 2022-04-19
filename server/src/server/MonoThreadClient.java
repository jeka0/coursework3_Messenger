package server;

import DataBase.DB;
import business.Message;
import business.Serializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadClient implements Runnable{
    //private DB db = DB.getInstance();
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    //private Serializer serializer = new Serializer();
    //private static boolean flag;

    public MonoThreadClient(Socket client) {
        this.client = client;
        /*flag = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (flag) {
                        out.writeUTF(serializer.serialize(db.getMessages("database\\Chats\\chat.json")));
                        out.flush();
                        flag=false;
                    }
                }catch(IOException e){System.out.println(e.getMessage());}
            }
        });*/
    }
    public void run() {
        try {
            DB db = DB.getInstance();
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
            Serializer serializer = new Serializer();
            System.out.println(client.getInetAddress());
            while (!client.isClosed()) {
                String str =in.readUTF();
                if(str==null)break;
                db.addMessage("database\\Chats\\chat.json", serializer.deserialize(str,Message.class));
                //flag=true;
                out.writeUTF(serializer.serialize(db.getMessages("database\\Chats\\chat.json")));
                out.flush();
            }
            out.close();
            in.close();
            client.close();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
}
