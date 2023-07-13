package lk.ijse.chatapp;
import lk.ijse.chatapp.ClientHandler.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerLaucher {
    private static ArrayList<Handler>clients = new ArrayList<Handler>();
    public static ServerSocket serverSocket;
    public static Socket socket;

    public static void main(String[] args) {
        try{
            serverSocket = new ServerSocket(3004);
            while(true){
                System.out.println("waiting");
                socket = serverSocket.accept();
                System.out.println("client connected");
                Handler handler = new Handler(socket,clients);
                clients.add(handler);
                handler.start();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
