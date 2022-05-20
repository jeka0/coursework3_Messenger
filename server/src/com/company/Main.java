package com.company;
import DataBase.DB;
import server.IServer;
import server.Server;

public class Main {
    public static void main(String[] args) {
        DB db = DB.getInstance();
        IServer server = new Server(8090,100);
        server.Start();
    }
}
