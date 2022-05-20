package RequestHandler;

import DataBase.DB;
import DataBase.IDB;
import business.Chat;
import business.Message;
import business.Request;
import business.User;
import server.IMonoThreadClient;
import server.IServer;
import server.ReceivingAndSendingData;

import java.io.IOException;

public class RequestHandler implements IRequestHandler{
    private IDB db = DB.getInstance();
    private ReceivingAndSendingData recAndSendData;
    private IServer server;
    private IMonoThreadClient monoThreadClient;
    public RequestHandler(){}
    public RequestHandler(ReceivingAndSendingData recAndSendData, IServer server, IMonoThreadClient monoThreadClient)
    {
        this.recAndSendData=recAndSendData;
        this.server=server;
        this.monoThreadClient=monoThreadClient;

    }
    public void handle(Request request)throws IOException
    {
        User user;
        switch (request.getRequest())
        {
            case "Add":
                Message message = (Message)request.getData();
                db.addMessage(message);
                server.UpdateChat(db.getChat(message.getChatName()));
            break;
            case "CheckUser":
                user = (User)request.getData();
                if(db.CheckUserPassword(user)){monoThreadClient.setUser(user);answer(new Request("AnswerYes"));}
                else answer(new Request("AnswerNo"));
                break;
            case "Registration":
                user = (User)request.getData();
                if(db.UserRegistration(user)){monoThreadClient.setUser(user);answer(new Request("AnswerYes"));}
                else answer(new Request("AnswerNo"));
                break;
            case "UpdatePosts":
                answer(request);
                break;
            case "AddChat":
                Chat thisChat = (Chat)request.getData();
                if(db.addChat(thisChat)){answer(new Request("AnswerYes"));server.UpdateChatList(thisChat.getUsers());}
                else answer(new Request("AnswerNo"));
                break;
            case "GetChats":
                answer(new Request("UpdateChats", db.getChats(db.getChatsNames((String)request.getData()))));
                break;
            case "GetSelectedChats":
                answer(new Request("UpdateSelectedChats",db.getSelectedChats()));
                break;
            case "AddChatToUser":
                Chat chat = (Chat) request.getData();
                db.AddChatToUser(chat);
                answer(new Request("UpdateChats", db.getChats(db.getChatsNames(chat.getUsers().get(0)))));
                break;
            case "GetUsers":
                answer(new Request("UpdateUsers",db.GetUsersWithoutPasswords()));
                break;
            case "UpdateUser":
                if(db.UpdateUser((User)request.getData()))answer(new Request("AnswerUserYes"));
                else answer(new Request("AnswerUserNo"));
                break;
        }
    }
    public void answer(Request request) throws IOException
    {
        switch (request.getRequest())
        {
            case "UpdatePosts":
                Message[] messages = db.getMessages((String) request.getData());
                recAndSendData.pushObject(new Request("UpdateMessages", messages));
                break;
            case "AnswerUserYes":
                recAndSendData.pushObject(new Request("AnswerUser",true));
                break;
            case "AnswerUserNo":
                recAndSendData.pushObject(new Request("AnswerUser",false));
                break;
            case "AnswerYes":
                recAndSendData.pushObject(new Request("Answer",true));
                break;
            case "AnswerNo":
                recAndSendData.pushObject(new Request("Answer",false));
                break;
            case "UpdateChats":
                recAndSendData.pushObject(new Request("UpdateChats",request.getData()));
                break;
            case "UpdateSelectedChats":
                recAndSendData.pushObject(new Request("UpdateSelectedChats",request.getData()));
                break;
                case "UpdateUsers":
                    recAndSendData.pushObject(request);
                    break;
        }
    }

}
