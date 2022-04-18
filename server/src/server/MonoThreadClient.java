package server;

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
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());

            System.out.println(client.getInetAddress());
            while (!client.isClosed()) {
                String mess = in.readUTF();
                if(mess==null)break;
                System.out.print(mess);
            }
            out.close();
            in.close();
            client.close();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
}
