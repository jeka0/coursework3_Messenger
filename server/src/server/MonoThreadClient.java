package server;

import DataBase.DB;
import business.Message;
import business.Serializer;

import java.io.IOException;
import java.net.Socket;

public class MonoThreadClient implements Runnable{
    private DB db = DB.getInstance();
    private Socket client;
    private Server server;
    private ReceivingAndSendingData recAndSendData;
    private Serializer serializer = new Serializer();
    private boolean flag = true;

    public MonoThreadClient(Socket client, Server server) {
        this.client = client;
        this.server = server;
        recAndSendData = new ReceivingAndSendingData(client);
        new Thread(()-> SendingData()).start();
    }
    public void run() {
        try {
            System.out.println(client.getInetAddress());
            while (!client.isClosed()) {
                if(!GettingData())break;
                server.UpdateFlags();
            }
            recAndSendData.ClosingStreams();
            client.close();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    private void SendingData()
    {
        try {
            while (!client.isClosed())
                if (flag) {
                    SubmitReply();
                    flag=false;
                }
        }catch(IOException e){System.out.println(e.getMessage());}
    }
    private boolean GettingData() throws IOException
    {
        String str = recAndSendData.receiveMessage();
        Message message = serializer.deserialize(str,Message.class);
        if(str==null)return false;
        db.addMessage("database\\Chats\\chat.json", message);
        return true;
    }
    private void SubmitReply() throws IOException
    {
        String message = serializer.serialize(db.getMessages("database\\Chats\\chat.json"));
        recAndSendData.pushMessage(message);
    }

    public void setUpdateMessagesFlag(boolean flag) { this.flag = flag; }
}
