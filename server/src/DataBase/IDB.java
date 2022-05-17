package DataBase;

import business.Chat;
import business.Message;
import business.User;

import java.util.ArrayList;

public interface IDB {
    boolean CheckUserPassword(User user);
    boolean UserRegistration(User user);
    void CreateUser(User newUser);
    void addMessage(Message message);
    void addChat(Chat chat);
    Chat[] getChats(String[] chatsStrs);
    String[] getChatsNames(String user);
    Message[] getMessages(String path);
    ArrayList<Chat> getSelectedChats();
}
