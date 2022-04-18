package com.company;
import DataBase.DB;
import business.User;
import server.MonoThreadClient;
import server.Server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args) {
        DB db = DB.getInstance();
        db.CreateUser(new User("Женя","12345"));
        Server server = new Server(8090,2);
        server.Start();
    }
}
