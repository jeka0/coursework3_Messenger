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
    void DeleteUser(User user);
    void DeleteChatToUser(Chat chat, User user);
    void DeleteChat(String chatName);
    Chat[] getChats(String[] chatsStrs);
    void AddChatToUser(Chat chat);
    boolean UpdateUser(User newUser);
    String[] getChatsNames(String user);
    Message[] getMessages(String path);
    Chat getChat(String chatName);
    ArrayList<Chat> getSelectedChats();
}
