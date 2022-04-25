package ViewModels;

import business.Message;
import business.User;

public class MainViewModel extends MyViewModel {
    private User user;
    private Message[] messages = new Message[0];
    public MainViewModel()
    {
        super();
        new Thread(()->super.getClientAccess().UpdatePosts()).start();
    }
    public void setMessages(Message[] messages) { this.messages = messages; }
    public void setUser(User user) {
        this.user = user;
    }
    public Message[] getMessages() {
        return messages;
    }
    public User getUser() {
        return user;
    }
}
