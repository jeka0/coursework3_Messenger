package server;

import DataBase.DB;
import RequestHandler.RequestHandler;
import business.Message;
import business.Request;

import java.io.IOException;
import java.net.Socket;

public class MonoThreadClient implements Runnable{
    private DB db = DB.getInstance();
    private Socket client;
    private Server server;
    private ReceivingAndSendingData recAndSendData;
    private RequestHandler requestHandler;
    private boolean flag = false;

    public MonoThreadClient(Socket client, Server server) {
        this.client = client;
        this.server = server;
        recAndSendData = new ReceivingAndSendingData(client);
        requestHandler = new RequestHandler(recAndSendData, server);
        new Thread(()-> SendingData()).start();
    }
    public void run() {
        try {
            System.out.println(client.getInetAddress());
            while (!client.isClosed()) {
                if(!GettingData())break;
            }
            recAndSendData.ClosingStreams();
            client.close();
        }catch (IOException e){System.out.println(e.getMessage());}
        catch (ClassNotFoundException e){System.out.println(e.getMessage());}
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
    private boolean GettingData() throws IOException, ClassNotFoundException
    {
        Request request = (Request) recAndSendData.receiveObject();
        Object object = recAndSendData.receiveObject();
        if(request==null||object==null)return false;
        requestHandler.handle(request,object);
        return true;
    }
    private void SubmitReply() throws IOException
    {
        requestHandler.answer(new Request("UpdatePosts"));
    }

    public void setUpdateMessagesFlag(boolean flag) { this.flag = flag; }
}
