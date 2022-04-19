package com.company;
import DataBase.DB;
import server.Server;

public class Main {
    public static void main(String[] args) {
        DB db = DB.getInstance();
        Server server = new Server(8090,2);
        server.Start();
    }
}
