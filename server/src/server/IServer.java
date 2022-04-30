package server;

public interface IServer {
    void Start();
    void UpdateFlags();
    void removeThread(IMonoThreadClient threadClient);
}
