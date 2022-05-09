package ViewModels;

import ViewModels.IViewModels.IMainViewModel;
import business.Chat;
import business.Message;

public class MainViewModel extends MessengerViewModel implements IMainViewModel {
    private Chat chat;
    private Message[] messages = new Message[0];
    public MainViewModel()
    {
        super();
    }
    public void setPosition(int position)
    {
        chat = getChats()[position];
        /*if(chat.getMessages()!=null)messages = chat.getMessages().toArray(new Message[0]);
        else*/ new Thread(()->super.getClientAccess().UpdatePosts(chat.getName())).start();
    }
    public String getChatName(){return chat.getName();}

    public void setMessages(Message[] messages) { this.messages = messages; }
    public Message[] getMessages() {
        return messages;
    }
}
