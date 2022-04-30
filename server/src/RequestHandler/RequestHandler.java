package RequestHandler;

import DataBase.DB;
import DataBase.IDB;
import business.Chat;
import business.Message;
import business.Request;
import business.User;
import server.IServer;
import server.ReceivingAndSendingData;

import java.io.IOException;

public class RequestHandler implements IRequestHandler{
    private IDB db = DB.getInstance();
    private ReceivingAndSendingData recAndSendData;
    private IServer server;
    public RequestHandler(){}
    public RequestHandler(ReceivingAndSendingData recAndSendData, IServer server)
    {
        this.recAndSendData=recAndSendData;
        this.server=server;
    }
    public void handle(Request request)throws IOException
    {
        switch (request.getRequest())
        {
            case "Add":
                db.addMessage("database\\Chats\\chat.json", (Message)request.getData());
                server.UpdateFlags();
            break;
            case "CheckUser":
                if(db.CheckUserPassword((User)request.getData()))answer(new Request("AnswerYes"));
                else answer(new Request("AnswerNo"));
                break;
            case "Registration":
                if(db.UserRegistration((User)request.getData()))answer(new Request("AnswerYes"));
                else answer(new Request("AnswerNo"));
                break;
            case "UpdatePosts":
                answer(request);
                break;
            case "AddChat":
                db.addChat((Chat)request.getData());
                break;
            case "GetChats":
                answer(new Request("UpdateChats", db.getChats((User)request.getData())));
                break;
        }
    }
    public void answer(Request request) throws IOException
    {
        switch (request.getRequest())
        {
            case "UpdatePosts":
                Message[] messages = db.getMessages("database\\Chats\\chat.json");
                recAndSendData.pushObject(new Request("UpdateMessages", messages));
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
        }
    }

}
