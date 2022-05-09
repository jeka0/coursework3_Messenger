package DataBase;

import business.Chat;
import business.Message;
import business.User;

public interface IDB {
    boolean CheckUserPassword(User user);
    boolean UserRegistration(User user);
    void CreateUser(User newUser);
    void addMessage(Message message);
    void addChat(Chat chat);
    Chat[] getChats(String user);
    String[] getChatsNames(String user);
    Message[] getMessages(String path);
}
