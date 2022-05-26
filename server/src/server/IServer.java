package server;

import business.Chat;
import business.User;

import java.util.ArrayList;

public interface IServer {
    void Start();
    void UpdateChat(Chat chat);
    //void removeThread(IMonoThreadClient threadClient);
    void UpdateChatList(ArrayList<String> users);
    //int UserCount(User user);
    void AddChat(Chat chat);
    boolean isConnect(User user);
    void AddUser(User user,  String[] chats, IMonoThreadClient client);
    void AddToChat(User user, String chat);
    void RemoveFromChat(User user, String chat);
    void RemoveUser(User user,  String[] chats);
}
