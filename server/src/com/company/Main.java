package com.company;
import server.MonoThreadClient;
import server.Server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args) {
        Server server = new Server(8090,2);
        server.Start();
    }
}
