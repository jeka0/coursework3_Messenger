package ViewModels;

import ViewModels.IViewModels.IMainViewModel;
import business.Message;

public class MainViewModel extends MyViewModel implements IMainViewModel {
    private Message[] messages = new Message[0];
    public MainViewModel()
    {
        super();
        new Thread(()->super.getClientAccess().UpdatePosts()).start();
    }
    public void setMessages(Message[] messages) { this.messages = messages; }
    public Message[] getMessages() {
        return messages;
    }
}
