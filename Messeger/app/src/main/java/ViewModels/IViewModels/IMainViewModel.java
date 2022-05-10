package ViewModels.IViewModels;

import business.Message;
import business.User;

public interface IMainViewModel extends IMessengerViewModel{
    Message[] getMessages();
    void setPosition(int position);
    String getChatName();
}
