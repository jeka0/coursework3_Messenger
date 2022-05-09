package ViewModels.IViewModels;

import business.Message;
import business.User;

public interface IMainViewModel extends IMessengerViewModel{
    void setMessages(Message[] messages);
    //void setUser(User user);
    Message[] getMessages();
    void setPosition(int position);
    String getChatName();
    //User getUser();
}
