package lk.ijse.chatapp.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    @FXML
    private JFXTextArea server_txtArea;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String msg="";


    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(3003);
                server_txtArea.appendText("Start Server");
                serverSocket.accept();
                server_txtArea.appendText("Accept Client");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream= new DataOutputStream(socket.getOutputStream());
                while (!msg.equals("finish")){
                    msg=dataInputStream.readUTF();
                    server_txtArea.appendText("Server : "+msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
