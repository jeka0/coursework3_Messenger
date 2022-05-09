package server;

public interface IServer {
    void Start();
    void UpdateFlags(String chat);
    void removeThread(IMonoThreadClient threadClient);
}
