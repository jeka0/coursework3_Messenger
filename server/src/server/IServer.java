package server;

import business.Chat;

import java.util.ArrayList;

public interface IServer {
    void Start();
    void UpdateChat(Chat chat);
    void removeThread(IMonoThreadClient threadClient);
    void UpdateChatList(ArrayList<String> users);
}
