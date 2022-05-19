package server;

import business.User;

public interface IMonoThreadClient extends Runnable {
    void run();
    void setUpdateMessagesFlag(boolean flag);
    void setNameChat(String nameChat);
    void closeClient();
    void Notify();
    void setUser(User user);
    User getUser();
}
