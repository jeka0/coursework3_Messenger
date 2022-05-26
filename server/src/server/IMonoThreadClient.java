package server;

import business.User;

import java.io.IOException;

public interface IMonoThreadClient extends Runnable {
    void run();
    void setUpdateMessagesFlag(boolean flag);
    void setNameChat(String nameChat);
    void closeClient();
    void DeleteChat(String nameChat);
    void Notify(String str);
    void setUser(User user);
    User getUser();
}
