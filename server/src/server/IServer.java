package server;

import business.Chat;
import business.User;

import java.util.ArrayList;

public interface IServer {
    void Start();
    void UpdateChat(Chat chat);
    void removeThread(IMonoThreadClient threadClient);
    void UpdateChatList(ArrayList<String> users);
    int UserCount(User user);
}
