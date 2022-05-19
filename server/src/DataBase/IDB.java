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
    User[] GetUsersWithoutPasswords();
    boolean addChat(Chat chat);
    Chat[] getChats(String[] chatsStrs);
    void AddChatToUser(Chat chat);
    String[] getChatsNames(String user);
    Message[] getMessages(String path);
    ArrayList<Chat> getSelectedChats();
}
