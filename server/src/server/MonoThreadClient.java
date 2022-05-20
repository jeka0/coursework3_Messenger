package server;

import DataBase.DB;
import RequestHandler.RequestHandler;
import RequestHandler.IRequestHandler;
import business.Request;
import business.User;

import java.io.IOException;
import java.net.Socket;

public class MonoThreadClient implements IMonoThreadClient{
    private DB db = DB.getInstance();
    private Socket client;
    private IServer server;
    private ReceivingAndSendingData recAndSendData;
    private IRequestHandler requestHandler;
    private String NameChat;
    private User user;
    private boolean flag = false;

    public MonoThreadClient(Socket client, IServer server) {
        this.client = client;
        this.server = server;
        recAndSendData = new ReceivingAndSendingData(client);
        requestHandler = new RequestHandler(recAndSendData, server,this);
    }
    public void run() {
        try {
            System.out.println(client.getInetAddress());
                while (!client.isClosed()) {
                    if (!GettingData()) break;
                }
            }catch(IOException e){closeClient();}
            catch (ClassNotFoundException e){System.out.println(e.getMessage());}
    }
    public void Notify(String str)
    {
        try {
            switch(str) {
                case "UpdatePosts":
                UpdatePosts();
                break;
                case "UpdateChats":
                    UpdateChats();
                    break;
            }
        }catch (IOException e){System.out.println(e.getMessage());}
    }
    private boolean GettingData() throws IOException, ClassNotFoundException
    {
        Request request = (Request) recAndSendData.receiveObject();
        if(request==null)return false;
        requestHandler.handle(request);
        return true;
    }
    private void UpdateChats() throws IOException
    {
        requestHandler.answer(new Request("UpdateChats",db.getChats(db.getChatsNames(user.getName()))));
    }
    private void UpdatePosts() throws IOException
    {
        requestHandler.answer(new Request("UpdatePosts",NameChat));
    }

    public void setUpdateMessagesFlag(boolean flag) { this.flag = flag; }

    public void closeClient()
    {
        try {
            server.removeThread(this);
            recAndSendData.ClosingStreams();
            client.close();
            System.out.println("Client disabled");
        }catch (IOException e){System.out.println(e.getMessage());}
    }

    public void setNameChat(String nameChat) {
        NameChat = nameChat;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
