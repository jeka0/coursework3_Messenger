package server;

import DataBase.DB;
import RequestHandler.RequestHandler;
import RequestHandler.IRequestHandler;
import business.Request;

import java.io.IOException;
import java.net.Socket;

public class MonoThreadClient implements IMonoThreadClient{
    private DB db = DB.getInstance();
    private Socket client;
    private IServer server;
    private ReceivingAndSendingData recAndSendData;
    private IRequestHandler requestHandler;
    private boolean flag = false;

    public MonoThreadClient(Socket client, IServer server) {
        this.client = client;
        this.server = server;
        recAndSendData = new ReceivingAndSendingData(client);
        requestHandler = new RequestHandler(recAndSendData, server);
        new Thread(()-> Listener()).start();
    }
    public void run() {
        try {
            System.out.println(client.getInetAddress());
            while (!client.isClosed()) {
                if (flag) {
                    SubmitReply();
                    flag=false;
                }
            }
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    private void Listener()
    {
        try {
            while (!client.isClosed()) {
                if (!GettingData()) break;
            }
        }catch(IOException e){System.out.println("Client disabled");closeClient();}
        catch (ClassNotFoundException e){System.out.println(e.getMessage());}
    }
    private boolean GettingData() throws IOException, ClassNotFoundException
    {
        Request request = (Request) recAndSendData.receiveObject();
        if(request==null)return false;
        requestHandler.handle(request);
        return true;
    }
    private void SubmitReply() throws IOException
    {
        requestHandler.answer(new Request("UpdatePosts"));
    }

    public void setUpdateMessagesFlag(boolean flag) { this.flag = flag; }

    public void closeClient()
    {
        try {
            server.removeThread(this);
            recAndSendData.ClosingStreams();
            client.close();
        }catch (IOException e){System.out.println(e.getMessage());}
    }
}
