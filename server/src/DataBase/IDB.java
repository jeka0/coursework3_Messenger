package DataBase;

import business.Chat;
import business.Message;
import business.User;

public interface IDB {
    boolean CheckUserPassword(User user);
    boolean UserRegistration(User user);
    void CreateUser(User newUser);
    void addMessage(String path, Message message);
    void addChat(Chat chat);
    Chat[] getChats(User user);
    String[] getChatsNames(User user);
    Message[] getMessages(String path);
}
