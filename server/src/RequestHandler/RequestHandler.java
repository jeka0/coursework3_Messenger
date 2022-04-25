package RequestHandler;

import DataBase.DB;
import business.Message;
import business.Request;
import business.User;
import server.ReceivingAndSendingData;
import server.Server;

import java.io.IOException;

public class RequestHandler {
    private DB db = DB.getInstance();
    private ReceivingAndSendingData recAndSendData;
    private Server server;
    public RequestHandler(){}
    public RequestHandler(ReceivingAndSendingData recAndSendData, Server server)
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
        }
    }

}
