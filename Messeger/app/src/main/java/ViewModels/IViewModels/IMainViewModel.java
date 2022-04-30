package ViewModels.IViewModels;

import business.Message;
import business.User;

public interface IMainViewModel extends IMyViewModel{
    void setMessages(Message[] messages);
    //void setUser(User user);
    Message[] getMessages();
    //User getUser();
}
