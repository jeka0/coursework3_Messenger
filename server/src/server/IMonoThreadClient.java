package server;

public interface IMonoThreadClient extends Runnable {
    void run();
    void setUpdateMessagesFlag(boolean flag);
    void setNameChat(String nameChat);
    void closeClient();
    void Notify();
}
